package projeto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Principal {

	public static void main(String[] args) throws SQLException {
		
		
		Scanner sc = new Scanner(System.in);
		
		String usuario;
		String senha;
		String cmd;
		String user = null;
		String password = null;
		String chaveCurso = "idcurso";
		//--------------------------
		String tabela = "";
		String coluna = "";
		String valor = "";
		String nome;
		String id = null;
		int idc;
		
		
		int op = 0;
		Connection conn = null;
		
		ResultSet rs = null;
		
		Statement st = null;
		
		Funcoes m = new Funcoes();
		
		Conexao cx = new Conexao();
		
		//-----------------------------------------------------------
		
		conn = cx.getConnection();
		
		if(conn == null) {
			
			JOptionPane.showMessageDialog(null,"A conex�o n�o ocorreu");
			
		}
		else {
			
			JOptionPane.showMessageDialog(null,"O banco de dados est� conectado");
		
		}
		
		//-----------------------------------------------------------
		
		do {
		
		
		usuario = JOptionPane.showInputDialog(null,"Usu�rio");
		
		cmd = "select usuario from login where usuario = '" + usuario + "'";
		
		st = conn.createStatement();
		
		rs = st.executeQuery(cmd);
		
		while(rs.next()) {
			
			user = rs.getString("usuario");
			
		}
		
		senha = JOptionPane.showInputDialog(null,"Senha");
		
		cmd = "select senha from login where senha = '" + senha + "' and usuario = '" + usuario + "'";
		
		rs = st.executeQuery(cmd);
		
		while(rs.next()) {
			password = rs.getString("senha");
		}
		
		
		if(!usuario.equals(user) || !senha.equals(password)) {
			JOptionPane.showMessageDialog(null,"Usuario ou senha inv�lidos");
		}
		
		}while(!usuario.equals(user) || !senha.equals(password));
			
		
		JOptionPane.showMessageDialog(null,"Bem vindo " + usuario);
		
		do {
			
		String menu = "1-Incluir dados \n";
		menu += "2-Alterar dados pelo c�digo \n";
		menu += "3-Alterar dados pelo nome \n";
		menu += "4-Excluir dados pelo c�digo \n";
		menu += "5-Excluir dados pelo nome \n";
		menu += "6-Listar os dados cadastrados \n";
		menu += "7-Listar os dados cadastrados em uma ordem espec�fica \n";
		menu += "8-Listar os dados cadastrados com crit�rio \n";
		menu += "9-Listar dados com Inner Join \n";
		menu += "10-Mostrar soma total dos dados \n";
		menu += "11-Apresentar a m�dia dos dados cadastrados \n";
		menu += "12-Listar com agrupamento \n";
		menu += "13-Encerrar programa \n";
		
		String opcao = JOptionPane.showInputDialog(null,menu,"Menu",1);
		
		op = Integer.parseInt(opcao);
		
		switch(op){
			
		case 1://Incluir dados
			
			String desc;
			int ano;
			String totaulas;
			int carga;
			int idcurso = 32;
			
			
			
			JOptionPane.showMessageDialog(null,"Informe os dados do registro que ser� inseridos:");
			
			nome = JOptionPane.showInputDialog(null,"Nome do curso","Inclus�o",3);
			
			desc = JOptionPane.showInputDialog(null,"Descri��o do curso");
			
			carga = Integer.parseInt(JOptionPane.showInputDialog(null,"Carga do curso","Inclus�o",3));
			
			totaulas = JOptionPane.showInputDialog(null,"Total de aulas","Inclus�o",3);
			
			ano = Integer.parseInt(JOptionPane.showInputDialog(null,"Ano de in�cio do curso","Inclus�o",3));
			
			
			
			cmd = "INSERT INTO cursos (idcurso,nome,descricao,carga,totaulas,ano) VALUES (' " +idcurso+ "',' " +nome+ "','" +desc+ "','" +carga+ "','" +totaulas+ "','" +ano+ "')";
			
			try {
			if(m.incluirDados(cmd,conn)) {
				JOptionPane.showMessageDialog(null,"Inlcus�o realizada com sucesso");
				idcurso++;
			}
			else {
				JOptionPane.showMessageDialog(null,"Inlcus�o N�O realizada");
			}
			}
			catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "J� existem registros com o id informado");
			}
			
			
			
			
			break;
			
			//-----------------------------------------------------------
			
		case 2://Alterar dados pelo c�digo
			
			
			JOptionPane.showMessageDialog(null, "Informe:");
			
			id = JOptionPane.showInputDialog(null,"O C�digo correspondente a coluna que deseja alterar","Altera��o",3);
			
			coluna = JOptionPane.showInputDialog(null,"O nome da coluna que deseja alterar \n[NOME-DESCRICAO-CARGA-ANO-TOTAL DE AULAS","Altera��o]",3);
			
			valor = JOptionPane.showInputDialog(null,"O novo registro que ser� atualizado na tabela","Altera��o",3);
			
			cmd = "select " + coluna + " as 'coluna' from cursos where " + chaveCurso + " = '" + id + "'";
			
			m.select(cmd, conn);
			JOptionPane.showMessageDialog(null, m.getDados());
			
			cmd = "UPDATE cursos SET " + coluna + " = '" + valor + " ' WHERE  idcurso = " + id;
			
			try {
			
				if(m.alterarPeloCod(cmd, conn)) {
					JOptionPane.showMessageDialog(null,"Altera��o realizada com sucesso");
				}
				else {
					JOptionPane.showMessageDialog(null,"Registro n�o existe na tabela");
					JOptionPane.showMessageDialog(null,"Altera��o N�O realizada");
				}
			}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "O registro j� existe na tabela");
			}
			
			
			break;
			
			//-----------------------------------------------------------
			
			
			
			
		case 3://Alterar dados pelo nome
			
			JOptionPane.showMessageDialog(null, "Informe:");
			
			coluna = JOptionPane.showInputDialog(null,"O nome da coluna que deseja alterar \n[NOME-DESCRICAO-CARGA-ANO-TOTAL DE AULAS\",\"Altera��o]","Altera��o",3);
			
			nome = JOptionPane.showInputDialog(null,"O nome correspondente a coluna que deseja alterar","Altera��o",3);
			
			valor = JOptionPane.showInputDialog(null,"O novo registro que ser� atualizada na tabela","Altera��o",3);
			
			cmd = "select " + coluna + " as 'coluna', idcurso as 'cod' from cursos where nome = '" + nome + "'";
			
			m.select(cmd, conn);
			JOptionPane.showMessageDialog(null, m.getDados());
			
			cmd = "UPDATE cursos SET " + coluna + " = '" + valor + " ' WHERE  nome = '" + nome + " ' ";
			
			if(m.alterarPeloNome(cmd, conn)) {
				JOptionPane.showMessageDialog(null,"Altera��o realizada com sucesso");
			}
			else {
				JOptionPane.showMessageDialog(null,"Registro n�o existe na tabela");
				JOptionPane.showMessageDialog(null,"Altera��o N�O realizada");
			}
			
			
			break;
			
			//-----------------------------------------------------------
			
		case 4://Excluir dados pelo c�digo
			
			JOptionPane.showMessageDialog(null, "Informe:");
			
			valor = JOptionPane.showInputDialog(null,"C�digo correspondente ao curso que deseja excluir","Exclus�o",3);
				
			cmd = "select * from cursos where " + chaveCurso + " = '" + valor + "'";
			
			m.listarCadastrados(cmd, conn); 
			JOptionPane.showMessageDialog(null, m.getDados());
			
			cmd = "DELETE FROM cursos WHERE idcurso = ' " + valor + " ' ";
			
			if(m.excluirPeloCod(cmd, conn)) {
				JOptionPane.showMessageDialog(null,"Exclus�o realizada com sucesso");
			}
			else {
				JOptionPane.showMessageDialog(null,"Registro n�o existe na tabela");
				JOptionPane.showMessageDialog(null,"Exclus�o N�O realizada");
			}
			
			
			break;
			
			//-----------------------------------------------------------
			
			
		case 5://Excluir dados pelo nome
			
			JOptionPane.showMessageDialog(null, "Informe:");
			
			valor = JOptionPane.showInputDialog(null,"O nome correspondente aos registros que deseja excluir","Exclus�o",3);
			
			cmd = "select * from cursos where nome = '" + valor + "'";
			
			m.listarCadastrados(cmd, conn); 
			JOptionPane.showMessageDialog(null, m.getDados());
			
			cmd = "DELETE FROM cursos WHERE nome = '" + valor + "' ";
			
			if(m.excluirPeloNome(cmd, conn)) {
				JOptionPane.showMessageDialog(null,"Exclus�o realizada com sucesso");
			}
			else {
				JOptionPane.showMessageDialog(null,"Registro n�o existe na tabela");
				JOptionPane.showMessageDialog(null,"Exclus�o N�O realizada");
			}
			
			break;
			
			//-----------------------------------------------------------
			
		case 6://Listar os dados cadastrados
			
			tabela = JOptionPane.showInputDialog(null,"Informe o nome da tabela que deseja listar os dados","Listagem",3);
			
			cmd = "select * from " + tabela;
			
			m.listarCadastrados(cmd, conn);
			
			System.out.println(m.getDados());
			
			JOptionPane.showMessageDialog(null, m.getDados());
			
			break;
			
			//-----------------------------------------------------------
			
			
		case 7://Listar os dados cadastrados em uma ordem espec�fica
			
			tabela = JOptionPane.showInputDialog(null,"Informe de qual tabela deseja listar os dados","Listagem",3);
			
			coluna = JOptionPane.showInputDialog(null,"Ordem:(Ex:Ordenado por nome, ordenado por ano...)","Listagem",3);
			
			String ordem = JOptionPane.showInputDialog(null,"Ordena��o de forma asc(ascendente) ou desc(descendente)","Listagem",3);
			
			cmd = "SELECT * FROM  " + tabela + " order BY " + coluna + " " + ordem;
			
			m.listarEmOrdem(cmd, conn);
			
			System.out.println(m.getDados());
			
			JOptionPane.showMessageDialog(null, m.getDados());
			
			break;
			
			//-----------------------------------------------------------
			
		case 8://Listar os dados cadastrados com crit�rio
			
			JOptionPane.showMessageDialog(null, "Aqui os dados ser�o listados com crit�rio. Exemplo:Registros com ano maior que 2016:");
			JOptionPane.showMessageDialog(null, "Informe:");
			
			coluna = JOptionPane.showInputDialog(null,"A coluna que ser� comparada","Listagem",3);
			
			String operador = JOptionPane.showInputDialog(null,"Informe o operador l�gico:(Ex:> ou < ou =)","Listagem",3);
			
			valor = JOptionPane.showInputDialog(null,"Informe o valor de compara��o","Listagem",3);
			
			cmd = "SELECT * FROM cursos WHERE " + coluna + " " + operador + " ' " + valor + " ' ";
			
			m.listarCriterio(cmd, conn);
			
			System.out.println(m.getDados());
			
			JOptionPane.showMessageDialog(null, m.getDados());
			
			break;
			
			//-----------------------------------------------------------
			
			
		case 9://Listar dados com Inner Join
			
			JOptionPane.showMessageDialog(null, "Aqui ser�o listados os dados de duas colunas diferentes que possuem liga��o");
			JOptionPane.showMessageDialog(null, "Ex: O curso em que um aluno est� cadastrado");
			
			JOptionPane.showMessageDialog(null, "Informe:");
			
			String coluna1 = JOptionPane.showInputDialog(null,"Coluna da tabela de alunos","Listagem",3);
			
			String coluna2 = JOptionPane.showInputDialog(null,"Coluna da tabela de cursos","Listagem",3);
			
			cmd = "SELECT alunos." + coluna1 + " as coluna1,cursos." + coluna2 + " as 'coluna2' FROM cursos INNER JOIN alunos ON cursos.idcurso = alunos.cursopreferido";
			
			m.listarInnerJoin(cmd, conn);
			
			System.out.println(m.getDados());
			
			JOptionPane.showMessageDialog(null, m.getDados());
			
			break;
			
			//-----------------------------------------------------------
			
		case 10://Mostrar soma total dos dados
			
			tabela = JOptionPane.showInputDialog(null,"Informe o nome da tabela","Listagem",3);
			
			coluna = JOptionPane.showInputDialog(null,"Informe o nome da coluna que deseja obter a soma dos registros","Listagem",3);
			
			cmd = "SELECT SUM(" + coluna + ") AS total FROM " + tabela;
			
			m.somarDados(cmd, conn);
			
			JOptionPane.showMessageDialog(null, m.getSoma());
			
			break;
			
			//-----------------------------------------------------------
			
			
			
		case 11://Apresentar a m�dia dos dados cadastrados
			
			tabela = JOptionPane.showInputDialog(null,"Informe o nome da tabela","Listagem",3);
			
			coluna = JOptionPane.showInputDialog(null,"Informe o nome da coluna que deseja obter a m�dia dos registros","Listagem",3);
			
			cmd = "SELECT AVG(" + coluna + ") AS total FROM " + tabela;
			
			m.mediaDados(cmd, conn);
			
			JOptionPane.showMessageDialog(null, m.getMedia());
			
			break;
			
			//-----------------------------------------------------------
			
		case 12://Listar com agrupamento
			
			JOptionPane.showMessageDialog(null, "Aqui ser�o agrupados os dados de determinado grupo,o n�mero de elementos que o grupo possui");
			JOptionPane.showMessageDialog(null, "Ex: Grupo[2018]: Total[3]");
			
			tabela = JOptionPane.showInputDialog(null,"Informe o nome da tabela","Listagem",3);
			
			coluna = JOptionPane.showInputDialog(null,"Informe o nome da coluna que deseja agrupar","Listagem",3);
			
			cmd = "SELECT " + coluna + " as coluna,COUNT(*) as total FROM " + tabela + " GROUP BY " + coluna;
			
			m.listarAgrupamento(cmd, conn);
			
			System.out.println(m.getDados());
			
			JOptionPane.showMessageDialog(null, m.getDados());
			
			break;
			
			//-----------------------------------------------------------
			
			
		default:
			
			JOptionPane.showMessageDialog(null, "N�mero inv�lido");
			
			
			
			
			
			}
		
		}while(op != 13);
		
	}

}

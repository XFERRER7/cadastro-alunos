package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bd.Conn;

public class Funcoes {

	
	String usuario;
	String senha;
	String user = null;
	String password = null;
	ResultSet rs = null;
	Connection conn = null;
	Statement st = null;
	private String dados = "";
	String soma;
	String media;
	
	
	
	
	//-----------------------------------------------------------
	
	
	
	
	public boolean incluirDados(String sql,Connection c) throws SQLException {
		
		dados = " ";
		
		st = c.createStatement();
		
		int intRs = st.executeUpdate(sql);
		
		if(intRs > 0) {
			
			return true;
			
		}
		
		else {
			
			return false;
			
		}
		
	}
	
	//-----------------------------------------------------------
	
	
	
	public boolean alterarPeloCod(String sql,Connection c) throws SQLException {
		
		dados = " ";
		
		st = c.createStatement();
		
		int Rs = st.executeUpdate(sql);
		
		if(Rs > 0) {
			
			return true;
			
		}
		
		else {
			
			return false;
			
		}
		
		
	}
	
	//-----------------------------------------------------------
	
	public boolean alterarPeloNome(String sql,Connection c) throws SQLException {
		
		dados = " ";
		
		st = c.createStatement();
		
		int Rs = st.executeUpdate(sql);
		
		if(Rs > 0) {
			
			return true;
			
		}
		
		else {
			
			return false;
			
		}
		
	}
	
	
	//-----------------------------------------------------------
	
	
	public boolean excluirPeloCod(String sql,Connection c) throws SQLException {
		
		dados = " ";
		
		st = c.createStatement();
		
		int Rs = st.executeUpdate(sql);
		
		if(Rs > 0) {
			
			return true;
			
		}
		
		else {
			
			return false;
			
		}
		
		
		
		
		
		
	}
	

	//-----------------------------------------------------------
	
	public boolean excluirPeloNome(String sql,Connection c) throws SQLException {
		
		dados = " ";
		
		st = c.createStatement();
		
		int intRs = st.executeUpdate(sql);
		
		if(intRs > 0) {
			
			return true;
			
		}
		
		else {
			
			return false;
			
		}
		
	}
	
	
	//-----------------------------------------------------------
	
	public void listarCadastrados(String sql,Connection c) throws SQLException {
		
		dados = " ";
		
		st = c.createStatement();
		
		rs = st.executeQuery(sql);
		
		while(rs.next()) {
			
			dados += "NOME = [" + rs.getString("nome") + "]" + "\n" + "DESCRIÇÃO = [" + rs.getString("descricao") + "]";
			dados += "CARGA = [" + rs.getString("carga") + "]" + "\n" + "TOTAL DE AULAS = [" + rs.getString("totaulas") + "]" + "\n" + "ANO = [" + rs.getString("ano") + "]" + "\n";
			dados += "--------------------------------------" + "\n";
			
		}
		
	}
	
	
	public String getDados() {
		return dados;
	}




	public void setDados(String dados) {
		this.dados = dados;
	}


	//-----------------------------------------------------------

	public void listarEmOrdem(String sql,Connection c) throws SQLException {
		
		dados = " ";
		
		st = c.createStatement();
		
		rs = st.executeQuery(sql);
		
		while(rs.next()) {
			
			dados += "NOME = [" + rs.getString("nome") + "]" + "\n" + "DESCRIÇÃO = [" + rs.getString("descricao") + "]";
			dados += "CARGA = [" + rs.getString("carga") + "]" + "\n" + "TOTAL DE AULAS = [" + rs.getString("totaulas") + "]" + "\n" + "ANO = [" + rs.getString("ano") + "]" + "\n";
			dados += "--------------------------------------" + "\n";
			
		}
	
	}
	
	//-----------------------------------------------------------
	
	
	public void listarCriterio(String sql,Connection c) throws SQLException {
		
		dados = " ";
		
		try {
			
			st = c.createStatement();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		rs = st.executeQuery(sql);
		
		while(rs.next()) {
			
			dados += "NOME = [" + rs.getString("nome") + "]" + "\n" + "DESCRIÇÃO = [" + rs.getString("descricao") + "]";
			dados += "CARGA = [" + rs.getString("carga") + "]" + "\n" + "TOTAL DE AULAS = [" + rs.getString("totaulas") + "]" + "\n" + "ANO = [" + rs.getString("ano") + "]" + "\n";
			dados += "--------------------------------------" + "\n";
			
		}
		
	}
	
	//-----------------------------------------------------------
	
	
	public void listarInnerJoin(String sql,Connection c) throws SQLException {
	
		dados = " ";
		
		try {
			
			st = c.createStatement();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		rs = st.executeQuery(sql);
		
		while(rs.next()) {
			
			dados += "Nome = " + rs.getString("coluna1") + " | ";
			dados += "Curso = " + rs.getString("coluna2") + " \n ";
		}
	}
	
	//-----------------------------------------------------------
	
	
	public void somarDados(String sql,Connection c) throws SQLException {
		
		dados = " ";
		
		st = c.createStatement();
		
		rs = st.executeQuery(sql);
		
		while(rs.next()) {
			
			soma = "Soma = " + rs.getInt("total");
			
		}
		
		
		
	}
	
	public String getSoma() {
		
		return soma;
	}
	
	//-----------------------------------------------------------
	
	public void mediaDados(String sql,Connection c) throws SQLException {

		dados = " ";
		
		st = c.createStatement();
		
		rs = st.executeQuery(sql);
		
		while(rs.next()) {
			
			media = "Média = " + rs.getDouble("total");
			
		}
		
		
		
	}
	
	public String getMedia() {
		
			return media;
	}
	
	//-----------------------------------------------------------
	
	
	
	public void listarAgrupamento(String sql,Connection c) throws SQLException {
		
		dados = " ";
		
		st = c.createStatement();
		
		rs = st.executeQuery(sql);
		
		while(rs.next()) {
			
			dados += "Grupo = [" + rs.getString("coluna") + "]" + "\n";
			dados += "Total = [" + rs.getDouble("total") + "]" + "\n";
			
		}
		
		
	}
	
	public void select(String sql,Connection c) throws SQLException {
		
		dados = " ";
		
		st = c.createStatement();
		
		rs = st.executeQuery(sql);
		
		dados = "";
		
		while(rs.next()) {
			
			 
			dados += "Coluna = [" + rs.getString("coluna") + "]" + "\n";
			
		}
	
}
	
}

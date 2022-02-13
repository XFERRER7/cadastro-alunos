package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
	public class Conexao {

		
		
		private String login = "#";
		private String senha = "#";
		private String host = "#";
		private String dbName = "#";
		private String url = "jdbc:mysql://"+host+"/"+dbName;
		
		public Connection conexao = null;
		
		public Conexao() {}
		
		public Connection getConnection() {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException e) {
				
				return null;
				
			}
			
			try {
				this.conexao = (Connection) DriverManager.getConnection(url,login,senha);
			}
			catch(SQLException ex) {
				
				return null;
				
			}
			
			return this.conexao;
		
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}



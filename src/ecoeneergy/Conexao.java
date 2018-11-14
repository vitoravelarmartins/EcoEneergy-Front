package ecoeneergy;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {
	
	private static Conexao instancia;

    static PreparedStatement prepareStatement(String SQL) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	private String usuario = "root";
	private String senha = "root"; //coloquem root
	private String url = "jdbc:mysql://localhost:3306/EcoEneergy";
	
	private Connection conn;
	
	private Conexao() throws SQLException {
		conn = DriverManager.getConnection(url, usuario, senha);
	}
	
	public static Conexao getIntancia() throws SQLException { 
		if (instancia == null) {
			instancia = new Conexao();
		}
		
		return instancia;
	}
	
	public Connection getConexao() {
		return conn;
	}
}

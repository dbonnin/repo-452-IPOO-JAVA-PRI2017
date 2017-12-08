package com.diegobonnin.cajeroAutomatico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private String url;
	private String driver;
	private String usuario;
	private String password;
	
	private Connection con;
	
	public Conexion(String url, String driver, String usuario, String password){
		this.url=url;
		this.driver=driver;
		this.usuario=usuario;
		this.password=password;
	}
	
	public void conectar()throws DBException{
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, usuario, password);
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new DBException(sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			throw new DBException(cnfe.getMessage());
		}
		
		
	}
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public void desconectar()throws DBException{
		
		if(con!=null){
				
			try {
				if(!con.isClosed()){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException(e.getMessage());
			}
			
		}
		
	}

}
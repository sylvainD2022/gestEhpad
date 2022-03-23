package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoAccess {

	private String strClassName = "com.mysql.cj.jdbc.Driver";
	private String url;
	private String login;
	private String mdp;

	@SuppressWarnings("unused")
	private String driver;

	private PreparedStatement prs;
	private Connection conn;

	// Set the constructor of DAOAccess
	public DaoAccess(String url, String nomBDD, String login, String mdp, String driver) {
		this.login = login;
		this.mdp = mdp;
		this.driver = driver;
		this.url = "jdbc:mysql://" + url + "/" + nomBDD + "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";

		try {
			Class.forName(strClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Driver non chargé !");
			e.printStackTrace();
		}
	}

	public void connect() {
		try { // try for connexion to database
			this.conn = DriverManager.getConnection(this.url, this.login, this.mdp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setPreparedStatement(String quary) {
		try {
			this.prs = conn.prepareStatement(quary);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public PreparedStatement getPreparedStatement() {
		return prs;
	}

}

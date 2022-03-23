package controlleur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import modele.DaoAccess;
import modele.Etablissement;

public class Utils {

	public static ArrayList<String> getList(String str, HttpSession s) {

		DaoAccess bdd = ((DaoAccess) s.getAttribute("BDD"));
		String strQueryGetChefEquipe = "select DISTINCT nom"+str+" from " + str;
		ResultSet rsUsers;
		ArrayList<String> ls = new ArrayList<String>();

		try {
			bdd.connect();

			bdd.setPreparedStatement(strQueryGetChefEquipe);
			rsUsers = bdd.getPreparedStatement().executeQuery();

			while (rsUsers.next())
				ls.add(rsUsers.getString("nom" + str));

			bdd.disconnect();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	public static String[] getArray(String str, HttpSession s) {
		String strQueryGetVal = "select * from " + str;
		String strQueryGetnb = "select Count(*) nb from " + str;
		ResultSet rsUsers;
		String[] ls = null;
		DaoAccess bdd = ((DaoAccess) s.getAttribute("BDD"));
		try {
			bdd.connect();

			bdd.setPreparedStatement(strQueryGetnb);
			rsUsers = bdd.getPreparedStatement().executeQuery();

			rsUsers.next();
			int nb = rsUsers.getInt("nb");

			ls = new String[nb];

			bdd.setPreparedStatement(strQueryGetVal);
			rsUsers = bdd.getPreparedStatement().executeQuery();

			int i = 0;
			while (rsUsers.next())
				ls[i++] = rsUsers.getString("nom" + str);

			bdd.disconnect();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	public static int returnIdinBdd(String tableName, String columnName, String what, HttpSession s)
			throws SQLException {
		DaoAccess bdd = ((DaoAccess) s.getAttribute("BDD"));
		int id = 0;
		ResultSet rs = null;
		String primaryColumn = null;

		bdd.connect();
		String sql = "SHOW KEYS FROM " + tableName + " WHERE Key_name = 'PRIMARY'";
		bdd.setPreparedStatement(sql);
		rs = bdd.getPreparedStatement().executeQuery();
		while (rs.next()) {
			primaryColumn = rs.getString("Column_name");
			break;
		}

		sql = "select " + primaryColumn + " from " + tableName + " where " + columnName + " = '" + what + "';";

		bdd.setPreparedStatement(sql);
		rs = bdd.getPreparedStatement().executeQuery();
		while (rs.next()) {
			id = rs.getInt(primaryColumn);
		}

		bdd.disconnect();
		return id;
	}

	public static String getTitreReunion(int id, HttpSession s) throws SQLException {
		String str = null;
		ResultSet rs;
		DaoAccess bdd = ((DaoAccess) s.getAttribute("BDD"));
		bdd.connect();

		String sql = "select titreReunion from reunion where `idReunion` = " + id + ";";
		bdd.setPreparedStatement(sql);
		rs = bdd.getPreparedStatement().executeQuery();
		while (rs.next()) {
			str = rs.getString("titreReunion");
			System.out.println(rs.getString("titreReunion"));
		}
		bdd.disconnect();
		return str;
	}

	public static Date getDateAgenda(int id, boolean isReunion, HttpSession s) throws SQLException, ParseException {

		String table = "visite";
		String column = "dateVisite";
		String primaryColumn = "idVisite";
		Date date = null;
		DaoAccess bdd = ((DaoAccess) s.getAttribute("BDD"));
		if (isReunion) {
			table = "reunion";
			column = "dateReunion";
			primaryColumn = "idReunion";
		}

		ResultSet rs;
		bdd.connect();

		String sql = "select " + column + " from " + table + " where " + primaryColumn + " = " + id + ";";
		bdd.setPreparedStatement(sql);
		rs = bdd.getPreparedStatement().executeQuery();

		while (rs.next()) {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(column));
		}

		bdd.disconnect();

		return date;
	}

	public static void reloadData(HttpSession s) {
		
		
		int id = ((Etablissement) s.getAttribute("etablissement")).getId();
		s.setAttribute("etablissement", new Etablissement(id, s));
		
	}
}

package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class Registre {

	private ArrayList<Evenement> listEvenements = new ArrayList<>();
	private String service;
	private int id;

	private static String strQueryGetEvenements = "SELECT * FROM Evenement where idRegistre = ?;";

	public Registre(int id, String nomService, ArrayList<Personnel> lPersonnel, ArrayList<Resident> lResident,
			HttpSession session) {
		this.service = nomService;
		this.id = id;
		DaoAccess bdd = (DaoAccess) session.getAttribute("BDD");

		try {
			ResultSet rsUsers;
			bdd.setPreparedStatement(strQueryGetEvenements);
			bdd.getPreparedStatement().setInt(1, id);
			rsUsers = bdd.getPreparedStatement().executeQuery();
			while (rsUsers.next())
				listEvenements.add(new Evenement(rsUsers.getInt("idEvenement"), lPersonnel, lResident, session));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Evenement> getListEvenements() {
		return listEvenements;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getId() {
		return id;
	}

}

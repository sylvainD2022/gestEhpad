package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class Equipe {
	private int numEquipe;
	private String horaire;
	private ArrayList<Employe> listeEmploye = new ArrayList<>();

	private static String strQueryGetEquipe = "SELECT * FROM Equipe WHERE idEquipe = ? ;";
	private static String strQueryGetEquipeMembers = "SELECT * FROM Employe WHERE idEquipe = ? ;";

	public Equipe(int id, ArrayList<Employe> lEmployes, HttpSession session) {
		this.numEquipe = id;
		DaoAccess bdd = (DaoAccess) session.getAttribute("BDD");

		try {
			ResultSet rsUsers;

			bdd.setPreparedStatement(strQueryGetEquipe);
			bdd.getPreparedStatement().setInt(1, id);
			rsUsers = bdd.getPreparedStatement().executeQuery();
			while (rsUsers.next())
				this.horaire = rsUsers.getString("horaireEquipe");

			bdd.setPreparedStatement(strQueryGetEquipeMembers);
			bdd.getPreparedStatement().setInt(1, id);
			rsUsers = bdd.getPreparedStatement().executeQuery();
			while (rsUsers.next()) {
				for (Employe e : lEmployes) {
					if (e.getIdE() == rsUsers.getInt("idEmploye"))
						listeEmploye.add(e);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getNumEquipe() {
		return numEquipe;
	}

	public String getHoraire() {
		return horaire;
	}

	public ArrayList<Employe> getListeEmploye() {
		return listeEmploye;
	}

	public void setNumEquipe(int numEquipe) {
		this.numEquipe = numEquipe;
	}

	public void setHoraire(String horaire) {
		this.horaire = horaire;
	}

	@Override
	public String toString() {
		return "Equipe [listeEmploye=" + listeEmploye + "]";
	}

}

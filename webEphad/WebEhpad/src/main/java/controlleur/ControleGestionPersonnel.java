package controlleur;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.ChefService;
import modele.DaoAccess;
import modele.Directeur;
import modele.Employe;
import modele.Etablissement;
import modele.Personnel;

/**
 * Servlet implementation class ControleGestionPersonnel
 */
@WebServlet("/ControleGestionPersonnel")
public class ControleGestionPersonnel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String strQueryUpdatePersonnel = "UPDATE personnel SET nomPersonnel = ? , prenomPersonnel = ? , identifiant = ? , password = ? , intitulePoste = ? , numTelPoste = ? WHERE personnel.idPersonnel = ?;";
	private static String strQueryInsertPersonnel = "INSERT INTO personnel (nomPersonnel, prenomPersonnel, identifiant, password, intitulePoste, numTelPoste, idEtablissement, idService) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

	private static String strQueryInsertDir = "INSERT INTO directeur (idPersonnel) VALUES ( ? );";
	private static String strQueryInsertChef = "INSERT INTO chef (idPersonnel) VALUES ( ? );";
	private static String strQueryInsertEmp = "INSERT INTO employe (idPersonnel) VALUES ( ? );";

	private static String strQueryDeleteDir = "DELETE FROM directeur WHERE idPersonnel = ? ;";
	private static String strQueryDeleteChef = "DELETE FROM chef WHERE idPersonnel = ? ;";
	private static String strQueryDeleteEmp = "DELETE FROM employe WHERE idPersonnel = ? ;";

	public ControleGestionPersonnel() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Personnel working_personnel = (Personnel) session.getAttribute("personnel_concerne");
		session.setAttribute("personnel_concerne",null);
		DaoAccess bdd = (DaoAccess) session.getAttribute("BDD");
		Etablissement etab = (Etablissement) session.getAttribute("etablissement");

		String nomP = (String) request.getParameter("nom");
		String prenomP = (String) request.getParameter("prenom");
		String nivHierarchique = (String) request.getParameter("niv_hierarchique");
		String identifiantP = (String) request.getParameter("id");
		String passwordP = (String) request.getParameter("mdp");
		String posteP = (String) request.getParameter("poste");
		int numTelP = Integer.parseInt((String) request.getParameter("num_poste"));
		
		String serviceP = (String) request.getParameter("service");

		session.setAttribute("personnel_concerne", null);
		

		if (!(nomP.isBlank() || prenomP.isBlank() || identifiantP.isBlank() || passwordP.isBlank() || posteP.isBlank()
				|| serviceP.isBlank())) {
			
			if (working_personnel == null) {
				int idService;
				try {
					idService = Utils.returnIdinBdd("Service", "nomService", serviceP, session);
					bdd.connect();

					bdd.setPreparedStatement(strQueryInsertPersonnel);
					bdd.getPreparedStatement().setString(1, nomP);
					bdd.getPreparedStatement().setString(2, prenomP);
					bdd.getPreparedStatement().setString(3, identifiantP);
					bdd.getPreparedStatement().setString(4, passwordP);
					bdd.getPreparedStatement().setString(5, posteP);
					bdd.getPreparedStatement().setInt(6, numTelP);
					bdd.getPreparedStatement().setInt(7, etab.getId());
					bdd.getPreparedStatement().setInt(8, idService);

					bdd.getPreparedStatement().executeUpdate();

					bdd.disconnect();

					int id = Utils.returnIdinBdd("personnel", "identifiant", identifiantP, session);
					bdd.connect();

					if (nivHierarchique.equals("employe")) {

						bdd.setPreparedStatement(strQueryInsertEmp);
						bdd.getPreparedStatement().setInt(1, id);
						bdd.getPreparedStatement().executeUpdate();

						bdd.disconnect();
						int idD = Utils.returnIdinBdd("directeur", "idPersonnel", Integer.toString(id), session);
						bdd.connect();
						etab.getListDirecteur().add(new Directeur(idD, id, session));

					} else if (nivHierarchique.equals("chef_service")) {
						bdd.setPreparedStatement(strQueryInsertChef);
						bdd.getPreparedStatement().setInt(1, id);
						bdd.getPreparedStatement().executeUpdate();

						bdd.disconnect();
						int idD = Utils.returnIdinBdd("directeur", "idPersonnel", Integer.toString(id), session);
						bdd.connect();
						etab.getListDirecteur().add(new Directeur(idD, id, session));
					}

					else if (nivHierarchique.equals("directeur")) {
						bdd.setPreparedStatement(strQueryInsertDir);
						bdd.getPreparedStatement().setInt(1, id);
						bdd.getPreparedStatement().executeUpdate();

						bdd.disconnect();
						int idD = Utils.returnIdinBdd("directeur", "idPersonnel", Integer.toString(id), session);
						bdd.connect();
						etab.getListDirecteur().add(new Directeur(idD, id, session));
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				// modification Personnel
				try {
					int id = working_personnel.getId();


					bdd.connect();

					bdd.setPreparedStatement(strQueryUpdatePersonnel);
					bdd.getPreparedStatement().setString(1, nomP);
					bdd.getPreparedStatement().setString(2, prenomP);
					bdd.getPreparedStatement().setString(3, identifiantP);
					bdd.getPreparedStatement().setString(4, passwordP);
					bdd.getPreparedStatement().setString(5, posteP);
					bdd.getPreparedStatement().setInt(6, numTelP);
					bdd.getPreparedStatement().setInt(7, id);

					bdd.getPreparedStatement().executeUpdate();

					if (changeType(working_personnel, nivHierarchique)) {
						if (working_personnel.getClass() == Directeur.class) {
							bdd.setPreparedStatement(strQueryDeleteDir);
							bdd.getPreparedStatement().setInt(1, id);
							bdd.getPreparedStatement().executeUpdate();

							etab.getListDirecteur().remove(working_personnel);
						} else if (working_personnel.getClass() == ChefService.class) {
							bdd.setPreparedStatement(strQueryDeleteChef);
							bdd.getPreparedStatement().setInt(1, id);
							bdd.getPreparedStatement().executeUpdate();

							etab.getListChefServices().remove(working_personnel);
						} else if (working_personnel.getClass() == Employe.class) {
							bdd.setPreparedStatement(strQueryDeleteEmp);
							bdd.getPreparedStatement().setInt(1, id);
							bdd.getPreparedStatement().executeUpdate();

							etab.getListEmployes().remove(working_personnel);
						}

						if (nivHierarchique.equals("directeur")) {
							bdd.setPreparedStatement(strQueryInsertDir);
							bdd.getPreparedStatement().setInt(1, id);
							bdd.getPreparedStatement().executeUpdate();

							bdd.disconnect();
							int idD = Utils.returnIdinBdd("directeur", "idPersonnel", Integer.toString(id), session);
							bdd.connect();

							etab.getListDirecteur().add(new Directeur(idD, id, session));
						} else if (nivHierarchique.equals("chef_service")) {
							bdd.setPreparedStatement(strQueryInsertChef);
							bdd.getPreparedStatement().setInt(1, id);
							bdd.getPreparedStatement().executeUpdate();

							bdd.disconnect();
							int idC = Utils.returnIdinBdd("chef", "idPersonnel", Integer.toString(id), session);
							bdd.connect();

							etab.getListChefServices().add(new ChefService(idC, id, etab.getListEmployes(), session));
						} else if (nivHierarchique.equals("Employe")) {
							bdd.setPreparedStatement(strQueryInsertEmp);
							bdd.getPreparedStatement().setInt(1, id);
							bdd.getPreparedStatement().executeUpdate();

							bdd.disconnect();
							int idE = Utils.returnIdinBdd("employe", "idPersonnel", Integer.toString(id), session);
							bdd.connect();

							etab.getListEmployes().add(new Employe(idE, id, session));
						}
					} else {

						working_personnel.actualise(bdd);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				bdd.disconnect();

			}

			session.setAttribute("page", 3);

			response.sendRedirect("App");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private boolean changeType(Personnel p, String newType) {
		if (p.getClass() == Directeur.class && newType.equals("directeur"))
			return false;

		if (p.getClass() == ChefService.class && newType.equals("chef_service"))
			return false;

		if (p.getClass() == Employe.class && newType.equals("employe"))
			return false;

		return true;
	}

}

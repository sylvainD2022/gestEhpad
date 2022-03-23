package controlleur;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.Administrateur;
import modele.ChefService;
import modele.DaoAccess;
import modele.Directeur;
import modele.Employe;
import modele.Etablissement;
import modele.Personnel;

/**
 * Servlet implementation class ControleConnexion
 */
@WebServlet("/ControleConnexion")
public class ControleConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String strQueryCheckConnect = "SELECT * FROM Personnel WHERE identifiant = ?;";

	private DaoAccess bdd;

	public ControleConnexion() {
		super();

	}

	public ControleConnexion(String id, String mdp, HttpServletRequest request, HttpServletResponse response) {
		super();

		HttpSession session = request.getSession();
		bdd = (DaoAccess) session.getAttribute("BDD");

		connect(id, mdp, request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		bdd = new DaoAccess("localhost", "gestehpad", "gestEhpad", "1234", null);
		session.setAttribute("BDD", bdd);

		String id = request.getParameter("id");
		String mdp = request.getParameter("mdp");

		connect(id, mdp, request, response);
	}

	private void connect(String id, String mdp, HttpServletRequest request, HttpServletResponse response) {
		bdd.connect();
		if (!(id.isEmpty() || mdp.isEmpty() || id.isBlank() || mdp.isBlank())
				&& (id.matches("^[a-zA-Z]*$") && mdp.matches("^[a-zA-Z0-9]*$"))) {
			HttpSession session = request.getSession();

			ResultSet rsUsers;
			// verificationBdd

			try {
				bdd.setPreparedStatement(strQueryCheckConnect);
				bdd.getPreparedStatement().setString(1, id);
				rsUsers = bdd.getPreparedStatement().executeQuery();
				if (rsUsers.next()) {
					if (rsUsers.getString("password").equals(mdp)) {
						// connexion ok
						int idEtab = rsUsers.getInt("idEtablissement");
						Etablissement e = new Etablissement(idEtab, session);

						session.setAttribute("etablissement", e);

						Personnel find;

						find = findInListA(e.getListAdmin(), id);
						if (find == null) {
							find = findInListD(e.getListDirecteur(), id);
						}
						if (find == null) {
							find = findInListC(e.getListChefServices(), id);
						}
						if (find == null) {
							find = findInListE(e.getListEmployes(), id);
						}

						session.setAttribute("connected", find);
						session.setAttribute("page", 0); // TODO remettre a 0

						bdd.disconnect();

						response.sendRedirect("App");
					} else {
						// connexion KO MDP

						bdd.disconnect();
						session.setAttribute("erreur", true);
						response.sendRedirect("Connection");
					}
				} else {
					// connexion KO ID

					bdd.disconnect();
					session.setAttribute("erreur", true);
					response.sendRedirect("Connection");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			bdd.disconnect();
		} else {
			try {
				bdd.disconnect();
				request.setAttribute("erreur", true);
				response.sendRedirect("Connection");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Personnel findInListA(ArrayList<Administrateur> arrayList, String id) {
		Personnel tmp = null;
		for (Personnel p : arrayList) {
			if (p.getIdentifiant().equals(id))
				tmp = p;
		}

		return tmp;
	}

	/**
	 * Method privee pour la recherche du personnel connecte
	 * 
	 * @param arrayList
	 * @param id
	 * @return
	 */
	private Personnel findInListD(ArrayList<Directeur> arrayList, String id) {
		Personnel tmp = null;
		for (Personnel p : arrayList) {
			if (p.getIdentifiant().equals(id))
				tmp = p;
		}

		return tmp;
	}

	/**
	 * Method privee pour la recherche du personnel connecte
	 * 
	 * @param arrayList
	 * @param id
	 * @return
	 */
	private Personnel findInListC(ArrayList<ChefService> arrayList, String id) {
		Personnel tmp = null;
		for (Personnel p : arrayList) {
			if (p.getIdentifiant().equals(id))
				tmp = p;
		}

		return tmp;
	}

	/**
	 * Method privee pour la recherche du personnel connecte
	 * 
	 * @param arrayList
	 * @param id
	 * @return
	 */
	private Personnel findInListE(ArrayList<Employe> arrayList, String id) {
		Personnel tmp = null;
		for (Personnel p : arrayList) {
			if (p.getIdentifiant().equals(id))
				tmp = p;
		}

		return tmp;
	}
}

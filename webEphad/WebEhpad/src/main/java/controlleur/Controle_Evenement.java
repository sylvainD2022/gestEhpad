package controlleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.DaoAccess;
import modele.Etablissement;
import modele.Registre;
import modele.Resident;

/**
 * Servlet implementation class Controle_Evenement
 */
@WebServlet("/Controle_Evenement")
public class Controle_Evenement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controle_Evenement() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		// String input = (String)request.getAttribute("input_text");
		HttpSession session = request.getSession();
		DaoAccess bdd = (DaoAccess) session.getAttribute("BDD");

		Etablissement e = (Etablissement) session.getAttribute("etablissement");

		ArrayList<Resident> resident = new ArrayList<Resident>();// 2 boucles for

		Registre r = (Registre) session.getAttribute("registre");

		for (int i = 0; i < r.getListEvenements().size(); i++) {// parcours de la collection registre;
			if (r.getListEvenements().get(i) == session.getAttribute("idEvent")) {

				response.sendRedirect("View_Evenement");
			}
			if (request.getParameter("procedure1") == "Modifier") {

				response.sendRedirect("CreationEvenment");
			}
			if (request.getParameter("procedure2") == "Ajouter") {

				response.sendRedirect("CreationEvenment");
			}
			if (request.getParameter("procedure3") == "Lecture") {

				response.sendRedirect("View_Evenement");
			}
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

}

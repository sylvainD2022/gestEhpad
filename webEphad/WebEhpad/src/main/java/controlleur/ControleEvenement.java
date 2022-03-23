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
import modele.Evenement;
import modele.Registre;
import modele.Resident;

/**
 * Servlet implementation class Controle_Evenement
 */
@WebServlet("/ControleEvenement")
public class ControleEvenement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControleEvenement() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	

		HttpSession session = request.getSession();
		//DaoAccess bdd = (DaoAccess) session.getAttribute("BDD");
		String str = request.getParameter("procedure");
		Etablissement e = (Etablissement) session.getAttribute("etablissement");

		Registre regi = (Registre) session.getAttribute("registre");
		String r = request.getParameter("idEvent");
		int reg = Integer.parseInt(r);
		
		for ( int i = 0 ; i < regi.getListEvenements().size() ; i++ ) {// parcours de la collection registre;
			if ( reg == regi.getListEvenements().get(i).getId() ) 
				session.setAttribute("evenement", regi.getListEvenements().get(i));
		}
			
		if ( str.equals("Modifier") ) {
			session.setAttribute("page", 7);
			response.sendRedirect("App");
		}
		else if ( str.equals("Ajouter") ) {
			session.setAttribute("evenement", null);
			session.setAttribute("page", 7);
			response.sendRedirect("App");
		}
		else if ( str.equals("Lire") ) {
			session.setAttribute("page", 6);
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

}

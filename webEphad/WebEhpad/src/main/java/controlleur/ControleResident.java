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
 * Servlet implementation class ControleResident
 */
@WebServlet("/ControleResident")
public class ControleResident extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControleResident() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		DaoAccess bdd = (DaoAccess) session.getAttribute("BDD");
		Etablissement e = (Etablissement) session.getAttribute("etablissement");

		ArrayList<Resident> listRes = new ArrayList<Resident>();
		listRes = e.getListResident();
		String r = request.getParameter("resident");
		int resid = ( (String)(request.getParameter("procedure")) ).equals("creation") ? 0 : Integer.parseInt(r);
		System.out.println(resid);
		for (int i = 0; i < listRes.size(); i++)// parcours de la liste resident
		{
			if ( resid ==listRes.get(i).getId())																																							
			{
				
				session.setAttribute("res",listRes.get(i));
				
				if (request.getParameter("procedure").equals("modification")) {
					session.setAttribute("page", 4);
					session.setAttribute("do", "modification");
					response.sendRedirect("App");
				}
				else if (request.getParameter("procedure").equals("affichage")) {

					session.setAttribute("page", 4);
					session.setAttribute("do","affichage");
					response.sendRedirect("App");
					
				}
			}
		}
		
		if (request.getParameter("procedure").equals("creation")) {
			session.setAttribute("do","creation");
			session.setAttribute("page", 4);
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

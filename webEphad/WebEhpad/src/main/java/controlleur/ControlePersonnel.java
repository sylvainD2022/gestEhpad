package controlleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.Etablissement;
import modele.Personnel;

/**
 * Servlet implementation class ControlePersonnel
 */
@WebServlet("/ControlePersonnel")
public class ControlePersonnel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlePersonnel() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String toto = request.getParameter("personnel");

		HttpSession session = request.getSession();
		Personnel find = null;

		
		if(toto != null)
		{
			int id = Integer.parseInt(toto);
			Etablissement e = (Etablissement) session.getAttribute("etablissement");
			
			for (Personnel p : e.getListPersonnel()) {
				if (p.getId() == id)
					find = p;
			}
		}

		session.setAttribute("personnel_concerne", find);

		session.setAttribute("page", 5);
		response.sendRedirect("App");

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

package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.Administrateur;
import modele.Personnel;

/**
 * Servlet implementation class App
 */
@WebServlet("/App")
public class App extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean isAdmin = false;

	private static String[] cssString = { "ViewMultiRegistre", "ViewRegistre", "ListResident", "ViewPersonnel",
			"CreationResident", "CreationPersonnel", "ViewEvenement", "CreationEvenement", "CreationEquipe" };
	private static String[] cssOpt = { "", "liste", "", "", "doubleLister", "", "", "doubleLister", "doubleLister" };

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public App() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		

		Personnel p = (Personnel) session.getAttribute("connected");
		if (p instanceof Administrateur) {
			isAdmin = true;
			if(request.getParameter("serviceToShow") != null) {
				session.setAttribute("serviceToShow", request.getParameter("serviceToShow"));
			}
		}

		boolean opt = cssOpt[(int) session.getAttribute("page")].isBlank();

		response.getWriter()
				.append("<html> <head> " + "<link rel=\"stylesheet\" " + "media=\"screen\" type=\"text/css\" "
						+ "href=\"/ClientLeger/css/Jonathan.css\" />"

						+ "<link rel=\"stylesheet\" " + "media=\"screen\" type=\"text/css\" " + "href=\"/ClientLeger/css/"
						+ cssString[(int) session.getAttribute("page")] + ".css\" />"
						+ (!opt ? ("<link rel=\"stylesheet\" media=\"screen\" type=\"text/css\" href=\"/ClientLeger/css/"
								+ cssOpt[(int) session.getAttribute("page")] + ".css\" />") : "") // CSS optionnel si
																									// cssOpt n'est pas
																									// vide
						+ "</head> <body>");

		response.getWriter().append("<div class=\"grid-container\"><div class=\"content\">");

		getServletContext().getRequestDispatcher("/" + cssString[(int) session.getAttribute("page")]).include(request,
				response);

		response.getWriter().append("</div><div class=\"navBar\">");

		getServletContext().getRequestDispatcher("/NavBar").include(request, response);

		response.getWriter()
				.append("</div>\r\n" + "<div class=\"header\">\r\n" + "<div class=\"messageAccueil\">"
						+ "<h1>Bienvenue " + p.getNom() + " " + p.getPrenom() + "</h1>" // MESSAGE ACCUEIL
						+ "</div>\r\n" + "</div>\r\n" + "</div>");

		response.getWriter().append("</body> </html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public static boolean isAdmin() {
		return isAdmin;
	}

	public static void setAdmin(boolean isAdmin) {
		App.isAdmin = isAdmin;
	}

}

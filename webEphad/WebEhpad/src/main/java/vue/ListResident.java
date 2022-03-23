package vue;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlleur.Utils;
import modele.Etablissement;
import modele.Personnel;
import modele.Resident;


/**
 * Servlet implementation class ListResident
 */
@WebServlet("/ListResident")
public class ListResident extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public ListResident() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		ArrayList<Resident> listRes = ((Etablissement) session.getAttribute("etablissement")).getListResident();
		
		

		/**
		 * champs pour paramètres de recherche résident
		 */
		out.println("<p id='p1'>" +"<form name='recherRes' action='ControleRechercheResident' method='post'>"
				+ "nom <input type='text' name='nom'>"// nom résident
				+ "Pr&eacute;nom <input type='text' name='prenom'>" // prénom
				+ "num&eacute;ro S&eacute;cu <input type='text'name='numSecu'>"// num sécu
				+ "chambre <input type='text' name='chambre' >");// num chambre
		out.println("<input type='submit' name='filtre' value='filtrer'>");

		out.println("</form></p>");
		/**
		 * tableau liste résident et filtrage
		 */
		Etablissement e = (Etablissement) session.getAttribute("etablissement");
		
		
		out.println("<p id='pTab'><link href=\"css/liste.css\" rel=\"stylesheet\" type=\"text/css\">\r\n"
				+ "<script src=\"js/liste.js\"></script>"+"<form action=\"ControleResident\" method=\"post\">\r\n");

		out.println("<table class=\"liste\" width=\"100%\">" + "<thead class=\"listeH\">\r\n" + "<tr>\r\n"
				+ "<td>Nom</td>\r\n" + "<td>Prenom</td>\r\n" + "<td>NumSecu</td>\r\n" + "<td>NumChambre</td>\r\n"
				+ "</tr>\r\n" + "</thead>" + "<tbody class=\"listeB\">");
		
		ArrayList<Resident> lst = (ArrayList<Resident>) session.getAttribute("listeResid");
		if(lst == null)
			lst = e.getListResident();
		
		session.setAttribute("listeResid",null);
		
		/**affichage de la liste des résidents*/
		for (Resident r : lst) {
			out.println("<tr onclick=\"trclick(this)\" >" + "<td style=\"display:none\">" + r.getId() + "</td>" + "<td>"
					+ r.getNom() + "</td>" + "<td>" + r.getPrenom() + "</td>" + "<td>" + r.getNumSecu() + "</td>"
					+ "<td>" + r.getNumChambre() + "</td></tr>");
		}

		out.println("</tbody></table>");

		out.println("<input type=\"hidden\" name=\"resident\" id=\"sender\" value=\""+lst.get(0).getId()+"\">\r\n"
				+ "<input type=\"submit\" name='procedure' value=\"creation\">"	
				
				+"<input type=\"submit\" name=\"procedure\" value=\"affichage\">"
				+ "<input type=\"submit\" name='procedure'  value=\"modification\">\r\n");
				
				
		out.println("</form></p></html>");

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

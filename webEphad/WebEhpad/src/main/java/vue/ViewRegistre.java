package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.Etablissement;
import modele.Evenement;
import modele.Registre;
import modele.Resident;

/**
 * Servlet implementation class Administrateur
 */
@WebServlet("/ViewRegistre")
public class ViewRegistre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public ViewRegistre() {
		super();
	}

	/**
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException,PrintWriter out=response.getWriter(); {
	 * out.println(""); out.println(""); }
	 **/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		
		Etablissement e = (Etablissement) session.getAttribute("etablissement");
		Registre reg = (Registre)session.getAttribute("registre");
		

		/*for (Registre r : e.getListRegistre()) {
			if (r.getId() == 5) {
				reg = r;
			}
		}
		session.setAttribute("registre", reg);*/

		String retour = "	<title> View Registre </title>\r\n"
				+ "	<link href=\"css/liste.css\" rel=\"stylesheet\" type=\"text/css\">\r\n"
				+ "	<script src=\"js/liste.js\"></script>\r\n"
				+ "		<form action='ControleRechercheRegistre' method='post'>"
				+ "			<select name='importance'>\r\n"
				+ "				<option>Niveau Importance</option>\r\n"
				+ "				<option>1</option>\r\n"
				+ "				<option>2</option>\r\n"
				+ "				<option>3</option>\r\n"
				+ "			</select>\r\n"
				+ "			<select name='declarant'>\r\n"
				+ "				<option>Declarant</option>\r\n";

		for (int i = 0; i < e.getListEmployes().size(); i++) {
			retour += "<option>" + e.getListEmployes().get(i).getNom()
					+ e.getListEmployes().get(i).getPrenom().substring(0, 1) + "</option>\r\n";
		}

		retour += "			</select>\r\n"
				+ "			<input type=\"text\" name=\"rechercher\" placeholder=\"Mots clefs\" width='100%'>\r\n"
				+ "			<input type=\"submit\" value=\"rechercher\">\r\n"
				+ "\r\n"
				+ "	</form>"
				+ "\r\n";
		retour += "<table border=\"1\" class=\"liste\" width=\"100%\">" 
				+ "<thead class=\"listeH\">" 
				+ "<tr>"
				+ "<td style=\"width:20%;\">Importance</td>" 
				+ "<td style=\"width:20%;\">Date</td>" 
				+ "<td style=\"width:20%;\">Declarant</td>" 
				+ "<td style=\"width:20%;\">Titre</td>"
				+ "<td style=\"width:20%;\">Voir plus</td>" 
				+ "</tr>" 
				+ "</thead>" 
				+ "<tbody class=\"listeB\">";
		
		ArrayList<Evenement> lst = (ArrayList<Evenement>) session.getAttribute("listeEvents");
		if(lst == null)
			lst = reg.getListEvenements();
		
		session.setAttribute("listeEvents",null);

		for (Evenement event : lst) {
			retour += "<tr onclick=\"trclick(this);\">";
			retour += "<td style=\"display:none\">" + event.getId() + "</td>";
			retour += "<td>" + event.getGravite() + "</td>";
			retour += "<td>" + event.getDateEmission() + "</td>";
			retour += "<td>" + event.getDeclarant() + "</td>"; 
			retour += "<td>" + event.getTitre() + "</td>";
			retour += "<td>" + event.getDescription() + "</td>";
			retour += "</tr>";
		}

		retour += " </tbody></table>"
				+ " 	<form action=\"ControleEvenement\" method=\"get\">"
				+ " 		<input type=\"hidden\" name=\"idEvent\" id=\"sender\">\r\n"
				+ " 		<input type=\"submit\" name=\"procedure\" value=\"Modifier\">\r\n"
				+ "			<input type=\"submit\" name=\"procedure\" value=\"Ajouter\">\r\n"
				+ "			<input type=\"submit\" name=\"procedure\" value=\"Lire\">\r\n" 
				+ " 	</form>";
		response.getWriter().println(retour);
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

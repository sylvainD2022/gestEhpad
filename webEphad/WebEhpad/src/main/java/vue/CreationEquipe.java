package vue;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.ChefService;
import modele.Employe;
import modele.Equipe;
import modele.Etablissement;
import modele.Personnel;

/**
 * Servlet implementation class CreationEquipe
 */
@WebServlet("/CreationEquipe")
public class CreationEquipe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationEquipe() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession h = request.getSession();

		Etablissement etabe = (Etablissement) h.getAttribute("etablissement");
		 int idEquipe = (int) h.getAttribute("idEquipe");
		//int idEquipe = 0;
		// ****************************
		// Initialisation lorsque modif
		Equipe equipe = null;
		String listeChef = "";

		for (int i = 0; i < etabe.getListChefServices().size(); i++) {
			
			for (Equipe e : etabe.getListChefServices().get(i).getListeEquipe()) {
				if (e.getNumEquipe() == idEquipe) {
					listeChef += "<option selected>" + etabe.getListChefServices().get(i).getIdentifiant() + "</option>\r\n";
					equipe = e;
				}else {
					listeChef += "<option>" + etabe.getListChefServices().get(i).getIdentifiant() + "</option>\r\n";
				}


				}

			}
		
//		for(int i=0;i<etabe.getListEmployes().size() ;i++) {
//			listeEmploye+="<tr><td>"+etabe.getListEmployes().get(i).getIdentifiant()+"</td></tr>";
//			
//		}
//		if(idEquipe!=0) {
//			for(int i=0;i<equipe.getListeEmploye().size();i++) {
//				listeEmployeEquipe+="<tr><td>"+equipe.getListeEmploye().get(i)+"</td></tr>";
//			}
//		}

		PrintWriter out = response.getWriter();
		out.println("<form name='formulaire' action='ControleGestionEquipe' method='GET'>"// TODO remplir la valeur d'action
				+ "			<table width=100%>" + "				<tr>" + "					<td>"
				+ "						<b>Chef de service</b>" + "						<select name='chefService'>"
				+ listeChef + "						</select>" + "					</td>" + "					<td>"
				+ "						<b>Horaire</b>" + "						<input name='horaire' type='text' value=");
		if (idEquipe == 0) {
			out.println("''");
		} else {
			out.println(equipe.getHoraire());
		}
		out.println("></td>" + "				</tr>" + "				<tr>" + "					<td colspan=2>");
		if (idEquipe == 0) {
			out.println("	<b>Nouvelle équipe</b>");
		} else {
			out.println("	<b>Equipe n°" + idEquipe + "</b>");
		}

		out.println("		</td></tr>" + "			<tr>" + "				<td colspan=2>"
				+ "					<b>Employés</b>" + "				</td>	" + "			</tr>"
				+ "		</table>");
//				
//				+ "							<input type='button' value='Ajouter' onclick=>"			
//				+ "							<input type='button' value='Retirer'>"

		out.println("<div class=\"mainBlock\">\r\n" + "<div></div>" + "        <div class=\"left\" id=\"D\">   \r\n"
				+ "            \r\n" + "        </div>\r\n" + "        <div  class=\"right\" id=\"R\">\r\n"
				+ "            \r\n" + "        </div>\r\n"
				+ "		<select id=\"listOut\" name=\"listOut\" style='visibility:hidden;' multiple></select>	"
				+ "<div></div>" + "    </div>");
		out.println("	<table width=100%><tr>		"
				+ "			<td width=50% align=center><input type='button' onClick='submitForm()' value='Valider'></td>"
				+ "			<td width=50% align=center><input type='button' value='Retour'></td>"
				+ "		</tr></table></form>"
		);

		boolean first = true;
		out.println("<script type=\"text/javascript\">var listNom=[");
		for (Employe e : etabe.getListEmployes()) {
			if (first) {
				out.println("'" + e.toString() + e.getId() + "'");
				first = false;
			} else {
				out.println(",'" + e.toString() + e.getId() + "'");
			}
		}
		out.println("]; var listBdd = [");
		if(equipe != null) {
			for (Personnel p : equipe.getListeEmploye()) {
				if (first) {
					out.println("'" + p.toString() + p.getId() + "'");
					first = false;
				} else {
					out.println(",'" + p.toString() + p.getId()  + "'");
				}
			}
		}	
		
		out.println("];</script><script type=\"text/javascript\" src=\"/ClientLeger/js/dLister.js\"></script>");
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

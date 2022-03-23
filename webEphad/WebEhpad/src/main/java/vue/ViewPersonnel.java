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

import modele.Administrateur;
import modele.ChefService;
import modele.Directeur;
import modele.Employe;
import modele.Equipe;
import modele.Etablissement;
import modele.Personnel;

/**
 * Servlet implementation class ViewPersonnel
 */
@WebServlet("/ViewPersonnel")
public class ViewPersonnel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPersonnel() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		out.println("<table border=1 width=\"100%\" heigth=\"100%\">");

		out.println("<tr>"
				    +"<td rowspan=\"3\">" + getTreeEtab(session) + "</td>"
				    +"<td>" + getRecherche() + "</td>"
				    +"</tr>"
					+"<tr>"
					+"<td>" + getListe(session)
					+"<br>" + getfootBouton() + "</td>"
					+"</tr>");
		
		out.println("</table>");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String getRecherche()
	{
		String ret = "";
		
		ret += "<form action=\"ControleRecherchePersonnel\" method=post>";
		ret += "<label>Prenom:</label>";
		ret += "<input type=\"text\" name=\"prenom\" />";
		ret += "<label>Nom:</label>";
		ret += "<input type=\"text\" name=\"nom\" />";
	    ret += "<input type=\"submit\" value=\"Recherche\">";
		ret += "</form>";
		
		return ret;
	}

	private String getListe(HttpSession session)
	{
		String ret = "";

		Etablissement e = (Etablissement) session.getAttribute("etablissement");
		
		ret += "<link href=\"css/liste.css\" rel=\"stylesheet\" type=\"text/css\">\r\n"
			+  "<script src=\"js/liste.js\"></script>";
		
		ret += "<table class=\"liste\" width=\"100%\">"
				+ "<thead class=\"listeH\">\r\n"
				+ "<tr>\r\n"
				+ "<td>Nom</td>\r\n"
				+ "<td>Prenom</td>\r\n"
				+ "<td>NumTelPoste</td>\r\n"
				+ "<td>Service</td>\r\n"
				+ "<td>Poste</td>\r\n"
				+ "<td>Identifiant</td>\r\n"
				+ "<td>Mot de passe</td>\r\n"
				+ "</tr>\r\n"
				+ "</thead>"
				+ "<tbody class=\"listeB\">";
		
		ArrayList<Personnel> lst = (ArrayList<Personnel>) session.getAttribute("listePerso");
		if(lst == null)
			lst = e.getListPersonnel();
		
		session.setAttribute("listePerso",null);
		
		for(Personnel p : lst)
		{
			ret += "<tr onclick=\"trclick(this);\">";
			ret += "<td style=\"display:none\">" + p.getId() + "</td>";
			ret += "<td>" + p.getNom() + "</td>";
			ret += "<td>" +  p.getPrenom() + "</td>";
			ret += "<td>" +  p.getNumTelPoste() + "</td>";
			ret += "<td>" +  p.getService() + "</td>";
			ret += "<td>" +  p.getIntitulePoste() + "</td>";
			ret += "<td>" +  p.getIdentifiant() + "</td>";
			ret += "<td>" +  p.getMdp() + "</td>";
			ret += "</tr>";
		}
		
		ret += "</tbody></table>";

		return ret;
	}

	private String getfootBouton()
	{
		String ret = "";

	    ret += "<form action=\"ControlePersonnel\" method=\"post\">\r\n"
	    		+ "<input type=\"hidden\" name=\"personnel\" id=\"sender\">\r\n"
	    		+ "<input type=\"submit\" value=\"Modifier\">\r\n"
	    		+ "</form>"
	    		+ "<form action=\"ControlePersonnel\" method=\"post\">"
				+ "<input type=\"submit\" value=\"Ajouter\">"
				+ "</form>";
		
		return ret;
	}

	private String getTreeEtab(HttpSession session)
	{
		String ret = "Organigramme : <br>";

		Etablissement e = (Etablissement) session.getAttribute("etablissement");
		
		ret += "<form action=\"ControleEquipe\" method=\"post\">";
		ret += "<select size=\"20\" name=\"labelEquipe\">";

		ret += "<optgroup label=\"" + e.getNomEtablissement() + "\">";
		ret += "<option>Administrateur</option>";
		for(Administrateur a: e.getListAdmin())
		{
			ret += "<option disabled>"+a.getNom()+" " + a.getPrenom() +"</option>";
		}
		ret += "<option>Directeur</option>";
		for(Directeur a: e.getListDirecteur())
		{
			ret += "<option disabled>"+a.getNom()+" " + a.getPrenom() +"</option>";
		}

        ArrayList<Employe> nonAffecte = new ArrayList<Employe>();
        nonAffecte.addAll(e.getListEmployes());        
        
		for(ChefService r: e.getListChefServices()) {
			ret += "<option>" + r.getService() +"</option>" ;
			
			ret+= "<option disabled>"+r.getNom()+" " + r.getPrenom() +"</option>";
			
			for(Equipe eq : r.getListeEquipe())
			{
				ret += "<option>Equipe " + Integer.toString(eq.getNumEquipe()) + "</option>";
				for(Employe emp : eq.getListeEmploye())
				{
					ret += "<option disabled>" + emp.getNom() + " " + emp.getPrenom() + "</option>";
					nonAffecte.remove(emp);
				}
			}
		}
		ret += "<option>Employés non Affecté</option>";
		
		for(Employe emp : nonAffecte)
		{
			ret += "<option disabled>" + emp.getNom() + " " + emp.getPrenom() + "</option>";
		}
		
		ret += "</optgroup>";
		
		
		ret += "</select>";
		ret += "<br>";
	    ret += "<input type=\"submit\" name=\"bp\" value=\"Modifier\">";
	    ret += "<input type=\"submit\" name=\"bp\" value=\"Ajouter\">";
		ret += "</form>";
		
		return ret;
	}
	
}

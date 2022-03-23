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
import modele.Directeur;
import modele.Employe;
import modele.Etablissement;
import modele.Personnel;
import modele.Registre;

/**
 * Servlet implementation class CreationPersonnel
 */
@WebServlet("/CreationPersonnel")
public class CreationPersonnel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationPersonnel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		String nomP;
		String prenomP;
		Class<? extends Personnel> nivHierarchique;
		int numPoste;
		String nomPoste;
		String service;
		String id;
		String mdp;
		
		Personnel personnel_concerne = (Personnel) session.getAttribute("personnel_concerne");
		
		if(personnel_concerne == null)
		{
			nomP = "";
			prenomP = "";
			nivHierarchique = Employe.class;
			numPoste = 0;
			nomPoste = "";
			service = "";
			id = "";
			mdp = "";
			
		}
		else 
		{
			//Personnel personnel_concerne = (Personnel) session.getAttribute("personnel_concerne");
			nomP = personnel_concerne.getNom();
			prenomP = personnel_concerne.getPrenom();
			nivHierarchique = personnel_concerne.getClass();
			numPoste = personnel_concerne.getNumTelPoste();
			nomPoste = personnel_concerne.getIntitulePoste();
			service = personnel_concerne.getService();
			id = personnel_concerne.getIdentifiant();
			mdp = personnel_concerne.getMdp();
			
		}
		
		Etablissement etab = (Etablissement) session.getAttribute("etablissement");
		
		
		boolean dir = false;
		boolean chef = false;
		boolean emp = false;
		
		if(nivHierarchique == Directeur.class)
		{
			dir = true;
		}
		if(nivHierarchique == ChefService.class)
		{
			chef = true;
		}
		if(nivHierarchique == Employe.class)
		{
			emp = true;
		}
		
		
		
		out.println("<form action=\"ControleGestionPersonnel\" method=\"POST\">\r\n"
				+ "		<div style=\"text-align: center;\r\n"
				+ "		margin-top: 2%;\r\n"
				+ "		background-color: #ccc;\r\n"
				+ "		margin-left: 26%;\r\n"
				+ "		margin-right: 26%;padding: 1%;\">\r\n"
				+ "			<label>Nom</label>\r\n"
				+ "			<label style=\"padding-left: 8.5%;\">Prenom</label>\r\n"
				+ "			<br>\r\n"
				+ "			<input type=\"text\" name=\"nom\" value=" + nomP + ">\r\n"
				+ "			<input type=\"text\" name=\"prenom\" value=" + prenomP + ">\r\n"
				+ "		</div>\r\n"
				+ "\r\n"
				+ "		<div style=\"text-align: center;\r\n"
				+ "		margin-top: 2%;\r\n"
				+ "		background-color: #ccc;\r\n"
				+ "		margin-left: 26%;\r\n"
				+ "		margin-right: 26%;padding: 1%;\">\r\n"
				+ "\r\n"
				+ "			<label>Niveau hiérarchique</label>\r\n"
				+ "			<br>\r\n"
				+ "			<select name=\"niv_hierarchique\">\r\n"
				+ "	<option value=\"employe\" " + (emp ? "selected" : "") +">Employe</option>\r\n"
				+ "				<option value=\"chef_service\" " + (chef ? "selected" : "") +">Chef de service</option>\r\n"
				+ "				<option value=\"directeur\" " + (dir ? "selected" : "") +">Directeur</option>\r\n"
				+ "	</select>\r\n"
				+ "		</div>\r\n"
				+ "\r\n"
				+ "		<div style=\"text-align: center;\r\n"
				+ "		margin-top: 2%;\r\n"
				+ "		background-color: #ccc;\r\n"
				+ "		margin-left: 26%;\r\n"
				+ "		margin-right: 26%;padding: 1%;\">\r\n"
				+ "\r\n"
				+ "		<label>Numéro de poste</label>\r\n"
				+ "		<label style=\"padding-left: 8.5%;\">Poste</label>\r\n"
				+ "		<label style=\"padding-left: 8.5%;\">Service</label>\r\n"
				+ "		<br>\r\n"
				+ "		<input type=\"text\" name=\"num_poste\" value=" + numPoste + ">\r\n"
				+ "		<input type=\"text\" name=\"poste\" value=" + nomPoste + ">\r\n"
				+ "		<select name=\"service\">\r\n");
		
				for(Registre r : etab.getListRegistre())
				{
					out.println("<option value=\""+ r.getService() +"\" "+(service.equals(r.getService()) ? "selected" : "")+">" + r.getService() + "</option>\\r\\n");
				}			
				
				out.println("		</select>\r\n"
				+ "\r\n"
				+ "		</div>\r\n"
				+ "\r\n"
				+ "		<div style=\"text-align: center;\r\n"
				+ "		margin-top: 2%;\r\n"
				+ "		background-color: #ccc;\r\n"
				+ "		margin-left: 26%;\r\n"
				+ "		margin-right: 26%;padding: 1%;\">\r\n"
				+ "		<b>Informations log</b>\r\n"
				+ "		<br>\r\n"
				+ "		<br>\r\n"
				+ "		<label>Identifiant</label>\r\n"
				+ "			<label style=\"padding-left: 8.5%;\">Mot de passe</label>\r\n"
				+ "			<br>\r\n"
				+ "			<input type=\"text\" name=\"id\" value=" + id + ">\r\n"
				+ "			<input type=\"text\" name=\"mdp\" value=" + mdp + ">\r\n"
				+ "\r\n"
				+ "		</div>\r\n"
				+ "		<br>\r\n"
				+ "		<br>\r\n"
				+ "		<div style=\"text-align: center;\">\r\n"
				+ "			<input type=\"submit\" style=\"background-color: green;font-size: 100%;\" name=\"valider\" value=\"valider\">\r\n"
				+ "		</div>\r\n"
				+ "	</form>\r\n"
				+ "<form action='' method=\"POST\">\r\n"
				+ "		<div style=\"text-align: center;\">\r\n"
				+ "			<input type=\"submit\" style=\"background-color: red;font-size: 100%;\" name=\"reset\" value=\"Annuler\">\r\n"
				+ "		</div>\r\n"
				+ "	</form>\r\n");
				
				session.setAttribute("page", 3);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

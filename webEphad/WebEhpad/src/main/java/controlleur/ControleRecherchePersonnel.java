package controlleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.Etablissement;
import modele.Personnel;
import vue.App;

/**
 * Servlet implementation class ControleRecherchePersonnel
 */
@WebServlet("/ControleRecherchePersonnel")
public class ControleRecherchePersonnel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleRecherchePersonnel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ArrayList<Personnel> ls = new ArrayList<>();
		String txtNom = request.getParameter("nom");
		String txtPrenom = request.getParameter("prenom");
		
		HttpSession session = request.getSession();
		Etablissement etab = (Etablissement) session.getAttribute("etablissement");
		
		if(!txtNom.isBlank() || !txtPrenom.isBlank())
		{
			for(Personnel p : etab.getListPersonnel())
			{
				if((!txtNom.isBlank() && p.getNom().toLowerCase().contains(txtNom.toLowerCase())) || (!txtPrenom.isBlank() && p.getPrenom().toLowerCase().contains(txtPrenom.toLowerCase())))
					ls.add(p);
			}		
			
			session.setAttribute("listePerso", ls);
		}				
		response.sendRedirect("App");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

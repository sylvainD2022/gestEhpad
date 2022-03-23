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
import modele.Resident;
import vue.App;

/**
 * Servlet implementation class ControleRechercheResident
 */
@WebServlet("/ControleRechercheResident")
public class ControleRechercheResident extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleRechercheResident() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ArrayList<Resident> ls = new ArrayList<>();
		String txtNom = request.getParameter("nom");
		String txtPrenom = request.getParameter("prenom");
		String txtNumSecu = request.getParameter("numSecu");
		String numChambre = request.getParameter("chambre");
		
		HttpSession session = request.getSession();
		Etablissement etab = (Etablissement) session.getAttribute("etablissement");
		
		if(!txtNom.isBlank() || !txtPrenom.isBlank() || !txtNumSecu.isBlank() || !numChambre.isBlank())
		{
			for(Resident re : etab.getListResident())
			{
				if(( !txtNom.isBlank() && re.getNom().contains(txtNom))
					|| (!txtPrenom.isBlank() && re.getPrenom().contains(txtPrenom))
					|| (!txtNumSecu.isBlank() && re.getNumSecu().contains(txtNumSecu))
					|| (!numChambre.isBlank() && Integer.toString(re.getNumChambre()).contains(numChambre)))
				{
					ls.add(re);
				}
			}			
			session.setAttribute("listeResid", ls);
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

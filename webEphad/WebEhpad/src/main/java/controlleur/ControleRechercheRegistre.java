package controlleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.Region;

import modele.Etablissement;
import modele.Evenement;
import modele.Registre;
import modele.Resident;

/**
 * Servlet implementation class ControleRechercheRegistre
 */
@WebServlet("/ControleRechercheRegistre")
public class ControleRechercheRegistre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleRechercheRegistre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Evenement> ls = new ArrayList<>();
		String declarant	= request.getParameter("declarant");
		String importance	= request.getParameter("importance");
		String mclefs		= request.getParameter("rechercher");

		HttpSession session = request.getSession();
		
		Registre reg = (Registre) session.getAttribute("registre");
		
		if(declarant.equals("Declarant") && importance.equals("Niveau Importance") && mclefs.isBlank())
		{
			
			System.out.println("toto" + declarant + importance +" "+ mclefs);
			session.setAttribute("listeEvents", null);
		}
		else
		{
			for(Evenement ev : reg.getListEvenements())
			{
				if(declarant.equals(ev.getDeclarant().getIdentifiant())
				|| importance.equals(Integer.toString(ev.getGravite())))
				{
					ls.add(ev);
				}
				else
				{
					String segments[] = mclefs.split(" ");
					if(segments.length > 0)
					{
						for(String mot : segments)
						{
							if(ev.getTitre().contains(mot) || ev.getDescription().contains(mot))
								ls.add(ev);
						}
					}					
				}
			}
			session.setAttribute("listeEvents", ls);
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

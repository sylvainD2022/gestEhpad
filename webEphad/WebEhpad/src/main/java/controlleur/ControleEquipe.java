package controlleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ControleEquipe
 */
@WebServlet("/ControleEquipe")
public class ControleEquipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleEquipe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * Récupération de la variable de session, où l'attribut bp indique s'il s'agit d'une modification ou d'une création d'équipe
		 */
		HttpSession session = request.getSession();
		String bp=(String) request.getParameter("bp");
		
		int idEquipe = 0 ;
		/*
		 * Si c'est une modification, on récupère le labelEquipe qui correspond au label sélectionné dans l'organigramme
		 */
		if(bp.equals("Modifier")) {
			String label=(String) request.getParameter("labelEquipe");
			/*
			 * Si le label récupéré commence par Equipe, on récupère le numéro d'équipe
			 */
			if(label != null && label.substring(0,7).equals("Equipe ")) {
				idEquipe=Integer.parseInt(label.substring(7));
				System.out.println("azeaze");
			}
			/*
			 * Si le label ne contient pas Equipe, on retourne sur la page 3, ViewPersonnel 
			 */
			else {
				session.setAttribute("page", 3);
				response.sendRedirect("App");
				return;
			}
		}
		if(bp.equals("Ajouter")) {
			idEquipe=0;
		}
		/*
		 * on ajoute l'attribut idEquipe à la variable de session et on est redirigé vers la page 8, CreationEquipe
		 */
		session.setAttribute("idEquipe", idEquipe);
		session.setAttribute("page",8 );
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

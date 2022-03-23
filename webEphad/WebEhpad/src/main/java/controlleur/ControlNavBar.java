package controlleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import modele.Etablissement;
import modele.Personnel;
import modele.Registre;

/**
 * Servlet implementation class ControlNavBar
 */
@WebServlet("/ControlNavBar")
public class ControlNavBar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlNavBar() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		int type_button = Integer.parseInt(request.getParameter("i"));

		HttpSession session = request.getSession();
		
		if( type_button == 1 && request.getParameter("reg").equals("connected") ) {
			session.setAttribute("registre", getRegistre(session, "connected") ); // Registre de la personne connecté			
		}
		else if ( type_button == 1 && request.getParameter("reg").equals("Global")) {
			session.setAttribute("registre", getRegistre(session, "Global") ); // Registre Global
		}
		session.setAttribute("page", type_button);

		response.sendRedirect("App");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
	/**
	 * 
	 * @param s HttpSession en cours
	 * @param str Variable de session à rechercher. Si "connected" renverra le registre de la personne connecté
	 * @return Registre dont le nom correspond à "str"
	 */
	private Registre getRegistre(HttpSession s, String str) {
		Etablissement e = (Etablissement) s.getAttribute("etablissement");
		ArrayList<Registre> lsR =  e.getListRegistre();
		String nameReg = null;
		
		if(str.equals("connected")) {
			nameReg = ((Personnel)s.getAttribute("connected")).getService();
		}else {
			nameReg = str;			
		}
	
		for(Registre r : lsR) {
			if (r.getService().equals(nameReg)) {
				return r;
			}
		}
		return null;
		
	}
}

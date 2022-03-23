package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NevBar
 */
@WebServlet("/NavBar")
public class NavBar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavBar() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String str[] = { "Accueil", "Registres Global", "Registre Service", "Résidents", "Personnel" };
		
		for (int i = 0; i < str.length; i++) {
			response.getWriter().append("<a href=\"/ClientLeger/ControlNavBar?i=" 
					+( (i==1 || i==2) ? ( (i==1) ? ("1&reg=Global") : ("1&reg=connected") ) : ( (i==0) ? 0 : i-1 ) )
					+"\" ><button class=\"btnNav\" >" + str[i] + "</button></a><br>");
		}
		
		
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

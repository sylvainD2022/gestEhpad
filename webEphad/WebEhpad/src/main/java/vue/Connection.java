package vue;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connection() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		boolean err = ( session.getAttribute("erreur") == null ? false : (boolean) session.getAttribute("erreur"));
		
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + "<html style=\"background-color: #cccccc;\">\r\n" + "\r\n" + "<head>\r\n"
				+ "	<title>Webehpad</title>\r\n" + "</head>\r\n" + "\r\n" + "<body>\r\n" + "\r\n"
				+ "	<div style=\"margin-left: 17%;margin-right: 17%;margin-top: 10%;\">\r\n" + "\r\n"
				+ "		<div style=\"text-align: center;\">\r\n" + "\r\n"
				+ "			<b style=\"font-size: 200%;\">Bienvenue</b>\r\n" + "\r\n" + "		</div>\r\n" + "\r\n"
				+ "		<br>\r\n" + "		<br>\r\n" + "		<div style=\"text-align: center;\">\r\n"
				+ "			<form action=\"ControleConnexion\" method=\"POST\">\r\n"
				+ "				<label style=\"margin-left: -9%;\">Identifiant</label>\r\n" + "				<br>\r\n"
				+ "				<input style=\"\" type=\"text\" name=\"id\">\r\n" + "				<br>\r\n"
				+ "				<br>\r\n" + "				<label style=\"margin-left: -7%;\">Mot de passe</label>\r\n"
				+ "				<br>\r\n" + "				<input style=\"\" type=\"text\" name=\"mdp\">\r\n"
				+ "				<br>\r\n" + "				<br>\r\n" + "				<br>\r\n"
				+ "				<input type=\"submit\" style=\"font-size: 96%;\" name=\"valider\" value=\"Connexion\">\r\n"
				+ "			</form>\r\n" + "		</div>\r\n");

		if (err) {
			out.println("<br><br>Erreur identifiant ou mot de passe" + "\r\n" + "</div>\r\n" + "\r\n" + "\r\n" + "\r\n"
					+ "</body>\r\n" + "\r\n" + "</html>");
		} else {
			out.println("</div>\r\n" + "\r\n" + "\r\n" + "\r\n" + "</body>\r\n" + "\r\n" + "</html>");
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

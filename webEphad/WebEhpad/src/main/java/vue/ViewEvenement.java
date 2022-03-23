package vue;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.Evenement;

/**
 * Servlet implementation class ViewEvenement
 */
@WebServlet("/ViewEvenement")
public class ViewEvenement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewEvenement(Evenement event, boolean lectureEvent, String service) {
		super();

		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		//HttpSession session = request.getSession();
		out.println("<button style='border-radius: 2px'>retour</button>");
		out.println("<p style='margin-top:-20px; margin-left:55px; font-family:cambria'>Niveau d'importance :</p>");
		out.println("<div style='margin-left:210px; margin-top:-35px'><input type='text'></input><div>");
		out.println("<p style='margin-top:20px; margin-left:-130px;font-family:cambria'>Date :</p>");
		out.println("<div style='margin-left:-1px; margin-top:-40px'><input type='text'></input><div>");
		out.println(
				"<form name='formul' action='/CreationEvenement\' method='GET'><input type='text' style='margin-left :-100px; margin-top:100px;width:400px; height:400px name='input_text''></input></form>");
		out.println("<p style='font-family:cambria'>Résidents Concernés :</p>");
		out.println(
				"<table style='border:1px; color: black'><th style='font-family:cambria'>Nom</th><th style='font-family:cambria'>Prenom</th><th style='font-family:cambria'>DateDeNaissance</th><th style='font-family:cambria'>NuméroSecu</th><th>Chambres</th><tr></tr></table>");

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

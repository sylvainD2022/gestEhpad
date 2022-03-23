package vue;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlleur.Utils;
import modele.Etablissement;
import modele.Evenement;
import modele.Registre;
import modele.Resident;

/**
 * Servlet implementation class CreationEvenement
 */
@WebServlet("/CreationEvenement")
public class CreationEvenement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationEvenement() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		/*
		 * Penser à créer un booléen pour déterminer si création d'evenement ou
		 * modification evenement
		 */
		
		Etablissement e = (Etablissement) session.getAttribute("etablissement");
		//session.setAttribute("registre",(Registre) e.getListRegistre().get(2));
		Registre reg = (Registre) session.getAttribute("registre");
		//session.setAttribute("evenement", reg.getListEvenements().get(0));
		Evenement event = (Evenement) session.getAttribute("evenement");
		String evenement = (event == null ) ? "1" : "0";

		//event 
		//=((Etablissement)session.getAttribute("etablissement")).getListRegistre().get(0).getListEvenements().get(0);
		
		
		/*
		 * tests : 
		 * event = e.getListRegistre().get(0).getListEvenements().get(0);
		 * reg = (Registre) e.getListRegistre().get(2);
		 */ 
		 
	
			//remet a 0 l'evenement de session


		//event=null;
		
		
		
		
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String dateSt; 
		//date, soit de l'évenement / si création date du jour
		dateSt= evenement.equals("1") ? df.format(d) :  df.format(event.getDateEmission());
		System.out.println(evenement.equals("1"));
		//session.setAttribute("BDD", new DaoAccess("localhost", "gestehpad", "gestEhpad", "1234", null));
		out.println("<form name='formulaire' action='ControleGestionEvenement' method='get' accept-charset='UTF8' >"
				+ "<input type='hidden' id='event' name='event' value='" + evenement + "'/>"
				+ "<input id='date' name='date' type='hidden' value='" + dateSt + "'/>"
				+ "<table width='100%' border='1'>" 
					+ "<tr width='100%'>" 
						+ "<td width='30%' align='center'>"
							+ "<select name='selectService'>");
		String[] ls = null;
		int nb = Utils.getArray("Service", session).length;
		ls = new String[nb];
		ls = Utils.getArray("Service", session);
		
		for (int i = 0; i < ls.length; i++) 
		{
			// Tout ça juste pour créer un sélecteur, ternaire pour préselectionner le service/Registre
			out.println(" <option value='" + ls[i] + "'" +((evenement.equals("0") && reg.getService().equals(ls[i])) ? "selected>":">") + ls[i] 
			+ "</option>");
		}
		
		out.println("" 		+ "</select>" 
						+ "</td>" 
						+ "<td align='center'><b>importance : </b>"
							+ "<select name='selectGravite'>" 
								+ "<option value=1 "+((evenement.equals("0") && event.getGravite() == 1 ) ? "selected":"") + ">1</option>" 
								+ "<option value=2 "+((evenement.equals("0") && event.getGravite() == 2 ) ? "selected":"") + ">2</option>"
								+ "<option value=3 "+((evenement.equals("0") && event.getGravite() == 3 ) ? "selected":"") + ">3</option>" 
								

							+ "</select><br><br>" 
						+ "</td>" 
						+ "<td width='40%' align='center'><b>"
							+ dateSt + "</b></td>" 
					+ "</tr>" 
					+ "<tr>" 
						+ "<td colspan='3' align = 'center' height='60px'>"
							+ "<input type='text' name='titre' autofocus maxlength='44' size='44' required style='height:50px; font-size:24pts;'"
								+ (evenement.equals("1") 
									? "placeholder='Entrez un titre ...'/>" 
									: "value='" + event.getTitre() + "' />")
						+ "</td>" 
					+ "</tr>" 
					+ "<tr>" 
						+ "<td align='center' colspan='3' height='400px'>"
							+ "<textarea name='description' rows='25' cols='180' height='auto' resize='none' required"
								+ (evenement.equals("1") 
									? "placeholder='Entrez votre texte ...'/>" 
									: "/>" + event.getDescription())
							+ "</textarea>" + "</td>" + "</tr>" + "</table>");

		out.println("<div class=\"mainBlock\">\r\n" + "<div></div>" 
		+ "        <div class=\"left\" id=\"D\">   \r\n"
				+ "            \r\n" 
				+ "</div>\r\n" 
				+ "<div  class=\"right\" id=\"R\">\r\n"
				+ "            \r\n" 
				+ "</div>\r\n"
				+ "		<select id=\"listOut\" name=\"listOut\" style='visibility:hidden;' multiple></select>	"
				+ "<div></div>" 
				+ "</div>");

		out.println("<div id='envoyer' width='250px'>" + "<input onclick=\"submitForm()\" type='submit' value='envoyer'>" + "</div>");



		boolean first = true;
		out.println("<script type=\"text/javascript\">var listNom=[");
		//style foreach resident dans liste Resident
		for (Resident r : e.getListResident()) {
			if (first) {
				out.println("'" + r.toString() + r.getId() + "'");
				first = false;
			} else {
				out.println(",'" + r.toString() + r.getId() + "'");
			}
		}
		out.println("];var listBdd=[");
		
		if(!evenement.equals("1"))
		{
			for (Resident r : event.getListeResident()) {
				if (first) {
					out.println("'" + r.toString() + r.getId() + "'");
					first = false;
				} else {
					out.println(",'" + r.toString() + r.getId() + "'");
				}
			}
		}
		out.println("];</script><script type=\"text/javascript\" src=\"/ClientLeger/js/dLister.js\"></script>"
		+"</form>");

		

	
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

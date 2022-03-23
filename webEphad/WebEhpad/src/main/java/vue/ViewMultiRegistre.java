package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlleur.Utils;
import modele.Etablissement;
import modele.Evenement;
import modele.Personnel;


/**
 * Servlet implementation class ViewMultiRegistre
 */
@WebServlet("/ViewMultiRegistre")
public class ViewMultiRegistre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewMultiRegistre() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Personnel p = (Personnel) session.getAttribute("connected");

		String toShow = null;
		ArrayList<String> lsService = Utils.getList("Service", session);
		
		lsService.remove("Admin");
		lsService.remove("Global");
		
		if (App.isAdmin() && session.getAttribute("serviceToShow") == null) {
			toShow = lsService.get(0);
		} else if (!(App.isAdmin())) {
			toShow = p.getService();
		} else {
			toShow = (String) session.getAttribute("serviceToShow");
		}

		Etablissement e = (Etablissement) session.getAttribute("etablissement");

		ArrayList<ArrayList<Evenement>> ls = new ArrayList<ArrayList<Evenement>>();
		ArrayList<Evenement> l1 = new ArrayList<>();
		ArrayList<Evenement> l2 = new ArrayList<>();
		ArrayList<Evenement> l3 = new ArrayList<>();
		ls.add(l1);
		ls.add(l2);
		ls.add(l3);

		for (int i = 0; i < e.getListRegistre().size(); i++) {
			if (e.getListRegistre().get(i).getService().equals("Global")) {
				l1.addAll(e.getListRegistre().get(i).getListEvenements());
			} else if (e.getListRegistre().get(i).getService().equals(toShow)) {// trouver service
				l2.addAll(e.getListRegistre().get(i).getListEvenements());
			} else if (App.isAdmin() && e.getListRegistre().get(i).getService().equals("Admin")) {
				l3.addAll(e.getListRegistre().get(i).getListEvenements());
			}

		}

		String div1 = "<div class=\"eventTuile\"><span>";
		String div2 = "</span></div>";
		boolean first = true;

		response.getWriter().append("<div class=\"divMain\">");

		for (int i = 0; i < 2; i++) {
			response.getWriter().append("<div class=\"reg" + (i + 1) + "\">");

			if (App.isAdmin() && i == 1) {
				response.getWriter()
						.append("<div class=\"comboReg\">" + "<select name=\"serviceCombo\" onchange=\"comboChange()\" id=\"comboReg\">");
				for (String str : lsService) {
					if (first || str.equals(toShow)) {
						response.getWriter().append("<option value=\"" + str + "\" selected>" + str + "</option>");
						first = false;
					} else {
						response.getWriter().append("<option value=\"" + str + "\">" + str + "</option>");
					}
				}
				response.getWriter().append("</select></div>");
			}

			for (Evenement l : ls.get(i)) {
				if (i == 1 && App.isAdmin()) {
					response.getWriter().append(div1 + (String) l.getTitre() + div2);
				} else {
					response.getWriter().append(div1 + (String) l.getTitre() + div2);
				}
			}
			response.getWriter().append("</div>");
		}

		if (App.isAdmin()) {
			response.getWriter().append("<div class=\"reg3\">");
			for (Evenement l : ls.get(2)) {
				response.getWriter().append(div1 + (String) l.getTitre() + div2);

			}
			response.getWriter().append("</div>");

		}

		response.getWriter().append("</div>"
		 +"</script><script type=\"text/javascript\" src=\"/ClientLeger/js/comboReg.js\"></script>");
		

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

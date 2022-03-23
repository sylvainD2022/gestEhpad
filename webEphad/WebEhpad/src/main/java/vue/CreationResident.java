package vue;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlleur.Utils;
import modele.DaoAccess;
import modele.Etablissement;
import modele.Resident;

/**
 * Servlet implementation class CreationResident
 */
@WebServlet("/CreationResident")
public class CreationResident extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationResident() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		DaoAccess bdd = (DaoAccess)session.getAttribute("BDD");
		
		Boolean disable = ((String) session.getAttribute("do")).equals("affichage"); // Si true alors désactivé l'écriture
		Resident rp = (Resident) session.getAttribute("res");
		
//		(rp == null) ? "": rp.getNom();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<div style='background-color: darkgrey; height:100%;'><form method='get' action='ControleGestionResident'>"
				+ (disable?"<fieldset disabled=\"disabled\">":"") // Rend "disable" tout son contenu
				+ "<div class='boxResidentTop' style='display: flex; justify-content: space-around; text-align: center;'>"
				+ "<div class='boxResidentInput'<p>Nom Résident</p><input type='text' name='nomRes' value=\""+ ( (rp == null) ? "": rp.getNom() )+"\"></div>"
				+ "<div class='boxResidentInput'><p>Prénom Résident</p><input type='text' name='prenomRes' value=\""+ ( (rp == null) ? "": rp.getPrenom() )+"\"></div>"
				+ "</div>"
				+ "<div class='boxResidentTop' style='display: flex; justify-content: space-around; text-align: center; margin-left: 1%;'>"
				+ "<div class='boxResidentInput'><p>Date de Naissance</p><input type='date' name='dateNaissance' value=\""+ ( (rp == null) ? "": rp.getDateNaissance() )+"\"></div>"
				+ "<div class='boxResidentInput'><p>Numéro de Sécurité social</p><input type='text' name='numSecu' value=\""+ ( (rp == null) ? "": rp.getNumSecu() )+"\" style='margin-left: 3%;'></div>"
				+ "</div>"
				+ "<div class='boxResidentTop' style='text-align: right; display: flex; align-content: space-around; flex-direction: column; lign-items: flex-end; margin-right: 18.5%;'>"
				+ "<div class='boxResidentInput'><p style='margin-right: 1.5%;'>Numero de Chambre</p><input type='text' name='numChambre' value=\""+ ( (rp == null) ? "": rp.getNumChambre() )+"\"></div>"
				+ "</div>" + "<h2 style='text-align:center'>Contact</h2>"
				+ "<div class='lineContactResident' style='display: flex; justify-content: space-around; text-align: center;'>"
				+ "<div class='boxResidentInputLine'><p>Lien Parenté</p><input type='text' name='lienParent' value=\""+ ( (rp == null) ? "": rp.getRelationUrgence() )+"\"></div>"
				+ "<div class='boxResidentInputLine'><p>Numéro de téléphone</p><input type='text' name='numTel' value=\""+ ( (rp == null) ? "": rp.getNumUrgence() )+"\"></div>"
				+ "<div class='boxResidentInputLine'><p>Nom</p><input type='text' name='nomParent' value=\""+ ( (rp == null) ? "": rp.getNomUrgence() )+"\"></div>" + "</div>"
				+ "<input type=\"hidden\" name=\"id\" value=\""+ ( (rp == null) ? 0: rp.getId() )+"\">"
				+ (disable?"</fieldset>":"")
				);
		/***/
		
		
		if(disable) {
			out.println("<div class=\"mainBlock\" style='justify-content: center; align-items:center;'>"
					+"<br>Pathologie<div  class=\"right\" id=\"R\"></div>"
					+ "<br>Allergie<div  class=\"right\" id=\"R2\"></div>"
					+ "</div>"
					);
		}else {
			out.println("<br><div class=\"mainBlock\">\r\n" + "<div></div>" + "        <br>Pathologie  <div class=\"left\" id=\"D\" style=\"visibility:"+ (disable? "hidden":"visible") +";\">   \r\n"
					+ "            \r\n" + "        </div>\r\n" + "        <div  class=\"right\" id=\"R\">\r\n"
					+ "            \r\n" + "        </div>\r\n"
					+ "		<select id=\"listOut\" name=\"listOut0\" style='visibility:hidden;' multiple></select>	"
					+ "<div></div>" + "    </div>");
			
			
			
			out.println("<br><div class=\"mainBlock\">\r\n" + "<div></div>" + "        <br>Allergie<div class=\"left\" id=\"D2\">   \r\n"
					+ "            \r\n" + "        </div>\r\n" + "        <div  class=\"right\" id=\"R2\">\r\n"
					+ "            \r\n" + "        </div>\r\n"
					+ "		<select id=\"listOut\" name=\"listOut1\" style='visibility:hidden;' multiple></select>	"
					+ "<div></div>" + "    </div>");
			
			out.println("<div id='envoyer' width='250px'>" 
					+ "<input onclick=\"submitForm()\" type='submit'></div>"); 
			
		}
			out.println("</form></div>");
		
		/***/
		ArrayList<String> listTex = new ArrayList<>();
		//TODO faire la liste et voir quoi en faire
		
		
		
		
		ArrayList<String> listPat = new ArrayList<>();
		ArrayList<String> listAler = new ArrayList<>();
		
		ResultSet rs;
		
		String sqlPat ="SELECT concat(`nomPathologie`,`idPathologie`) nom FROM `pathologie`";
		String sqlAler ="SELECT concat(`nomAllergie`,`idAllergie`) nom FROM `allergie`";
		
		try {
			
			bdd.connect();
			
			bdd.setPreparedStatement(sqlPat);
			rs = bdd.getPreparedStatement().executeQuery();
			
			while (rs.next()) {
				listPat.add( rs.getString("nom") );
			}
			
			bdd.setPreparedStatement(sqlAler);
			rs = bdd.getPreparedStatement().executeQuery();
			while (rs.next()) {
				listAler.add( rs.getString("nom") );
			}
			
			boolean first = true;
			out.println("<script type=\"text/javascript\">var disable =" + disable + "; var listNom0=[");
			
			for (String r : listPat) {
				if (first) {
					out.println("'" + r + "'");
					first = false;
				} else {
					out.println(",'" + r + "'");
				}
			}
			out.println("];var listBdd0=[");
			
			if(rp != null) {
				for (String r : rp.getNomPathologie()) {
			
					for (String parent : listPat) {
						if ( parent.contains(r) ){
							if (first) {
						out.println("'" + parent + "'");
						first = false;
					} else {
						out.println(",'" + parent + "'");
					}
						}
					}
				}
			}
			out.println("];var listNom1=[");
			for (String r : listAler) {
				if (first) {
					out.println("'" + r + "'");
					first = false;
				} else {
					out.println(",'" + r + "'");
				}
			}
			out.println("];var listBdd1=[");
			
			if(rp != null) {
				for (String r : rp.getNomAllergie()) {
					for (String parent : listAler) {
						if ( parent.contains(r) ){
							if (first) {
								out.println("'" + parent + "'");
								first = false;
							} else {
								out.println(",'" + parent + "'");
							}
						}
					}
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.println("];</script>\r\n");
		out.println("<script type=\"text/javascript\" src=\"/ClientLeger/js/dLister2.js\"></script>");
		session.setAttribute("res", null);
		
		/***/
		
//		out.println("</div>" + "<div class='leftElement'><form action='");
//		session.setAttribute("page", 2);
//		out.println("' method='GET'><button>Retour</button></form></div>" + "</div>" + "</div>" + "</body>" + "</html>");

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

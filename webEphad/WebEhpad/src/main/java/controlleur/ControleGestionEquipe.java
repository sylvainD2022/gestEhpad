package controlleur;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.ChefService;
import modele.DaoAccess;
import modele.Employe;
import modele.Etablissement;

/**
 * Servlet implementation class ControleGestionEquipe
 */
@WebServlet("/ControleGestionEquipe")
public class ControleGestionEquipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleGestionEquipe() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession s = request.getSession();
		DaoAccess bdd = (DaoAccess)s.getAttribute("BDD");
		Etablissement etab = (Etablissement) s.getAttribute("etablissement");
		int e = (int) s.getAttribute("idEquipe"); //Integer.parseInt( idE );  //TODO LA MER NOIRE

		ChefService cs = null;
		String chef = request.getParameter("chefService");
		for(ChefService c : etab.getListChefServices()) {
			if( c.getIdentifiant().equals(chef)) {
				cs = c;
			}
		}
		
		
		String horaire = request.getParameter("horaire");
		String str = (String) request.getParameter("listOut");
		String[] ls = str.split(",");
		
		ArrayList<Employe> list = new ArrayList<Employe>();
		
		for(String strl : ls) {
			for (Employe emp : etab.getListEmployes()) {
				if ( emp.getId() == Integer.parseInt(strl)) {
					list.add(emp);
				}
			}
		}
		 
		//Variables locales
		ArrayList<Integer> alIntPer = new ArrayList<Integer>();
		ArrayList<Integer> alIntEmp = new ArrayList<Integer>();
		int idChefPer = 0, idChef =  0;
		String sql = null;
		ResultSet rs = null;
		
		for(Employe o : list ) {
			alIntPer.add( o.getId());
			try {
				alIntEmp.add( Utils.returnIdinBdd( "employe", "idEmploye", String.valueOf(((Employe)(o)).getId()), s ) );
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println(alIntEmp);
		
		
		
		try {
			idChefPer = Utils.returnIdinBdd("Personnel", "identifiant", cs.getIdentifiant(), s);
			idChef = Utils.returnIdinBdd("chef", "idPersonnel" , String.valueOf(idChefPer), s);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		bdd.connect();
		
		
		if ( e == 0 ) {
			try {
				sql = "INSERT INTO equipe (`idEquipe`, `horaireEquipe`, `idChef`, `idPersonnel`)\r\n"
						+ "VALUES\r\n"
						+ "(null, '"+ horaire +"', "+idChef+", "+idChefPer+");";
				System.out.println(sql);
				bdd.setPreparedStatement(sql);
				bdd.getPreparedStatement().executeUpdate();
				
				sql = "select max(idEquipe) as max from equipe";
				bdd.setPreparedStatement(sql);
				rs = bdd.getPreparedStatement().executeQuery();
				while(rs.next()) {
					e = rs.getInt("max");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else {
			sql = "UPDATE equipe SET\r\n"
					+ "`horaireEquipe` = '"+ horaire +"',\r\n"
					+ "`idChef` = "+ idChef +",\r\n"
					+ "`idPersonnel` = "+ idChefPer +" \r\n"
					+ "WHERE `idEquipe` = "+e+";";
			System.out.println(sql);
			bdd.setPreparedStatement(sql);
			try {
				bdd.getPreparedStatement().executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
		
		
		for ( int idPer : alIntPer ) {
			sql = "UPDATE employe SET `idEquipe` = "+ e +" WHERE `idPersonnel` = "+ idPer +";";
			System.out.println(sql);
			bdd.setPreparedStatement(sql);
			try {
				bdd.getPreparedStatement().executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
		boolean first = true;
		sql = "UPDATE employe SET `idEquipe` = NULL WHERE `idEquipe` = "+ e +" AND NOT (";
	
		//TODO changer en idemploye
		for (int idPer : alIntPer) {
			if(!first) {
				sql += " OR";
			}
			sql += " `idPersonnel` = "+idPer;
			first = false;
			
		}
		sql += ");";
		if (sql.contains("`idPersonnel`")) {
			bdd.setPreparedStatement(sql);
			try {
				bdd.getPreparedStatement().executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		Utils.reloadData(s);
		bdd.disconnect();
		
		
		s.setAttribute("page", 3);
		response.sendRedirect("App");
		/******/
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

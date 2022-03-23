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

import modele.DaoAccess;
import modele.Etablissement;
import modele.Personnel;
import modele.Resident;
import vue.App;

/**
 * Servlet implementation class ControleGestionResident
 */
@WebServlet("/ControleGestionResident")
public class ControleGestionResident extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleGestionResident() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Resident working_resident = (Resident) request.getAttribute("resident");
		DaoAccess bdd = (DaoAccess) request.getAttribute("BDD");
		Etablissement etab = (Etablissement) request.getAttribute("etablissement");
		
		String prenomRes = (String) request.getAttribute("prenomRes");
		String nomRes = (String) request.getAttribute("nomRes");
		String numSSRes = (String) request.getAttribute("numSSRes");
		String numChambreRes = (String) request.getAttribute("numChambreRes");
		String nomContact = (String) request.getAttribute("nomContact");
		String numTelContact = (String) request.getAttribute("numTelContact");
		String lienContact = (String) request.getAttribute("lienContact");
		String dateNaissance = (String) request.getAttribute("dateNaissance");
		String texture = (String) request.getAttribute("texture");
		
		String lsPat = request.getParameter("listOut0");
		String lsAle = request.getParameter("listOut1");
		
		String[] str = null;
		ArrayList<String> lsPathologie = new ArrayList<String>();
		ArrayList<String> lsAllergie = new ArrayList<String>();
		try {
			str = lsPat.split(",");
			for(String s : str) {
				String sql = "SELECT * FROM pathologie WHERE idPathologie = ?;";
				bdd.setPreparedStatement(sql);
				bdd.getPreparedStatement().setInt(1, Integer.parseInt(s));
				ResultSet rsUsers = bdd.getPreparedStatement().executeQuery();
				while (rsUsers.next())
					lsPathologie.add( rsUsers.getString("nomPathologie") );
				
			}
			
			str = lsAle.split(",");
			for(String s : str) {
				String sql = "SELECT * FROM pathologie WHERE idPathologie = ?;";
				bdd.setPreparedStatement(sql);
				bdd.getPreparedStatement().setInt(1, Integer.parseInt(s));
				ResultSet rsUsers = bdd.getPreparedStatement().executeQuery();
				while (rsUsers.next())
					lsAllergie.add( rsUsers.getString("nomPathologie") );
		}
		}catch(Exception e) {
			
		}
		
		
		int idRes = (working_resident == null ? -1 : working_resident.getId());
		
		if ( working_resident != null && dateNaissance != "" && prenomRes != "" && nomRes != "" && numSSRes != ""&& numChambreRes != "" && nomContact != "" &&  numTelContact != "" && lienContact != "") 
		{
			working_resident.setPrenom(prenomRes);
			working_resident.setNom(nomRes);
			working_resident.setNumSecu(numSSRes);
			working_resident.setNumChambre(Integer.parseInt(numChambreRes));
			working_resident.setNomUrgence(nomContact);
			working_resident.setNumUrgence(numTelContact);
			working_resident.setRelationUrgence(lienContact);
			working_resident.setDateNaissance(dateNaissance);
			working_resident.setNomRegime(texture);
			working_resident.setNomPathologie(lsPathologie);
			working_resident.setNomAllergie(lsAllergie);
			/*working_resident.setNomPathologie(cr.getPathologies().getList()); 
			working_resident.setNomAllergie(cr.getAllergies().getList());*/ //TODO : Trouver une soluce pour recuperer les listes des doubles listes
			
			String strQuery;
			
			bdd.connect(); 
			
			ResultSet rs;
			
			int idRegime = 0;
			strQuery = "SELECT idRegime FROM regime WHERE nomRegime = '" + working_resident.getNomRegime() + "';";
			bdd.setPreparedStatement(strQuery);
			try 
			{
				rs = bdd.getPreparedStatement().executeQuery();
				
				while(rs.next()) 
				{
					idRegime = rs.getInt(1);
				}
				
			} 
			
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			//Modification d'un résident
			if (idRes !=-1) 
			{
			
				// récupère idDossierMedical en base
				int idDossierMedical = 0;
				strQuery = "SELECT idDossierMedical FROM dossiermedical WHERE idResident = " + idRes + ";";
				bdd.setPreparedStatement(strQuery);
				try 
				{
					rs = bdd.getPreparedStatement().executeQuery();
					while(rs.next()) 
					{
						idDossierMedical = rs.getInt(1);
					}
				} 
				catch (SQLException e) 
				{
					
					e.printStackTrace();
				}
				
			
				// si le résident n'a pas de dossier médical
				if(idDossierMedical == 0) 
				{
				
					// insère un dossier médical
					strQuery="INSERT INTO dossiermedical (idRegime, idResident) VALUES (" 
						+ idRegime + ", " + idRes + ");";	
					bdd.setPreparedStatement(strQuery);
					try 
					{
						bdd.getPreparedStatement().executeUpdate();
						
						strQuery = "SELECT idDossierMedical FROM dossiermedical WHERE idResident = " + idRes + ";";
						bdd.setPreparedStatement(strQuery);
						rs = bdd.getPreparedStatement().executeQuery();
						while(rs.next()) 
						{
							idDossierMedical = rs.getInt(1);
						}
					} 
					catch (SQLException e) 
					{
						
						e.printStackTrace();
					}
					
				}
				
				strQuery = "DELETE FROM listepathologie WHERE idDossierMedical = " + idDossierMedical + ";";						
				bdd.setPreparedStatement(strQuery);
				try 
				{
					bdd.getPreparedStatement().executeUpdate();
				} 
				
				catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				for(int j=0;j<working_resident.getNomPathologie().size();j++) 
				{
					// récupère idPathologie en base
					int idPathologie = 0;
					strQuery = "SELECT idPathologie FROM pathologie WHERE nomPathologie = '" + working_resident.getNomPathologie().get(j) + "';";
					bdd.setPreparedStatement(strQuery);
					
					try 
					{
						rs = bdd.getPreparedStatement().executeQuery();
						while(rs.next()) 
						{
							idPathologie = rs.getInt(1);
						}
					}
					
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					
					// insère les pathologies du résident en base
					strQuery="INSERT INTO listepathologie (idPathologie, idDossierMedical) VALUES (" 
							+ idPathologie + ", " + idDossierMedical + ");";	
					bdd.setPreparedStatement(strQuery);
					
					try 
					{
						bdd.getPreparedStatement().executeUpdate();
					}
					
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
				
				// modifie les allergies du résident
				// efface les allergies du résident
				strQuery = "DELETE FROM listeallergie WHERE idDossierMedical = " + idDossierMedical + ";";
				
				bdd.setPreparedStatement(strQuery);
				try 
				{
					bdd.getPreparedStatement().executeUpdate();
				}
				
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				// crée les pathologies du résident
				for(int j=0;j<working_resident.getNomAllergie().size();j++) 
				{
					// récupère idAllergie en base
					int idAllergie = 0;
					strQuery = "SELECT idAllergie FROM allergie WHERE nomAllergie = '" + working_resident.getNomAllergie().get(j) + "';";
					bdd.setPreparedStatement(strQuery);
					try {
						rs = bdd.getPreparedStatement().executeQuery();
						while(rs.next()) {
							idAllergie = rs.getInt(1);
						}
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					
					// insère les allergies du résident en base
					strQuery="INSERT INTO listeallergie (idAllergie, idDossierMedical) VALUES (" 
							+ idAllergie + ", " + idDossierMedical + ");";	
					bdd.setPreparedStatement(strQuery);
					try {
						bdd.getPreparedStatement().executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				// modifie régime du résident
				strQuery = "UPDATE dossiermedical SET idRegime = " + idRegime + " WHERE idResident = " + working_resident.getId() + ";";
				bdd.setPreparedStatement(strQuery);
				try {
					bdd.getPreparedStatement().executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				// récupère idContact en base
				int idContact = 0;
				strQuery = "SELECT idContactUrgence FROM resident WHERE idResident = '" + idRes + "';";
				bdd.setPreparedStatement(strQuery);
				try {
					rs = bdd.getPreparedStatement().executeQuery();
					while(rs.next()) {
						idContact = rs.getInt(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				// modifie informations du contact du résident
				strQuery = "UPDATE contacturgence SET nomContact = '" + working_resident.getNomUrgence() + "' , numContact = '" + working_resident.getNumUrgence()
								+  "', relationUrgence = '" + working_resident.getRelationUrgence() 
								+ "' WHERE idContactUrgence = " + idContact + ";";
				bdd.setPreparedStatement(strQuery);
				try {
					bdd.getPreparedStatement().executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				// modifie informations du résident
				strQuery = "UPDATE resident SET nomResident = '" + working_resident.getNom() + "' , prenomResident = '" + working_resident.getPrenom() + "' , idEtablissement = 1,"
								+ " naissanceResident = '" + working_resident.getDateNaissance() + "', " 
								+ "numSecuResident = '" + working_resident.getNumSecu() + "', numChambreResident = " + working_resident.getNumChambre() 
								+ " WHERE idResident = " + working_resident.getId() + ";";
				bdd.setPreparedStatement(strQuery);
				try {
					bdd.getPreparedStatement().executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			//Création d'un résident
			else 
			{
				// insère le contact d'urgence du résident
				strQuery = "INSERT INTO contacturgence (nomContact, numContact, relationUrgence) VALUES ('" 
						+ working_resident.getNomUrgence() + "', '" + working_resident.getNumUrgence() + "', '" + working_resident.getRelationUrgence() + "');";		
				bdd.setPreparedStatement(strQuery);
				try {
					bdd.getPreparedStatement().executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				// récupère idContact en base
				int idContact = 0;
				strQuery = "SELECT idContactUrgence FROM contacturgence WHERE numContact = '" + working_resident.getNumUrgence() + "';";
				bdd.setPreparedStatement(strQuery);
				try {
					rs = bdd.getPreparedStatement().executeQuery();
					while(rs.next()) {
						idContact = rs.getInt(1);
					}
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				
				// insère le nouveau résident en base
				strQuery = "INSERT INTO resident (nomResident, prenomResident, naissanceResident, idEtablissement, numSecuResident, numChambreResident, idContactUrgence) VALUES ('" 
							+ working_resident.getNom() + "', '" + working_resident.getPrenom() + "', '" + working_resident.getDateNaissance() + "', 1,"
							+ "'" + working_resident.getNumSecu() + "', " + working_resident.getNumChambre() + ", " + idContact + ");";
				bdd.setPreparedStatement(strQuery);
				try {
					bdd.getPreparedStatement().executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				// récupère idResident en base
				int idResident = 0;
				strQuery = "SELECT idResident FROM resident WHERE numSecuResident = '" + working_resident.getNumSecu() + "';";
				bdd.setPreparedStatement(strQuery);
				try {
					rs = bdd.getPreparedStatement().executeQuery();
					while(rs.next()) {
						idResident = rs.getInt(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				// insère le régime du résident, création dossier médical
				strQuery = "INSERT INTO dossiermedical (idRegime, idResident) VALUES (" 
						+ idRegime + ", " + idResident + ");";
				bdd.setPreparedStatement(strQuery);
				try {
					bdd.getPreparedStatement().executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				// récupère idDossierMedical en base
				int idDossierMedical = 0;
				strQuery = "SELECT idDossierMedical FROM dossiermedical WHERE idResident = " + idResident + ";";
				bdd.setPreparedStatement(strQuery);
				try {
					rs = bdd.getPreparedStatement().executeQuery();
					while(rs.next()) {
						idDossierMedical = rs.getInt(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				// crée les pathologies du résident
				for(int j=0;j<working_resident.getNomPathologie().size();j++) 
				{
					
					// récupère idPathologie en base
					int idPathologie = 0;
					strQuery = "SELECT idPathologie FROM pathologie WHERE nomPathologie = '" + working_resident.getNomPathologie().get(j) + "';";
					bdd.setPreparedStatement(strQuery);
					try {
						rs = bdd.getPreparedStatement().executeQuery();
						while(rs.next()) {
							idPathologie = rs.getInt(1);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					// insère les pathologies du résident en base
					strQuery="INSERT INTO listepathologie (idPathologie, idDossierMedical) VALUES (" 
							+ idPathologie + ", " + idDossierMedical + ");";	
					bdd.setPreparedStatement(strQuery);
					try {
						bdd.getPreparedStatement().executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}	
								
				// crée les allesrgies du résident
				for(int j=0;j<working_resident.getNomAllergie().size();j++) 
				{
					// récupère idPathologie en base
					int idAllergie = 0;
					strQuery = "SELECT idAllergie FROM allergie WHERE nomAllergie = '" + working_resident.getNomAllergie().get(j) + "';";
					bdd.setPreparedStatement(strQuery);
					try {
						rs = bdd.getPreparedStatement().executeQuery();
						while(rs.next()) {
							idAllergie = rs.getInt(1);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					
					// insère les allergies du résident en base
					strQuery="INSERT INTO listeallergie (idAllergie, idDossierMedical) VALUES (" 
							+ idAllergie + ", " + idDossierMedical + ");";	
					bdd.setPreparedStatement(strQuery);
					try {
						bdd.getPreparedStatement().executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}	
			}
			bdd.disconnect();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package controlleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.DaoAccess;
import modele.Etablissement;
import modele.Evenement;
import modele.Personnel;
import modele.Resident;
import vue.App;
import vue.CreationEvenement;

/**
 * Servlet implementation class ControleGestionEvenement
 */
@WebServlet("/ControleGestionEvenement")
public class ControleGestionEvenement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DaoAccess bdd;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleGestionEvenement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
			PrintWriter out = response.getWriter();
			Evenement event = (Evenement) session.getAttribute("evenement");//TODO a verifier
//			out.println(event.getDescription()+event.getDateEmission());
			session.setAttribute("evenement", null);
			Personnel p = (Personnel) session.getAttribute("connected");
			Etablissement eta=(Etablissement) session.getAttribute("etablissement");
			
			
			
			String titreText = (String) request.getParameter("titre");
			String description = (String)request.getParameter("description");
			String date = (String) request.getParameter("date");
			String nomRegistre = (String) request.getParameter("selectService");
			String importance = (String) request.getParameter("selectGravite");
			String res=(String) request.getParameter("resident");
			ArrayList<Resident>listRes=new ArrayList<Resident>();
			
			/*
			 * integre les résidents selectionnés(CreationEvenement) dans listRes
			 */
			res="";
			
		try 
		{
			System.out.println(titreText+description+date+nomRegistre+importance+event.getId());
			/*
			 * TODO récup double liste, récup une String à déchiffrer et entrer en ArrayList
			 * getDl() == recup du doublelister
			 */
			String qqc[]=res.split(";");
			if(!qqc[0].isEmpty())
			{
					for(int i=0; i<qqc.length;i++)
				{
					 listRes.add(eta.getListResident().get(Integer.parseInt(qqc[i])));		
				}
				System.out.println(listRes);
			}
		
			
			
			out.println(date);
			int idevent;
			
			/*
			 * gestion Si event= null, alors création evenement
			 */
			if(event == null)
				{
				System.out.println("erreur...");
				idevent=0;
				}
			else 
			{
				idevent =  Utils.returnIdinBdd("evenement", "descriptionEvent",event.getDescription(),session);
			}
			
				if (titreText != "" && description != "" && date != ""&& nomRegistre != "" && importance != "") 
				{
					
					/*******************************************************************************
					 * injecter evenement dans bdd uniquement
					 *******************************************************************************/
					
					String strQuery=null;	
			        bdd = new DaoAccess("localhost", "gestehpad", "gestEhpad", "1234", null);
	
					bdd.connect(); 				
					/*******************************************************************************
					 * Modification d'un evenement
					 *******************************************************************************/
					if (idevent !=0) 
					{
						/********************************************************************************
						 * On modifie les valeurs de l'évenement
						 ********************************************************************************/
						event.setTitre(titreText);
						event.setDescription(description);
						event.setDateEmission(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
						event.setGravite(Integer.parseInt(importance));
						event.setListeResident(listRes);
	
						/*******************************************************************************
						 *  modifie informations de l'event
						 *******************************************************************************/
						System.out.println(event.getGravite());
						System.out.println(event.getTitre());
						System.out.println(event.getDescription());
						System.out.println(date);
						System.out.println(idevent);
						/*******************************************************************************
						 *  Mise à jour de l'affichage, retour à la liste de résidents
						 *******************************************************************************/
	//					String nomRegistre=(String) e.getSelectRegistre().getSelectedItem();
	//					for (int i=0; i<e.getListRegistre().size();i++)
	//					{
	//						if (e.getSelectRegistre().getSelectedItem().equals(e.getListRegistre().get(i).getService()))
	//						{
	//							nomRegistre=e.getListRegistre().get(i).getService();
	//						}
	//					}
						/*******************************************************************************
						 *  récupère idregistre en BDD
						 **************************************************************************/
						
						int idRegistre = 0;
						strQuery = "SELECT idService FROM service WHERE nomService = '" + nomRegistre + "';";
						bdd.setPreparedStatement(strQuery);
						ResultSet rs = bdd.getPreparedStatement().executeQuery();
						while(rs.next()) {
						idRegistre = rs.getInt(1);
						}
						
						strQuery = "UPDATE evenement SET graviteEvent = '" + event.getGravite()
										+  "' , titreEvent = '" + event.getTitre()
										+ "' , descriptionEvent = '"+event.getDescription()
										+"' , idRegistre = '"+idRegistre
										+ "' WHERE idEvenement = " + idevent  + ";";
						
						bdd.setPreparedStatement(strQuery);
						bdd.getPreparedStatement().executeUpdate();
						
						/*******************************************************************************
						 *  modifie les residents concernés par l'event
						 *******************************************************************************/
	
						try {
						strQuery = "DELETE FROM `listresidentevent` WHERE idEvent = "+idevent;						
						bdd.setPreparedStatement(strQuery);
						bdd.getPreparedStatement().executeUpdate();
						}catch (Exception e1) {
						}
						
						for(int j=0;j<listRes.size();j++) 
						{
							strQuery="INSERT INTO listresidentevent (idResident, idEvent) VALUES ('" 
									+ listRes.get(j).getId() + "', '" + idevent + "');";
							bdd.setPreparedStatement(strQuery);
							bdd.getPreparedStatement().executeUpdate();
							
						}	
						
					}
					/*************************************************************************************************************************************************************
					 * Création d'un résident
					 *************************************************************************************************************************************************************/
					else 
					{		
			
						/*******************************************************************************
						 *  Mise à jour de l'affichage, retour à la liste de résidents
						 *******************************************************************************/
	//					String nomRegistre=(String) e.getSelectRegistre().getSelectedItem();
	//					for (int i=0; i<e.getListRegistre().size();i++)
	//					{
	//						if (e.getSelectRegistre().getSelectedItem().equals(e.getListRegistre().get(i).getService()))
	//						{
	//							nomRegistre=e.getListRegistre().get(i).getService();
	//						}
	//					}
						/*******************************************************************************
						 *  récupère idregistre en BDD
						 **************************************************************************/
						int idRegistre = 0;
						strQuery = "SELECT idService FROM service WHERE nomService = '" + nomRegistre + "';";
						bdd.setPreparedStatement(strQuery);
						ResultSet rs = bdd.getPreparedStatement().executeQuery();
						while(rs.next()) {
						idRegistre = rs.getInt(1);
						}
						System.out.println("");
						
						
						/*******************************************************************************
						 * insère les valeurs dans evenement
						 *******************************************************************************/
	
	
						strQuery = "INSERT INTO evenement (graviteEvent, dateEmission, titreEvent, descriptionEvent, idRegistre, idPersonnel) "
								+ "VALUES ('" + importance + "', '" + date +  "', '" +titreText + "', '" +description+"','"
								+ idRegistre+"','"+p.getId()+"');";
						
						bdd.setPreparedStatement(strQuery);
						bdd.getPreparedStatement().executeUpdate();
						
						/*******************************************************************************
						 *  insere id resident et idEvent dans listresidentEvent
						 *******************************************************************************/
						System.out.println();
						for(int j=0;j<listRes.size();j++) 
						{
							
							strQuery="INSERT INTO listresidentevent (idResident, idEvent) VALUES ('" 
									+ listRes.get(j).getId() + "', '" + Utils.returnIdinBdd("evenement", "dateEmission", date, session) + "');";		
							bdd.connect();
							bdd.setPreparedStatement(strQuery);
							bdd.getPreparedStatement().executeUpdate();
							
						}					
					}
	
	
					/*******************************************************************************
					 *  Mise à jour de l'affichage, retour à la liste de résidents
					 *******************************************************************************/
	//				String nomRegistre=(String) e.getSelectRegistre().getSelectedItem();
	//				for (int i=0; i<e.getListRegistre().size();i++)
	//				{
	//					if (e.getSelectRegistre().getSelectedItem().equals(e.getListRegistre().get(i).getService()))
	//					{
	//						nomRegistre=e.getListRegistre().get(i).getService();
	//					}
	//				}
					
					/*******************************************************************************
					 *  récupère idregistre en BDD
					 **************************************************************************/
					int idRegistre = 0;
					strQuery = "SELECT idService FROM service WHERE nomService = '" + nomRegistre + "';";
					bdd.setPreparedStatement(strQuery);
					ResultSet rs = bdd.getPreparedStatement().executeQuery();
					while(rs.next()) {
					idRegistre = rs.getInt(1);
					}				
					
					/********************************************************************************
					 *  Déconnexion de la base de données
					 *******************************************************************************/
	
					bdd.disconnect();
					
					/*******************************************************************************
					 * changement de frame
					 *******************************************************************************/
					session.setAttribute("page", 1);
					response.sendRedirect("App");
				}
				else 
				{
					System.out.println("Un des champs est vide");
					session.setAttribute("page", 7);
					response.sendRedirect("App");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
			out.println(titreText+description+date+nomRegistre+importance);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

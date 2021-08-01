package com.GDI.dao;

import static com.GDI.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.GDI.dao.DAOUtilitaire.fermetureSilencieuse;

import static com.GDI.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.GDI.beans.Intervention;
import com.GDI.beans.Materiel;
import com.GDI.beans.Utilisateur;
import com.GDI.beans.interventionTerminee;
import com.GDI.gestionpole.ListeIntervention;
import com.GDI.gestionpole.ListeMateriaux;
import com.GDI.gestionpole.ListeUtilisateurs;

public class ChefDePoleDaoImpl implements ChefDePoleDao 
{
	private DAOFactory          daoFactory;

	 ChefDePoleDaoImpl( DAOFactory daoFactory ) 
	 {
		 this.daoFactory = daoFactory;
	 }
	 
	 private  String SQL_SELECT = "SELECT * FROM demanderIntervention where tdp='r' ";
	 
		 	

	@Override
	public ListeIntervention listerIntervention(String s) throws DAOException 
	{
		switch(s)
		{
			case "Electricite":SQL_SELECT = "SELECT * FROM demanderIntervention WHERE tdp='Electricite' and statut!='termine'"; break;
			case "Plomberie":SQL_SELECT = "SELECT * FROM demanderIntervention WHERE tdp='Plomberie' and statut!='termine'"; break;
			case "Menuiserie":SQL_SELECT = "SELECT * FROM demanderIntervention WHERE tdp='Menuiserie' and statut!='termine'"; break;
			case "Climatisation":SQL_SELECT = "SELECT * FROM demanderIntervention WHERE tdp='Climatisation' and statut!='termine'"; break;
			case "Maçonnerie":SQL_SELECT = "SELECT * FROM demanderIntervention WHERE tdp='Maçonnerie' and statut!='termine'"; break;
			case "Video-projecteur":SQL_SELECT = "SELECT * FROM demanderIntervention WHERE tdp='Video-Projecteur' and statut!='termine'"; break;
			case "Urgent":SQL_SELECT = "SELECT * FROM demanderIntervention WHERE priorite='Urgent'"; break;
			case "Peu-Urgent":SQL_SELECT = "SELECT * FROM demanderIntervention WHERE priorite='Peu Urgent'"; break;
			case "Pas-Urgent":SQL_SELECT = "SELECT * FROM demanderIntervention WHERE priorite='Pas Urgent'"; break;
			case "Message":SQL_SELECT = "SELECT * FROM demanderIntervention WHERE statut='termine'"; break;
		}
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ListeIntervention liste=new ListeIntervention();
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT, false );
	         ResultSet resultSet = preparedStatement.executeQuery();
	         /* Analyse du statut retourné par la requête d'insertion */
	         while(resultSet.next())
	         {
	        	 if(!liste.estDans(map(resultSet)))
	        		 liste.ajouterIntervention(map(resultSet));
	         }
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  preparedStatement, connexion );
	     }
	     return liste;
	}
	
	private static Intervention map( ResultSet resultSet ) throws SQLException 
	{
		Intervention utilisateur = new Intervention();
	    utilisateur.setMatricule( resultSet.getString( "matricule" ) );
	    utilisateur.setId( resultSet.getLong( "id" ) );
	    utilisateur.setTdp( resultSet.getString( "tdp" ) );
	    utilisateur.setCdd( resultSet.getString( "cdd" ) );
	    utilisateur.setService( resultSet.getString( "service" ) );
	    utilisateur.setPriorite( resultSet.getString( "priorite" ) );
	    utilisateur.setDate( resultSet.getTimestamp("dateDemande") );
	    utilisateur.setStatut( resultSet.getString( "statut" ) );
	    return utilisateur;
	}
	
	private static final String SQL_SELECT1 = "SELECT * FROM utilisateurs ";
	@Override
	public ListeUtilisateurs listerUtilisateur() throws DAOException 
	{
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ListeUtilisateurs liste=new ListeUtilisateurs();
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT1, false );
	         ResultSet resultSet = preparedStatement.executeQuery();
	         /* Analyse du statut retourné par la requête d'insertion */
	         while(resultSet.next())
	         {
	        	 liste.ajouterUtilisateur(mapUtilisateur(resultSet));
	         }
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  preparedStatement, connexion );
	     }
	     return liste;
	}
	
	private static Utilisateur mapUtilisateur( ResultSet resultSet ) throws SQLException 
	{
		Utilisateur utilisateur = new Utilisateur();
	    utilisateur.setMatricule( resultSet.getString( "matricule" ) );
	    utilisateur.setNom( resultSet.getString( "nom" ) );
	    utilisateur.setPrenom( resultSet.getString( "prenom" ) );
	    utilisateur.setEmail( resultSet.getString( "email" ) );
	    utilisateur.setFonction( resultSet.getString( "fonction" ) );
	    utilisateur.setTelephone( resultSet.getString( "telephone" ) );
	    return utilisateur;
	}
	

	 private  String SQL_SELECTM = "SELECT * FROM demanderIntervention where tdp='r' ";

	@Override
	public ListeMateriaux listerMateriaux(String s) throws DAOException 
	{
		switch(s)
		{
			case "Electricite":SQL_SELECTM = "SELECT * FROM Electricite"; break;
			case "Plomberie":SQL_SELECTM = "SELECT * FROM Plomberie"; break;
			case "Menuiserie":SQL_SELECTM = "SELECT * FROM Menuiserie"; break;
			case "Climatisation":SQL_SELECTM = "SELECT * FROM Climatisation"; break;
			case "Maçonnerie":SQL_SELECTM = "SELECT * FROM Maçonnerie"; break;
			case "Video-projecteur":SQL_SELECTM = "SELECT * FROM videoprojecteur"; break;
			
		}
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ListeMateriaux liste=new ListeMateriaux();
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECTM, false );
	         ResultSet resultSet = preparedStatement.executeQuery();
	         /* Analyse du statut retourné par la requête d'insertion */
	         while(resultSet.next())
	         {
	        	 Materiel m=mapMateriel(resultSet);
	        	 liste.ajouterMateriel(m.getId(),m);
	         }
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  preparedStatement, connexion );
	     }
	     return liste;
	}
	
	
	private  String SQL_SELECTM13 = "SELECT * FROM Electricite "; 
	private  String SQL_SELECTM14 = "SELECT * FROM Plomberie "; 
	private  String SQL_SELECTM15 = "SELECT * FROM Menuiserie ";
	private  String SQL_SELECTM16 = "SELECT * FROM Climatisation "; 
	private  String SQL_SELECTM17 = "SELECT * FROM Maçonnerie "; 
	private  String SQL_SELECTM18 = "SELECT * FROM videoprojecteur "; 
	private String[] sqli= {SQL_SELECTM13,SQL_SELECTM14,SQL_SELECTM15,SQL_SELECTM16,SQL_SELECTM17,SQL_SELECTM18};
	@Override
	public ListeMateriaux listerMateriaux1() throws DAOException 
	{
		int k=0;
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ListeMateriaux liste=new ListeMateriaux();
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         for(int i=0;i<6;i++)
	         {
	        	 preparedStatement = initialisationRequetePreparee( connexion, sqli[i], false );
	        	 ResultSet resultSet = preparedStatement.executeQuery();
	        	 /* Analyse du statut retourné par la requête d'insertion */
		         while(resultSet.next())
		         {
		        	 
		        	 liste.ajouterMateriel(Long.valueOf(k++),mapMateriel(resultSet));
		         }
	         }
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  preparedStatement, connexion );
	     }
	     return liste;
	}

/*	private  String SQL_SELECTM3 = "SELECT * FROM Electricite where quantite<5"; 
	private  String SQL_SELECTM4 = "SELECT * FROM Plomberie where quantite<5"; 
	private  String SQL_SELECTM5 = "SELECT * FROM Menuiserie where quantite<5";
	private  String SQL_SELECTM6 = "SELECT * FROM Climatisation where quantite<5"; 
	private  String SQL_SELECTM7 = "SELECT * FROM Maçonnerie where quantite<5"; 
	private  String SQL_SELECTM8 = "SELECT * FROM videoprojecteur where quantite<5"; 

	private String[] sqli1= {SQL_SELECTM3,SQL_SELECTM4,SQL_SELECTM5,SQL_SELECTM6,SQL_SELECTM7,SQL_SELECTM8};

	@Override
	public ListeMateriaux listerMateriaux2() throws DAOException 
	{
		
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ListeMateriaux liste=new ListeMateriaux();
	     try 
	     {
	         connexion = daoFactory.getConnection();
	         for(int i=0;i<6;i++)
	         {
		         preparedStatement = initialisationRequetePreparee( connexion,sqli1[i] , false );
		         ResultSet resultSet = preparedStatement.executeQuery();
		         while(resultSet.next())
		         {
		        	 
		        	 liste.ajouterMateriel(mapMateriel(resultSet));
		         }
	         }
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  preparedStatement, connexion );
	     }
	     return liste;
	}

	*/
	private static Materiel mapMateriel( ResultSet resultSet ) throws SQLException 
	{
		Materiel utilisateur = new Materiel();
	    utilisateur.setId( resultSet.getLong( "id" ) );
	    utilisateur.setNom( resultSet.getString( "nom" ) );
	    utilisateur.setQuantite(resultSet.getLong( "quantite" ) );
	    utilisateur.setReference( resultSet.getString( "reference" ) );
	    utilisateur.setMarque( resultSet.getString( "marque" ) );
	    
	    return utilisateur;
	}
	
	private static Materiel mapMateriel1( ResultSet resultSet ) throws SQLException 
	{
		Materiel utilisateur = new Materiel();
	    utilisateur.setId( resultSet.getLong( "idMateriel" ) );
	    utilisateur.setNom( resultSet.getString( "nom" ) );
	    utilisateur.setQuantite(resultSet.getLong( "quantite" ) );
	    utilisateur.setReference( resultSet.getString( "reference" ) );
	    utilisateur.setMarque( resultSet.getString( "marque" ) );
	    
	    return utilisateur;
	}
	

	 private  String SQL_UPDATE = "UPDATE demanderIntervention set statut='termine' where id=? ";

	 private  String SQL_INSERT = "INSERT INTO interventionterminee values(?,?,?,?) ";

	@Override
	public void termineeIntervention(interventionTerminee it) throws DAOException
	{
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, false,it.getId(),it.getIntervenants(),it.getDuree(),it.getTpm() );
	         int statut = preparedStatement.executeUpdate();
	         /* Analyse du statut retourné par la requête d'insertion */
	         if ( statut == 0 ) 
	         {
	             throw new DAOException( "Échec de la requete terminer demande." );
	         }
	         
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  preparedStatement, connexion );
	     }
	        
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, false,it.getId() );
	         int statut = preparedStatement.executeUpdate();
	         /* Analyse du statut retourné par la requête d'insertion */
	         if ( statut == 0 ) 
	         {
	             throw new DAOException( "Échec de la requete terminer demande." );
	         }
	         
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  preparedStatement, connexion );
	     }
	     
	
	}
	
	
	 private  String SQL_UPDATE2 = "SELECT * FROM ELECTRICITE where id=? ";

	 private  String SQL_DELETE = "DELETE FROM materiauxutilise where idIntervention=?";
	 

	 private  String SQL_SEL = "SELECT * FROM materiauxutilise where idIntervention=?";
	
	 private  String SQL_UPDATE1 = "SELECT * FROM ELECTRICITE where id=? ";

	 private  String SQL_INSERT1 = "INSERT INTO materiauxutilise(idIntervention,nom,idMateriel,quantite,reference,marque) values(?,?,?,?,?,?) ";

	@Override
	public void enregistrer(HttpServletRequest request) throws DAOException 
	{
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     PreparedStatement preparedStatement1 = null;
	     ResultSet resultSet=null;
	     try 
	     {
	    	 /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	    	 switch(request.getParameter("name").split(" ")[1])
	 		 {
	 			case "Electricite":SQL_UPDATE1 = "UPDATE  Electricite set quantite=quantite-? where id=?"; break;
	 			case "Plomberie":SQL_UPDATE1 = "UPDATE  Plomberie set quantite=quantite-? where id=?"; break;
	 			case "Menuiserie":SQL_UPDATE1 = "UPDATE  Menuiserie set quantite=quantite-? where id=?"; break;
	 			case "Climatisation":SQL_UPDATE1 = "UPDATE  Climatisation set quantite=quantite-? where id=?"; break;
	 			case "Maçonnerie":SQL_UPDATE1 = "UPDATE  Maçonnerie set quantite=quantite-? where id=?"; break;
	 			case "Video-projecteur":SQL_UPDATE1 = "UPDATE  videoprojecteur set quantite=quantite-? where id=?"; break;
	 			
	 		 }
	        
	    	 switch(request.getParameter("name").split(" ")[1])
	 		 {
	 			case "Electricite":SQL_UPDATE2 = "UPDATE  Electricite set quantite=quantite+? where id=?"; break;
	 			case "Plomberie":SQL_UPDATE2 = "UPDATE  Plomberie set quantite=quantite+? where id=?"; break;
	 			case "Menuiserie":SQL_UPDATE2 = "UPDATE  Menuiserie set quantite=quantite+? where id=?"; break;
	 			case "Climatisation":SQL_UPDATE2 = "UPDATE  Climatisation set quantite=quantite+? where id=?"; break;
	 			case "Maçonnerie":SQL_UPDATE2 = "UPDATE  Maçonnerie set quantite=quantite+? where id=?"; break;
	 			case "Video-projecteur":SQL_UPDATE2 = "UPDATE  videoprojecteur set quantite=quantite+? where id=?"; break;
	 			
	 		 }
	    	 
	    	 preparedStatement = initialisationRequetePreparee( connexion, SQL_SEL, false,request.getParameter("demandeid") );
			  resultSet=preparedStatement.executeQuery();
			  while(resultSet.next())
			  {
				  preparedStatement1 = initialisationRequetePreparee( connexion, SQL_UPDATE2, false,resultSet.getLong("quantite"),resultSet.getLong("idMateriel") );
				  preparedStatement1.executeUpdate();
			  }
			  preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE, false,request.getParameter("demandeid") );
			  preparedStatement.executeUpdate();
	         for(int i=0;i<100;i++)
	         {
	        	 if(request.getParameter("q"+i)!=null && request.getParameter("q"+i).length()!=0 )
	        	 {
	        		 int q=Integer.valueOf(request.getParameter("q"+i));
	        		 if( q!=0)
	        		 {
	        			 preparedStatement1 = initialisationRequetePreparee( connexion, SQL_INSERT1, true,request.getParameter("demandeid"),request.getParameter("nom"+i),request.getParameter("materielid"+i),request.getParameter("q"+i),request.getParameter("reference"+i),request.getParameter("marque"+i) );
	        			 int statut1 =preparedStatement1.executeUpdate();
	        			 if ( statut1 == 0 ) 
		    	         {
		    	             throw new DAOException( "Échec de la requete ." );
		    	         }
	        			 
	        			 preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE1, false,q,i );
	        			 int statut =preparedStatement.executeUpdate();
		        		 if ( statut == 0 ) 
		    	         {
		    	             throw new DAOException( "Échec de la requete ." );
		    	         }
	        		 }
	        	 }
	         }
	         
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  preparedStatement, connexion );
	         fermetureSilencieuse(  preparedStatement1);
	         fermetureSilencieuse( resultSet);
	     }
	     
		
	}

	private  String SQL_SELECTU="SELECT * FROM materiauxutilise";
	
	@Override
	public Map<Long, ArrayList<Materiel>> listerMateriauxU() throws DAOException 
	{
		Connection connexion = null;
	     PreparedStatement preparedStatement1 = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet=null;
	     ResultSet resultSet1=null;
	     Map<Long, ArrayList<Materiel>> l=new HashMap<Long, ArrayList<Materiel>>();
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECTU, false );
	         resultSet=preparedStatement.executeQuery();
	         while(resultSet.next())
	         {
	        	 l.put(resultSet.getLong("idIntervention"),new ArrayList<Materiel>());

	         }

	         preparedStatement1 = initialisationRequetePreparee( connexion, SQL_SELECTU, false );
	         resultSet1=preparedStatement.executeQuery();
	         while(resultSet1.next())
	         {
	        	 l.get(resultSet1.getLong("idIntervention")).add(mapMateriel1(resultSet1));

	         }

	         
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  resultSet,preparedStatement, connexion );
	         fermetureSilencieuse(resultSet1);
	         fermetureSilencieuse(preparedStatement1);
	     }
	     return l;
	}

	
	private  String SQL_SELECT11="SELECT * FROM utilisateurs where matricule=?";
	
	private  String SQL_SELECT12="SELECT matricule FROM demanderintervention where id=?";
	
	
	
	@Override
	public Utilisateur unUtilisateur(Long id) throws DAOException 
	{
		Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     PreparedStatement preparedStatement1 = null;
	     ResultSet resultSet=null ,resultSet1=null;
	     Utilisateur liste=new Utilisateur();
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT12, false,id );
	         resultSet = preparedStatement.executeQuery();
	         /* Analyse du statut retourné par la requête d'insertion */
	         if(resultSet.next())
	         {
		         preparedStatement1 = initialisationRequetePreparee( connexion, SQL_SELECT11, false,resultSet.getString("matricule") );
		         resultSet1 = preparedStatement1.executeQuery();
		        
		         if(resultSet1.next())
		        	 liste=mapUtilisateur(resultSet1);
	         }
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  resultSet,preparedStatement, connexion );
	         fermetureSilencieuse(resultSet1);
	         fermetureSilencieuse(preparedStatement1);
	     }
	     return liste;
	}

	
	private  String SQL_SELECT13="SELECT * FROM demanderintervention where id=?";
	
	@Override
	public Intervention uneIntervention(Long id) throws DAOException 
	{
		Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet=null ;
	     Intervention liste=new Intervention();
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT13, false,id );
	         resultSet = preparedStatement.executeQuery();
	         /* Analyse du statut retourné par la requête d'insertion */
	         if(resultSet.next())
	         {
		         liste=map(resultSet);
	         }
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  resultSet,preparedStatement, connexion );
	        
	     }
	     return liste;
	}
	

	private  String SQL_SELECT14="SELECT * FROM interventionterminee where id=?";
	
	@Override
	public interventionTerminee uneInterventionTerminee(Long id) throws DAOException 
	{
		Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet=null ;
	     interventionTerminee liste=new interventionTerminee();
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT14, false,id );
	         resultSet = preparedStatement.executeQuery();
	         /* Analyse du statut retourné par la requête d'insertion */
	         if(resultSet.next())
	         {
		         liste=mapIt(resultSet);
	         }
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  resultSet,preparedStatement, connexion );
	        
	     }
	     return liste;
	}

	private  String SQL_SELECT15="SELECT * FROM materiauxutilise where idIntervention=?";
	
	@Override
	public ListeMateriaux uneListeMateriaux(Long id) throws DAOException 
	{
		int k=0;
		Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet resultSet=null ;
	     ListeMateriaux liste=new ListeMateriaux();
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT15, false,id );
	         resultSet = preparedStatement.executeQuery();
	         /* Analyse du statut retourné par la requête d'insertion */
	         while(resultSet.next())
	         {
		         liste.ajouterMateriel((long) k++,mapMateriel(resultSet));
	         }
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  resultSet,preparedStatement, connexion );
	        
	     }
	     return liste;
	}
	
	private static interventionTerminee mapIt( ResultSet resultSet ) throws SQLException 
	{
		interventionTerminee utilisateur = new interventionTerminee();
	    utilisateur.setIntervenants( resultSet.getString( "intervenants" ) );
	    utilisateur.setId( resultSet.getLong( "id" ) );
	    utilisateur.setTpm( resultSet.getString( "typeDeMaintenance" ) );
	    
	    utilisateur.setDuree( resultSet.getString( "duree" ) );
	    return utilisateur;
	}

	private  String SQL_SELECT16="SELECT * FROM materiauxutilise where idIntervention=1";
	
	
	@Override
	public void ajouter(HttpServletRequest request) throws DAOException 
	{
		switch(request.getParameter("categorie"))
		{
			case "Electricite":SQL_SELECT16 = "UPDATE Electricite set quantite=quantite+? where id=?"; break;
			case "Plomberie":SQL_SELECT16 = "UPDATE Plomberie set quantite=quantite+? where id=?"; break;
			case "Menuiserie":SQL_SELECT16 = "UPDATE Menuiserie set quantite=quantite+? where id=?"; break;
			case "Climatisation":SQL_SELECT16 = "UPDATE Climatisation set quantite=quantite+? where id=?"; break;
			case "Maçonnerie":SQL_SELECT16 = "UPDATE Maçonnerie set quantite=quantite+? where id=?"; break;
			case "Video-projecteur":SQL_SELECT16 = "UPDATE videoprojecteur set quantite=quantite+? where id=?"; break;
			
		}
		Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         for(int i=0;i<100;i++)
	         {
	        	 if(request.getParameter("a"+i)!=null && request.getParameter("a"+i).length()!=0 )
	        	 {
	        
			         preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT16, false,request.getParameter("a"+i),request.getParameter("idMateriel"+i) );
			         preparedStatement.executeUpdate();
			         
	        	 }
	         }
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  preparedStatement, connexion );
	        
	     }
	}

	@Override
	public void supprimer(HttpServletRequest request) throws DAOException 
	{
		switch(request.getParameter("categorie"))
		{
			case "Electricite":SQL_SELECT16 = "DELETE FROM Electricite where id=?"; break;
			case "Plomberie":SQL_SELECT16 = "DELETE  FROM Plomberie where id=?"; break;
			case "Menuiserie":SQL_SELECT16 = "DELETE FROM Menuiserie where id=?"; break;
			case "Climatisation":SQL_SELECT16 = "DELETE FROM Climatisation where id=?"; break;
			case "Maçonnerie":SQL_SELECT16 = "DELETE FROM Maçonnerie where id=?"; break;
			case "Video-projecteur":SQL_SELECT16 = "DELETE FROM videoprojecteur where id=?"; break;
		}

		Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         for(int i=0;i<100;i++)
	         {
	        	 if(request.getParameter("a"+i)!=null && request.getParameter("a"+i).length()!=0 )
	        	 {
	        
			         preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT16, false,request.getParameter("idMateriel"+i) );
			         preparedStatement.executeUpdate();
			         
	        	 }
	         }
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  preparedStatement, connexion );
	        
	     }
	
	
	}

	@Override
	public void diminuer(HttpServletRequest request) throws DAOException 
	{
		switch(request.getParameter("categorie"))
		{
			case "Electricite":SQL_SELECT16 = "UPDATE Electricite set quantite=quantite-? where id=?"; break;
			case "Plomberie":SQL_SELECT16 = "UPDATE Plomberie set quantite=quantite-? where id=?"; break;
			case "Menuiserie":SQL_SELECT16 = "UPDATE Menuiserie set quantite=quantite-? where id=?"; break;
			case "Climatisation":SQL_SELECT16 = "UPDATE Climatisation set quantite=quantite-? where id=?"; break;
			case "Maçonnerie":SQL_SELECT16 = "UPDATE Maçonnerie set quantite=quantite-? where id=?"; break;
			case "Video-projecteur":SQL_SELECT16 = "UPDATE videoprojecteur set quantite=quantite-? where id=?"; break;
		}

		Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     try 
	     {
	         /* Récupération d'une connexion depuis la Factory */
	         connexion = daoFactory.getConnection();
	         for(int i=0;i<100;i++)
	         {
	        	 if(request.getParameter("d"+i)!=null && request.getParameter("d"+i).length()!=0 )
	        	 {
	        
			         preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT16, false,request.getParameter("d"+i),request.getParameter("idMateriel"+i) );
			         preparedStatement.executeUpdate();
			         
	        	 }
	         }
	     } catch ( SQLException e ) 
	     {
	         throw new DAOException( e );
	     } finally 
	     {
	         fermeturesSilencieuses(  preparedStatement, connexion );
	        
	     }
		
	}
	


}

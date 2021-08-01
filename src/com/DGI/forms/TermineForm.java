package com.DGI.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.GDI.beans.interventionTerminee;
import com.GDI.dao.ChefDePoleDao;
import com.GDI.dao.DAOException;

public class TermineForm 
{
	public static final String CHAMP_INTERVENANTS = "intervenants";
	public static final String CHAMP_DUREE = "duree";
	public static final String CHAMP_TPM = "type-maintenance";
	private ChefDePoleDao cdpd;
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    
    public TermineForm(ChefDePoleDao cdpd)
    {
    	this.cdpd=cdpd;
    }
    
    public void termineIntervention(HttpServletRequest request)
    {
    	String intervenants = getValeurChamp( request, CHAMP_INTERVENANTS );
	    String duree = getValeurChamp( request, CHAMP_DUREE );
	    String tpm = getValeurChamp( request, CHAMP_TPM );
	    try 
	    {
	    	try 
	        {
		        traiterIntervenants(intervenants );
	        } catch ( FormValidationException e ) 
	        {
	            setErreur( CHAMP_INTERVENANTS, e.getMessage() );
	        }
	    	try 
	        {
	    		traiterDuree( duree);
	        } catch ( FormValidationException e ) 
	        {
	            setErreur( CHAMP_DUREE, e.getMessage() );
	        }
	    	
	        if ( erreurs.isEmpty() ) 
	        {
	        	interventionTerminee it=new interventionTerminee();
	        	it.setId(Long.valueOf((request.getParameter("demandeid"))));
	        	it.setDuree(duree);
	        	it.setIntervenants(intervenants);
	        	it.setTpm(tpm);
	            cdpd.termineeIntervention(it  );
	            resultat = "Succès demande terminee.";
	        } else 
	        {
	            resultat = "Échec de terminer la demande.";
	        }
	    } catch ( DAOException e )
	    {
	        resultat = "Échec pour terminer demande : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	        e.printStackTrace();
	    }
    	
    }

	private void traiterDuree(String duree)throws FormValidationException 
	{
		if(duree==null || duree.length()==0)
		{
			throw new FormValidationException( "Merci de saisir une duree." );
		}
		
	}

	private void traiterIntervenants(String intervenants) throws FormValidationException
	{
		if(intervenants==null || intervenants.length()==0)
		{
			throw new FormValidationException( "Merci de saisir des intervenants." );
		}
		
	}

	public String getResultat() 
	{
		return resultat;
	}

	public void setResultat(String resultat) 
	{
		this.resultat = resultat;
	}

	public Map<String, String> getErreurs() 
	{
		return erreurs;
	}

	private void setErreur( String champ, String message ) 
	{
       erreurs.put( champ, message );
	}
    
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) 
	{
       String valeur = request.getParameter( nomChamp );
       if ( valeur == null || valeur.trim().length() == 0 ) 
       {
           return null;
       } else 
       {
           return valeur.trim();
       }
	}
}

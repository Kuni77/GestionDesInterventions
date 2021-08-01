package com.GDI.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.GDI.beans.Intervention;
import com.GDI.beans.Utilisateur;
import com.GDI.beans.interventionTerminee;
import com.GDI.dao.ChefDePoleDao;
import com.GDI.dao.DAOFactory;
import com.GDI.gestionpole.ListeMateriaux;

public class DetailIntervention extends HttpServlet
{
	public static final String VUE = "/WEB-INF/detail-intervention.jsp";
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_MATERIEL="listeMateriel";
	public static final String ATT_INTERVENTION="intervention";
	public static final String ATT_INTERVENTIONT="interventionT";
	public static final String ATT_USER="utilisateur";
	private ChefDePoleDao cdpd;
	
	public void init() throws ServletException 
    {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.cdpd = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getChefDePoleDao();
    }
	
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        /* Affichage de la page d'inscription */
    	Utilisateur U=cdpd.unUtilisateur(Long.valueOf(request.getParameter("demandeid")));
    	Intervention I=cdpd.uneIntervention(Long.valueOf(request.getParameter("demandeid")));
    	interventionTerminee IT=cdpd.uneInterventionTerminee(Long.valueOf(request.getParameter("demandeid")));
    	ListeMateriaux LM=cdpd.uneListeMateriaux(Long.valueOf(request.getParameter("demandeid")));
    	request.setAttribute( ATT_MATERIEL, LM.getListeMateriel());
    	request.setAttribute( ATT_INTERVENTION, I);
    	request.setAttribute( ATT_INTERVENTIONT, IT);
    	request.setAttribute( ATT_USER, U);
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}

package com.GDI.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.GDI.beans.Materiel;
import com.GDI.dao.ChefDePoleDao;
import com.GDI.dao.DAOFactory;
import com.GDI.gestionpole.ListeIntervention;
import com.GDI.gestionpole.ListeMateriaux;
import com.GDI.gestionpole.ListeUtilisateurs;
import com.GDI.gestionpole.aide;


@WebServlet("/MessageChefPole")
public class MessageChefPole extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/WEB-INF/message-chef-pole.jsp";

	public void init() throws ServletException 
    {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.cdpd = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getChefDePoleDao();
    }
	
    public MessageChefPole() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}


	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_LISTE="listeIntervention";
	public static final String ATT_LISTEU="listeUtilisateur";
	public static final String ATT_MATERIEL="listeMateriel";
	public static final String ATT_MATERIELU="listeMaterielU";
	public static final String ATT_MATERIELE="listeMaterielEpuise";
	private ChefDePoleDao cdpd;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		aide a=new aide();
		ListeIntervention liste=cdpd.listerIntervention("Message");
		request.setAttribute( ATT_LISTE, liste.getListeIntervention() );
		ListeUtilisateurs listeU=cdpd.listerUtilisateur();
		request.setAttribute( ATT_LISTEU, listeU.getListeUtilisateur());

		ListeMateriaux listeM=cdpd.listerMateriaux1();
		request.setAttribute( ATT_MATERIEL, listeM.getListeMateriel());
		Map<Long,ArrayList<Materiel>>listeMU=new HashMap<Long,ArrayList<Materiel>>();
		listeMU=cdpd.listerMateriauxU();

		request.setAttribute( ATT_MATERIELU, listeMU);
		

		request.setAttribute( "aide", a);
		this.getServletContext().getRequestDispatcher( VUE).forward( request, response );
	}

}

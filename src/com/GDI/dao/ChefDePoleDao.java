package com.GDI.dao;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.GDI.beans.Intervention;
import com.GDI.beans.Materiel;
import com.GDI.beans.Utilisateur;
import com.GDI.beans.interventionTerminee;
import com.GDI.gestionpole.ListeIntervention;
import com.GDI.gestionpole.ListeMateriaux;
import com.GDI.gestionpole.ListeUtilisateurs;
import com.GDI.servlets.InterventionTerminee;

public interface ChefDePoleDao 
{
	ListeIntervention listerIntervention(String s)throws DAOException;
	ListeUtilisateurs listerUtilisateur()throws DAOException;
	ListeMateriaux listerMateriaux(String s)throws DAOException;
	ListeMateriaux listerMateriaux1()throws DAOException;
	//ListeMateriaux listerMateriaux2()throws DAOException;
	Map<Long,ArrayList<Materiel>> listerMateriauxU()throws DAOException;
	void termineeIntervention(interventionTerminee it)throws DAOException;
	void enregistrer(HttpServletRequest request)throws DAOException;
	Utilisateur unUtilisateur(Long id) throws DAOException;
	Intervention uneIntervention(Long id)throws DAOException;
	interventionTerminee uneInterventionTerminee(Long id)throws DAOException;
	ListeMateriaux uneListeMateriaux(Long id)throws DAOException;
	void ajouter(HttpServletRequest request)throws DAOException;
	void supprimer(HttpServletRequest request)throws DAOException;
	void diminuer(HttpServletRequest request)throws DAOException;
}

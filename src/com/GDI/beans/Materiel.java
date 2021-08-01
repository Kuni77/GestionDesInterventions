package com.GDI.beans;

import java.util.ArrayList;

public class Materiel 
{
	private Long id;
	private String nom;
	private Long quantite;
	private String reference;
	private String marque;
	
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	public String getNom() 
	{
		return nom;
	}
	public void setNom(String nom) 
	{
		this.nom = nom;
	}
	public Long getQuantite() 
	{
		return quantite;
	}
	

	public void setQuantite(Long quantite)
	{
		this.quantite = quantite;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
}

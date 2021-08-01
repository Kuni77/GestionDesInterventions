package com.GDI.gestionpole;

import java.util.ArrayList;

import com.GDI.beans.Materiel;

public class aide 
{
	public boolean contient(ArrayList<Materiel> am,Materiel m)
	{
		for(int i=0; i<am.size();i++)
		{
			if(am.get(i).getId().equals(m.getId()) && am.get(i).getNom().equals(m.getNom()) && am.get(i).getMarque().equals(m.getMarque()) )//)&& am.get(i).getReference().equals(m.getReference()))
				return true;
		}
		return false;
	}
	
	public int con(ArrayList<Materiel> am,Materiel m)
	{
		for(int i=0; i<am.size();i++)
		{
			if(am.get(i).getId().equals(m.getId()) && am.get(i).getNom().equals(m.getNom()) && am.get(i).getMarque().equals(m.getMarque()) )//)&& am.get(i).getReference().equals(m.getReference()))
				return i;
		}
		return -1;
	}
	
	public Long quantite(ArrayList<Materiel> am,Materiel m)
	{
		
		return am.get(con(am,m)).getQuantite();
	}
}

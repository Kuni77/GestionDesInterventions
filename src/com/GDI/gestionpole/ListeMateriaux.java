package com.GDI.gestionpole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.GDI.beans.Materiel;

public class ListeMateriaux 
{
	private  Map<Long,Materiel>listeMateriel=new HashMap<Long,Materiel>();

	public Map<Long,Materiel> getListeMateriel() {
		return listeMateriel;
	}

	public void setListeMateriel(Map<Long,Materiel> listeMateriel) {
		this.listeMateriel = listeMateriel;
	}


	public void ajouterMateriel(Long i,Materiel m)
	{
		if(!listeMateriel.containsValue(m))
			listeMateriel.put(i, m);
	}
	
		

}

package com.GDI.beans;

public class interventionTerminee 
{
	private Long id;
	private String intervenants;
	private String duree;
	private String tpm;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIntervenants() {
		return intervenants;
	}
	public void setIntervenants(String intervenants) {
		this.intervenants = intervenants;
	}
	public String getDuree() {
		return duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
	}
	public String getTpm() {
		return tpm;
	}
	public void setTpm(String tpm) {
		this.tpm = tpm;
	}
}

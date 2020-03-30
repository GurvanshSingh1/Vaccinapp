package com.user.mareez.model;

public class AdminVaccinationInfo {
	private String vaccinType, notes;
	int vaccinEffective;

	public String getVaccinType() {
		return vaccinType;
	}

	public void setVaccinType(String vaccinType) {
		this.vaccinType = vaccinType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getVaccinEffective() {
		return vaccinEffective;
	}

	public void setVaccinEffective(int vaccinEffective) {
		this.vaccinEffective = vaccinEffective;
	}

}

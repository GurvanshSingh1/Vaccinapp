package com.user.mareez.model;

public class AdminVaccinationInfo {
	private String vaccinType, notes;
	private int vaccinEffective, vaccinId;

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

	public int getVaccinId() {
		return vaccinId;
	}

	public void setVaccinId(int vaccinId) {
		this.vaccinId = vaccinId;
	}

}

package repo;

import java.time.LocalDate;

public class Vaccination  {
	
	
	LocalDate vaccinationDate = LocalDate.now();
	LocalDate vaccinationExpirationDate = LocalDate.now().plusYears(1);
	String vaccinationDetails;
	
	public LocalDate getVaccinationDate() {
		return vaccinationDate;
	}
	public void setVaccinationDate(LocalDate vaccinationDate) {
		this.vaccinationDate = vaccinationDate;
	}
	public LocalDate getVaccinationExpirationDate() {
		return vaccinationExpirationDate;
	}
	public void setVaccinationExpirationDate(LocalDate vaccinationExpirationDate) {
		this.vaccinationExpirationDate = vaccinationExpirationDate;
	}
	public String getVaccinationDetails() {
		return vaccinationDetails;
	}
	public void setVaccinationDetails(String vaccinationDetails) {
		this.vaccinationDetails = vaccinationDetails;
	}
	public Vaccination(LocalDate vaccinationDate, LocalDate vaccinationExpirationDate, String vaccinationDetails) {
		
		this.vaccinationDate = vaccinationDate;
		this.vaccinationExpirationDate = vaccinationExpirationDate;
		this.vaccinationDetails = vaccinationDetails;
	}
	public Vaccination(String vaccinationDetails) {
		
		this.vaccinationDetails = vaccinationDetails;
	}
	@Override
	public String toString() {
		return "Oltás dátuma:" + vaccinationDate + ", lejárati dátum:"
				+ vaccinationExpirationDate + ", oka:" + vaccinationDetails;
	}
	
	
	
	

}

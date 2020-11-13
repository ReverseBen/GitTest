package repo;

import java.util.ArrayList;
import java.util.List;

public class Pets {

	String name;
	String species;
	String type;
	String colour;
	String borndate;
	String weight;
	String chipnumber;
	Person owner;

	List<Vaccination> vaccinationList = new ArrayList<>();

	public void addVaccination(Vaccination vacc) {
		this.vaccinationList.add(vacc);
	}

	public List<Vaccination> getVaccinationList() {
		return vaccinationList;
	}

	public void setVaccinationList(List<Vaccination> vaccinationList) {
		this.vaccinationList = vaccinationList;
	}

	public Pets() {

	}

	public Pets(String name, String species, String type, String colour, String borndate, String weight,
			String chipnumber) {

		this.name = name;
		this.species = species;
		this.type = type;
		this.colour = colour;
		this.borndate = borndate;
		this.weight = weight;
		this.chipnumber = chipnumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getBorndate() {
		return borndate;
	}

	public void setBorndate(String borndate) {
		this.borndate = borndate;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getChipnumber() {
		return chipnumber;
	}

	public void setChipnumber(String chipnumber) {
		this.chipnumber = chipnumber;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Person getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		return "Állat \n név:" + name + ", fajta:" + species + ", típusa:" + type + ", színe:" + colour
				+ ", szül.datum:" + borndate + ", súlya:" + weight + ", chipszám:" + chipnumber + ", gazdi neve:"
				+ owner.getName();
	}

}

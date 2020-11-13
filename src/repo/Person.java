package repo;

import java.util.ArrayList;
import java.util.List;

import services.Services;

public class Person {

	String id;
	String name;
	String email;
	String first_phonenumber;
	String secondary_phonenumber;
	List<Pets> pets = new ArrayList<>();

	public List<Pets> getPets() {
		return pets;
	}

	public void setPets(List<Pets> pets) {
		this.pets = pets;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_phonenumber() {
		return first_phonenumber;
	}

	public void setFirst_phonenumber(String first_phonenumber) {
		this.first_phonenumber = first_phonenumber;
	}

	public String getSecondary_phonenumber() {
		return secondary_phonenumber;
	}

	public void setSecondary_phonenumber(String secondary_phonenumber) {
		this.secondary_phonenumber = secondary_phonenumber;
	}

	public void addPets(Pets pet) {
		this.pets.add(pet);
	}

	public Person() {

	}

	public Person(String id, String name, String email, String first_phonenumber, String secondary_phonenumber) {

		this.id = id;
		this.name = name;
		this.email = email;
		this.first_phonenumber = first_phonenumber;
		this.secondary_phonenumber = secondary_phonenumber;
	}

	@Override
	public String toString() {
		return "Gazdi \n id:" + id + ", név:" + name + ", email:" + email + ", elsõdleges tel.szam:" + first_phonenumber
				+ ", másodlagos tel.szam:" + secondary_phonenumber + ", kisállatai: " + pets;
	}

}

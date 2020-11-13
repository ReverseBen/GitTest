package services;

import java.util.List;

import java.util.Scanner;

import java.util.stream.Collectors;

import repo.Person;
import repo.Pets;
import repo.Vaccination;

public class Services implements IServices {
	Scanner sc = new Scanner(System.in);

	public Person personImport() {
		System.out.println("K�rem adja meg az azonos�t�j�t:");
		String id = sc.nextLine();
		System.out.println("K�rem adja meg a nev�t:");
		String name = sc.nextLine();
		System.out.println("K�rem adja meg az e-mail c�m�t:");
		String email = sc.nextLine();
		System.out.println("K�rem adja meg az els�dleges telefonsz�m�t:");
		String first_phonenumber = sc.nextLine();
		System.out.println("K�rem adja meg a m�sodlagos telefonsz�m�t:");
		String secondary_phonenumber = sc.nextLine();

		return new Person(id, name, email, first_phonenumber, secondary_phonenumber);
	}

	public Pets petsImport() {
		System.out.println("K�rem az �llat nev�t:");
		String name = sc.nextLine();
		System.out.println("K�rem az �llat fajt�j�t:");
		String species = sc.nextLine();
		System.out.println("K�rem az �llat t�pus�t:");
		String type = sc.nextLine();
		System.out.println("K�rem az �llat sz�n�t:");
		String colour = sc.nextLine();
		System.out.println("K�rem az �llat sz�let�si d�tum�t:");
		String borndate = sc.nextLine();
		System.out.println("K�rem az �llat s�ly�t:");
		String weight = sc.nextLine();
		System.out.println("K�rem az �llat chip sz�m�t:");
		String chipnumber = sc.nextLine();

		return new Pets(name, species, type, colour, borndate, weight, chipnumber);
	}

	@Override
	public void button1(List<Person> list, List<Pets> pets) {
		Scanner sc = new Scanner(System.in);
		int status;
		System.out.println("Megl�v� gazda 1-es gomb");
		System.out.println("�j gazda 2-es gomb");

		Person owner;
		status = sc.nextInt();

		if (status == 1) {
			System.out.println("K�rem a gazdi azonos�t�t:");
			String inputOwnerId = sc.next();
			List<Person> ownerList = ownerSearch(list, inputOwnerId);
			if (ownerList.isEmpty()) {
				System.out.println("Nincs is ilyen gazdi!");
				return;
			}
			owner = ownerList.get(0);
		} else if (status == 2) {

			owner = personImport();
			list.add(owner);
		} else {

			return;
		}

		Pets pet = petsImport();
		pets.add(pet);
		pet.setOwner(owner);
		owner.addPets(pet);

	}

	private List<Person> ownerSearch(List<Person> list, String inputOwnerId) {

		return list.stream().filter(p -> p.getId().equals(inputOwnerId)).collect(Collectors.toList());
	}

	@Override
	public void button2(List<Person> list, List<Pets> pets) {
		Scanner sc = new Scanner(System.in);
		int status;
		System.out.println("Gazdi azonos�t�ja alapj�n 1-es gomb");
		System.out.println("Gazdi neve �s email c�me alapj�n 2-es gomb");
		System.out.println("Kis�llat chipsz�ma alapj�n 3-as gomb");
		status = sc.nextInt();
		if (status == 1) {
			System.out.println("K�rem a gazdi azonos�t�j�t:");
			String inputOwnerId = sc.next();
			List<Person> ownerList = ownerSearch(list, inputOwnerId);
			if (ownerList.isEmpty()) {
				System.out.println("Nincs is ilyen gazdi!");
				return;
			}
			Person owner = ownerList.get(0);

			prettyPrint(owner);
		} else if (status == 2) {
			sc.nextLine();
			System.out.println("K�rem a gazdi nev�t!");
			String ownerName = sc.nextLine();
			System.out.println("K�rem a gazdi email c�m�t!");
			String ownerEmail = sc.nextLine();

			List<Person> ownerList = ownerSearchByNameAndEmail(list, ownerName, ownerEmail);
			if (ownerList.isEmpty()) {
				System.out.println("Nincs is ilyen gazdi!");
				return;
			}

			for (Person owner : ownerList) {
				prettyPrint(owner);
			}
		} else if (status == 3) {
			sc.nextLine();
			System.out.println("K�rem a kis�llat chipsz�m�t!");
			String petChip = sc.nextLine();

			Person owner = ownerSearchByPetChip(pets, petChip);

			prettyPrint(owner);
		} else {
			return;
		}

	}

	private void prettyPrint(Person owner) {
		System.out.println(
				"N�v: " + owner.getName() + " tel: " + owner.getFirst_phonenumber() + " email: " + owner.getEmail());
		System.out.println("------------�llatai:---------");
		owner.getPets().stream()
				.forEach(list -> System.out.println(list.getSpecies() + ": " + list.getName() + "    t�pusa: "
						+ list.getType() + "  sz�ne: " + list.getColour() + ", sz�l.d�tum: " + list.getBorndate()
						+ "   s�lya: " + list.getWeight() + "kg, chipsz�m: CHIP" + list.getChipnumber()));
		System.out.println();
		System.out.println();
	}

	private Person ownerSearchByPetChip(List<Pets> pets, String petChip) {
		List<Pets> foundPets = pets.stream().filter(p -> p.getChipnumber().equals(petChip))
				.collect(Collectors.toList());

		if (foundPets.isEmpty()) {
			return null;
		} else {
			Pets foundPet = foundPets.get(0);
			return foundPet.getOwner();
		}
	}

	private List<Person> ownerSearchByNameAndEmail(List<Person> list, String ownerName, String ownerEmail) {
		return list.stream().filter(p -> p.getEmail().equals(ownerEmail)).filter(p -> p.getName().equals(ownerName))
				.collect(Collectors.toList());
	}

	@Override
	public void button3(List<Person> list, List<Pets> pets) {
		Scanner sc = new Scanner(System.in);
		int status;
		System.out.println("�j olt�s bead�sa kis�llatnak 1-es gomb");
		System.out.println("Kis�llat olt�sainak list�z�sa 2-es gomb");
		status = sc.nextInt();
		if (status == 1) {
			sc.nextLine();
			System.out.println("K�rem a kis�llat chipsz�m�t!");
			String petChip = sc.nextLine();
			System.out.println("K�rem az olt�s ok�t!");
			String vaccinationDetail = sc.nextLine();

			Pets foundPet = searchPetByChip(pets, petChip);
			if (foundPet == null) {
				System.out.println("Nincs ilyen chipsz�m!");
				return;
			}
			foundPet.addVaccination(new Vaccination(vaccinationDetail));
		} else if (status == 2) {
			sc.nextLine();
			System.out.println("K�rem a kis�llat chipsz�m�t!");
			String petChip = sc.nextLine();
			Pets foundPet = searchPetByChip(pets, petChip);
			if (foundPet == null) {
				System.out.println("Nincs ilyen chipsz�m!");
				return;
			}
			foundPet.getVaccinationList().stream().forEach(System.out::println);

		}
	}

	private Pets searchPetByChip(List<Pets> pet, String chipNumber) {
		List<Pets> petsByChipNumber = pet.stream().filter(c -> c.getChipnumber().equals(chipNumber))
				.collect(Collectors.toList());
		if (petsByChipNumber.isEmpty()) {
			return null;
		} else {
			Pets foundPet = petsByChipNumber.get(0);
			return foundPet;
		}

	}

}

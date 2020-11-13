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
		System.out.println("Kérem adja meg az azonosítóját:");
		String id = sc.nextLine();
		System.out.println("Kérem adja meg a nevét:");
		String name = sc.nextLine();
		System.out.println("Kérem adja meg az e-mail címét:");
		String email = sc.nextLine();
		System.out.println("Kérem adja meg az elsõdleges telefonszámát:");
		String first_phonenumber = sc.nextLine();
		System.out.println("Kérem adja meg a másodlagos telefonszámát:");
		String secondary_phonenumber = sc.nextLine();

		return new Person(id, name, email, first_phonenumber, secondary_phonenumber);
	}

	public Pets petsImport() {
		System.out.println("Kérem az állat nevét:");
		String name = sc.nextLine();
		System.out.println("Kérem az állat fajtáját:");
		String species = sc.nextLine();
		System.out.println("Kérem az állat típusát:");
		String type = sc.nextLine();
		System.out.println("Kérem az állat színét:");
		String colour = sc.nextLine();
		System.out.println("Kérem az állat születési dátumát:");
		String borndate = sc.nextLine();
		System.out.println("Kérem az állat súlyát:");
		String weight = sc.nextLine();
		System.out.println("Kérem az állat chip számát:");
		String chipnumber = sc.nextLine();

		return new Pets(name, species, type, colour, borndate, weight, chipnumber);
	}

	@Override
	public void button1(List<Person> list, List<Pets> pets) {
		Scanner sc = new Scanner(System.in);
		int status;
		System.out.println("Meglévõ gazda 1-es gomb");
		System.out.println("Új gazda 2-es gomb");

		Person owner;
		status = sc.nextInt();

		if (status == 1) {
			System.out.println("Kérem a gazdi azonosítót:");
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
		System.out.println("Gazdi azonosítója alapján 1-es gomb");
		System.out.println("Gazdi neve és email címe alapján 2-es gomb");
		System.out.println("Kisállat chipszáma alapján 3-as gomb");
		status = sc.nextInt();
		if (status == 1) {
			System.out.println("Kérem a gazdi azonosítóját:");
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
			System.out.println("Kérem a gazdi nevét!");
			String ownerName = sc.nextLine();
			System.out.println("Kérem a gazdi email címét!");
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
			System.out.println("Kérem a kisállat chipszámát!");
			String petChip = sc.nextLine();

			Person owner = ownerSearchByPetChip(pets, petChip);

			prettyPrint(owner);
		} else {
			return;
		}

	}

	private void prettyPrint(Person owner) {
		System.out.println(
				"Név: " + owner.getName() + " tel: " + owner.getFirst_phonenumber() + " email: " + owner.getEmail());
		System.out.println("------------Állatai:---------");
		owner.getPets().stream()
				.forEach(list -> System.out.println(list.getSpecies() + ": " + list.getName() + "    típusa: "
						+ list.getType() + "  színe: " + list.getColour() + ", szül.dátum: " + list.getBorndate()
						+ "   súlya: " + list.getWeight() + "kg, chipszám: CHIP" + list.getChipnumber()));
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
		System.out.println("Új oltás beadása kisállatnak 1-es gomb");
		System.out.println("Kisállat oltásainak listázása 2-es gomb");
		status = sc.nextInt();
		if (status == 1) {
			sc.nextLine();
			System.out.println("Kérem a kisállat chipszámát!");
			String petChip = sc.nextLine();
			System.out.println("Kérem az oltás okát!");
			String vaccinationDetail = sc.nextLine();

			Pets foundPet = searchPetByChip(pets, petChip);
			if (foundPet == null) {
				System.out.println("Nincs ilyen chipszám!");
				return;
			}
			foundPet.addVaccination(new Vaccination(vaccinationDetail));
		} else if (status == 2) {
			sc.nextLine();
			System.out.println("Kérem a kisállat chipszámát!");
			String petChip = sc.nextLine();
			Pets foundPet = searchPetByChip(pets, petChip);
			if (foundPet == null) {
				System.out.println("Nincs ilyen chipszám!");
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

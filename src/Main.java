
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import repo.Person;
import repo.Pets;
import repo.Vaccination;
import services.IServices;
import services.Services;

public class Main {

	public static void main(String[] args) {

		Services sv = new Services();
		List<Person> listPerson = new ArrayList<>();
		List<Pets> allPets = new ArrayList<>();

		Person pista = new Person("777", "Pista", "pista@valami.com", "205556677", "");
		Pets bodri = new Pets("Bodri", "kutya", "tacskó", "barna", "2000.11.11.", "3", "7777");
		Pets mirci = new Pets("Mirci", "macska", "perzsa", "szürke", "2010.11.22.", "9", "8888");
		pista.addPets(bodri);
		pista.addPets(mirci);
		listPerson.add(pista);
		mirci.setOwner(pista);
		bodri.setOwner(pista);
		allPets.add(bodri);
		allPets.add(mirci);
		mirci.addVaccination(new Vaccination("Valami"));

		Scanner scanner;
		int status = 0;
		while (status != 4) {
			System.out.println("\n----------Üdvözöljük----------");
			System.out.println("############ MENÜ ############");
			System.out.println("1.- Kisállat felvitele");
			System.out.println("2.- Keresés");
			System.out.println("3.- Oltás");
			System.out.println("4.- Kilépés");
			scanner = new Scanner(System.in);
			try {
				status = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Nem jó karaktert adtál meg!");
				continue;
			}
			try {
				switch (status) {
				case 1:
					sv.button1(listPerson, allPets);
					break;
				case 2:
					sv.button2(listPerson, allPets);
					break;
				case 3:
					sv.button3(listPerson, allPets);
					break;

				}
			} catch (Exception e) {
				System.out.println("Hiba a végrehajtásban!");
			}

		}
	}
}

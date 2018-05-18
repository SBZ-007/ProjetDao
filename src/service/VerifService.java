package service;

import java.util.Scanner;

public class VerifService {

	public int lectureEntier() {
		boolean boucle = false;
		Scanner scString = new Scanner(System.in);
		int choix = 0;
		Integer entier = 0;

		do {// boucle tant que l'entrée n'est pas un entier
			try {
				choix = entier.parseInt(scString.nextLine());// transforme le string en entire
				boucle = true;// permet de sortir de la boucle
			} catch (NumberFormatException e) {
				System.out.println("L'entrée doit être un entier.");
			}
		} while (boucle == false);
		return choix;
	}
}

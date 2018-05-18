package lanceur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domaineMetier.Client;
import service.ClientService;
import service.VerifService;

public class Main {

	public static void main(String[] args) {

		Scanner scString = new Scanner(System.in); // Cr�ation du Scanner String
		Scanner scInt = new Scanner(System.in); // Cr�ation du Scanner int
		Scanner scChar = new Scanner(System.in); // Cr�ation du Scanner char
		int choix = 0; // Cr�ation variable recevant les r�ponses aux choix
		boolean sortie = false; // cr�ation variable sortie du programme
		ClientService serviceClient = new ClientService(); // Instanciation de la classe service
		boolean reponse = false; // Cr�ation de la variable reponse
		Client monClient; // Cr�ation de la variable client
		int idClient = 0; // cr�ation variable permettant de r�cup�rer l'id du client sur lequel
							// travailler
		String nom; // cr�ation des variables recevant les informations des clients
		String prenom;
		int age = 0;
		int idConseiller = 0;
		char answer = ' '; // cr�ation de la variable recevant la r�ponse
		VerifService verifService = new VerifService();

		System.out.println("--------------------\n|     Bienvenue    |\n--------------------");
		do { // boucle tant que l'on ne d�sire pas sortir du programme
			System.out.println("\n Que d�sirez-vous faire ? \n  1- Cr�er une client \n  2- Afficher un client \n  "
					+ "3- Modifier un client \n  4- Supprimer un client \n  5- Afficher tous les clients \n  6- Sortir");
			choix = verifService.lectureEntier();// S�curit� sur l'entr�e qui doit �tre un entier
			switch (choix) {// appel les diff�rentes m�thodes selon le choix r�alis�
			case 1: // Cr�er une client
				System.out.println("Quel est l'ID du client ?");
				idClient = verifService.lectureEntier();// S�curit� sur l'entr�e qui doit �tre un entier
				System.out.println("Quel est le nom du client ?"); // R�cup�ration des donn�es
				nom = scString.nextLine();
				System.out.println("Quel est le pr�nom du client ?");
				prenom = scString.nextLine();
				System.out.println("Quel est l'age du client ?");
				age = verifService.lectureEntier();// S�curit� sur l'entr�e qui doit �tre un entier
				System.out.println("Quel est l'ID du conseiller ?");
				idConseiller = verifService.lectureEntier();// S�curit� sur l'entr�e qui doit �tre un entier
				monClient = new Client(idClient,nom, prenom, age, idConseiller); // cr�ation du client
				reponse = serviceClient.createClient(monClient);// int�gration du client dans la base de donn�e
				if (reponse == true) {// v�rification que la cr�ation du client s'est bien effectu�e
					System.out.println("Le client a bien �t� cr��.");
				} else {
					System.out.println("Un probl�me est survenu lors de la cr�ation du client.");
				}
				break;
			case 2: // Afficher un client
				System.out.println("Quel est l'ID du client � afficher ?");
				idClient = verifService.lectureEntier();// S�curit� sur l'entr�e qui doit �tre un entier
				monClient = serviceClient.getClient(idClient);// va chercher le client gr�ce � l'id
				System.out.println(monClient);// affiche le client
				break;
			case 3: // Modifier un client
				boolean retour = false; // cr�ation de la variable permettant de sortir de la modification client
				System.out.println("Connaissez-vous l'ID du client � modifier (O/N)?");
				do {//// s�curit� sur l'entr�e d'un caract�re O ou N majuscule ou minuscule
					answer = scChar.nextLine().charAt(0);
				} while (answer != 'O' && answer != 'o' && answer != 'n' && answer != 'N');
				if (answer == 'o' || answer == 'O') {
					System.out.println("Quel est l'ID du client � modifier ?");
					idClient = verifService.lectureEntier();// S�curit� sur l'entr�e qui doit �tre un entier
				} else {// si on ne connais pas l'id du client, affiche tous les clients pour connaitre
						// l'id
					System.out.println("Voici la liste des clients comprenant l'ID demand� :");
					List<Client> listClient = new ArrayList<Client>();// cr�ation de la liste de client
					listClient = serviceClient.getAllClient();// r�cup�ration de la liste de tous les clients
					System.out.println("Voici la liste des clients :");
					for (Client client : listClient) {// affichage de tous les clients de la liste
						System.out.println(client);
					}
					System.out.println("Quel est l'ID du client � modifier ?");
					idClient = verifService.lectureEntier();// S�curit� sur l'entr�e qui doit �tre un entier
				}
				do {// boucle tant que l'on ne veut pas revenir au menu pr�c�dent et continuer �
					// modifier le client s�lectionn�
					monClient = serviceClient.getClient(idClient);
					System.out.println(
							"\nQue d�sirez-vous modifier ? \n  1- Nom \n  2- Pr�nom \n  3- Age \n  4- Conseiller \n  5- Revenir au menu pr�c�dent");
					choix = verifService.lectureEntier();// S�curit� sur l'entr�e qui doit �tre un entier
					switch (choix) {// change les diff�rents attribut selon le choix r�alis�
					case 1:// changer le nom du client
						System.out.println("Quel est le nouveau nom ?");
						nom = scString.nextLine();
						monClient.setNom(nom);
						serviceClient.updateClient(monClient);
						break;
					case 2:// changer le pr�nom du client
						System.out.println("Quel est le nouveau pr�nom ?");
						prenom = scString.nextLine();
						monClient.setPrenom(prenom);
						serviceClient.updateClient(monClient);
						break;
					case 3:// changer l'age du client
						System.out.println("Quel est le nouvel age ?");
						age = verifService.lectureEntier();// S�curit� sur l'entr�e qui doit �tre un entier
						monClient.setAge(age);
						serviceClient.updateClient(monClient);
						break;
					case 4:// changer l'id du conseiller associ� au client
						System.out.println("Quel est l'ID du nouveau conseiller associ� au client ?");
						idConseiller = verifService.lectureEntier();// S�curit� sur l'entr�e qui doit �tre un entier
						monClient.setIdConseiller(idConseiller);
						serviceClient.updateClient(monClient);
						break;
					case 5:// revenir au menu pr�c�dent
						retour = true;
						break;
					}
				} while (retour == false);
				break;
			case 4: // Supprimer un client
				System.out.println("Connaissez-vous l'ID du client � supprimer (O/N)?");
				do {// s�curit� sur l'entr�e d'un caract�re 'O' ou 'N' minuscule ou majuscule
					answer = scChar.nextLine().charAt(0);
				} while (answer != 'O' && answer != 'o' && answer != 'n' && answer != 'N');
				if (answer == 'o' || answer == 'O') {
					System.out.println("Quel est l'ID du client � supprimer ?");
					idClient = verifService.lectureEntier();// S�curit� sur l'entr�e qui doit �tre un entier
				} else {
					System.out.println("Voici la liste des clients comprenant l'ID demand� :");
					List<Client> listClient = new ArrayList<Client>();// cr�ation de la liste de client
					listClient = serviceClient.getAllClient();
					System.out.println("Voici la liste des clients :");
					for (Client client : listClient) {
						System.out.println(client);
					}
					System.out.println("Quel est l'ID du client � supprimer ?");
					idClient = verifService.lectureEntier();// S�curit� sur l'entr�e qui doit �tre un entier
				}
				monClient = serviceClient.getClient(idClient);
				reponse = serviceClient.deleteClient(monClient);// suppression du client
				if (reponse == true) {// v�rification que le client a bien �t� surpprim�
					System.out.println("Le client a bien �t� supprim�.");
				} else {
					System.out.println("Un probl�me est survenu lors de la suppression du client.");
				}
				break;
			case 5: // Afficher tous les clients
				List<Client> listClient = new ArrayList<Client>();// cr�ation de la liste de client
				listClient = serviceClient.getAllClient();
				System.out.println("Voici la liste des clients :");
				for (Client client : listClient) {
					System.out.println(client);
				}
				break;
			case 6: // Sortir
				sortie = true;
				break;
			default:
				System.out.println("L'entr�e doit �tre un chiffre entre 1 et 6 !");
			}

		} while (sortie == false);

		System.out.println("--------------------\n|    Au revoir.    |\n--------------------");
	}

}

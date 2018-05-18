package lanceur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domaineMetier.Client;
import service.ClientService;
import service.VerifService;

public class Main {

	public static void main(String[] args) {

		Scanner scString = new Scanner(System.in); // Création du Scanner String
		Scanner scInt = new Scanner(System.in); // Création du Scanner int
		Scanner scChar = new Scanner(System.in); // Création du Scanner char
		int choix = 0; // Création variable recevant les réponses aux choix
		boolean sortie = false; // création variable sortie du programme
		ClientService serviceClient = new ClientService(); // Instanciation de la classe service
		boolean reponse = false; // Création de la variable reponse
		Client monClient; // Création de la variable client
		int idClient = 0; // création variable permettant de récupérer l'id du client sur lequel
							// travailler
		String nom; // création des variables recevant les informations des clients
		String prenom;
		int age = 0;
		int idConseiller = 0;
		char answer = ' '; // création de la variable recevant la réponse
		VerifService verifService = new VerifService();

		System.out.println("--------------------\n|     Bienvenue    |\n--------------------");
		do { // boucle tant que l'on ne désire pas sortir du programme
			System.out.println("\n Que désirez-vous faire ? \n  1- Créer une client \n  2- Afficher un client \n  "
					+ "3- Modifier un client \n  4- Supprimer un client \n  5- Afficher tous les clients \n  6- Sortir");
			choix = verifService.lectureEntier();// Sécurité sur l'entrée qui doit être un entier
			switch (choix) {// appel les différentes méthodes selon le choix réalisé
			case 1: // Créer une client
				System.out.println("Quel est l'ID du client ?");
				idClient = verifService.lectureEntier();// Sécurité sur l'entrée qui doit être un entier
				System.out.println("Quel est le nom du client ?"); // Récupération des données
				nom = scString.nextLine();
				System.out.println("Quel est le prénom du client ?");
				prenom = scString.nextLine();
				System.out.println("Quel est l'age du client ?");
				age = verifService.lectureEntier();// Sécurité sur l'entrée qui doit être un entier
				System.out.println("Quel est l'ID du conseiller ?");
				idConseiller = verifService.lectureEntier();// Sécurité sur l'entrée qui doit être un entier
				monClient = new Client(idClient,nom, prenom, age, idConseiller); // création du client
				reponse = serviceClient.createClient(monClient);// intégration du client dans la base de donnée
				if (reponse == true) {// vérification que la création du client s'est bien effectuée
					System.out.println("Le client a bien été créé.");
				} else {
					System.out.println("Un problème est survenu lors de la création du client.");
				}
				break;
			case 2: // Afficher un client
				System.out.println("Quel est l'ID du client à afficher ?");
				idClient = verifService.lectureEntier();// Sécurité sur l'entrée qui doit être un entier
				monClient = serviceClient.getClient(idClient);// va chercher le client grâce à l'id
				System.out.println(monClient);// affiche le client
				break;
			case 3: // Modifier un client
				boolean retour = false; // création de la variable permettant de sortir de la modification client
				System.out.println("Connaissez-vous l'ID du client à modifier (O/N)?");
				do {//// sécurité sur l'entrée d'un caractère O ou N majuscule ou minuscule
					answer = scChar.nextLine().charAt(0);
				} while (answer != 'O' && answer != 'o' && answer != 'n' && answer != 'N');
				if (answer == 'o' || answer == 'O') {
					System.out.println("Quel est l'ID du client à modifier ?");
					idClient = verifService.lectureEntier();// Sécurité sur l'entrée qui doit être un entier
				} else {// si on ne connais pas l'id du client, affiche tous les clients pour connaitre
						// l'id
					System.out.println("Voici la liste des clients comprenant l'ID demandé :");
					List<Client> listClient = new ArrayList<Client>();// création de la liste de client
					listClient = serviceClient.getAllClient();// récupération de la liste de tous les clients
					System.out.println("Voici la liste des clients :");
					for (Client client : listClient) {// affichage de tous les clients de la liste
						System.out.println(client);
					}
					System.out.println("Quel est l'ID du client à modifier ?");
					idClient = verifService.lectureEntier();// Sécurité sur l'entrée qui doit être un entier
				}
				do {// boucle tant que l'on ne veut pas revenir au menu précédent et continuer à
					// modifier le client sélectionné
					monClient = serviceClient.getClient(idClient);
					System.out.println(
							"\nQue désirez-vous modifier ? \n  1- Nom \n  2- Prénom \n  3- Age \n  4- Conseiller \n  5- Revenir au menu précédent");
					choix = verifService.lectureEntier();// Sécurité sur l'entrée qui doit être un entier
					switch (choix) {// change les différents attribut selon le choix réalisé
					case 1:// changer le nom du client
						System.out.println("Quel est le nouveau nom ?");
						nom = scString.nextLine();
						monClient.setNom(nom);
						serviceClient.updateClient(monClient);
						break;
					case 2:// changer le prénom du client
						System.out.println("Quel est le nouveau prénom ?");
						prenom = scString.nextLine();
						monClient.setPrenom(prenom);
						serviceClient.updateClient(monClient);
						break;
					case 3:// changer l'age du client
						System.out.println("Quel est le nouvel age ?");
						age = verifService.lectureEntier();// Sécurité sur l'entrée qui doit être un entier
						monClient.setAge(age);
						serviceClient.updateClient(monClient);
						break;
					case 4:// changer l'id du conseiller associé au client
						System.out.println("Quel est l'ID du nouveau conseiller associé au client ?");
						idConseiller = verifService.lectureEntier();// Sécurité sur l'entrée qui doit être un entier
						monClient.setIdConseiller(idConseiller);
						serviceClient.updateClient(monClient);
						break;
					case 5:// revenir au menu précédent
						retour = true;
						break;
					}
				} while (retour == false);
				break;
			case 4: // Supprimer un client
				System.out.println("Connaissez-vous l'ID du client à supprimer (O/N)?");
				do {// sécurité sur l'entrée d'un caractère 'O' ou 'N' minuscule ou majuscule
					answer = scChar.nextLine().charAt(0);
				} while (answer != 'O' && answer != 'o' && answer != 'n' && answer != 'N');
				if (answer == 'o' || answer == 'O') {
					System.out.println("Quel est l'ID du client à supprimer ?");
					idClient = verifService.lectureEntier();// Sécurité sur l'entrée qui doit être un entier
				} else {
					System.out.println("Voici la liste des clients comprenant l'ID demandé :");
					List<Client> listClient = new ArrayList<Client>();// création de la liste de client
					listClient = serviceClient.getAllClient();
					System.out.println("Voici la liste des clients :");
					for (Client client : listClient) {
						System.out.println(client);
					}
					System.out.println("Quel est l'ID du client à supprimer ?");
					idClient = verifService.lectureEntier();// Sécurité sur l'entrée qui doit être un entier
				}
				monClient = serviceClient.getClient(idClient);
				reponse = serviceClient.deleteClient(monClient);// suppression du client
				if (reponse == true) {// vérification que le client a bien été surpprimé
					System.out.println("Le client a bien été supprimé.");
				} else {
					System.out.println("Un problème est survenu lors de la suppression du client.");
				}
				break;
			case 5: // Afficher tous les clients
				List<Client> listClient = new ArrayList<Client>();// création de la liste de client
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
				System.out.println("L'entrée doit être un chiffre entre 1 et 6 !");
			}

		} while (sortie == false);

		System.out.println("--------------------\n|    Au revoir.    |\n--------------------");
	}

}

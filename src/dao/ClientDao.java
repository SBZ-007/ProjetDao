package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domaineMetier.Client;

public class ClientDao {

	private Statement connect() {
		try {
			// Chargement du driver (dans le pilote)
			Class.forName("com.mysql.jdbc.Driver");

			// Connexion � la base de donn�e
			String url = "jdbc:mysql://localhost:3306/bdd";
			String login = "root";
			String mdp = "";
			Connection connection = DriverManager.getConnection(url, login, mdp);
			
			// Pr�paration de la requ�te
			Statement stmt = connection.createStatement();
			return stmt;
		} catch (ClassNotFoundException e) {
			System.out.println("Probl�me chargement du driver !");
			e.printStackTrace();
			Statement stmt = null;
			return stmt;
		} catch (SQLException e) {
			System.out.println("Probl�me de connexion � la base de donn�e !");
			e.printStackTrace();
			Statement stmt = null;
			return stmt;
		}
	}

	public boolean createClient(Client monClient) {
		boolean reponse = false; // Cr�ation de la variable de sortie
		try {
			Statement stmt = this.connect(); // Connexion et pr�paration de la requ�te
			String sql = "INSERT INTO `client`(`idClient`,`nom`, `prenom`, `age`, `idConseiller`) VALUES ('"+ monClient.getIdClient()+"', '" + monClient.getNom()
					+ "', '" + monClient.getPrenom() + "', " + monClient.getAge() + ", "
					+ monClient.getIdConseiller() + ");";
			int result = stmt.executeUpdate(sql); // Ex�cution de la requ�te
			if (result > 0) { // Lecture des r�sultats
				reponse = true;
			} else {
				reponse = false;
			}
			return reponse; // retour de la r�ponse
		} catch (SQLException e) {
			System.out.println("Probl�me lors de la cr�ation du client !");
			e.printStackTrace();
			return reponse;
		}
	}

	public Client getClient(int id) {
		Client monClient = new Client(); // Cr�ation de la variable de sortie
		try {
			Statement stmt = this.connect(); // Connexion et pr�paration de la requ�te
			String sql = "SELECT `idClient`, `nom`, `prenom`, `age`, `idConseiller` FROM `client` WHERE idClient = "
					+ id;
			ResultSet result = stmt.executeQuery(sql); // Ex�cution de la requ�te
			result.next();
			monClient.setIdClient(result.getInt("idClient")); // R�cup�ration des donn�es
			monClient.setNom(result.getString("nom"));
			monClient.setPrenom(result.getString("prenom"));
			monClient.setAge(result.getInt("age"));
			monClient.setIdConseiller(result.getInt("idConseiller"));
			return monClient; // retour de la r�ponse
		} catch (SQLException e) {
			System.out.println("Probl�me lors de la r�cup�ration du client !");
			e.printStackTrace();
			return monClient;
		}
	}

	public Client updateClient(Client monClient) {
		try {
			Statement stmt = this.connect(); // Connexion et pr�paration de la requ�te
			String sql = "UPDATE `client` SET `nom` = '" + monClient.getNom() + "', `prenom` = '"
					+ monClient.getPrenom() + "', `age` = " + monClient.getAge() + ", `idConseiller` = "
					+ monClient.getIdConseiller() + " WHERE `idClient` = " + monClient.getIdClient();			
			int result = stmt.executeUpdate(sql); // Ex�cution de la requ�te
			if (result > 0) {
				return monClient; // retour de la r�ponse
			} else {
				System.out.println("Un probl�me est survenu lors de la modification du client.");
				return monClient;// retour de la r�ponse
			}
		} catch (SQLException e) {
			System.out.println("Probl�me lors de la modification du client !");
			e.printStackTrace();
			return monClient;// retour de la r�ponse
		}
	}

	public boolean deleteClient(Client monClient) {
		boolean reponse = false; // Cr�ation variable de retour
		try {
			Statement stmt = this.connect(); // Connexion et pr�paration de la requ�te
			String sql = "DELETE FROM client WHERE `idClient` = " + monClient.getIdClient();

			int result = stmt.executeUpdate(sql); // Ex�cution de la requ�te
			if (result > 0) { // Lecture des r�sultats
				reponse = true;
			} else {
				reponse = false;
			}
			return reponse; // retour de la r�ponse
		} catch (SQLException e) {
			System.out.println("Probl�me lors de la suppression du client !");
			e.printStackTrace();
			return reponse;
		}
	}

	public List<Client> getAllClient() {
		List<Client> listClient = new ArrayList<Client>();
		try {
			Statement stmt = this.connect(); // Connexion et pr�paration de la requ�te
			String sql = "SELECT * FROM `client`;";
			ResultSet result = stmt.executeQuery(sql); // Ex�cution de la requ�te
			while (result.next()) {
				Client monClient = new Client(); // Cr�ation de la variable de sortie
				monClient.setIdClient(result.getInt("idClient")); // R�cup�ration des donn�es
				monClient.setNom(result.getString("nom"));
				monClient.setPrenom(result.getString("prenom"));
				monClient.setAge(result.getInt("age"));
				monClient.setIdConseiller(result.getInt("idConseiller"));
				listClient.add(monClient);
			}
			return listClient; // retour de la r�ponse
		} catch (SQLException e) {
			System.out.println("Probl�me lors de la r�cup�ration des client !");
			e.printStackTrace();
			return listClient;
		}
	}

}

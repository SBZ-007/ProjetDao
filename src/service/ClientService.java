package service;

import java.util.List;

import dao.ClientDao;
import domaineMetier.Client;

public class ClientService {

	ClientDao dao = new ClientDao();

	public boolean createClient(Client monClient) {
		boolean reponse = dao.createClient(monClient);
		return reponse; // retour de la réponse
	}

	public Client getClient(int id) {
		Client monClient = dao.getClient(id);
		return monClient;
	}

	public Client updateClient(Client monClient) {
		monClient = dao.updateClient(monClient);
		return monClient;
	}

	public boolean deleteClient(Client monClient) {
		boolean reponse = dao.deleteClient(monClient);
		return reponse; // retour de la réponse
	}

	public List<Client> getAllClient() {
		List<Client> listClient = dao.getAllClient();
		return listClient;
	}
}

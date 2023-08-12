package com.yiidein.yiidein.services;

import java.util.List;

import com.yiidein.yiidein.entities.Client;
import com.yiidein.yiidein.entities.Prestataire;
import com.yiidein.yiidein.entities.RendezVous;
import com.yiidein.yiidein.entities.Service;
import com.yiidein.yiidein.entities.Utilisateur;
import com.yiidein.yiidein.exceptions.ClientNotFoundException;
import com.yiidein.yiidein.exceptions.ClientOrPrestataireNotFoundException;
import com.yiidein.yiidein.exceptions.PrestataireNotFoundException;
import com.yiidein.yiidein.exceptions.RendezVousNotFoundException;

public interface YiideinServiceInt {
	
	Client saveClient(Client client);
	Service saveService(Service service);
	Prestataire savePrestataire(Long idService, String nom) throws ClientNotFoundException;
	RendezVous saveRendezVous(String idClient, String idPrestataire, String motif) throws ClientOrPrestataireNotFoundException;
	
	List<Client> clients();
	List<Prestataire> prestataires();
	List<RendezVous> rendezVous();
	List<Service> services();
	List<Utilisateur> utilisateurs();
	
	Client getClient(String idClient) throws ClientNotFoundException;
	Prestataire getPrestataire(String idPrestataire) throws PrestataireNotFoundException;
	RendezVous getRendezVous(Long idRendezVous) throws RendezVousNotFoundException;

}

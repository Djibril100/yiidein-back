package com.yiidein.yiidein.services;

import java.util.List;

import com.yiidein.yiidein.dtos.ClientDTO;



import com.yiidein.yiidein.entities.RendezVous;

import com.yiidein.yiidein.entities.Utilisateur;
import com.yiidein.yiidein.exceptions.ClientNotFoundException;
import com.yiidein.yiidein.exceptions.ClientOrPrestataireNotFoundException;

import com.yiidein.yiidein.exceptions.RendezVousNotFoundException;

public interface YiideinServiceClientInterface {
	
	ClientDTO saveClient(ClientDTO clientDTO);
	ClientDTO updateClient(ClientDTO clientDTO);
	void deleteClient(String idClient);
	
	RendezVous saveRendezVous(String idClient, String idPrestataire, String motif) throws ClientOrPrestataireNotFoundException;
	
	List<ClientDTO> clients();
	
	List<RendezVous> rendezVous();
	List<Utilisateur> utilisateurs();
	
	ClientDTO getClient(String idClient) throws ClientNotFoundException;
	
	RendezVous getRendezVous(Long idRendezVous) throws RendezVousNotFoundException;

}

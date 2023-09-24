package com.yiidein.yiidein.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiidein.yiidein.dtos.ClientDTO;

import com.yiidein.yiidein.entities.Client;
import com.yiidein.yiidein.entities.Prestataire;
import com.yiidein.yiidein.entities.RendezVous;

import com.yiidein.yiidein.entities.Utilisateur;
import com.yiidein.yiidein.enums.StatusDesRendezVous;
import com.yiidein.yiidein.exceptions.ClientNotFoundException;
import com.yiidein.yiidein.exceptions.ClientOrPrestataireNotFoundException;

import com.yiidein.yiidein.exceptions.RendezVousNotFoundException;
import com.yiidein.yiidein.mappers.YiideinMapperClient;
import com.yiidein.yiidein.repositories.ClientRepository;
import com.yiidein.yiidein.repositories.PrestataireRepository;
import com.yiidein.yiidein.repositories.RendezVousRepository;

import com.yiidein.yiidein.repositories.UtilisateurRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class YiideinServiceClient implements YiideinServiceClientInterface {
	
	private ClientRepository clientRep;
	private RendezVousRepository rendezVousRep;
	private UtilisateurRepository utilisateurRep;
	private PrestataireRepository prestataireRep;
	private YiideinMapperClient mapperClient;
	

	@Override
	public ClientDTO saveClient(ClientDTO clientDTO) {
		// TODO Auto-generated method stub
		log.info("Enregistrement d'un nouveau client");
		
		Client client = this.mapperClient.fromClientDTO(clientDTO);
		client.setId(UUID.randomUUID().toString());
		Client savedClient = clientRep.save(client);
		return this.mapperClient.fromClient(savedClient);
	}


	@Override
	public RendezVous saveRendezVous(String idClient, String idPrestataire, String motif) throws ClientOrPrestataireNotFoundException {
		// TODO Auto-generated method stub
		log.info("Enregistrement d'un rendezVous");
		Client client = clientRep.findById(idClient).orElse(null);
		Prestataire prestataire = prestataireRep.findById(idPrestataire).orElse(null);
		if(client==null || prestataire==null)
			throw new ClientOrPrestataireNotFoundException("Client ou Prestataire non trouves !");
		RendezVous rendezVous = new RendezVous();
		rendezVous.setMotif(motif);
		rendezVous.setDateDeDebut(new Date());
		rendezVous.setStatus(StatusDesRendezVous.CREE);
		rendezVous.setClient(client);
		rendezVous.setPrestataire(prestataire);
		rendezVous = rendezVousRep.save(rendezVous);
		rendezVous.setStatus(StatusDesRendezVous.EN_ATTENTE);
		return rendezVous;
	}

	@Override
	public List<ClientDTO> clients() {
		// TODO Auto-generated method stub
		
	  List<Client> clients = clientRep.findAll();
	  List<ClientDTO> clientsDTO = clients.stream().map(client->
			  this.mapperClient.fromClient(client)).collect(Collectors.toList());
	  
	  return clientsDTO;
	}

	@Override
	public List<RendezVous> rendezVous() {
		// TODO Auto-generated method stub
		return rendezVousRep.findAll();
	}

	

	@Override
	public List<Utilisateur> utilisateurs() {
		// TODO Auto-generated method stub
		return utilisateurRep.findAll();
	}

	@Override
	public ClientDTO getClient(String idClient) throws ClientNotFoundException {
		// TODO Auto-generated method stub
		Client client = clientRep.findById(idClient).orElseThrow(()->
					new ClientNotFoundException("Client non trouve !"));
		
		return this.mapperClient.fromClient(client);
	}



	@Override
	public RendezVous getRendezVous(Long idRendezVous) throws RendezVousNotFoundException {
		// TODO Auto-generated method stub
		RendezVous rendezVous = rendezVousRep.findById(idRendezVous).orElseThrow(()->
						new RendezVousNotFoundException("RendezVous non trouve !"));
		return rendezVous;
	}

	@Override
	public ClientDTO updateClient(ClientDTO clientDTO) {
		// TODO Auto-generated method stub
		log.info("Mise a jour d'un Client !");
		
		Client client = this.mapperClient.fromClientDTO(clientDTO);
		client.setId(UUID.randomUUID().toString());
		Client updatedClient = clientRep.save(client);
		return this.mapperClient.fromClient(updatedClient);
	}

	@Override
	public void deleteClient(String idClient) {
		// TODO Auto-generated method stub
		this.clientRep.deleteById(idClient);
		
	}

	

	

	
	
	

}

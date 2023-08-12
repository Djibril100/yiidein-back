package com.yiidein.yiidein.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.yiidein.yiidein.entities.Client;
import com.yiidein.yiidein.entities.Prestataire;
import com.yiidein.yiidein.entities.RendezVous;
import com.yiidein.yiidein.entities.Service;
import com.yiidein.yiidein.entities.Utilisateur;
import com.yiidein.yiidein.enums.StatusDesRendezVous;
import com.yiidein.yiidein.exceptions.ClientNotFoundException;
import com.yiidein.yiidein.exceptions.ClientOrPrestataireNotFoundException;
import com.yiidein.yiidein.exceptions.PrestataireNotFoundException;
import com.yiidein.yiidein.exceptions.RendezVousNotFoundException;
import com.yiidein.yiidein.repositories.ClientRepository;
import com.yiidein.yiidein.repositories.PrestataireRepository;
import com.yiidein.yiidein.repositories.RendezVousRepository;
import com.yiidein.yiidein.repositories.ServiceRepository;
import com.yiidein.yiidein.repositories.UtilisateurRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@org.springframework.stereotype.Service
@Transactional
@AllArgsConstructor
@Slf4j
public class YiideinService implements YiideinServiceInt {
	
	private ClientRepository clientRep;
	private RendezVousRepository rendezVousRep;
	private PrestataireRepository prestataireRep;
	private ServiceRepository serviceRep;
	private UtilisateurRepository utilisateurRep;

	@Override
	public Client saveClient(Client client) {
		// TODO Auto-generated method stub
		log.info("Enregistrement d'un nouveau client");
		client.setId(UUID.randomUUID().toString());
		return clientRep.save(client);
	}

	@Override
	public Service saveService(Service service) {
		// TODO Auto-generated method stub
		log.info("Enregistrement d'un nouveau service");
		return serviceRep.save(service);
	}

	@Override
	public Prestataire savePrestataire(Long idService, String nom) throws ClientNotFoundException {
		// TODO Auto-generated method stub
		log.info("Enregistrement d'un prestataire");
		Service service = serviceRep.findById(idService).orElse(null);
		
		if(service==null)
			throw new ClientNotFoundException("Client non trouvé !");
		Prestataire prestataire = new Prestataire();
		prestataire.setId(UUID.randomUUID().toString());
		prestataire.setNom(nom);
		prestataire.setService(service);
		return prestataireRep.save(prestataire);
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
	public List<Client> clients() {
		// TODO Auto-generated method stub
		return clientRep.findAll();
	}

	@Override
	public List<Prestataire> prestataires() {
		// TODO Auto-generated method stub
		return prestataireRep.findAll();
	}

	@Override
	public List<RendezVous> rendezVous() {
		// TODO Auto-generated method stub
		return rendezVousRep.findAll();
	}

	@Override
	public List<Service> services() {
		// TODO Auto-generated method stub
		return serviceRep.findAll();
	}

	@Override
	public List<Utilisateur> utilisateurs() {
		// TODO Auto-generated method stub
		return utilisateurRep.findAll();
	}

	@Override
	public Client getClient(String idClient) throws ClientNotFoundException {
		// TODO Auto-generated method stub
		Client client = clientRep.findById(idClient).orElseThrow(()->
					new ClientNotFoundException("Client non trouve !"));
		return client;
	}

	@Override
	public Prestataire getPrestataire(String idPrestataire) throws PrestataireNotFoundException {
		// TODO Auto-generated method stub
		Prestataire prestataire = prestataireRep.findById(idPrestataire).orElseThrow(()->
					new PrestataireNotFoundException("Prestataire non trouve !"));
		return prestataire;
	}

	@Override
	public RendezVous getRendezVous(Long idRendezVous) throws RendezVousNotFoundException {
		// TODO Auto-generated method stub
		RendezVous rendezVous = rendezVousRep.findById(idRendezVous).orElseThrow(()->
						new RendezVousNotFoundException("RendezVous non trouve !"));
		return rendezVous;
	}
	
	

}

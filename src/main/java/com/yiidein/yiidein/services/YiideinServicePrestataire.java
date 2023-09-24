package com.yiidein.yiidein.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yiidein.yiidein.dtos.PrestataireDTO;
import com.yiidein.yiidein.entities.Prestataire;
import com.yiidein.yiidein.entities.Service_;
import com.yiidein.yiidein.exceptions.PrestataireNotFoundException;
import com.yiidein.yiidein.exceptions.ServiceNotFoundException;
import com.yiidein.yiidein.mappers.YiideinMapperPrestataire;
import com.yiidein.yiidein.repositories.PrestataireRepository;
import com.yiidein.yiidein.repositories.ServiceRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class YiideinServicePrestataire implements YiideinServicePrestataireInterface {
	
	private PrestataireRepository prestataireRep;
	private ServiceRepository serviceRep;
	private YiideinMapperPrestataire mapperPrestataire;

	@Override
	public PrestataireDTO savePrestataire(Long idService, String nom) throws ServiceNotFoundException {
		// TODO Auto-generated method stub
		log.info("Enregistrement d'un nouveau prestataire");
		Service_ service = serviceRep.findById(idService).orElse(null);
		
		if(service==null)
			throw new ServiceNotFoundException("Service non trouve !");
		
		Prestataire prestataire = new Prestataire();
		prestataire.setId(UUID.randomUUID().toString());
		prestataire.setNom(nom);
		prestataire.setService(service);
		Prestataire savedPrestataire = prestataireRep.save(prestataire);
		
		return this.mapperPrestataire.fromPrestataire(savedPrestataire);
	}

	@Override
	public PrestataireDTO updatePrestataire(Long idService, String nom, 
					PrestataireDTO prestataireDTO) throws ServiceNotFoundException {
		// TODO Auto-generated method stub
		log.info("Mise a jour d'un prestataire");
		Service_ service = serviceRep.findById(idService).orElse(null);
		
		if(service==null)
			throw new ServiceNotFoundException("Service non trouve !");
		
		Prestataire prestataire = new Prestataire();
		prestataire.setId(UUID.randomUUID().toString());
		prestataire.setNom(nom);
		prestataire.setService(service);
		Prestataire updatedPrestataire = prestataireRep.save(prestataire);
		prestataireDTO = this.mapperPrestataire.fromPrestataire(updatedPrestataire);
		
		return prestataireDTO;
	}

	@Override
	public void deletePrestataire(String idPrestataire) {
		// TODO Auto-generated method stub
		this.prestataireRep.deleteById(idPrestataire);
		
	}

	@Override
	public List<PrestataireDTO> prestataires() {
		// TODO Auto-generated method stub
		
		List<Prestataire> prestataires = prestataireRep.findAll();
		
		List<PrestataireDTO> prestatairesDTO = prestataires.stream().map(pres->
				this.mapperPrestataire.fromPrestataire(pres)).collect(Collectors.toList());
		
		return prestatairesDTO;
	}

	@Override
	public PrestataireDTO getPrestataire(String idPrestataire) throws PrestataireNotFoundException {
		// TODO Auto-generated method stub
		Prestataire prestataire = prestataireRep.findById(idPrestataire).orElseThrow(()->
		new PrestataireNotFoundException("Prestataire non trouve !"));
		
		return this.mapperPrestataire.fromPrestataire(prestataire);
	}

	@Override
	public List<PrestataireDTO> getprestatairesByService(Long idService) {
		// TODO Auto-generated method stub
		List<Prestataire> prestataires = prestataireRep.findByServiceId(idService);
		List<PrestataireDTO> prestatairesDTO = prestataires.stream().map(pres->
			this.mapperPrestataire.fromPrestataire(pres)).collect(Collectors.toList());
		
		return prestatairesDTO;
	}

}

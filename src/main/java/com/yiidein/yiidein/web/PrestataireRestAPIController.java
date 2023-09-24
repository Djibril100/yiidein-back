package com.yiidein.yiidein.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yiidein.yiidein.dtos.PrestataireDTO;
import com.yiidein.yiidein.exceptions.PrestataireNotFoundException;
import com.yiidein.yiidein.exceptions.ServiceNotFoundException;
import com.yiidein.yiidein.services.YiideinServicePrestataire;

@RestController
public class PrestataireRestAPIController {

	@Autowired
	private YiideinServicePrestataire service;
	
	@PostMapping("/prestataires")
	public PrestataireDTO savePrestataire(Long idService, @RequestBody String nom) throws ServiceNotFoundException {
		return this.service.savePrestataire(idService, nom);
	}
	
	@PutMapping("/prestataires/{id}")
	public PrestataireDTO updatePrestataire(@PathVariable(name="id") String idPrestataire, 
			Long idService, String nom, 
			PrestataireDTO prestataireDTO) throws ServiceNotFoundException {
		
		prestataireDTO.setId(idPrestataire);
		return this.service.updatePrestataire(idService, nom, prestataireDTO);
	}
	
	@DeleteMapping("/prestataires")
	public void deletePrestataire(String idPrestataire) {
		this.service.deletePrestataire(idPrestataire);
	}
	
	@GetMapping("/prestataires")
	public List<PrestataireDTO> prestataires() {
		return this.service.prestataires();
	}
	
	@GetMapping("/prestataires/{idPrestataire}")
	public PrestataireDTO getPrestataire(@PathVariable String idPrestataire) throws PrestataireNotFoundException {
		return this.service.getPrestataire(idPrestataire);
	}
	
	//Affichage de l'ens des prestataires d'un Service
	@GetMapping("/services/{idService}/prestataires")
	public List<PrestataireDTO> getprestatairesByService(@PathVariable Long idService){
		return this.service.getprestatairesByService(idService);
	}
}

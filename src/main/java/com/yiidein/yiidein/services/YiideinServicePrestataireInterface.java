package com.yiidein.yiidein.services;

import java.util.List;

import com.yiidein.yiidein.dtos.PrestataireDTO;
import com.yiidein.yiidein.exceptions.PrestataireNotFoundException;
import com.yiidein.yiidein.exceptions.ServiceNotFoundException;

public interface YiideinServicePrestataireInterface {

	PrestataireDTO savePrestataire(Long idService, String nom) throws ServiceNotFoundException;
	PrestataireDTO updatePrestataire(Long idService, String nom, PrestataireDTO prestataireDTO) throws ServiceNotFoundException;
	void deletePrestataire(String idPrestataire);
	
	List<PrestataireDTO> prestataires();
	PrestataireDTO getPrestataire(String idPrestataire) throws PrestataireNotFoundException;
	List<PrestataireDTO> getprestatairesByService(Long idService);
}

package com.yiidein.yiidein.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.yiidein.yiidein.dtos.PrestataireDTO;
import com.yiidein.yiidein.entities.Prestataire;

@Service
public class YiideinMapperPrestataire {

	public PrestataireDTO fromPrestataire(Prestataire prestataire) {
		
		PrestataireDTO prestataireDTO = new PrestataireDTO();
		BeanUtils.copyProperties(prestataire, prestataireDTO);
		prestataireDTO.setServiceDTO(YiideinMapperService.fromService(prestataire.getService()));
		
		return prestataireDTO;
	}
	
	public Prestataire fromPrestataireDTO(PrestataireDTO prestataireDTO) {
		
		Prestataire prestataire = new Prestataire();
		BeanUtils.copyProperties(prestataireDTO, prestataire);
		prestataire.setService(YiideinMapperService.fromServiceDTO(prestataireDTO.getServiceDTO()));
		
		return prestataire;
	}
}

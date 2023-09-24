package com.yiidein.yiidein.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yiidein.yiidein.dtos.ServiceDTO;
import com.yiidein.yiidein.entities.Service_;
import com.yiidein.yiidein.mappers.YiideinMapperService;
import com.yiidein.yiidein.repositories.ServiceRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class YiideinService implements YiideinServiceInterface {

	private ServiceRepository serviceRep;
	private YiideinMapperService mapperService;
	
	@Override
	public ServiceDTO saveService(ServiceDTO serviceDTO) {
		// TODO Auto-generated method stub
		log.info("Enregistrement d'un nouveau service");
		
		Service_ service = this.mapperService.fromServiceDTO(serviceDTO);
		Service_ savedService = this.serviceRep.save(service);
		return this.mapperService.fromService(savedService);
	}
	
	@Override
	public ServiceDTO updateService(ServiceDTO serviceDTO) {
		// TODO Auto-generated method stub
		log.info("Mise a jour d'un service");
		
		Service_ service = this.mapperService.fromServiceDTO(serviceDTO);
		Service_ updatedService = this.serviceRep.save(service);
		return this.mapperService.fromService(updatedService);
	}
	
	@Override
	public void deleteService(Long idService) {
		// TODO Auto-generated method stub
		this.serviceRep.deleteById(idService);
		
	}
	
	@Override
	public List<ServiceDTO> services() {
		// TODO Auto-generated method stub
		List<Service_> services = serviceRep.findAll();
		List<ServiceDTO> servicesDTO = services.stream().map(service->
				this.mapperService.fromService(service)).collect(Collectors.toList());
		
		return servicesDTO;
	}
}

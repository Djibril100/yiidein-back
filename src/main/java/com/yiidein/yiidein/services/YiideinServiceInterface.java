package com.yiidein.yiidein.services;

import java.util.List;

import com.yiidein.yiidein.dtos.ServiceDTO;

public interface YiideinServiceInterface {

	ServiceDTO saveService(ServiceDTO service);
	ServiceDTO updateService(ServiceDTO serviceDTO);
	void deleteService(Long idService);
	
	List<ServiceDTO> services();
}

package com.yiidein.yiidein.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.yiidein.yiidein.dtos.ServiceDTO;
import com.yiidein.yiidein.entities.Service_;

@Service
public class YiideinMapperService {

	public static ServiceDTO fromService(Service_ service) {
		
		ServiceDTO serviceDTO = new ServiceDTO();
		BeanUtils.copyProperties(service, serviceDTO);
		return serviceDTO;
	}
	
	public static Service_ fromServiceDTO(ServiceDTO serviceDTO) {
		
		Service_ service = new Service_();
		BeanUtils.copyProperties(serviceDTO, service);
		
		return service;
	}
}

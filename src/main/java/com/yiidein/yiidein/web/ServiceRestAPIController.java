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

import com.yiidein.yiidein.dtos.ServiceDTO;
import com.yiidein.yiidein.services.YiideinService;

@RestController
public class ServiceRestAPIController {

	@Autowired
	private YiideinService service;
	
	@PostMapping("/services")
	public ServiceDTO saveService(@RequestBody ServiceDTO serviceDTO) {
		return this.service.saveService(serviceDTO);
	}
	
	@PutMapping("/services/{id}")
	public ServiceDTO updateService(@PathVariable(name="id") Long idService, 
										ServiceDTO serviceDTO) {
		serviceDTO.setId(idService);
		return this.service.updateService(serviceDTO);
	}
	
	@DeleteMapping("/services/{id}")
	public void deleteService(@PathVariable(name="id") Long idService) {
		this.service.deleteService(idService);
	}
	
	@GetMapping("/services")
	public List<ServiceDTO> services() {
		return this.service.services();
	}
	
	
}

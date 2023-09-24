package com.yiidein.yiidein.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yiidein.yiidein.dtos.ClientDTO;
import com.yiidein.yiidein.exceptions.ClientNotFoundException;
import com.yiidein.yiidein.services.YiideinServiceClient;

@RestController
public class ClientRestAPIController {
	
	private YiideinServiceClient service;
	
	//Injection des dependance <==> @Autowired | @AllArgsConstructor
	public ClientRestAPIController(YiideinServiceClient service) {
		this.service = service;
	}
	
	//Affichage de tous les Clients
	@GetMapping("/clients")
	public List<ClientDTO> clients(){
		
		return this.service.clients();
	}
	
	//Affichage d'un seul client connaissant son id
	@GetMapping("/clients/{id}")
	public ClientDTO getClient(@PathVariable(name = "id") String idClient) throws ClientNotFoundException {
		
		return this.service.getClient(idClient);
	}
	
	//Ajout d'un client
	@PostMapping("/clients")
	public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
		return this.service.saveClient(clientDTO);
	}
	
	//Mise à jour d'un Client
	@PutMapping("/clients/{id}")
	public ClientDTO updateClient(@PathVariable(name ="id") String idClient, 
									ClientDTO clientDTO) {
		clientDTO.setId(idClient);
		return this.service.updateClient(clientDTO);
	}
	
	//Suppression d'un Client
	@DeleteMapping("/clients/{idClient}")
	public void deleteClient(@PathVariable String idClient) {
		
		this.service.deleteClient(idClient);
	}
	
	
	

}

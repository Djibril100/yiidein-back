package com.yiidein.yiidein.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.yiidein.yiidein.dtos.ClientDTO;
import com.yiidein.yiidein.entities.Client;

@Service
public class YiideinMapperClient {
	
	public ClientDTO fromClient(Client client) {
		
		ClientDTO clientDTO = new ClientDTO();
		BeanUtils.copyProperties(client, clientDTO);
		return clientDTO;
	}
	
	public Client fromClientDTO(ClientDTO clientDTO) {
		
		Client client = new Client();
		BeanUtils.copyProperties(clientDTO, client);
		return client;
	}

}

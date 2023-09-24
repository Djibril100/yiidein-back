package com.yiidein.yiidein.dtos;

import com.yiidein.yiidein.entities.Service_;

import lombok.Data;

@Data
public class PrestataireDTO {

	private String id;
	private String nom;
	private ServiceDTO serviceDTO;
}

package com.yiidein.yiidein;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yiidein.yiidein.dtos.ClientDTO;
import com.yiidein.yiidein.dtos.ServiceDTO;
import com.yiidein.yiidein.entities.Client;
import com.yiidein.yiidein.entities.Prestataire;
import com.yiidein.yiidein.entities.Service_;
import com.yiidein.yiidein.exceptions.ClientNotFoundException;
import com.yiidein.yiidein.exceptions.ClientOrPrestataireNotFoundException;
import com.yiidein.yiidein.exceptions.ServiceNotFoundException;
import com.yiidein.yiidein.services.YiideinServiceClientInterface;
import com.yiidein.yiidein.services.YiideinServiceInterface;
import com.yiidein.yiidein.services.YiideinServicePrestataireInterface;

@SpringBootApplication
public class YiideinApplication {

	public static void main(String[] args) {
		SpringApplication.run(YiideinApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(YiideinServiceClientInterface yiideinClient, 
							YiideinServiceInterface yiideinService,
							YiideinServicePrestataireInterface yiideinPrestataire) {
		
		return args->{
			String[] prenoms = {"Djibril", "Souleymane", "Yaya", "Oumar", "Ibrahim", "Mohamed", "Aly"};
			String[] phones = {"0658972155", "0733456786", "0678998765", "0787654312", "0678560989", "0233456765", "0212322145"};
			String[] adresses = {"Conakry", "Rennes", "Paris", "Mamou", "Dalaba", "Lyon", "Kindia"};
			String[] services = {"Campus France", "Capago", "ONABE", "Documents Biometriques", "CHU Donka"};
			String[] prestataires = {"Campus France Conakry", "Campus France Mamou", "Passeport Agence Coleah", "Passeport Agence Nongo", "Passeport Agence Matoto", "Carte d'identite", "ONABE Bourse Maroc", "ONABE Bourse Russie", "ONABE Bourse Turquie", "ONABE Bourse Chine", "CHU Donka Radiologie"};
			String[] genres = {"Masculin", "Feminin"};
			String[] motifs = {"Depot", "Retrait"};
			Stream.of("Barry", "Diallo", "Soumah", "Camara", "Bangoura", "Bah", "Sylla").forEach(nom->{
				ClientDTO clientDTO = new ClientDTO();
				clientDTO.setNom(nom);
				String prenom = prenoms[new Random().nextInt(prenoms.length)];
				clientDTO.setPrenom(prenom);
				clientDTO.setEmail(prenom+"."+nom+"@gmail.com");
				String telephone = phones[new Random().nextInt(phones.length)];
				clientDTO.setTelephone(telephone);
				String adresse = adresses[new Random().nextInt(adresses.length)];
				clientDTO.setAdresse(adresse);
				String genre = genres[new Random().nextInt(genres.length)];
				clientDTO.setGenre(genre);
				yiideinClient.saveClient(clientDTO);
			});
			Stream.of("Campus France", "Capago", "ONABE", "Documents Biometriques", "CHU Donka")
						.forEach(nom->{
						ServiceDTO serviceDTO = new ServiceDTO();
						serviceDTO.setNom(nom);
						serviceDTO.setEmail(nom+"@yahoo.fr");
						String adresse = adresses[new Random().nextInt(adresses.length)];
						serviceDTO.setAdresse(adresse);
						String telephone = phones[new Random().nextInt(phones.length)];
						serviceDTO.setTelephone(telephone);
						yiideinService.saveService(serviceDTO);
					});
			yiideinService.services().forEach(service->{
				for (int i = 0; i < 10; i++) {
					String nom = prestataires[new Random().nextInt(prestataires.length)];
					try {
						yiideinPrestataire.savePrestataire(service.getId(), nom);
					} catch (ServiceNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			yiideinPrestataire.prestataires().forEach(prestataire->{
				List<ClientDTO> clients = yiideinClient.clients();
				for(ClientDTO clientDTO: clients) {
					String motif = motifs[new Random().nextInt(motifs.length)];
					try {
						yiideinClient.saveRendezVous(clientDTO.getId(), prestataire.getId(), motif);
					} catch (ClientOrPrestataireNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
		};
	}

}

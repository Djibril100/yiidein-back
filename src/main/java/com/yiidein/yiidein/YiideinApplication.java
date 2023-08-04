package com.yiidein.yiidein;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yiidein.yiidein.entities.Client;
import com.yiidein.yiidein.entities.Prestataire;
import com.yiidein.yiidein.entities.Service;
import com.yiidein.yiidein.exceptions.ClientNotFoundException;
import com.yiidein.yiidein.exceptions.ClientOrPrestataireNotFoundException;
import com.yiidein.yiidein.services.YiideinServiceInt;

@SpringBootApplication
public class YiideinApplication {

	public static void main(String[] args) {
		SpringApplication.run(YiideinApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(YiideinServiceInt yiidein) {
		
		return args->{
			String[] prenoms = {"Djibril", "Souleymane", "Yaya", "Oumar", "Ibrahim", "Mohamed", "Aly"};
			String[] phones = {"0658972155", "0733456786", "0678998765", "0787654312", "0678560989", "0233456765", "0212322145"};
			String[] adresses = {"Conakry", "Rennes", "Paris", "Mamou", "Dalaba", "Lyon", "Kindia"};
			String[] services = {"Campus France", "Capago", "ONABE", "Documents Biometriques", "CHU Donka"};
			String[] prestataires = {"Campus France Conakry", "Campus France Mamou", "Passeport Agence Coleah", "Passeport Agence Nongo", "Passeport Agence Matoto", "Carte d'identite", "ONABE Bourse Maroc", "ONABE Bourse Russie", "ONABE Bourse Turquie", "ONABE Bourse Chine", "CHU Donka Radiologie"};
			String[] genres = {"Masculin", "Feminin"};
			String[] motifs = {"Depot", "Retrait"};
			Stream.of("Barry", "Diallo", "Soumah", "Camara", "Bangoura", "Bah", "Sylla").forEach(nom->{
				Client client = new Client();
				client.setNom(nom);
				String prenom = prenoms[new Random().nextInt(prenoms.length)];
				client.setPrenom(prenom);
				client.setEmail(prenom+"."+nom+"@gmail.com");
				String telephone = phones[new Random().nextInt(phones.length)];
				client.setTelephone(telephone);
				String adresse = adresses[new Random().nextInt(adresses.length)];
				client.setAdresse(adresse);
				String genre = genres[new Random().nextInt(genres.length)];
				client.setGenre(genre);
				yiidein.saveClient(client);
			});
			Stream.of("Campus France", "Capago", "ONABE", "Documents Biometriques", "CHU Donka")
						.forEach(nom->{
						Service service = new Service();
						service.setNom(nom);
						service.setEmail(nom+"@yahoo.fr");
						String adresse = adresses[new Random().nextInt(adresses.length)];
						service.setAdresse(adresse);
						String telephone = phones[new Random().nextInt(phones.length)];
						service.setTelephone(telephone);
						yiidein.saveService(service);
					});
			yiidein.services().forEach(service->{
				for (int i = 0; i < 10; i++) {
					String nom = prestataires[new Random().nextInt(prestataires.length)];
					try {
						yiidein.savePrestataire(service.getId(), nom);
					} catch (ClientNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			yiidein.prestataires().forEach(prestataire->{
				List<Client> clients = yiidein.clients();
				for(Client client: clients) {
					String motif = motifs[new Random().nextInt(motifs.length)];
					try {
						yiidein.saveRendezVous(client.getId(), prestataire.getId(), motif);
					} catch (ClientOrPrestataireNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
		};
	}

}

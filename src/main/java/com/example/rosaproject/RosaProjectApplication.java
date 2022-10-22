package com.example.rosaproject;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Echange;
import com.example.rosaproject.controller.entity.Entreprise;
import com.example.rosaproject.controller.entity.User;
import com.example.rosaproject.repository.ContactRepository;
import com.example.rosaproject.repository.EchangeRepository;
import com.example.rosaproject.repository.EntrepriseRepository;
import com.example.rosaproject.repository.UserRepository;
import com.example.rosaproject.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class RosaProjectApplication implements CommandLineRunner {

    StorageService storageService;

    UserRepository userRepository;

    EntrepriseRepository entrepriseRepository;

    ContactRepository contactRepository;

    EchangeRepository echangeRepository;

    public RosaProjectApplication(StorageService storageService, UserRepository userRepository, EntrepriseRepository entrepriseRepository, ContactRepository contactRepository, EchangeRepository echangeRepository) {
        this.storageService = storageService;
        this.userRepository = userRepository;
        this.entrepriseRepository = entrepriseRepository;
        this.contactRepository = contactRepository;
        this.echangeRepository = echangeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RosaProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("COMMAND LINE RUNNER");
        storageService.init();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String mdp = encoder.encode("0123");
        User user = new User(mdp,"http://localhost:8080/images/"+"personne1","math.mariot@gmail.com", (short) 1);
        userRepository.save(user);
        Entreprise entreprise = new Entreprise("www.monlogo.com","La redoute","00123659895","Lentreprise@entreprise.com","0750828587","Lentreprise@entreprise.com","www.monsite.com","16 rue de la chambre","batiment 3","Nantes","78000","Vente de vêtement", LocalDate.now(),user);
        entrepriseRepository.save(entreprise);

        Contact contact = new Contact("paul@entreprise.com","Paul","Ohrel","0750998872","0250998874",LocalDate.now(),false,user,entreprise,"http://localhost:8080/images/default.jpg","En cours de prospection");

        Contact contact1 = new Contact("Garitou@entreprise.com","Garitou","Labrou","0750995588","0250994403",LocalDate.now(),false,user,entreprise,"http://localhost:8080/images/default.jpg","A prospecter");
        contactRepository.save(contact);

        contactRepository.save(contact1);

        Echange echange=new Echange(LocalDateTime.now(),"En cours","Premier contact effectué .Me semble très sympatique et ouvert a la discussion",user,contact);

        Echange echange1=new Echange(LocalDateTime.now(),"En cours","Second contact prometteur nous devons finalisé",user,contact);

        echangeRepository.save(echange);

        echangeRepository.save(echange1);

    }


}

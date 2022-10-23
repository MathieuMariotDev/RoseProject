package com.example.rosaproject;

import com.example.rosaproject.controller.entity.*;
import com.example.rosaproject.model.Status;
import com.example.rosaproject.repository.*;
import com.example.rosaproject.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class RosaProjectApplication implements CommandLineRunner {

    StorageService storageService;

    UserRepository userRepository;

    EntrepriseRepository entrepriseRepository;

    ContactRepository contactRepository;

    EchangeRepository echangeRepository;

    EventRepository eventRepository;

    public RosaProjectApplication(StorageService storageService, UserRepository userRepository, EntrepriseRepository entrepriseRepository, ContactRepository contactRepository, EchangeRepository echangeRepository, EventRepository eventRepository) {
        this.storageService = storageService;
        this.userRepository = userRepository;
        this.entrepriseRepository = entrepriseRepository;
        this.contactRepository = contactRepository;
        this.echangeRepository = echangeRepository;
        this.eventRepository = eventRepository;
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
        Entreprise entreprise = new Entreprise("www.monlogo.com","La redoute","00123659895","Lentreprise@entreprise.com","+33750828587","+33233657895","www.monsite.com","100 rue de Brest","batiment 3","Rennes","35000","Vente de vêtement", LocalDate.now(),user);
        entrepriseRepository.save(entreprise);

        Contact contact = new Contact("paul@entreprise.com","Paul","Ohrel","0750998872","0250998874",LocalDate.now(),false,user,entreprise,"http://localhost:8080/images/default.jpg",Status.cours.getStatusName());

        Contact contact1 = new Contact("Garitou@entreprise.com","Garitou","Labrou","0750995588","0250994403",LocalDate.now(),false,user,entreprise,"http://localhost:8080/images/default.jpg", Status.Aucun.getStatusName());
        contactRepository.save(contact);

        contactRepository.save(contact1);

        Echange echange=new Echange(LocalDateTime.now(),"En cours","Premier contact effectué .Me semble très sympatique et ouvert a la discussion",user,contact,"Prospecting"+contact.getEntreprise().getName());

        Echange echange1=new Echange(LocalDateTime.now(),"En cours","Second contact prometteur nous devons finalisé",user,contact,"Prospecting"+contact.getEntreprise().getName());

        echangeRepository.save(echange);

        echangeRepository.save(echange1);

        Event event1 = new Event("event1","description1","Adresse 1", LocalDateTime.parse("2022-10-13T01:00:00"), LocalDateTime.parse("2022-10-10T02:00:00"), user,contact);
        Event event2 = new Event("event2","description2","Adresse 2", LocalDateTime.parse("2022-10-13T02:00:00"), LocalDateTime.parse("2022-10-13T03:00:00"), user,contact);
        Event event3 = new Event("event3","description3","Adresse 3", LocalDateTime.parse("2022-10-13T03:00:00"), LocalDateTime.parse("2022-10-13T05:00:00"), user,contact);
        Event event4 = new Event("event4","description4","Adresse 4", LocalDateTime.parse("2022-10-13T04:00:00"), LocalDateTime.parse("2022-10-13T06:00:00"), user,contact1);

        eventRepository.save(event1);
        eventRepository.save(event2);
        eventRepository.save(event3);
        eventRepository.save(event4);


    }


}

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

        User user = new User(mdp,"http://localhost:8080/images/user.jpg","math.mariot@gmail.com", "ROLE_USER");
        User rosa = new User(mdp,"http://localhost:8080/images/photo-mmerosa.jpg","rosa@worktogether.fr", "ROLE_ADMIN");

        userRepository.save(user);
        userRepository.save(rosa);

        Entreprise entreprise = new Entreprise("http://localhost:8080/images/La-Redoute-logo.png","La Redoute","00123659895","Lentreprise@entreprise.com","+33750828587","+33233657895","www.monsite.com","100 rue de Brest","batiment 3","Rennes","35000","Vente de vêtement", LocalDate.now(),rosa);
        Entreprise entreprise2 = new Entreprise("http://localhost:8080/images/entreprise-materiel-info.png","My Computer","00123612031","Lentreprise2@entreprise.com","+33750828587","+33233657823","www.monsite.com","10 rue de Brest","batiment C","Rennes","35000","Vente de matériel informatique", LocalDate.now(),rosa);
        Entreprise entreprise3 = new Entreprise("http://localhost:8080/images/capgemini.jpg","Capgemini","00123612031","capgemini@entreprise.com","+33750828599","+33233657756","www.capgemini.com","6 rue Nathalie Lemel","batiment Berlingot","Nantes","44000","ESN", LocalDate.now(),rosa);

        entrepriseRepository.save(entreprise);
        entrepriseRepository.save(entreprise2);
        entrepriseRepository.save(entreprise3);

        Contact contact = new Contact("paul@entreprise.com","Paul","Ohrel","0750998872","0250998874",LocalDate.now(),false,rosa,entreprise,"http://localhost:8080/images/contact.jpg",Status.cours.getStatusName());
        Contact contact1 = new Contact("Garitou@entreprise.com","Garitou","Labrou","0750995588","0250994403",LocalDate.now(),true,rosa,entreprise,"http://localhost:8080/images/contact2.jpg", Status.Aucun.getStatusName());
        Contact contact2 = new Contact("rose@entreprise.com","Rose","Christelle","0750999987","0250956478",LocalDate.now(),true,rosa,entreprise2,"http://localhost:8080/images/contact3.jpg", Status.Aucun.getStatusName());
        Contact contact3 = new Contact("rose@entreprise.com","Garden","Louise","0650999987","0225636478",LocalDate.now(),false,rosa,entreprise2,"http://localhost:8080/images/contact4.jpg", Status.Aucun.getStatusName());


        contactRepository.save(contact);
        contactRepository.save(contact1);
        contactRepository.save(contact2);
        contactRepository.save(contact3);

        Echange echange = new Echange(LocalDateTime.now(),"En cours","Premier contact effectué .Me semble très sympatique et ouvert a la discussion",rosa,contact,"Prospecting"+contact.getEntreprise().getName());
        Echange echange1 = new Echange(LocalDateTime.now(),"En cours","Second contact prometteur nous devons finalisé",rosa,contact,"Prospecting"+contact.getEntreprise().getName());
        Echange echange2 = new Echange(LocalDateTime.now(),"En cours","Finalisation de la prospection",rosa,contact,"Prospecting"+contact.getEntreprise().getName());
        Echange echange3 = new Echange(LocalDateTime.now(),"En cours","Je rencontre Mme Garden la semaine prochaine",rosa,contact3,"Prospecting"+contact.getEntreprise().getName());
        Echange echange4 = new Echange(LocalDateTime.now(),"En cours","Premier contact OK, à rappeler début semaine prochaine",rosa,contact3,"Prospecting"+contact.getEntreprise().getName());
        Echange echange5 = new Echange(LocalDateTime.now(),"En cours","Devis à réaliser",rosa,contact3,"Prospecting"+contact.getEntreprise().getName());


        echangeRepository.save(echange);
        echangeRepository.save(echange1);
        echangeRepository.save(echange2);
        echangeRepository.save(echange3);
        echangeRepository.save(echange4);
        echangeRepository.save(echange5);

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

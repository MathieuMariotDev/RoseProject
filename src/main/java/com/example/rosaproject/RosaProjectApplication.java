package com.example.rosaproject;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Echange;
import com.example.rosaproject.controller.entity.Entreprise;
import com.example.rosaproject.controller.entity.User;
import com.example.rosaproject.model.Status;
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

        User user = new User(mdp,"http://localhost:8080/images/user.jpg","math.mariot@gmail.com", (short) 1);
        User user1 = new User(mdp,"http://localhost:8080/images/photo-mmerosa.jpg","rosa@worktogether.fr", (short) 1);

        userRepository.save(user);
        userRepository.save(user1);

        Entreprise entreprise = new Entreprise("http://localhost:8080/images/La-Redoute-logo.png","La Redoute","00123659895","Lentreprise@entreprise.com","+33750828587","+33233657895","www.monsite.com","100 rue de Brest","batiment 3","Rennes","35000","Vente de vêtement", LocalDate.now(),user);
        Entreprise entreprise2 = new Entreprise("http://localhost:8080/images/entreprise-materiel-info.png","My Computer","00123612031","Lentreprise2@entreprise.com","+33750828587","+33233657823","www.monsite.com","10 rue de Brest","batiment C","Rennes","35000","Vente de matériel informatique", LocalDate.now(),user);
        Entreprise entreprise3 = new Entreprise("http://localhost:8080/images/capgemini.jpg","Capgemini","00123612031","capgemini@entreprise.com","+33750828599","+33233657756","www.capgemini.com","6 rue Nathalie Lemel","batiment Berlingot","Nantes","44000","ESN", LocalDate.now(),user);

        entrepriseRepository.save(entreprise);
        entrepriseRepository.save(entreprise2);
        entrepriseRepository.save(entreprise3);

        Contact contact = new Contact("paul@entreprise.com","Paul","Ohrel","0750998872","0250998874",LocalDate.now(),false,user,entreprise,"http://localhost:8080/images/default.jpg",Status.cours.getStatusName());
        Contact contact1 = new Contact("Garitou@entreprise.com","Garitou","Labrou","0750995588","0250994403",LocalDate.now(),false,user,entreprise,"http://localhost:8080/images/default.jpg", Status.Aucun.getStatusName());
        Contact contact2 = new Contact("rose@entreprise.com","Rose","Christelle","0750999987","0250956478",LocalDate.now(),true,user,entreprise2,"http://localhost:8080/images/default.jpg", Status.Aucun.getStatusName());

        contactRepository.save(contact);
        contactRepository.save(contact1);
        contactRepository.save(contact2);

        Echange echange = new Echange(LocalDateTime.now(),"En cours","Premier contact effectué .Me semble très sympatique et ouvert a la discussion",user,contact,"Prospecting"+contact.getEntreprise().getName());
        Echange echange1 = new Echange(LocalDateTime.now(),"En cours","Second contact prometteur nous devons finalisé",user,contact,"Prospecting"+contact.getEntreprise().getName());
        Echange echange2 = new Echange(LocalDateTime.now(),"En cours","Finalisation de la prospection",user,contact,"Prospecting"+contact.getEntreprise().getName());

        echangeRepository.save(echange);
        echangeRepository.save(echange1);
        echangeRepository.save(echange2);

    }


}

package com.example.rosaproject.service;

import com.example.rosaproject.controller.dto.CreateContactDto;
import com.example.rosaproject.controller.dto.SearchDto;
import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Echange;
import com.example.rosaproject.model.Status;
import com.example.rosaproject.repository.ContactRepository;
import com.example.rosaproject.repository.EchangeRepository;
import com.example.rosaproject.security.CustomUserDetails;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ContactService {

    ContactRepository contactRepository;

    StorageService storageService;
    UserService userService;

    EchangeRepository echangeRepository;

    public ContactService(ContactRepository contactRepository, StorageService storageService, UserService userService, EchangeRepository echangeRepository) {
        this.contactRepository = contactRepository;
        this.storageService = storageService;
        this.userService = userService;
        this.echangeRepository = echangeRepository;
    }

    public List<Contact> getAllProspect() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        return contactRepository.findContactByIsClientTrueAndUser(customUser.getUser());
    }

    public List<Contact> getAllClient() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        return contactRepository.findContactByIsClientTrueAndUser(customUser.getUser());
    }

    public void addProspect(CreateContactDto createContactDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        createContactDto.setUser(customUser.getUser());
        createContactDto.setCreateDate(LocalDate.now());
        createContactDto.setStatusProspecting(Status.Aucun.getStatusName());
        if (createContactDto.getFile().isEmpty() || createContactDto.getFile() == null){
            createContactDto.setPicture("http://localhost:8080/images/" + "default.jpg");
        }else{
            MultipartFile picture = createContactDto.getFile();
            storageService.save(picture);
            createContactDto.setPicture("http://localhost:8080/images/" + picture.getOriginalFilename());
        }
        contactRepository.save(createContactDto.toContact());
    }

    public void prospectToClient(Long idContact){
        Contact contact = findContactById(idContact);
        contact.setIsClient(true);

        contact.setStatusProspecting(Status.termine.getStatusName());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        Echange echange = new Echange(LocalDateTime.now(),Status.termine.getStatusName(),"Transformation du prospect en Client",customUser.getUser(),contact,"Client"+contact.getEntreprise().getName());
        contact.getEchangesById().add(echange);
        echangeRepository.save(echange);
        contactRepository.save(contact);
    }


    public void addClient(CreateContactDto createContactDto){ // ??
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        createContactDto.setUser(customUser.getUser());
        createContactDto.setCreateDate(LocalDate.now());
        createContactDto.setClient(true);
        if (createContactDto.getFile().isEmpty() || createContactDto.getFile() == null){
            createContactDto.setPicture("http://localhost:8080/images/" + "default.jpg");
        }else{
            MultipartFile picture = createContactDto.getFile();
            storageService.save(picture);
            createContactDto.setPicture("http://localhost:8080/images/" + picture.getOriginalFilename());
        }
        contactRepository.save(createContactDto.toContact());
    }

    public Contact findContactById(Long id){
        return contactRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Contact with id:"+id+" doest not exist"));
    }

    public void deleteContact(Long id){
        Contact contact = findContactById(id);
        contactRepository.delete(contact);
    }

    public void updateContact(Long id,CreateContactDto createContactDto){
        Contact contact = findContactById(id);
        if (createContactDto.getFile().isEmpty() || createContactDto.getFile() == null){
            contact.setPicture(createContactDto.getPicture());
        }else{
            MultipartFile picture = createContactDto.getFile();
            storageService.save(picture);
            contact.setPicture("http://localhost:8080/images/" + picture.getOriginalFilename());
        }

        contact.setEntreprise(createContactDto.getEntreprise());
        contact.setEmail(createContactDto.getEmail());
        contact.setIsClient(createContactDto.isClient());
        contact.setFirstName(createContactDto.getFirstName());
        contact.setName(createContactDto.getName());
        contact.setPhone(createContactDto.getPhone());
        contact.setCellPhone(createContactDto.getCellPhone());
        contactRepository.save(contact);

    }

    public void updateClient(Long id,CreateContactDto createClientDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        Contact client = findContactById(id);
        if (createClientDto.getFile().isEmpty() || createClientDto.getFile() == null){
            client.setPicture(createClientDto.getPicture());
        }else{
            MultipartFile picture = createClientDto.getFile();
            storageService.save(picture);
            client.setPicture("http://localhost:8080/images/" + picture.getOriginalFilename());
        }

        if (!client.getEntreprise().getId().equals(createClientDto.getEntreprise().getId())){
            client.setIsClient(false);
            client.setStatusProspecting(Status.Aucun.getStatusName());
            Echange echange = new Echange(LocalDateTime.now(),Status.Aucun.getStatusName(),"Auto note : Le client a changé d'ntreprise ancienne entreprise :" + client.getEntreprise() +". Nouvelle :"+createClientDto.getEntreprise(),customUser.getUser(),client,"Prospecting"+createClientDto.getEntreprise());
            echangeRepository.save(echange);
        }

        client.setEntreprise(createClientDto.getEntreprise());
        client.setEmail(createClientDto.getEmail());
        client.setFirstName(createClientDto.getFirstName());
        client.setName(createClientDto.getName());
        client.setPhone(createClientDto.getPhone());
        client.setCellPhone(createClientDto.getCellPhone());
        contactRepository.save(client);

    }



    public List<Contact> searchContact(SearchDto searchDto, boolean isClient){
        List<Contact> contactList = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        Sort sort=null;
        if(searchDto.getSearchAZ() && searchDto.getSearchRecent()){
             sort = Sort.by("name").ascending().by("firstName").ascending().by("createDate").descending();
        }
        else if (searchDto.getSearchZA() && searchDto.getSearchOld()){
             sort = Sort.by("name").descending().by("firstName").descending().by("createDate").ascending();
        }
        else if (searchDto.getSearchAZ() && searchDto.getSearchOld()){
            sort = Sort.by("name").ascending().by("firstName").ascending().by("createDate").ascending();
        }else if(searchDto.getSearchZA() && searchDto.getSearchRecent()){
            sort =  Sort.by("name").descending().by("firstName").descending().by("createDate").descending();
        }
        else if (searchDto.getSearchZA()){
             sort = Sort.by("name").descending();
        }
        else if (searchDto.getSearchAZ()){
            sort = Sort.by("name").ascending();
        }
        else if (searchDto.getSearchOld()){
            sort = Sort.by("createDate").ascending();
        }
        else if (searchDto.getSearchRecent()){
            sort = Sort.by("createDate").descending();
        }

        if (searchDto.getProspectingStatu() != null && searchDto.getSearchValue() != null){
            contactList = contactRepository.findContactByUserAndNameContainsOrFirstNameContainsStatusProspectingEqualsWithFilter(customUser.getUser(),sort, searchDto.getSearchValue(), searchDto.getProspectingStatu().getStatusName(),isClient);
        }else if (searchDto.getProspectingStatu() != null){
            contactList = contactRepository.findContactByUserWithFilterAndStatusProspecting(customUser.getUser(),sort, searchDto.getProspectingStatu().getStatusName(),isClient);
        }
        else if(searchDto.getSearchValue() != null){
            contactList = contactRepository.findContactByUserAndNameFirstNameContainsNofilter(customUser.getUser(),sort,searchDto.getSearchValue(),isClient);
        }else{
            contactList = contactRepository.findContactByUserWithFilter(customUser.getUser(),sort,isClient);
        }

        return contactList;
    }

    public List<Contact> autoUpdateListStatutWhenTimerEnd(List<Contact> listContact){
        return listContact.stream().map(contact -> compareDateUpdateContact(contact)).collect(Collectors.toList());
    }


    public Contact compareDateUpdateContact(Contact contact){
        if (contact.getEchangesById().size()-1 >-1 && contact.getTimeBeforeReminder() != null){
            if (contact.getTimeBeforeReminder().compareTo(contact.getEchangesById().get(contact.getEchangesById().size()-1).getCreateDate())<0){
                contact.setStatusProspecting(Status.relancer.getStatusName());
                contactRepository.save(contact);
            }
        }

        contact.setEchangesById(contact.getEchangesById().stream().map(echange -> updateGoodFormatForDisplay(echange)).collect(Collectors.toList()));
        return contact;
    }

    public Echange updateGoodFormatForDisplay(Echange echange){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime isoFormatDate = echange.getCreateDate();
        String newFormat = isoFormatDate.format(dateTimeFormatter);
        echange.setCreateDateString(newFormat);
        return echange;
    }


}

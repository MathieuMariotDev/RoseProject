package com.example.rosaproject.service;

import com.example.rosaproject.controller.dto.CreateContactDto;
import com.example.rosaproject.controller.dto.SearchDto;
import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.User;
import com.example.rosaproject.repository.ContactRepository;
import com.example.rosaproject.security.CustomUserDetails;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContactService {

    ContactRepository contactRepository;

    StorageService storageService;
    UserService userService;

    public ContactService(ContactRepository contactRepository, StorageService storageService, UserService userService) {
        this.contactRepository = contactRepository;
        this.storageService = storageService;
        this.userService = userService;
    }

    public List<Contact> getAllProspect() {
        return contactRepository.findContactByIsClientFalse();
    }

    public List<Contact> getAllClient() {
        return contactRepository.findContactByIsClientTrue();
    }

    public void addProspect(CreateContactDto createContactDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        createContactDto.setUser(customUser.getUser());
        createContactDto.setCreateDate(LocalDate.now());
        createContactDto.setStatusProspecting("A prospecter");
        if (createContactDto.getFile().isEmpty() || createContactDto.getFile() == null){
            createContactDto.setPicture("http://localhost:8080/images/" + "default.jpg");
        }else{
            MultipartFile picture = createContactDto.getFile();
            storageService.save(picture);
            createContactDto.setPicture("http://localhost:8080/images/" + picture.getOriginalFilename());
        }
        contactRepository.save(createContactDto.toContact());
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

    public List<Contact> searchProspect(SearchDto searchDto){
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
        contactList = contactRepository.findContactFilter(customUser.getUser(),sort);
        return contactList;
    }


}

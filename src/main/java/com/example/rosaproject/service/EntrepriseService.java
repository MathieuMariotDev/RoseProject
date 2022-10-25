package com.example.rosaproject.service;

import com.example.rosaproject.controller.dto.CreateEntrepriseDto;
import com.example.rosaproject.controller.entity.Entreprise;
import com.example.rosaproject.controller.entity.User;
import com.example.rosaproject.exception.EntrepriseNotFoundException;
import com.example.rosaproject.repository.EntrepriseRepository;
import com.example.rosaproject.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    private StorageService storageService;

    public EntrepriseService(EntrepriseRepository entrepriseRepository, StorageService storageService) {
        this.entrepriseRepository = entrepriseRepository;
        this.storageService = storageService;
    }

    public Entreprise updateDateFormat(Entreprise entreprise){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = entreprise.getCreateDate();
        String newFormat = date.format(dateTimeFormatter);
        entreprise.setCreateDateString(newFormat);
        return entreprise;
    }



    public List<Entreprise> getAllEntreprises() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
        List<Entreprise> listEntreprise = entrepriseRepository.findAllByUserOrderByNameAsc(connectedUser);
        List<Entreprise> listDateUpdate = listEntreprise.stream().map(entreprise -> updateDateFormat(entreprise)).collect(Collectors.toList());
        return (List<Entreprise>) listDateUpdate;
    }

    public Entreprise getSpecificEntreprise(long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();

        Optional<Entreprise> entrepriseOptional = Optional.ofNullable(this.entrepriseRepository.findByIdAndUser(id, connectedUser));

        if (entrepriseOptional.isPresent()) {
            Entreprise entreprise = entrepriseOptional.get();
            return entreprise;
        } else {
            throw new EntrepriseNotFoundException(id);
        }
    }

    public List<Entreprise> getEntrepriseByKeywords(String name, String city) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();
        List<Entreprise> entrepriseList = entrepriseRepository.findEntrepriseByNameContainsAndUserOrCityContainsAndUser(name, connectedUser, city, connectedUser);
        List<Entreprise> listDateUpdate = entrepriseList.stream().map(entreprise -> updateDateFormat(entreprise)).collect(Collectors.toList());
        return listDateUpdate;
    }

    public void createEntreprise(CreateEntrepriseDto createEntreprise) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();

        if (createEntreprise.getPictureFile().isEmpty() || createEntreprise.getPictureFile() == null){
            createEntreprise.setLogo("http://localhost:8080/images/" + "default.jpg");
        }else{
            MultipartFile picture = createEntreprise.getPictureFile();
            storageService.save(picture);
            createEntreprise.setLogo("http://localhost:8080/images/" + picture.getOriginalFilename());
        }

        createEntreprise.setUser(connectedUser);
        createEntreprise.setUser(customUser.getUser());

        entrepriseRepository.save(createEntreprise.toEntreprise());
    }

    public void editEntreprise(long id, CreateEntrepriseDto editEntreprise) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        User connectedUser = customUser.getUser();

        Optional<Entreprise> entrepriseOptional = Optional.ofNullable(this.entrepriseRepository.findByIdAndUser(id, connectedUser));

        if(entrepriseOptional.isPresent()) {
            Entreprise entreprise = entrepriseOptional.get();

            if (editEntreprise.getPictureFile().isEmpty() || editEntreprise.getPictureFile() == null){
                entreprise.setLogo(editEntreprise.getLogo());

            } else {
                MultipartFile picture = editEntreprise.getPictureFile();
                storageService.save(picture);
                entreprise.setLogo("http://localhost:8080/images/" + picture.getOriginalFilename());
            }

            entreprise.setName(editEntreprise.getName());
            entreprise.setSiret(editEntreprise.getSiret());
            entreprise.setEmail(editEntreprise.getEmail());
            entreprise.setCellPhone(editEntreprise.getCellPhone());
            entreprise.setPhone(editEntreprise.getPhone());
            entreprise.setUrlWebSite(editEntreprise.getUrlWebSite());
            entreprise.setAddress(editEntreprise.getAddress());
            entreprise.setAdditionalAddress(editEntreprise.getAdditionalAddress());
            entreprise.setCity(editEntreprise.getCity());
            entreprise.setPostalCode(editEntreprise.getPostalCode());
            entreprise.setTypeOfActivity(editEntreprise.getTypeOfActivity());
            entreprise.setCreateDate(editEntreprise.getCreateDate());

            entreprise.setUser(connectedUser);
            entreprise.setUser(customUser.getUser());

            entrepriseRepository.save(entreprise);

        } else {
            throw new EntrepriseNotFoundException(id);
        }
    }

    public void deleteEntreprise(long id) {
        entrepriseRepository.deleteById(id);
    }
}

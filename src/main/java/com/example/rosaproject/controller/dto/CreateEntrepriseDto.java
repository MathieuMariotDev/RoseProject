package com.example.rosaproject.controller.dto;

import com.example.rosaproject.controller.entity.Contact;
import com.example.rosaproject.controller.entity.Entreprise;
import com.example.rosaproject.controller.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

public class CreateEntrepriseDto {

    private Long id;

    private String logo;

    private MultipartFile pictureFile;

    private String name;

    private String siret;

    private String email;

    private String cellPhone;

    private String phone;

    private String urlWebSite;

    private String address;

    private String additionalAddress;

    private String city;

    private String postalCode;

    private String typeOfActivity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    private Collection<Contact> contactsById;

    private User user;




    public Entreprise toEntreprise(){

        Entreprise entreprise = new Entreprise();
        entreprise.setId(this.id);
        entreprise.setLogo(this.logo);
        entreprise.setName(this.name);
        entreprise.setSiret(this.siret);
        entreprise.setEmail(this.email);
        entreprise.setCellPhone(this.cellPhone);
        entreprise.setPhone(this.phone);
        entreprise.setUrlWebSite(this.urlWebSite);
        entreprise.setAddress(this.address);
        entreprise.setAdditionalAddress(this.additionalAddress);
        entreprise.setCity(this.city);
        entreprise.setPostalCode(this.postalCode);
        entreprise.setTypeOfActivity(this.typeOfActivity);
        entreprise.setCreateDate(this.createDate);
        entreprise.setUser(this.user);
        return entreprise;
    }

    public static CreateEntrepriseDto toEntrepriseDto(Entreprise entreprise) {
        CreateEntrepriseDto createEntrepriseDto = new CreateEntrepriseDto();
        createEntrepriseDto.setId(entreprise.getId());
        createEntrepriseDto.setLogo(entreprise.getLogo());
        createEntrepriseDto.setName(entreprise.getName());
        createEntrepriseDto.setSiret(entreprise.getSiret());
        createEntrepriseDto.setEmail(entreprise.getEmail());
        createEntrepriseDto.setCellPhone(entreprise.getCellPhone());
        createEntrepriseDto.setPhone(entreprise.getPhone());
        createEntrepriseDto.setUrlWebSite(entreprise.getUrlWebSite());
        createEntrepriseDto.setAddress(entreprise.getAddress());
        createEntrepriseDto.setAdditionalAddress(entreprise.getAdditionalAddress());
        createEntrepriseDto.setCity(entreprise.getCity());
        createEntrepriseDto.setPostalCode(entreprise.getPostalCode());
        createEntrepriseDto.setTypeOfActivity(entreprise.getTypeOfActivity());
        createEntrepriseDto.setCreateDate(entreprise.getCreateDate());
        createEntrepriseDto.setUser(entreprise.getUser());
        return createEntrepriseDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public MultipartFile getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(MultipartFile pictureFile) {
        this.pictureFile = pictureFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlWebSite() {
        return urlWebSite;
    }

    public void setUrlWebSite(String urlWebSite) {
        this.urlWebSite = urlWebSite;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdditionalAddress() {
        return additionalAddress;
    }

    public void setAdditionalAddress(String additionalAddress) {
        this.additionalAddress = additionalAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTypeOfActivity() {
        return typeOfActivity;
    }

    public void setTypeOfActivity(String typeOfActivity) {
        this.typeOfActivity = typeOfActivity;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Collection<Contact> getContactsById() {
        return contactsById;
    }

    public void setContactsById(Collection<Contact> contactsById) {
        this.contactsById = contactsById;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

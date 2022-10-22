package com.example.rosaproject.controller.dto;

import org.springframework.data.domain.Sort;

public class SearchDto {

    Boolean searchAZ=false;

    Boolean searchZA=false;

    Boolean searchOld=false;

    Boolean searchRecent=false;

    public SearchDto() {
    }

    public Boolean getSearchAZ() {
        return searchAZ;
    }

    public void setSearchAZ(Boolean searchAZ) {
        this.searchAZ = searchAZ;
    }

    public Boolean getSearchZA() {
        return searchZA;
    }

    public void setSearchZA(Boolean searchZA) {
        this.searchZA = searchZA;
    }

    public Boolean getSearchOld() {
        return searchOld;
    }

    public void setSearchOld(Boolean searchOld) {
        this.searchOld = searchOld;
    }

    public Boolean getSearchRecent() {
        return searchRecent;
    }

    public void setSearchRecent(Boolean searchRecent) {
        this.searchRecent = searchRecent;
    }
}


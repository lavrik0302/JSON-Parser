package com.intexsoft;
import lombok.*;

@Data
public class FootballClub { 
    private String club = "Arsenal";

    private Boolean qualifiedToUCL = false;

    private Number points = 67;

    public FootballClub getFootballClub() {
        FootballClub footballclub = new FootballClub();
        return footballclub;
    }
}
package com.intexsoft.pluginOutput;
import lombok.*;

@Data
public class UserAdress { 
    private String city = "Grodno";

    private Boolean moreThan100k = true;

    private Adress adress = new Adress().getAdress();

    public UserAdress getUserAdress() {
        UserAdress useradress = new UserAdress();
        return useradress;
    }
}
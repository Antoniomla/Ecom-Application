package com.app.ecom.dto;

import lombok.Data;

@Data
public class AddressesDTO {
    private String street; // rua
    private String city; // cidade
    private String state; //estado
    private String country; //pais
    private String zipcode; // codigo postal

}

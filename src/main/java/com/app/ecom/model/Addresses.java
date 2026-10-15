package com.app.ecom.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "addresses")
@NoArgsConstructor
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String street; // rua
    private String city; // cidade
    private String state; //estado
    private String country; //pais
    private String zipcode; // codigo postal

}

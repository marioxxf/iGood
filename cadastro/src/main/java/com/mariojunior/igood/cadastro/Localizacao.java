package com.mariojunior.igood.cadastro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurante")
public class Localizacao {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	
	public Double latitude;
	public Double longitude;
}

package com.mariojunior.igood.cadastro;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurante")
public class Restaurante extends PanacheEntityBase {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	
	public String proprietario;
	
	public String cnpj;
	
	public String nome;
	
	@ManyToOne
	public Localizacao localizacao;
	
	@CreationTimestamp
	public Date dataCriacao;
	
	@UpdateTimestamp
	public Date dataAtualizacao;
}

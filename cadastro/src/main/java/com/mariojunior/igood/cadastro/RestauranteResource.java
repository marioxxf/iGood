package com.mariojunior.igood.cadastro;

import java.util.List;
import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {
    @GET
    public List<Restaurante> listarRestaurantes() {
        return Restaurante.listAll();
    }
    
    @POST
    @Transactional
    public Response adicionarRestaurante(Restaurante dto) {
    	dto.persist();
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public void atualizarRestaurante(@PathParam("id") Long id, Restaurante dto) {
    	Optional<PanacheEntityBase> restauranteOp = Restaurante.findByIdOptional(id);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException();
    	}
    	
    	Restaurante restaurante = (Restaurante) restauranteOp.get();
    	
    	restaurante.nome = dto.nome;
    	restaurante.persist();
    }
    
    @DELETE
    @Path("{id}")
    @Transactional
    public void excluirRestaurante(@PathParam("id") Long id) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
    	restauranteOp.ifPresentOrElse(Restaurante::delete, () -> {
    		throw new NotFoundException();
		});
    }
    
    @GET
    @Path("{idRestaurante}/pratos")
    public List<Prato> listarPratos(@PathParam("idRestaurante") Long idRestaurante) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if(restauranteOp.isEmpty()) {
        	throw new NotFoundException("O restaurante não existe.");
        }
        return Prato.list("restaurante", restauranteOp.get());
    }
    
    @POST
    @Path("/pratos")
    @Transactional
    public Response adicionarPrato(Prato dto) {
    	dto.persist();
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("/pratos/{id}")
    @Transactional
    public void atualizarPrato(@PathParam("id") Long id, Prato dto) {
    	Optional<PanacheEntityBase> pratoOp = Prato.findByIdOptional(id);
    	if(pratoOp.isEmpty()) {
    		throw new NotFoundException();
    	}
    	
    	Prato prato = (Prato) pratoOp.get();
    	
    	prato.nome = dto.nome;
    	prato.persist();
    }
    
    @DELETE
    @Path("/pratos/{id}")
    @Transactional
    public void excluirPrato(@PathParam("id") Long id) {
    	Optional<Prato> pratoOp = Prato.findByIdOptional(id);
    	pratoOp.ifPresentOrElse(Prato::delete, () -> {
    		throw new NotFoundException();
		});
    }
}
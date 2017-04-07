/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.java.back.controllers;

import io.java.back.business.ChamadoData;
import io.java.back.data.Chamado;
import io.java.back.enumer.chamado.Status;

import java.io.Console;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author spezia
 */
@Path("chamados")
public class ChamadoController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Chamado> listChamados() {
        try {
            ChamadoData chamadoBus = new ChamadoData();
            return chamadoBus.listar();
        } catch (Exception ex) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR); // dispara cod 500
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Chamado getChamado(@PathParam("id") long id) {
        try {
        	if(id == 800){			// testes//
	        	Chamado chamado = new Chamado();
	        	chamado.setId(id);
	        	chamado.setAssunto("assunto" + id);
	        	chamado.setMensagem("mensagem" + id);
	        	chamado.setStatus(Status.NOVO);
	        	return chamado;
        	}
        	
            ChamadoData chamadoBus = new ChamadoData();
            return chamadoBus.selecionar(id);
        } catch (Exception ex) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response create(Chamado chamado) {
        try {
            ChamadoData chamadoBus = new ChamadoData();
            chamadoBus.inserir(chamado);
            Logger.getLogger(chamado.toString());
            return Response.status(Response.Status.OK).build(); // dispara cod 200
        } catch (Exception ex) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response update(Chamado chamado) {
        try {
            chamado.setStatus(Status.PENDENTE);
            ChamadoData chamadoBus = new ChamadoData();
            chamadoBus.alterar(chamado);
            Logger.getLogger(chamado.toString()); 
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("id") long id) {
        try {
            ChamadoData chamadoBus = new ChamadoData();
            chamadoBus.excluir(id);
            Logger.getLogger(" - Apaga registro com id: " + id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{id}/")
    public Response concluir(@PathParam("id") long id) {
        try {
            ChamadoData chamadoBus = new ChamadoData();

            Chamado chmd = chamadoBus.selecionar(id);
            chmd.setStatus(Status.FECHADO);

            chamadoBus.alterar(chmd);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.gonzalez.samples.javaee7angular.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import com.gonzalez.samples.javaee7angular.data.Imovel;
import com.gonzalez.samples.javaee7angular.pagination.ListaPaginada;

/**
 * REST Service to expose the data to display in the UI grid.
 *
 * @author Roberto Cortez
 */
@Stateless
@ApplicationPath("/resources")
@Path("imoveis")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ImovelResource extends Application {
    
	
	public ImovelResource() {
		super();
	}

	public ImovelResource(EntityManager entityManager) {
	
		this.entityManager = entityManager;
	}

	@PersistenceContext
    private EntityManager entityManager;

    private Integer contaImoveis() {
        Query query = entityManager.createQuery("SELECT COUNT(p.id) FROM Imovel p");
        return ((Long) query.getSingleResult()).intValue();
    }

    @SuppressWarnings("unchecked")
    private List<Imovel> encontraImovel(int PosicaoInicio, int Resultados, String sortFields, String sortDirections) {
        Query query =
                entityManager.createQuery("SELECT p FROM Imovel p ORDER BY p." + sortFields + " " + sortDirections);
        query.setFirstResult(PosicaoInicio);
        query.setMaxResults(Resultados);
        return query.getResultList();
    }
    
    private ListaPaginada encontraImovel(ListaPaginada wrapper) {
        wrapper.setTotalResultados(contaImoveis());
        int start = (wrapper.getPaginaAtual() - 1) * wrapper.getTamanhoPagina();
        wrapper.setLista(encontraImovel(start,
                                    wrapper.getTamanhoPagina(),
                                    wrapper.getCamposEscolhidos(),
                                    wrapper.getCaminhosEscolhidos()));
        return wrapper;
    }  

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ListaPaginada listaImovel(@DefaultValue("1")
                                            @QueryParam("page")
                                            Integer page,
                                            @DefaultValue("id")
                                            @QueryParam("camposEscolhidos")
                                            String sortFields,
                                            @DefaultValue("asc")
                                            @QueryParam("caminhosEscolhidos")
                                            String sortDirections) {
        ListaPaginada listaPaginada = new ListaPaginada();
        listaPaginada.setPaginaAtual(page);
        listaPaginada.setCamposEscolhidos(sortFields);
        listaPaginada.setCaminhosEscolhidos(sortDirections);
        listaPaginada.setTamanhoPagina(10);
        return encontraImovel(listaPaginada);
    }
    
    @GET
    @Path("{id}")
    public Imovel getImovel(@PathParam("id") Long id) {
        return entityManager.find(Imovel.class, id);
    }
    
    //findById
    public Imovel findById(Long artistId) {
        Imovel artist = entityManager.find(Imovel.class, artistId);
        if (artist == null) {
            throw new EntityNotFoundException("Can't find Artist for ID "  + artistId);
        }
        return artist;
    }
    
    
    public void deleteById(Imovel imovel) {
    Imovel imovelToErase = getImovel(imovel.getId());
    imovelToErase.setDescricao(imovel.getDescricao());
    imovelToErase.setEndereco(imovel.getEndereco());
    imovelToErase.setFotoUrl(imovel.getFotoUrl());
    imovel = entityManager.merge(imovelToErase);
    //return imovel;
    }
    
    public void apagaImovel (Imovel imovel) {
    	if (imovel.getId() != null) {
    	
    		Imovel apagaImv = new Imovel();
    		apagaImv.setTipo("");
    		apagaImv.setDescricao("");
    		apagaImv.setEndereco("");
    		apagaImv.setFotoUrl("");
    		entityManager.persist(imovel);   		
    	}
    }
    
    @POST
    public Imovel salvaImovel(Imovel imovel) {
        if (imovel.getId() == null) {
            Imovel imovelToSave = new Imovel();
            imovelToSave.setDescricao(imovel.getDescricao());
            imovelToSave.setEndereco(imovel.getEndereco());
            imovelToSave.setFotoUrl(imovel.getFotoUrl());
            entityManager.persist(imovel);
        } else {
            Imovel imovelToUpdate = getImovel(imovel.getId());
            imovelToUpdate.setDescricao(imovel.getDescricao());
            imovelToUpdate.setEndereco(imovel.getEndereco());
            imovelToUpdate.setFotoUrl(imovel.getFotoUrl());
            imovel = entityManager.merge(imovelToUpdate);
        }
        return imovel;
    }

    @DELETE
    @Path("{id}")
    public void deleteImovel(@PathParam("id") Long id) {
        entityManager.remove(getImovel(id));
	    }
	}

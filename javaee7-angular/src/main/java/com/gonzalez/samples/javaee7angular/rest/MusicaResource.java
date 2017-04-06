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

import com.gonzalez.samples.javaee7angular.data.Musica;
import com.gonzalez.samples.javaee7angular.pagination.ListaPaginadaMusica;


/**
 * REST Service para expor os dados a serem exibidos na grade de UI.
 *
 */
@Stateless
@ApplicationPath("/resources")
@Path("musicas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MusicaResource extends Application {
    
	
	public MusicaResource() {
		super();
	}

	public MusicaResource(EntityManager entityManager) {
	
		this.entityManager = entityManager;
	}

	@PersistenceContext
    private EntityManager entityManager;

    private Integer contaMusicas() {
        Query query = entityManager.createQuery("SELECT COUNT(p.id) FROM Musica p");
        return ((Long) query.getSingleResult()).intValue();
    }

    @SuppressWarnings("unchecked")
    private List<Musica> encontraMusica(int PosicaoInicio, int Resultados, String sortFields, String sortDirections) {
        Query query =
                entityManager.createQuery("SELECT p FROM Musica p ORDER BY p." + sortFields + " " + sortDirections);
        query.setFirstResult(PosicaoInicio);
        query.setMaxResults(Resultados);
        return query.getResultList();
    }
    
    private ListaPaginadaMusica encontraMusica(ListaPaginadaMusica wrapper) {
        wrapper.setTotalResultados(contaMusicas());
        int start = (wrapper.getPaginaAtual() - 1) * wrapper.getTamanhoPagina();
        wrapper.setLista(encontraMusica(start,
                                    wrapper.getTamanhoPagina(),
                                    wrapper.getCamposEscolhidos(),
                                    wrapper.getCaminhosEscolhidos()));
        return wrapper;
    }  

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ListaPaginadaMusica listaMusica(@DefaultValue("1")
                                            @QueryParam("page")
                                            Integer page,
                                            @DefaultValue("id")
                                            @QueryParam("camposEscolhidos")
                                            String sortFields,
                                            @DefaultValue("asc")
                                            @QueryParam("caminhosEscolhidos")
                                            String sortDirections) {
        ListaPaginadaMusica listaPaginada = new ListaPaginadaMusica();
        listaPaginada.setPaginaAtual(page);
        listaPaginada.setCamposEscolhidos(sortFields);
        listaPaginada.setCaminhosEscolhidos(sortDirections);
        listaPaginada.setTamanhoPagina(10);
        return encontraMusica(listaPaginada);
    }
    
    @GET
    @Path("{id}")
    public Musica getMusica(@PathParam("id") Long id) {
        return entityManager.find(Musica.class, id);
    }
    
    //findById
    public Musica findById(Long artistId) {
        Musica artist = entityManager.find(Musica.class, artistId);
        if (artist == null) {
            throw new EntityNotFoundException("Can't find Artist for ID "
                    + artistId);
        }
        return artist;
    }
    
    
    public void deleteById(Musica musica) {
    Musica ApagarMusica = getMusica(musica.getId());
    ApagarMusica.setNome(musica.getNome());
    ApagarMusica.setArtista(musica.getArtista());
    ApagarMusica.setLinkUrl(musica.getLinkUrl());
    musica = entityManager.merge(ApagarMusica);
    //return musica;
    }
    
    public void apagaMusica (Musica musica) {
    	if (musica.getId() != null) {
    	
    		Musica apagaImv = new Musica();
    		apagaImv.setEstilo("");
    		apagaImv.setNome("");
    		apagaImv.setArtista("");
    		apagaImv.setAlbum("");
    		entityManager.persist(musica);   		
    	}
    }
    
    @POST
    public Musica salvaMusica(Musica musica) {
        if (musica.getId() == null) {
            Musica novaMusica = new Musica();
            novaMusica.setNome(musica.getNome());
            novaMusica.setArtista(musica.getArtista());
            novaMusica.setLinkUrl(musica.getLinkUrl());
            entityManager.persist(musica);
        } else {
            Musica atualizarMusica = getMusica(musica.getId());
            atualizarMusica.setNome(musica.getNome());
            atualizarMusica.setArtista(musica.getArtista());
            atualizarMusica.setLinkUrl(musica.getLinkUrl());
            musica = entityManager.merge(atualizarMusica);
        }
        return musica;
    }

    @DELETE
    @Path("{id}")
    public void deleteMusica(@PathParam("id") Long id) {
        entityManager.remove(getMusica(id));
	    }
	}

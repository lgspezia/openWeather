package com.gonzalez.samples.javaee7angular.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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



@Stateless
@ApplicationPath("/resources")
@Path("musicas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AplicaRegrasPaginaMusica extends Application{
	@PersistenceContext
	private EntityManager entityManager;

    private Integer contaMusicas() {
    	Query query = entityManager.createQuery("SELECT COUNT(i.id) FROM Musica i");
        return ((Long) query.getSingleResult()).intValue();
    }
    
    @SuppressWarnings("unchecked")
    private List<Musica> encontraMusica(int startPosition, int maxResults, String campos, String caminhos) {
        Query query =
                entityManager.createQuery("SELECT i FROM Musica i ORDER BY i." + campos + " " + caminhos);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
    
    private ListaPaginadaMusica encontraMusica(ListaPaginadaMusica listaPag) {
    	listaPag.setTotalResultados(contaMusicas());
        int start = (listaPag.getPaginaAtual() - 1) * listaPag.getTamanhoPagina();
        listaPag.setLista(encontraMusica(start,
        		listaPag.getTamanhoPagina(),
        		listaPag.getCamposEscolhidos(),
        		listaPag.getCaminhosEscolhidos()));
        return listaPag;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ListaPaginadaMusica listaMusicas (@DefaultValue("1") @QueryParam("pagina") Integer pagina,
            @DefaultValue("id")   @QueryParam("campos")   String campos,
            @DefaultValue("asc")  @QueryParam("caminhos") String caminhos) {
    	ListaPaginadaMusica lp = new ListaPaginadaMusica();
    	lp.setPaginaAtual(pagina);
    	lp.setCamposEscolhidos(campos);
    	lp.setCaminhosEscolhidos(caminhos);
    	lp.setTamanhoPagina(10);
    	return encontraMusica(lp);
    }
    
    @GET
    @Path("{id}")
    public Musica getMusica(@PathParam("id")Long id) {
    	return entityManager.find(Musica.class, id);
    }
    
    
    @POST
    public Musica salvaMusicas (Musica musica) {
    	if (musica.getId() == null) {
    		Musica salvaMusica = new Musica();
    		salvaMusica.setEstilo(musica.getEstilo());
    		salvaMusica.setArtista(musica.getArtista());
    		salvaMusica.setNome(musica.getNome());
    		salvaMusica.setLinkUrl(musica.getLinkUrl());
    		entityManager.persist(musica);
    	} else {
    		Musica MusicaAtual = getMusica(musica.getId());
    		MusicaAtual.setEstilo(musica.getEstilo());
    		MusicaAtual.setArtista(musica.getArtista());
    		MusicaAtual.setNome(musica.getNome());
    		MusicaAtual.setLinkUrl(musica.getLinkUrl());
    		musica = entityManager.merge(musica);
    	}
    return musica;
    }
    
    @DELETE
    @Path("{id}")
    public void apagaMusica(@PathParam("id") Long id) {
        entityManager.remove(getMusica(id));
    }
    
}

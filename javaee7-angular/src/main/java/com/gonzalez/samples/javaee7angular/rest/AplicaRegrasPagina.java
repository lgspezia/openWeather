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

import com.gonzalez.samples.javaee7angular.data.Imovel;
import com.gonzalez.samples.javaee7angular.pagination.ListaPaginada;

@Stateless
@ApplicationPath("/resources")
@Path("imoveis")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AplicaRegrasPagina extends Application{
	@PersistenceContext
	private EntityManager entityManager;

    private Integer contaImoveis() {
    	Query query = entityManager.createQuery("SELECT COUNT(i.id) FROM Imovel i");
        return ((Long) query.getSingleResult()).intValue();
    }
    
    @SuppressWarnings("unchecked")
    private List<Imovel> encontraImovel(int startPosition, int maxResults, String campos, String caminhos) {
        Query query =
                entityManager.createQuery("SELECT i FROM Imovel i ORDER BY i." + campos + " " + caminhos);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
    
    private ListaPaginada encontraImovel(ListaPaginada listaPag) {
    	listaPag.setTotalResultados(contaImoveis());
        int start = (listaPag.getPaginaAtual() - 1) * listaPag.getTamanhoPagina();
        listaPag.setLista(encontraImovel(start,
        		listaPag.getTamanhoPagina(),
        		listaPag.getCamposEscolhidos(),
        		listaPag.getCaminhosEscolhidos()));
        return listaPag;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ListaPaginada listaImoveis (@DefaultValue("1") @QueryParam("pagina") Integer pagina,
            @DefaultValue("id")   @QueryParam("campos")   String campos,
            @DefaultValue("asc")  @QueryParam("caminhos") String caminhos) {
    	ListaPaginada lp = new ListaPaginada();
    	lp.setPaginaAtual(pagina);
    	lp.setCamposEscolhidos(campos);
    	lp.setCaminhosEscolhidos(caminhos);
    	lp.setTamanhoPagina(10);
    	return encontraImovel(lp);
    }
    
    @GET
    @Path("{id}")
    public Imovel getImovel(@PathParam("id")Long id) {
    	return entityManager.find(Imovel.class, id);
    }
    
    
    @POST
    public Imovel salvaImovel (Imovel imovel) {
    	if (imovel.getId() == null) {
    		Imovel salvaImv = new Imovel();
    		salvaImv.setTipo(imovel.getTipo());
    		salvaImv.setDescricao(imovel.getDescricao());
    		salvaImv.setEndereco(imovel.getEndereco());
    		salvaImv.setFotoUrl(imovel.getFotoUrl());
    		entityManager.persist(imovel);
    	} else {
    		Imovel AtualImv = getImovel(imovel.getId());
    		AtualImv.setTipo(imovel.getTipo());
    		AtualImv.setDescricao(imovel.getDescricao());
    		AtualImv.setEndereco(imovel.getEndereco());
    		AtualImv.setFotoUrl(imovel.getFotoUrl());
    		imovel = entityManager.merge(imovel);
    	}
    return imovel;
    }
    
    @DELETE
    @Path("{id}")
    public void apagaImovel(@PathParam("id") Long id) {
        entityManager.remove(getImovel(id));
    }
    
}

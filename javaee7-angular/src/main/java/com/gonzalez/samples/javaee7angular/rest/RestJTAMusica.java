package com.gonzalez.samples.javaee7angular.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.gonzalez.samples.javaee7angular.data.Musica;
import com.gonzalez.samples.javaee7angular.pagination.ListaPaginadaMusica;



@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class RestJTAMusica {
		
		  @PersistenceContext
		  private EntityManager entityManager;

		  @Resource
		  private UserTransaction userTransaction;

		  public void test() throws Exception {
			  ListaPaginadaMusica lp = new ListaPaginadaMusica();
		    	lp.setPaginaAtual(8);
		    	lp.setCamposEscolhidos("campos abertos");

		    userTransaction.begin();
		    entityManager.persist(lp);
		    userTransaction.commit();  }

		    private Integer contaMusicas() {
		    	Query query = entityManager.createQuery("SELECT COUNT(i.id) FROM Musica i");
		        return ((Long) query.getSingleResult()).intValue();  }
		    
		    @SuppressWarnings("unchecked")
		    private List<Musica> encontraMusica(int startPosition, int maxResults, String campos, String caminhos) {
		        Query query =
		                entityManager.createQuery("SELECT i FROM Musica i ORDER BY i." + campos + " " + caminhos);
		        query.setFirstResult(startPosition);
		        query.setMaxResults(maxResults);
		        return query.getResultList();  }
		  
		    private ListaPaginadaMusica encontraMusica(ListaPaginadaMusica listaPag) {
		    	listaPag.setTotalResultados(contaMusicas());
		        int start = (listaPag.getPaginaAtual() - 1) * listaPag.getTamanhoPagina();
		        listaPag.setLista(encontraMusica(start,
		        		listaPag.getTamanhoPagina(),
		        		listaPag.getCamposEscolhidos(),
		        		listaPag.getCaminhosEscolhidos()));
		        return listaPag;  }
		    
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
		    	return encontraMusica(lp);  }
		  
	}


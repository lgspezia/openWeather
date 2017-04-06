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

import com.gonzalez.samples.javaee7angular.data.Imovel;
import com.gonzalez.samples.javaee7angular.pagination.ListaPaginada;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class RestJTA {
		
		  @PersistenceContext
		  private EntityManager entityManager;

		  @Resource
		  private UserTransaction userTransaction;

		  public void test() throws Exception {
			  ListaPaginada lp = new ListaPaginada();
		    	lp.setPaginaAtual(8);
		    	lp.setCamposEscolhidos("campos abertos");

		    userTransaction.begin();
		    entityManager.persist(lp);
		    userTransaction.commit();  }

		    private Integer contaImoveis() {
		    	Query query = entityManager.createQuery("SELECT COUNT(i.id) FROM Imovel i");
		        return ((Long) query.getSingleResult()).intValue();  }
		    
		    @SuppressWarnings("unchecked")
		    private List<Imovel> encontraImovel(int startPosition, int maxResults, String campos, String caminhos) {
		        Query query =
		                entityManager.createQuery("SELECT i FROM Imovel i ORDER BY i." + campos + " " + caminhos);
		        query.setFirstResult(startPosition);
		        query.setMaxResults(maxResults);
		        return query.getResultList();  }
		  
		    private ListaPaginada encontraImovel(ListaPaginada listaPag) {
		    	listaPag.setTotalResultados(contaImoveis());
		        int start = (listaPag.getPaginaAtual() - 1) * listaPag.getTamanhoPagina();
		        listaPag.setLista(encontraImovel(start,
		        		listaPag.getTamanhoPagina(),
		        		listaPag.getCamposEscolhidos(),
		        		listaPag.getCaminhosEscolhidos()));
		        return listaPag;  }
		    
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
		    	return encontraImovel(lp);  }
		  
	}


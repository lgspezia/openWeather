package com.gonzalez.samples.javaee7angular.rest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import com.gonzalez.samples.javaee7angular.data.Calculo;

/**
 * REST Service to expose the data to display in the UI grid.
 *
 * @author Gonzalez
 */
@Stateless
@ApplicationPath("/resources")
@Path("calculos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalculoResource extends Application {
    @PersistenceContext
    private EntityManager entityManager;

    private Integer countCalculos() {
        Query query = entityManager.createQuery("SELECT COUNT(p.id) FROM Calculo p");
        return ((Long) query.getSingleResult()).intValue();
    }


    @GET
    @Path("{id}")
    public Calculo getCalculo(@PathParam("id") Long id) {
        return entityManager.find(Calculo.class, id);
    }

    @POST
    public Calculo saveCalculo(Calculo calculo) {
        if (calculo.getId() == null) {
            Calculo calculoToSave = new Calculo();
            calculoToSave.setInstituicao(calculo.getInstituicao());
            calculoToSave.setValorFinanciado(calculo.getValorFinanciado());
            calculoToSave.setValorParcela01(calculo.getValorParcela01());
            entityManager.persist(calculo);
        } else {
            Calculo calculoToUpdate = getCalculo(calculo.getId());
            calculoToUpdate.setInstituicao(calculo.getInstituicao());
            calculoToUpdate.setValorFinanciado(calculo.getValorFinanciado());
            calculoToUpdate.setValorParcela01(calculo.getValorParcela01());
            calculo = entityManager.merge(calculoToUpdate);
        }

        return calculo;
    }

    /*TODO 
     * https://www3.bcb.gov.br/CALCIDADAO/publico/exibirFormFinanciamentoPrestacoesFixas.do?method=exibirFormFinanciamentoPrestacoesFixas
     * https://pt.wikipedia.org/wiki/Juro*/
    
	public void calculaJuros(Calculo calculo) {
		if (calculo == null) {
			System.out.println(" o valor calculado eh nulo ");
		} else {
		
			if (calculo.getValorParcelaAtual() == calculo.getValorParcela01())
				System.out.println("sem juros");
			
			if(calculo.getValorParcelaAtual() > calculo.getValorParcela01()) {
				System.out.println(
			 "juros simples - (pago apenas sobre o valor do principal (ou montante) do empréstimo ou aplicação )"
			+"JUROS NOMINAIS (brutos, sem descontar taxas de inflacao / IPCA)"
			+"juros reais (simples + taxa da inflacao OU IPCA)"
			+"juros compostos - (pagos não apenas sobre o valor do principal, mas também sobre os juros obtidos em relação ao principal nos períodos anteriores)"
			+"juros compostos continuamente composta"
			+"juros compostos renda certa"
				);
			}
		}
	}
    @DELETE
    @Path("{id}")
    public void deleteCalculo(@PathParam("id") Long id) {
        entityManager.remove(getCalculo(id));
    }
}

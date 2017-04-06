package com.gonzalez.samples.javaee7angular.pagination;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.gonzalez.samples.javaee7angular.data.Imovel;

@XmlRootElement
public class ListaPaginada implements Serializable{
	
	//private static final long serialVersionUID = 1L;
	private Integer paginaAtual;
    private Integer tamanhoPagina;
	private Integer totalResultados;
    private String camposEscolhidos;
    private String caminhosEscolhidos;
    @XmlElement
    private List<Imovel> lista;
	
    public List<Imovel> getLista() {
    	return lista;
    }
    public void setLista(List<Imovel> lista) {
    	this.lista = lista;
    }
	public Integer getPaginaAtual() {
		return paginaAtual;
	}
	public void setPaginaAtual(Integer paginaAtual) {
		this.paginaAtual = paginaAtual;
	}
	public Integer getTamanhoPagina() {
		return tamanhoPagina;
	}
	public void setTamanhoPagina(Integer tamanhoPagina) {
		this.tamanhoPagina = tamanhoPagina;
	}
	public Integer getTotalResultados() {
		return totalResultados;
	}
	public void setTotalResultados(Integer totalResultados) {
		this.totalResultados = totalResultados;
	}
	public String getCamposEscolhidos() {
		return camposEscolhidos;
	}
	public void setCamposEscolhidos(String camposEscolhidos) {
		this.camposEscolhidos = camposEscolhidos;
	}
	public String getCaminhosEscolhidos() {
		return caminhosEscolhidos;
	}
	public void setCaminhosEscolhidos(String caminhosEscolhidos) {
		this.caminhosEscolhidos = caminhosEscolhidos;
	}


}

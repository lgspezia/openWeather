package com.gonzalez.samples.javaee7angular.data;

import javax.persistence.*;

/**
 * Simple entity.
 *
 * @author Gonzalez
 */
@Entity
@Table(name="calculo")
public class Calculo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idc")
    @SequenceGenerator(name = "idc", sequenceName = "idc")
    private Long id;

    private String instituicao;

    private double valorFinanciado;

    private double valorParcela01;
    
    private double valorParcelaAtual;
    
	private int numeroParcelaAtual;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String name) {
        this.instituicao = name;
    }

    public double getValorFinanciado() {
        return valorFinanciado;
    }

    public void setValorFinanciado(double description) {
        this.valorFinanciado = description;
    }

    public double getValorParcela01() {
		return valorParcela01;
	}

	public void setValorParcela01(double valorParcela01) {
		this.valorParcela01 = valorParcela01;
	}

	public double getValorParcelaAtual() {
		return valorParcelaAtual;
	}

	public void setValorParcelaAtual(double valorParcelaAtual) {
		this.valorParcelaAtual = valorParcelaAtual;
	}

	public int getNumeroParcelaAtual() {
		return numeroParcelaAtual;
	}

	public void setNumeroParcelaAtual(int numeroParcelaAtual) {
		this.numeroParcelaAtual = numeroParcelaAtual;
	}
    
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Calculo calculo = (Calculo) o;

        return id.equals(calculo.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

package com.gonzalez.samples.javaee7angular.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="imovel")
public class Imovel {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "id")
	private Long id;

    private String tipo;
    private String proprietario;
	private String descricao;
    private String endereco;

	private String fotoUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	 public String getProprietario() {
			return proprietario;
		}

		public void setProprietario(String proprietario) {
			this.proprietario = proprietario;
		}
		
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getEndereco() {
		return endereco;	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}
    
    

	/////////----------//////*****
	/*public Imovel() {
		super();
	}		
	
		public Imovel(Long idImv, String tpImv) {
	        super();
	        this.id = idImv;
	        this.tipo = tpImv;
	    }*/
	    
	@Override 
    /*public boolean equals(Object obj) {
	        if (this == obj) { return true; }
	        if (obj == null || getClass() != obj.getClass()) { return false; }

	        Imovel imovel = (Imovel) obj;

	        return id.equals(imovel.id);	    }*/
	
	public boolean equals(Object obj) {
	        //se nao forem objetos da mesma classe sao objetos diferentes
	        if(!(obj instanceof Imovel)) return false; 

	        //se forem o mesmo objeto, retorna true
	        if(obj == this) return true;

	        // aqui o cast é seguro por causa do teste feito acima
	        Imovel imovel = (Imovel) obj; 

	        //aqui você compara a seu gosto, o ideal é comparar atributo por atributo
	       return id.equals(imovel.id);
	    }
	
	 @Override
	    public int hashCode() {
	        //deve ser o mesmo resultado para um mesmo objeto, não pode ser aleatório
	        return this.id.hashCode();
	    }
	
}

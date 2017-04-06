package com.gonzalez.samples.javaee7angular.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="musica")
public class Musica {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idn")
    @SequenceGenerator(name = "idn", sequenceName = "idn")
	private Long id;

    private String estilo;
    private String artista;
	private String nome;
    private String album;

	private String linkUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	
	 public String getArtista() {
			return artista;
		}

		public void setArtista(String artista) {
			this.artista = artista;
		}
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAlbum() {
		return album;	}
	
	public void setAlbum(String album) {
		this.album = album;	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
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
	        if(!(obj instanceof Musica)) return false; 

	        //se forem o mesmo objeto, retorna true
	        if(obj == this) return true;

	        // aqui o cast é seguro por causa do teste feito acima
	        Musica imovel = (Musica) obj; 

	        //aqui você compara a seu gosto, o ideal é comparar atributo por atributo
	       return id.equals(imovel.id);
	    }
	
	 @Override
	    public int hashCode() {
	        //deve ser o mesmo resultado para um mesmo objeto, não pode ser aleatório
	        return this.id.hashCode();
	    }
	
}

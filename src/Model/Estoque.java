package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Estoque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int estCodigo;
	@Column(columnDefinition = "int NULL")
	private int estProd;
	@Column(columnDefinition = "int NULL")
	private int estQuantidade;

	
	
	public Estoque() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estoque(int estCodigo, int estProd, int estQuantidade) {
		super();
		this.estCodigo = estCodigo;
		this.estProd = estProd;
		this.estQuantidade = estQuantidade;
	}

	public int getEstCodigo() {
		return estCodigo;
	}

	public void setEstCodigo(int estCodigo) {
		this.estCodigo = estCodigo;
	}

	public int getEstProd() {
		return estProd;
	}

	public void setEstProd(int estProd) {
		this.estProd = estProd;
	}

	public int getEstQuantidade() {
		return estQuantidade;
	}

	public void setEstQuantidade(int estQuantidade) {
		this.estQuantidade = estQuantidade;
	}

	
	
	
}

package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Produto")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduto;
	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String nome;
	@Column(columnDefinition = "int NULL")
	private int estoque;
	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String prodTipo;
	@Column(columnDefinition = "float NULL")
	private float preco;

	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produto(int estCodigo, int estProd, int estQuantidade) {
		
		// TODO Auto-generated constructor stub
	}

	public Produto(int idProduto, String nome, int estoque, String prodTipo, float Preco) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
		this.estoque = estoque;
		this.prodTipo = prodTipo;
		this.preco = Preco;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int idEstoque) {
		this.estoque = idEstoque;
	}

	public String getProdTipo() {
		return prodTipo;
	}

	public void setProdTipo(String prodTipo) {
		this.prodTipo = prodTipo;
	}


	public float getPreco() {
		return preco;
	}

	public void setPreco(float Preco) {
		this.preco = Preco;
	}

}

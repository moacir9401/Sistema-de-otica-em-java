package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProdutoVendido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prodVenCodigo;
	@Column(columnDefinition = "int NULL")
	private int prodVenVenda;
	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String prodVenProduto;
	@Column(columnDefinition = "int NULL")
	private int prodVenQuantidade;

	public ProdutoVendido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProdutoVendido(int prodVenCodigo, int prodVenVenda, String prodVenProduto, int prodVenQuantidade) {
		super();
		this.prodVenCodigo = prodVenCodigo;
		this.prodVenVenda = prodVenVenda;
		this.prodVenProduto = prodVenProduto;
		this.prodVenQuantidade = prodVenQuantidade;
	}

	public int getProdVenCodigo() {
		return prodVenCodigo;
	}

	public void setProdVenCodigo(int prodVenCodigo) {
		this.prodVenCodigo = prodVenCodigo;
	}

	public int getProdVenVenda() {
		return prodVenVenda;
	}

	public void setProdVenVenda(int prodVenVenda) {
		this.prodVenVenda = prodVenVenda;
	}

	public String getProdVenProduto() {
		return prodVenProduto;
	}

	public void setProdVenProduto(String prodVenProduto) {
		this.prodVenProduto = prodVenProduto;
	}

	public int getProdVenQuantidade() {
		return prodVenQuantidade;
	}

	public void setProdVenQuantidade(int prodVenQuantidade) {
		this.prodVenQuantidade = prodVenQuantidade;
	}

}

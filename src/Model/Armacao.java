package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idProduto")
public class Armacao extends Produto {

	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String material;
	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String cor;
	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String formato;
	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String aro;

	public Armacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Armacao(int estCodigo, int estProd, int estQuantidade) {
		super(estCodigo, estProd, estQuantidade);
		// TODO Auto-generated constructor stub
	}

	public Armacao(int idProduto, String nome, int idEstoque, String prodTipo, float preco) {
		super(idProduto, nome, idEstoque, prodTipo, preco);
		// TODO Auto-generated constructor stub
	}

	public Armacao(String material, String cor, String formato, String aro) {
		super();
		this.material = material;
		this.cor = cor;
		this.formato = formato;
		this.aro = aro;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getAro() {
		return aro;
	}

	public void setAro(String aro) {
		this.aro = aro;
	}

}

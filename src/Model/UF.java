package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UF {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codUF;
	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String descricao;
	
	
	
	public UF() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UF(int codUF, String descricao) {
		super();
		this.codUF = codUF;
		this.descricao = descricao;
	}
	public int getCodUF() {
		return codUF;
	}
	public void setCodUF(int codUF) {
		this.codUF = codUF;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	

}

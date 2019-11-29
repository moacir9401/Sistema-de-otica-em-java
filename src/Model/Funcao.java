package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Funcao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int funcaoCodigo;
	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String funcaoDescricao;

	public Funcao(int funcaoCodigo, String funcaoDescricao) {
		super();
		this.funcaoCodigo = funcaoCodigo;
		this.funcaoDescricao = funcaoDescricao;
	}

	public Funcao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getFuncaoCodigo() {
		return funcaoCodigo;
	}

	public void setFuncaoCodigo(int funcaoCodigo) {
		this.funcaoCodigo = funcaoCodigo;
	}

	public String getFuncaoDescricao() {
		return funcaoDescricao;
	}

	public void setFuncaoDescricao(String funcaoDescricao) {
		this.funcaoDescricao = funcaoDescricao;
	}

}

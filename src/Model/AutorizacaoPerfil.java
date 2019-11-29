package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.swing.JFrame;

@Entity
public class AutorizacaoPerfil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String funcao;

	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String perfilFuncao;

	@Column(columnDefinition = "VARCHAR(10) NULL")
	private String permissao;

	public AutorizacaoPerfil() {
	}

	public AutorizacaoPerfil(int codigo, String funcao, String perfilFuncao, String permissao) {
		super();
		this.codigo = codigo;
		this.funcao = funcao;
		this.perfilFuncao = perfilFuncao;
		this.permissao = permissao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getPerfilFuncao() {
		return perfilFuncao;
	}

	public void setPerfilFuncao(String perfilFuncao) {
		this.perfilFuncao = perfilFuncao;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	

}

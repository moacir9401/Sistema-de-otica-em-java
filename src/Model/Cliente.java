package Model;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "idPessoa")
public class Cliente extends Pessoa {

	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String cpf;
	@Column(columnDefinition = "VARCHAR(20) NULL")
	private String rg;
	@Column(columnDefinition = "VARCHAR(255) NULL")
	private String descricao;

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Cliente(int idCliente, String cpf, String rg, String descricao) {
		this.cpf = cpf;
		this.rg = rg;
		this.descricao = descricao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

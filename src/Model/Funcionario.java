package Model;

import javax.persistence.*;

@Entity

@PrimaryKeyJoinColumn(name = "idPessoa")
public class Funcionario extends Pessoa {
	@Column(columnDefinition = "VARCHAR(110) NULL")
	@JoinColumn(name = "perfilFuncao")
	private String funFuncao;
	@Column(columnDefinition = "VARCHAR(110) NULL")
	private String funRG;
	@Column(columnDefinition = "VARCHAR(11) NULL")
	private String funCPF;
	@Column(columnDefinition = "VARCHAR(50) NULL")
	private String funLogin;
	@Column(columnDefinition = "VARCHAR(50) NULL")
	private String funSenha;

	public Funcionario(String funFuncao, String funRG, String funCPF, String funLogin, String funSenha) {
		super();
		this.funFuncao = funFuncao;
		this.funRG = funRG;
		this.funCPF = funCPF;
		this.funLogin = funLogin;
		this.funSenha = funSenha;
	}

	public Funcionario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFunFuncao() {
		return funFuncao;
	}

	public void setFunFuncao(String funFuncao) {
		this.funFuncao = funFuncao;
	}

	public String getFunRG() {
		return funRG;
	}

	public void setFunRG(String funRG) {
		this.funRG = funRG;
	}

	public String getFunCPF() {
		return funCPF;
	}

	public void setFunCPF(String funCPF) {
		this.funCPF = funCPF;
	}

	public String getFunLogin() {
		return funLogin;
	}

	public void setFunLogin(String funLogin) {
		this.funLogin = funLogin;
	}

	public String getFunSenha() {
		return funSenha;
	}

	public void setFunSenha(String funSenha) {
		this.funSenha = funSenha;
	}

}

package Model;

import javax.persistence.*;

@Entity
@Table(name = "Pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPessoa", unique = true)
	private int idPessoa;

	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String nome;
	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String rua;
	@Column(columnDefinition = "int NULL")
	private int numRua;
	@Column(columnDefinition = "VARCHAR(50) NULL")
	private String complemento;
	@Column(columnDefinition = "VARCHAR(80) NULL")
	private String bairro;
	@Column(columnDefinition = "VARCHAR(80) NULL")
	private String cidade;
	@Column(columnDefinition = "VARCHAR(2) NULL")
	private String uf;
	@Column(columnDefinition = "VARCHAR(11) NULL")
	private String cep;
	@Column(columnDefinition = "VARCHAR(11) NULL")
	private String telefone;
	@Column(columnDefinition = "VARCHAR(255) NULL")
	private String descricao;

	public Pessoa() {
	}

	public Pessoa(int idPessoa, String nome, String rua, int numRua, String complemento, String bairro, String cidade,
			String uf, String cep, String telefone, String descricao) {
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.rua = rua;
		this.numRua = numRua;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
		this.telefone = telefone;
		this.descricao = descricao;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumRua() {
		return numRua;
	}

	public void setNumRua(int numRua) {
		this.numRua = numRua;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

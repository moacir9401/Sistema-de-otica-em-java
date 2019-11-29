package Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int venCodigo;
	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String venFormaPagamento;
	@Column(columnDefinition = "VARCHAR(100)  NULL")
	private String venFuncionario;
	@Column(columnDefinition = "VARCHAR(100) NULL")
	private String venCliente;
	@Column(columnDefinition = "Date NULL")
	private Date venDataVenda;
	@Column(columnDefinition = "int NULL")
	private Float venTotalVenda;

	
	public Venda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Venda(int venCodigo, String venFormaPagamento, String venFuncionario, String venCliente, Date venDataVenda,
			Float venTotalVenda) {
		super();
		this.venCodigo = venCodigo;
		this.venFormaPagamento = venFormaPagamento;
		this.venFuncionario = venFuncionario;
		this.venCliente = venCliente;
		this.venDataVenda = venDataVenda;
		this.venTotalVenda = venTotalVenda;
	}

	public int getVenCodigo() {
		return venCodigo;
	}

	public void setVenCodigo(int venCodigo) {
		this.venCodigo = venCodigo;
	}

	public String getVenFormaPagamento() {
		return venFormaPagamento;
	}

	public void setVenFormaPagamento(String venFormaPagamento) {
		this.venFormaPagamento = venFormaPagamento;
	}

	public String getVenFuncionario() {
		return venFuncionario;
	}

	public void setVenFuncionario(String venFuncionario) {
		this.venFuncionario = venFuncionario;
	}

	public String getVenCliente() {
		return venCliente;
	}

	public void setVenCliente(String venCliente) {
		this.venCliente = venCliente;
	}

	public Date getVenDataVenda() {
		return venDataVenda;
	}

	public void setVenDataVenda(Date venDataVenda) {
		this.venDataVenda = venDataVenda;
	}

	public Float getVenTotalVenda() {
		return venTotalVenda;
	}

	public void setVenTotalVenda(Float venTotalVenda) {
		this.venTotalVenda = venTotalVenda;
	}

	
}

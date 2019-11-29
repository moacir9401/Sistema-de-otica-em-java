package Controller;

/**
 * Moacir Afonso Alves
 */

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.Conexao;
import Model.Cliente;
import View.CadastroCliente;
import utilitarios.LtpLib;

public class BizCliente implements Icrud {

	private static ArrayList<Cliente> listCliente = new ArrayList<Cliente>();

	public ArrayList<Cliente> getList() {
		return listCliente;
	}

	@Override
	public boolean Cadastrar(Object cliente) {

		try {
			Conexao.Save(cliente);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	

	@Override
	public boolean Deletar(String id) {
		Conexao.Remove(new Cliente(), Integer.parseInt(id));
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Cliente> Consultar(String id) {

		return (ArrayList<Cliente>) Conexao.Show("select p from Cliente p where p.idPessoa = '" + id + "'");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Cliente> Consultar() {

		return (ArrayList<Cliente>) Conexao.Show("select p from Cliente p");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Cliente> Consultar(String campo, String valor) {

		return (ArrayList<Cliente>) Conexao.Show("select p from Cliente p where " + campo + " = '" + valor + "'");

	}
	public ArrayList<Cliente> buscarCliente(String where) {

		return (ArrayList<Cliente>) Conexao.Show("select p from Cliente " + where);

	}
	
	
	public DefaultTableModel CarregaTable(DefaultTableModel model,String campo,String nome) {

		model.setRowCount(0);

		listCliente = Consultar(campo,nome);
		
		for (Model.Cliente cliente : listCliente) {
			model.addRow(new Object[] { cliente.getIdPessoa(), cliente.getNome(), cliente.getTelefone(),LtpLib.formatarCPF(cliente.getCpf()) });
		}

		return model;
	}
	
}

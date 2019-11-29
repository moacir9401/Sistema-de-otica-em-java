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
import Model.Funcionario;
import View.CadastroCliente;
import View.CadastroFuncionario;
import utilitarios.LtpLib;

public class BizFuncionario implements Icrud {

	private static ArrayList<Funcionario> list = new ArrayList<Funcionario>();

	public ArrayList<Funcionario> getList() {
		return list;
	}

	@Override
	public boolean Cadastrar(Object funcionario) {

		try {
			Conexao.Save(funcionario);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean Deletar(String id) {
		Conexao.Remove(new Funcionario(), Integer.parseInt(id));
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Funcionario> Consultar(String id) {
int t = 0;
		return (ArrayList<Funcionario>) Conexao
				.Show("select p from Funcionario p where p.idPessoa = '" + id + "'");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Funcionario> Consultar() {

		return (ArrayList<Funcionario>) Conexao.Show("select p from Funcionario p");

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Funcionario> ConsultarLogin(String login, String senha) {

		return (ArrayList<Funcionario>) Conexao
				.Show("select f from Funcionario f where funLogin = '" + login + "' and funSenha = '" + senha + "'");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Funcionario> Consultar(String campo, String valor) {

		return (ArrayList<Funcionario>) Conexao
				.Show("select p from Funcionario p where " + campo + " = '" + valor + "'");

	}

	public DefaultTableModel CarregaTable(DefaultTableModel model, String campo, String nome) {

		model.setRowCount(0);

		list = Consultar(campo, nome);

		for (Funcionario func : list) {
			if (func != null) {
				model.addRow(new Object[] { func.getIdPessoa(), func.getNome(), LtpLib.formatarCPF(func.getFunCPF()),
						func.getFunLogin() });
			}
		}

		return model;
	}

	

}

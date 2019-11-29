package Controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.Conexao;
import Model.Funcao;
import View.CadastroCliente;
import View.CadastroFuncao;
import utilitarios.LtpLib;

public class BizFuncao implements Icrud {
	private static ArrayList<Funcao> list = new ArrayList<Funcao>();

	public ArrayList<Funcao> getList() {
		return list;
	}
@Override
	public boolean Cadastrar(Object funcao) {

		try {
			Conexao.Save(funcao);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean Deletar(String id) {
		Conexao.Remove(new Funcao(), Integer.parseInt(id));
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Funcao> Consultar(String id) {

		return (ArrayList<Funcao>) Conexao.Show("select p from Funcao p where p.funcaoCodigo = '" + id + "'");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Funcao> Consultar() {

		return (ArrayList<Funcao>) Conexao.Show("select p from Funcao p");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Funcao> Consultar(String campo, String valor) {

		return (ArrayList<Funcao>) Conexao.Show("select p from Funcao p where " + campo + " = '" + valor + "'");

	}
	public DefaultTableModel CarregaTable(DefaultTableModel model,String campo,String nome) {

		model.setRowCount(0);

		list = Consultar(campo,nome);
		
		for (Funcao funcao : list) {
			model.addRow(new Object[] { funcao.getFuncaoCodigo(), funcao.getFuncaoDescricao() });
		}

		return model;
	}
	public void VisualizarFuncao(String codigo,JTable table, JFrame tela) {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Selecione um registro");
		} else {
			CadastroFuncao cadFuncao = new CadastroFuncao();
			cadFuncao.mostrar(tela, "Visualizar");
			cadFuncao.preencherCampo(Consultar(codigo));
			cadFuncao.bloqueioCampo();
		}
	}
}

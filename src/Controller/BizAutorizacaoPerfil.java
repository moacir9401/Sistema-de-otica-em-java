package Controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import DAO.Conexao;
import Model.AutorizacaoPerfil;
public class BizAutorizacaoPerfil implements Icrud {

	@Override
	public boolean Cadastrar(Object AutorizacaoPerfil) {

		try {
			Conexao.Save(AutorizacaoPerfil);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean Deletar(String id) {
		Conexao.Remove(new AutorizacaoPerfil(), Integer.parseInt(id));
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<AutorizacaoPerfil> Consultar(String where) {

		return (ArrayList<AutorizacaoPerfil>) Conexao.Show("select p from AutorizacaoPerfil p " + where);

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<AutorizacaoPerfil> Consultar() {

		return (ArrayList<AutorizacaoPerfil>) Conexao.Show("select p from AutorizacaoPerfil p");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<AutorizacaoPerfil> Consultar(String campo, String valor) {

		return (ArrayList<AutorizacaoPerfil>) Conexao
				.Show("select p from AutorizacaoPerfil p where " + campo + " = '" + valor + "'");

	}

	public DefaultTableModel CarregaTable(DefaultTableModel model, String where) {

		model.setRowCount(0);

		ArrayList<AutorizacaoPerfil> list = Consultar(where);

		for (AutorizacaoPerfil AutorizacaoPerfil : list) {
			model.addRow(new Object[] { AutorizacaoPerfil.getCodigo(), AutorizacaoPerfil.getFuncao() });
		}

		return model;
	}

}

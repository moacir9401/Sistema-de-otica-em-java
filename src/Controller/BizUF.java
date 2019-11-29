package Controller;

/**
 * Moacir Afonso Alves
 */

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.Conexao;
import Model.UF;


public class BizUF implements Icrud {

	private static ArrayList<UF> listUF = new ArrayList<UF>();

	public ArrayList<UF> getList() {
		return listUF;
	}

	@Override
	public boolean Cadastrar(Object uf) {

		try {
			Conexao.Save(uf);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean Deletar(String id) {
		Conexao.Remove(new UF(), Integer.parseInt(id));
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<UF> Consultar(String id) {

		return (ArrayList<UF>) Conexao.Show("select p from UF p where p.idUF = '" + id + "'");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<UF> Consultar() {

		return (ArrayList<UF>) Conexao.Show("select p from UF p");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<UF> Consultar(String campo, String valor) {

		return (ArrayList<UF>) Conexao.Show("select p from UF p where " + campo + " = '" + valor + "'");

	}
	
	
}

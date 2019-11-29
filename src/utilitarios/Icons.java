package utilitarios;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Icons {

	public static ImageIcon User(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/man-user.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon Password(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/lock.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon Fundo(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/bg_oticas.jpg"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}
	public static ImageIcon Compra(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/carrinho.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}
	public static ImageIcon Add(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/add.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon Cliente(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/user-58.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon AddCliente(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/user-40.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon EditarCliente(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/user-52.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon ExcluirCliente(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/user-42.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon PesquisarCliente(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/user-48.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon PesquisarFuncionario(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/user-28.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon VisualizarCliente(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/user-50.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon Funcionario(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/user-38.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon AddFuncionario(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/user-20.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon EditarFuncionario(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/user-32.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon ExcluirFuncionario(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/user-22.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon VisualizarFuncionario(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/user-50.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon AddProduto(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/price-tag.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon EditarProduto(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/price-tag-12.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon ExcluirProduto(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/price-tag-2.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon VisualizarProduto(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/price-tag-10.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon DesignarFuncao(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/list-9.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon AddPerfil(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/list.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon PesquisarProduto(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/price-tag-8.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon Produto(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/price-tag-18.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon Edit(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/Add.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}	


	public static ImageIcon Logo(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon PesquisarRelatorio(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/notepad-8.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

	public static ImageIcon Relatorio(int width, int height) {

		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/notepad-10.png"));
		Image img = icone.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		return new ImageIcon(newimg);
	}

}

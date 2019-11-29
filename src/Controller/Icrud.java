package Controller;

import java.util.ArrayList;

public interface Icrud {
	
	public boolean Cadastrar(Object obj);

	public ArrayList<?> Consultar();

	public ArrayList<?> Consultar(String id);

	public ArrayList<?> Consultar(String campo, String valor);

	public boolean Deletar(String id);

}

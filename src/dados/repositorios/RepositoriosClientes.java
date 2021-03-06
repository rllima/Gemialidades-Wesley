package dados.repositorios;

import java.io.IOException;

import negocios.classesBasicas.Cliente;
import negocios.exceptions.ClienteNaoEncontradoException;
import negocios.exceptions.EmptyListException;

public interface RepositoriosClientes {
	
	void inserir(Cliente clientes) throws IOException;
	Cliente procurar(String id);
	void atualizar(String id, Cliente clientes) throws IOException;
	void remover(String id) throws IOException;
	Iterator getIterator() throws EmptyListException;

}

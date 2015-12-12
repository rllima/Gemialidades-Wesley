package negocios.cadastro;

import negocios.classesBasicas.Entrega;
import negocios.exceptions.EmptyListException;
import negocios.exceptions.EntregaJaExisteException;
import negocios.exceptions.EntregaNaoEncontradaException;
import dados.repositorios.Iterator;
import dados.repositorios.RepositorioEntregas;
import dados.repositorios.RepositorioEntregasFila;

public class CadastroEntregas {
	
	private RepositorioEntregas repositorioEntregas;
	
	public CadastroEntregas(RepositorioEntregas repositorioEntregas) {
		this.repositorioEntregas = repositorioEntregas;
	}
	public void cadastrar(Entrega entrega) throws EntregaJaExisteException {
		if(repositorioEntregas.procurar(entrega.getId()) == null){
			repositorioEntregas.inserir(entrega);
		}else {
			throw new EntregaJaExisteException();
		}
	
		
	} 
	public void remover(String id) throws EntregaNaoEncontradaException, EmptyListException{
		if(repositorioEntregas.procurar(id) != null){
			repositorioEntregas.remover(id);
		}else{
			throw new EntregaNaoEncontradaException();
		}
	}
	public Entrega procurar(String id) throws EntregaNaoEncontradaException, EmptyListException {
		Entrega resposta = repositorioEntregas.procurar(id);
		if(resposta == null){
			throw new EntregaNaoEncontradaException();
		}
		return resposta;
		
	}
	public void atualizar(String id, Entrega entrega) throws EntregaNaoEncontradaException, EmptyListException{
		if(repositorioEntregas.procurar(id) == null){
			throw new EntregaNaoEncontradaException();
		}else{
			repositorioEntregas.atualizar(id, entrega);
		}
	}
	
	public Iterator getIteratorPendentes() throws EmptyListException {
		Iterator itr = null;
		if (repositorioEntregas instanceof RepositorioEntregasFila) {
			if (((RepositorioEntregasFila) repositorioEntregas).isEmpty()) {
				throw new EmptyListException();
			} else {
				itr = repositorioEntregas.getIteratorPendentes();
			}
		} else {
			itr = repositorioEntregas.getIteratorPendentes();
		}
		return itr;
	}
	
	public Iterator getIteratorEnviadas() throws EmptyListException {
		Iterator itr = null;
		if (repositorioEntregas instanceof RepositorioEntregasFila) {
			if (((RepositorioEntregasFila) repositorioEntregas).isEmpty()) {
				throw new EmptyListException();
			} else {
				itr = repositorioEntregas.getIteratorEnviadas();
			}
		} else {
			itr = repositorioEntregas.getIteratorEnviadas();
		}
		return itr;
	}

}

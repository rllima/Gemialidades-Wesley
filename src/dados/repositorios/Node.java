package dados.repositorios;

public class Node<T> {
	
	private T dado;
	private Node<T> proximo;
	private Node<T> anterior;
	
	/* Construtor que cria no e prepara a lista pra receber o
	 * proximo objeto. � usado quando a lista est� vazia
	 */
	public Node(T objeto){
		this(objeto, null);
	}

	// Construtor que cria um no com um objeto(dado) e referencia o proximo da lista.
	public Node(T objeto, Node<T> proximo) {
		this.dado = objeto;
		this.proximo = proximo;
	}
	
	public Node(Node<T> anterior, T objeto, Node<T> proximo) {
		this.anterior = anterior;
		this.dado = objeto;
		this.proximo = proximo;
	}

	public void setDado(T dado) {
		this.dado = dado;
	}

	// Retorna os dados daquele no
	public T getDado() {
		return dado;
	}

	// Retorna a referencia ao proximo da lista
	public Node<T> getProximo() {
		return proximo;
	}

	public void setProximo(Node<T> proximo) {
		this.proximo = proximo;
	}

	public Node<T> getAnterior() {
		return anterior;
	}
	
	public void setAnterior(Node<T> anterior) {
		this.anterior = anterior;
	}

}

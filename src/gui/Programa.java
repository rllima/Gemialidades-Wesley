package gui;

import java.io.FileNotFoundException;
import java.io.IOException;

import fachada.GemialidadesLoja;
import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Endereco;
import negocios.classesBasicas.Guloseimas;
import negocios.classesBasicas.Produto;
import negocios.classesBasicas.Travessuras;
import negocios.exceptions.ClienteJaExisteException;
import negocios.exceptions.EmptyListException;
import negocios.exceptions.EntregaJaExisteException;
import negocios.exceptions.EntregaNaoEncontradaException;
import negocios.exceptions.NaoHaEntregasException;
import negocios.exceptions.ProdutoJaExisteException;
import negocios.exceptions.ProdutoNaoEncontradoException;

public class Programa {

	/** Classe que testa a fachada, apenas. */

	public static void main(String[] args) {

		try {
			GemialidadesLoja loja = new GemialidadesLoja();
            System.out.println("Loja instanciada com sucesso");
			Endereco endereco = new Endereco("Recife", "Rua do crack", "90", "52041030", "Do lado da rua mizeravi");
			
			Cliente cliente1 = new Cliente("Alohea", "55", endereco, "alohea", "552");
			Cliente cliente2 = new Cliente("Rodrigo", "15", endereco, "523", "rodelicia");
			Cliente cliente3 = new Cliente("dougie", "3", endereco, "como", "salsicha");
			
			Produto travessura1 = new Travessuras("Orelha Extens�vel", "666", "Ouvir conversa alheia", 2, 15, 15.2);
			Produto guloseima1 = new Guloseimas("Sapo de chocolate", "555", "Feijoeszinhos com sabores diversos",
					"Nunca saber�s", 15.2);
			Produto travessura2 = new Travessuras("Kit mata-aula", "777", "Kit fugir-de-ricardo", 0, 0, 6.8);
		
			System.out.println("Clientes e produtos instanciados com sucesso!\nCadastrando clientes...");
			loja.inserirCliente(cliente1);
			loja.inserirCliente(cliente2);
			loja.inserirCliente(cliente3);
			
			System.out.println("Sucesso! Cadastrando travessuras...");
			loja.cadastrarProduto(travessura1);
			loja.cadastrarProduto(guloseima1);
			loja.cadastrarProduto(travessura2);
			
			System.out.println("Sucesso! Executando m�todo de venda...");
			loja.vender("2255", "666", "523");
			
			System.out.println("Sucesso! Executando m�todo de envio...");
			loja.enviar();
			

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ProdutoJaExisteException e) {
			System.out.println(e.getMessage());
		} catch (ClienteJaExisteException e) {
			System.out.println(e.getMessage());
		} catch (EntregaJaExisteException e) {
			System.out.println(e.getMessage());
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		} catch (EntregaNaoEncontradaException e) {
			System.out.println(e.getMessage());
		} catch (ProdutoNaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (NaoHaEntregasException e) {
			System.out.println(e.getMessage());
		}
	}

}

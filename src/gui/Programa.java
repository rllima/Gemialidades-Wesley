package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.DefaultListModel;

import dados.repositorios.Iterator;
import fachada.GemialidadesLoja;
import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Endereco;
import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Guloseimas;
import negocios.classesBasicas.Produto;
import negocios.classesBasicas.Travessuras;
import negocios.exceptions.ClienteJaExisteException;
import negocios.exceptions.ClienteNaoEncontradoException;
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
			String[] entregas = "51651 5656165 5615656 654654".split(" ");
			
			Cliente cliente1 = new Cliente("Alohea", "55", endereco, "alohea", "552");
			Cliente cliente2 = new Cliente("Rodrigo", "15", endereco, "523", "rodelicia", entregas);
			Cliente cliente3 = new Cliente("dougie", "3", endereco, "como", "salsicha");

			
			Produto travessura1 = new Travessuras("Orelha Extens�vel", "66aaa", "Ouvir conversa alheia", 2, 15, 15.2);
			Produto guloseima1 = new Guloseimas("Sapo de chocolate", "5a5a5", "Feijoeszinhos com sabores diversos",
					"Nunca saber�s", 15.2);
			Produto travessura2 = new Travessuras("Kit mata-aula", "7a7a7", "Kit fugir-de-ricardo", 0, 0, 6.8);

			System.out.println("Clientes e produtos instanciados com sucesso!\nCadastrando clientes...");
			loja.inserirCliente(cliente1);
			loja.inserirCliente(cliente2);
			loja.inserirCliente(cliente3);

			System.out.println("Sucesso! Cadastrando travessuras...");
			loja.cadastrarProduto(travessura1);
			loja.cadastrarProduto(guloseima1);
			loja.cadastrarProduto(travessura2);

			System.out.println("Sucesso! Executando m�todo de envio da entrega...");
			System.out.println("Sucesso! Executando m�todo de venda do item 666 ao cliente 523...");
			loja.vender("66aaa", "523");
			loja.vender("5a5a5", "523");
			loja.vender("7a7a7","523");

			loja.enviar();

			System.out.println("Sucesso! Imprimindo estoque...\n");

			Iterator prod = loja.getIteratorProduto();
			while(prod.hasNext()) {
				System.out.println(prod.next());
				System.out.println("\n");
			}

			System.out.println("Imprimindo dados de clientes...");

			Iterator clientes = loja.getIteratorCliente();
			while(clientes.hasNext()) {
				System.out.println(clientes.next());
				System.out.println("\n");
			}

			System.out.println("Imprimindo entregas pendentes...");

			Iterator pend = loja.getIteratorEntPendentes();
			while(pend.hasNext()) {
				System.out.println(pend.next());
				System.out.println("\n");
			}

			System.out.println("Imprimindo entregas enviadas...");

			Iterator env = loja.getIteratorEntEnviadas();
			while(env.hasNext()) {
				System.out.println(env.next());
				System.out.println("\n");
			}

            System.out.println("Gerando relatorios de Estoque...");

			FileOutputStream rel = new FileOutputStream("RelatorioProdutosEstoque.txt");
			ObjectOutputStream rl = new ObjectOutputStream(rel);
            Iterator rela = GemialidadesLoja.getInstance().getIteratorProduto();
			while ( rela.hasNext()){
				rl.writeObject(rela.next().toString() + "\n");

			rel.close();
			rl.close();
			}

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
		} catch (ClienteNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}
	}

}

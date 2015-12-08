package fachada;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import dados.repositorios.*;
import negocios.cadastro.*;
import negocios.classesBasicas.Cliente;
import negocios.classesBasicas.Entrega;
import negocios.classesBasicas.Produto;
import negocios.exceptions.ClienteJaExisteException;
import negocios.exceptions.ClienteNaoEncontradoException;
import negocios.exceptions.EmptyListException;
import negocios.exceptions.EntregaJaExisteException;
import negocios.exceptions.EntregaNaoEncontradaException;
import negocios.exceptions.ProdutoJaExisteException;
import negocios.exceptions.ProdutoNaoEncontradoException;
import negocios.exceptions.SenhaIncoretaException;

public class GemialidadesLoja {
	private CadastroClientes repClientes;
	private CadastroProdutos repProdutos;
	private CadastroEntregas repEntregas;
	private File config;
	private File excel;
	private char tipoRep;
	private HSSFWorkbook workbook;
	private static GemialidadesLoja instance;
	
	public static GemialidadesLoja getInstance() throws FileNotFoundException, IOException{
		if(instance == null){
			instance = new GemialidadesLoja();
		}
		return instance;
	}

	
	public GemialidadesLoja () throws IOException, FileNotFoundException{
		this.config = new File("config\\config.txt");
		FileInputStream in = new FileInputStream(config);
		this.tipoRep = (char) in.read();
		in.close();

		if(tipoRep == 'A' || tipoRep == 'a') {
			this.repClientes = new CadastroClientes(new RepositorioClientesArray());
			this.repProdutos = new CadastroProdutos(new RepositorioProdutosArray());
			this.repEntregas = new CadastroEntregas(new RepositorioEntregasArray());

		} else if (tipoRep == 'L' || tipoRep == 'l') {
			this.repClientes = new CadastroClientes(new RepositorioClientesLista());
			this.repProdutos = new CadastroProdutos(new RepositorioProdutosLista());
			this.repEntregas = new CadastroEntregas(new RepositorioEntregasFila());
			
		} else if (tipoRep == 'E' || tipoRep == 'e') {
			this.excel = new File("planilha.xls");
			if(!excel.exists()) {
				
				excel.createNewFile();
				FileOutputStream out = new FileOutputStream(excel);
				workbook = new HSSFWorkbook();
				workbook.write(out);
				out.close();
			} else {
				FileInputStream entradaArquivo = new FileInputStream(excel);
				workbook = new HSSFWorkbook(entradaArquivo);
				entradaArquivo.close();
				
			}
			this.repClientes = new CadastroClientes(new RepositorioClientesExcel(workbook));
			this.repProdutos = new CadastroProdutos(new RepositorioProdutosExcel(workbook));
			this.repEntregas = new CadastroEntregas(new RepositorioEntregasExcel(workbook));
		}
		
		/* Lembrar de colocar IF's nos repositorios pra conferir se ja existe a planilha(aba) */

	}

	public void atualizarCliente(String id, Cliente clientes)
			throws ClienteNaoEncontradoException, IOException, EmptyListException {
		repClientes.atualizar(id, clientes);
	}

	public void inserirCliente(Cliente clientes) throws ClienteJaExisteException, IOException {
		repClientes.inserir(clientes);
	}

	public Cliente procurarCliente(String id) throws ClienteNaoEncontradoException, EmptyListException {
		return repClientes.procurar(id);
	}

	public void removerCliente(String id) throws ClienteNaoEncontradoException, IOException, EmptyListException {
		repClientes.remover(id);
	}



	//Produtos


	public void cadastrarProduto(Produto produto) throws ProdutoJaExisteException, IOException {
		repProdutos.cadastrar(produto);
		excel = new File("planilha.xls");
		FileOutputStream saidaArquivo = new FileOutputStream(excel);
		workbook.write(saidaArquivo);
		saidaArquivo.close();
		
	}

	public void removerProduto(String codigo) throws ProdutoNaoEncontradoException {
		repProdutos.remover(codigo);
	}

	public Produto procurarProduto(String codigo) throws ProdutoNaoEncontradoException {
		return repProdutos.procurar(codigo);
	}

	public void atualizarProduto(String codigo, Produto produto)
			throws ProdutoNaoEncontradoException {
		repProdutos.atualizar(codigo, produto);
	}


	//Entregas

	public void cadastrarEntrega(Entrega entrega) throws EntregaJaExisteException {
		repEntregas.cadastrar(entrega);
	}

	public void removerEntrega(String id) throws EntregaNaoEncontradaException, EmptyListException {
		repEntregas.remover(id);
	}

	public Entrega procurarEntrega(String id) throws EntregaNaoEncontradaException, EmptyListException {
		return repEntregas.procurar(id);
	}

	public void atualizarEntrega(String id, Entrega entrega)
			throws EntregaNaoEncontradaException, EmptyListException {
		repEntregas.atualizar(id, entrega);
	}
	
	//Login
	
	public boolean login(String id, String senha) throws ClienteNaoEncontradoException, SenhaIncoretaException, EmptyListException {
		boolean resposta = false;
		Cliente cliente = this.procurarCliente(id);
		if((cliente.getSenha().equals(senha))) {
			resposta = true;
		} else {
			throw new SenhaIncoretaException();
		}
		return resposta;
	}
	
	public void closeWorkbook() throws IOException {
		workbook.close();
	}
   


}

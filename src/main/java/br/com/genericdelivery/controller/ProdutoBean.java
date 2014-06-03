package br.com.genericdelivery.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.genericdelivery.model.entity.Categoria;
import br.com.genericdelivery.model.entity.Produto;
import br.com.genericdelivery.model.entity.Tela;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.exceptions.ProdutoJaCadastrado;
import br.com.genericdelivery.service.interfaces.ProdutoService;
import br.com.genericdelivery.view.Messages;
import br.com.genericdelivery.view.web.faces.util.FacesUtil;

@Controller("produtoBean")
public class ProdutoBean {

	@Autowired
	private ProdutoService produtoService;

	private Produto produto;

	private List<Categoria> categorias;

	File file;

	public void fileUploadAction(FileUploadEvent event) {
		try {
			FacesContext aFacesContext = FacesContext.getCurrentInstance();
			ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

			String realPath = context.getRealPath("/");

			// Aqui cria o diretorio caso não exista
			file = new File(realPath + "/temp/");
			file.mkdirs();
			byte[] arquivo = event.getFile().getContents();

			FileUtils.writeByteArrayToFile(file, arquivo);


		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if (produto.getCodigo() != null) {
				produtoService.alterar(produto);
				FacesUtil.showMessageInfo(Messages.M6);
			} else {
				produtoService.salvar(produto);
				FacesUtil.showMessageInfo(Messages.M5);
			}
			salvarArquivo();
		} catch (CamposObrigatoriosNaoPrenchidos e) {
			FacesUtil.showMessageError(Messages.M2);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ProdutoJaCadastrado e) {
			FacesUtil.showMessageError(Messages.M20);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void salvarArquivo() throws IOException{
		if(file != null){
			FacesContext aFacesContext = FacesContext.getCurrentInstance();
			ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

			String realPath = context.getRealPath("/");

			// Aqui cria o diretorio caso não exista
			File fileAux = new File(realPath + "/img/");
			
			FileUtils.copyFileToDirectory(file, fileAux, true);
			
			produto.setImagem(fileAux.getAbsolutePath() + file.getName()); 
			
			file.delete();
		}
	}

	public String redirecionarCadastro() {
		limparCampos();
		return FacesUtil.facesRedirectUrl(Tela.PRODUTO_CADASTRO);
	}

	private void limparCampos() {
		produto = new Produto();
		produto.setAtivo(true);
	}

	/*
	 * public String voltar() { return FacesUtil.facesRedirectUrl(Tela.P); }
	 */

	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();
		}

		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Categoria> getCategorias() {
		if (categorias == null || categorias.isEmpty()) {
			categorias = produtoService.listarCategorias();
		}

		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}

package br.com.letscode.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.letscode.dto.ProdutoDTO;
import br.com.letscode.entidade.ProdutoEntidade;
import br.com.letscode.repositorio.ProdutoRepositorio;

@Controller
@RequestMapping("/produtos")
public class ProdutoControlador {

    @GetMapping("/form")
    public String obterFormulario() {
        return "produto-form";
    }

    @PostMapping("/cadastrar-produto")
    public RedirectView cadastrarProduto(ProdutoDTO produtoDTO, RedirectAttributes redirectAttributes) {
        System.out.println("ATRIBUTO RECEBIDO: " + produtoDTO);

        ProdutoRepositorio repositorio = new ProdutoRepositorio();

        ProdutoEntidade entidade = new ProdutoEntidade(produtoDTO);

        ProdutoEntidade entidadeSalva = repositorio.salvar(entidade);

        System.out.println("OBJETO SALVO: " + entidadeSalva.getId());

//        model.addAttribute("produtos", repositorio.obterTodos());

        // REPASSANDO ATRIBUTOS PARA LISTAR
        redirectAttributes.addFlashAttribute("produtos", repositorio.obterTodos());

        RedirectView redirectView = new RedirectView("/produtos/listar", true);

        return redirectView;
    }

    @GetMapping("/listar")
    public String listar() {
        return "listar-produtos";
    }

}

package dwbe.lojatenis.DAO;

import dwbe.lojatenis.Model.Produto;

import java.util.ArrayList;
import java.util.List;
public class ProdutoDAO {
    private List<Produto> produtos;
    private int idProduto;
    public ProdutoDAO() {
        this.produtos = new ArrayList<>();
        this.idProduto = 3;
    }
    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public int getIdProduto(){
        this.idProduto++;
        return idProduto;
    }
    public void setIdProduto() {this.idProduto++;};

    public void cadastrarProduto(Produto produto) {
        produtos.add(produto);
    }
    public List<Produto> listarProdutos() {
        return produtos;
    }
    public void excluirProduto(String id) {
        Produto produtoEncontrado = produtos.stream()
                .filter(produto -> produto.getId().equals(id))
                .findAny()
                .orElse(null);

        if (produtoEncontrado != null) {
            this.produtos.remove(produtoEncontrado);
            System.out.println("Produto " + produtoEncontrado + " excluido");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}

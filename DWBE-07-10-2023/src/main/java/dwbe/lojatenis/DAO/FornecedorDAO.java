package dwbe.lojatenis.DAO;

import dwbe.lojatenis.Model.Fornecedor;

import java.util.ArrayList;
import java.util.List;
public class FornecedorDAO {
    private List<Fornecedor> fornecedores;
    private int idFornecedor;
    public FornecedorDAO() {
        this.fornecedores = new ArrayList<>();
        this.idFornecedor = 3;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public int getIdFornecedor(){
        this.idFornecedor++;
        return idFornecedor;
    }
    public void setIdFornecedor() {this.idFornecedor++;};

    public void cadastrarFornecedor(Fornecedor fornecedor) {
        fornecedores.add(fornecedor);
    }

    public List<Fornecedor> listarFornecedores() {
        return fornecedores;
    }

    public void excluirFornecedor(String id) {
        Fornecedor fornecedorEncontrado = fornecedores.stream()
                .filter(fornecedor -> fornecedor.getId().equals(id))
                .findAny()
                .orElse(null);

        if (fornecedorEncontrado != null) {
            this.fornecedores.remove(fornecedorEncontrado);
            System.out.println("Fornecedor " + fornecedorEncontrado + " excluido");
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }
}

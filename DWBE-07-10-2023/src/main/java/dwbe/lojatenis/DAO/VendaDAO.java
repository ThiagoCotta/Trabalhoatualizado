package dwbe.lojatenis.DAO;

import dwbe.lojatenis.Model.Cliente;
import dwbe.lojatenis.Model.Estoque;
import dwbe.lojatenis.Model.Produto;
import dwbe.lojatenis.Model.Venda;

import java.util.ArrayList;
import java.util.List;
public class VendaDAO {
    private ArrayList<Venda> historicoVendas;

    public VendaDAO() {
        this.historicoVendas = new ArrayList<>();
    }
    public ArrayList<Venda> listarProdutos() {
        return historicoVendas;
    }
    public void salvar(Venda venda) {
        historicoVendas.add(venda);
    }
}

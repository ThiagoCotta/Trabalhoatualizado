package dwbe.lojatenis.DAO;

import dwbe.lojatenis.Model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private List<Cliente> clientes;
    private int idCliente;

    public ClienteDAO() {
        this.clientes = new ArrayList<>();
        this.idCliente = 3;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    public int getIdCliente(){
        this.idCliente++;
        return idCliente;
    }
    public void setIdCliente() {this.idCliente++;};
    public void cadastrarClinte(Cliente cliente) {
        clientes.add(cliente);
    }
    public List<Cliente> listarClientes() {
        return clientes;
    }
    public void excluirCliente(String id) {
        Cliente clienteEncontrado = clientes.stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findAny()
                .orElse(null);

        if (clienteEncontrado != null) {
            this.clientes.remove(clienteEncontrado);
            System.out.println("Cliente " + clienteEncontrado + " excluido");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}

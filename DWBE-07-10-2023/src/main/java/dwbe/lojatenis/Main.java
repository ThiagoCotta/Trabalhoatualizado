package dwbe.lojatenis;

import dwbe.lojatenis.DAO.ClienteDAO;
import dwbe.lojatenis.DAO.FornecedorDAO;
import dwbe.lojatenis.DAO.ProdutoDAO;
import dwbe.lojatenis.DAO.VendaDAO;
import dwbe.lojatenis.Model.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClienteDAO clienteDao = new ClienteDAO();
        ProdutoDAO produtoDao = new ProdutoDAO();
        FornecedorDAO fornecedorDao = new FornecedorDAO();
        VendaDAO vendaDAO = new VendaDAO();

        ArrayList<Produto> produtos = new ArrayList<Produto>();
        produtos.add(new Produto("1", 250.0, "36", "Branco", "Nike", "Esportivo"));
        produtos.add(new Produto("2", 300.0, "37", "Preto", "Adidas", "Casual"));
        produtos.add(new Produto("3", 350.0, "38", "Vermelho", "Puma", "Social"));

        ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
        fornecedores.add(new Fornecedor("1", "Thiago Cotta", "123.456.789-00", "Oscar Vidal - Juiz de Fora", "Homem", "31 9 7161-0440", "Thiago.cotta@gmail.com", "123456789", 326, "Nike", "09-03-2022", "Grande", "Venda de calçados", "Ativo"));
        fornecedores.add(new Fornecedor("2", "Ana Paula", "987.654.321-00", "Rua da Paz - Belo Horizonte", "Feminino", "31 9 9999-9999", "ana.paula@gmail.com", "98.765.432/0001-01", 789, "Adidas", "01-01-2023", "Médio", "Venda de roupas", "Inativo"));
        fornecedores.add(new Fornecedor("3", "João da Silva", "012.345.678-90", "Avenida Brasil - São Paulo", "Masculino", "31 9 8888-8888", "joao.silva@gmail.com", "01.234.567/0001-02", 321, "Puma", "22-02-2024", "Pequeno", "Venda de acessórios", "Ativo"));

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente("1", "Thiago Cotta", "123.456.789-00", "Rua Oscar Vidal, 123 - Juiz de Fora", "Masculino", "31 9 7161-0440", "thiago.cotta@gmail.com", "Ativo"));
        clientes.add(new Cliente("2", "Ana Paula", "987.654.321-00", "Rua da Paz, 456 - Belo Horizonte", "Feminino", "31 9 9999-9999", "ana.paula@gmail.com", "Inativo"));
        clientes.add(new Cliente("3", "João da Silva", "012.345.678-90", "Avenida Brasil, 789 - São Paulo", "Masculino", "31 9 8888-8888", "joao.silva@gmail.com", "Ativo"));

        ArrayList<Estoque> estoques = new ArrayList<Estoque>();

        produtoDao.setProdutos(produtos);
        fornecedorDao.setFornecedores(fornecedores);
        clienteDao.setClientes(clientes);

        var idCliente = 6;
        var idProduto = 3;
        var idVenda = 0;

        Scanner scanner = new Scanner(System.in);
        do {
            menuClear();
            System.out.println("1 - Fornecedor");
            System.out.println("2 - Cliente");
            System.out.println("3 - Produto");
            System.out.println("4 - Vender");
            System.out.println("exit - Finalizar");

            System.out.print("O que deseja? ");
            String resposta = scanner.next();

            switch (resposta) {
                case "1":
                    System.out.println("Opção Fornecedor selecionada.");
                    menuFornecedor(fornecedorDao, produtoDao,idCliente, idProduto);
                    break;
                case "2":
                    System.out.println("Opção Cliente selecionada.");
                    menuCliente(clienteDao,idCliente);
                    break;
                case "3":
                    System.out.println("Opção Produto selecionada.");
                    menuProduto(produtoDao,idProduto, fornecedores, estoques);
                    break;
                case "4":
                    System.out.println("Opção Vender selecionada.");
                    menuVender(estoques, clienteDao, vendaDAO, idVenda);
                    break;
                case "exit":
                    System.out.println("Finalizando o programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }

        } while (true);
    }
    public static void menuClear(){
        for (int i = 0; i < 15; i++) {
            System.out.println();
        }
    }


    public static void menuFornecedor(FornecedorDAO fornecedorDao, ProdutoDAO produtoDao,int idCliente, int idProduto) {
        Scanner scanner = new Scanner(System.in);
        do {
            menuClear();
            System.out.println("1 - Cadastrar Fornecedor");
            System.out.println("2 - Alterar Fornecedor");
            System.out.println("3 - Excluir Fornecedor");
            System.out.println("4 - Listar Fornecedores");
            System.out.println("0 - Voltar");
            System.out.print("O que deseja? ");
            String resposta = scanner.next();

            switch (resposta) {
                case "1":
                    System.out.println("Opção Cadastrar Fornecedor selecionada.");
                    cadastrarFornecedor(fornecedorDao, produtoDao, idCliente, idProduto);
                    break;
                case "2":
                    System.out.println("Opção Alterar Fornecedor selecionada.");
                    alterarFornecedor(fornecedorDao, produtoDao);
                    break;
                case "3":
                    menuClear();
                    listarFornecedoresMenu(fornecedorDao,false);
                    System.out.println("Qual Fornecedor você deseja apagar? ");
                    String respostaExcluirFornecedor = scanner.next();
                    fornecedorDao.excluirFornecedor(respostaExcluirFornecedor);
                    break;
                case "4":
                    System.out.println("Opção Listar Fornecedores selecionada");
                    listarFornecedoresMenu(fornecedorDao,true);
                    break;
                case "0":
                    System.out.println("Opção Voltar selecionada.");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (true);
    }
    public static void cadastrarFornecedor(FornecedorDAO fornecedorDao, ProdutoDAO produtoDao,int idCliente, int idProduto) {
        Scanner scanner = new Scanner(System.in);
        do {
            menuClear();
            System.out.println("Preencha o formulário");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Cpf: ");
            String cpf = scanner.nextLine();
            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();
            System.out.print("Sexo: ");
            String sexo = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Cnpj: ");
            String cnpj = scanner.nextLine();
            System.out.print("Numero de inscricao: ");
            int numeroInscricao = scanner.nextInt();
            System.out.print("Nome Fantasia: ");
            String nomeFantasia = scanner.nextLine();
            System.out.print("Data De Abertura: ");
            String dataDeAbertura = scanner.nextLine();
            System.out.print("Porte: ");
            String porte = scanner.nextLine();
            System.out.print("Atividade Economica Principal: ");
            String atividadeEconomicaPrincipal = scanner.nextLine();
            System.out.print("Situcao Cadastral: ");
            String situcaoCadastral = scanner.nextLine();

            Fornecedor novoFornecedor = new Fornecedor(Integer.toString(fornecedorDao.getIdFornecedor()), nome, cpf, endereco, sexo, telefone, email, cnpj, numeroInscricao, nomeFantasia, dataDeAbertura, porte, atividadeEconomicaPrincipal, situcaoCadastral);
            fornecedorDao.cadastrarFornecedor(novoFornecedor);

            System.out.print("Deseja adicionar um produto a esse fornecedor? (S/N): ");
            String respostaAdicionarProduto = scanner.next();
            if (respostaAdicionarProduto.equalsIgnoreCase("S")) {
                cadastrarProduto(produtoDao, idProduto, novoFornecedor);
            }

            System.out.print("Deseja adicionar outro fornecedor? (S/N): ");
            String resposta = scanner.next();

            if (!resposta.equalsIgnoreCase("S")) {
                break;
            }
        } while (true);
    }
    public static void alterarFornecedor(FornecedorDAO fornecedorDao, ProdutoDAO produtoDao) {
        Scanner scanner = new Scanner(System.in);
        do {
            menuClear();
            listarFornecedoresMenu(fornecedorDao, false);
            System.out.println("Id do fornecedor que deseja alterar");
            System.out.println("0 - Voltar");
            System.out.print("O que deseja? ");
            String resposta = scanner.next();

            switch (resposta) {
                case "0":
                    System.out.println("Opção Voltar selecionada.");
                    return;
                default:
                    var fornecedores = fornecedorDao.listarFornecedores();
                    Fornecedor fornecedorEncontrado = fornecedores.stream()
                            .filter(fornecedor -> fornecedor.getId().equals(resposta))
                            .findAny()
                            .orElse(null);
                    if (fornecedorEncontrado != null) {
                        menuClear();
                        System.out.println(fornecedorEncontrado);
                        System.out.println("O que deseja alterar?");
                        System.out.println("1 - Perfil do fornecedor");
                        System.out.println("2 - Produtos do fornecedor");
                        System.out.println("3 - Adicionar produtos ao fornecedor");
                        System.out.println("0 - Voltar");
                        System.out.print("O que deseja? ");
                        String respostaEscolhaDeAlteracao = scanner.next();

                        switch (respostaEscolhaDeAlteracao) {
                            case "1":
                                System.out.println("Opção Perfil do fornecedor selecionada.");
                                alterarFornecedorPerfil(fornecedorEncontrado);
                                return;
                            case "2":
                                System.out.println("Opção Produtos do fornecedor selecionada.");
                                alterarFornecedorProdutos(produtoDao, fornecedorEncontrado);
                                return;
                            case "3":
                                System.out.println("Opção Adicionar produtos ao fornecedor selecionada.");
                                AdicionarProdutosFornecedor(produtoDao, fornecedorEncontrado);
                                return;
                            case "0":
                                System.out.println("Opção Voltar selecionada.");
                                return;
                            default:
                        }
                    } else {
                        System.out.println("Fornecedor não encontrado.");
                    }
            }
        } while (true);
    }
    public static void alterarFornecedorPerfil(Fornecedor fornecedorEncontrado) {
        Scanner scanner = new Scanner(System.in);
        menuClear();
        System.out.println(fornecedorEncontrado);
        System.out.println("O que deseja alterar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Cpf");
        System.out.println("3 - Endereço");
        System.out.println("4 - Sexo");
        System.out.println("5 - Telefone");
        System.out.println("6 - Email");
        System.out.println("7 - Cnpj");
        System.out.println("8 - Número Incrição");
        System.out.println("9 - Nome Fantasia");
        System.out.println("10 - Data de abertura");
        System.out.println("11 - Porte");
        System.out.println("12 - Atividade economica principal");
        System.out.println("13 - Situação cadastral");
        System.out.println("0 - Voltar");
        System.out.print("O que deseja? ");
        String respostaAlteracao = scanner.next();

        switch (respostaAlteracao) {
            case "1":
                System.out.print("Qual o novo nome? ");
                String novoNome = scanner.nextLine();
                fornecedorEncontrado.setNome(novoNome);
                break;
            case "2":
                System.out.print("Qual o novo cpf? ");
                String novoCpf = scanner.nextLine();
                fornecedorEncontrado.setCpf(novoCpf);
                break;
            case "3":
                System.out.print("Qual a nova endereço? ");
                String novoEndereco = scanner.nextLine();
                fornecedorEncontrado.setEndereco(novoEndereco);
                break;
            case "4":
                System.out.print("Qual o sexo? ");
                String novoSexo = scanner.nextLine();
                fornecedorEncontrado.setSexo(novoSexo);
                break;
            case "5":
                System.out.print("Qual o novo telefone? ");
                String novoTelefone = scanner.nextLine();
                fornecedorEncontrado.setTelefone(novoTelefone);
                break;
            case "6":
                System.out.print("Qual o novo email? ");
                String novoEmail = scanner.nextLine();
                fornecedorEncontrado.setEndereco(novoEmail);
                break;
            case "7":
                System.out.print("Qual o novo cnpj? ");
                String novoCnpj = scanner.nextLine();
                fornecedorEncontrado.setCnpj(novoCnpj);
                break;
            case "8":
                System.out.print("Qual o novo número de inscrição? ");
                int novoNumeroInscricao = scanner.nextInt();
                fornecedorEncontrado.setNumeroInscricao(novoNumeroInscricao);
                break;
            case "9":
                System.out.print("Qual o novo nome fantasia? ");
                String novoNomeFantasia = scanner.nextLine();
                fornecedorEncontrado.setNomeFantasia(novoNomeFantasia);
                break;
            case "10":
                System.out.print("Qual a nova data de abertura? ");
                String novaDataDeAbertura = scanner.nextLine();
                fornecedorEncontrado.setDataDeAbertura(novaDataDeAbertura);
                break;
            case "11":
                System.out.print("Qual o novo porte? ");
                String novoPorte = scanner.nextLine();
                fornecedorEncontrado.setPorte(novoPorte);
                break;
            case "12":
                System.out.print("Qual a nova atividade economica principal? ");
                String novaAtividadeEconomicaPrincipal = scanner.nextLine();
                fornecedorEncontrado.setAtividadeEconomicaPrincipal(novaAtividadeEconomicaPrincipal);
                break;
            case "13":
                System.out.print("Qual a nova situcao cadastral? ");
                String novoSitucaoCadastral = scanner.nextLine();
                fornecedorEncontrado.setSitucaoCadastral(novoSitucaoCadastral);
                break;
            case "0":
                System.out.println("Opção Voltar selecionada.");
                return;
            default:
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
        }
    }
    public static void alterarFornecedorProdutos(ProdutoDAO produtoDao, Fornecedor fornecedorEncontrado) {
        alterarProduto(produtoDao, fornecedorEncontrado);
    }
    public static void AdicionarProdutosFornecedor(ProdutoDAO produtoDao, Fornecedor fornecedorEncontrado) {
        Scanner scanner = new Scanner(System.in);
        do {
            menuClear();
            listarProdutosMenu(produtoDao, false);
            System.out.println("Id do produto que deseja adicionar ao fornecedor " + fornecedorEncontrado.getNomeFantasia());
            System.out.println("0 - Voltar");
            System.out.print("O que deseja? ");
            String resposta = scanner.nextLine();

            switch (resposta) {
                case "0":
                    System.out.println("Opção Voltar selecionada.");
                    return;
                default:
                    var produtos = produtoDao.listarProdutos();
                    Produto produtoEncontrado = produtos.stream()
                            .filter(produto -> produto.getId().equals(resposta))
                            .findAny()
                            .orElse(null);

                    if (produtoEncontrado != null) {
                        menuClear();
                        fornecedorEncontrado.setProdutosAVenda(produtoEncontrado);
                        break;
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
            }
        } while (true);
    }
    public static void listarFornecedoresMenu(FornecedorDAO fornecedorDao, boolean Emenu) {
        if(Emenu){
            menuClear();
            Scanner scanner = new Scanner(System.in);
            do {
                for (var fornecedor : fornecedorDao.listarFornecedores()) {
                    System.out.println(fornecedor);
                }
                System.out.println("0 - Voltar");
                System.out.print("O que deseja? ");
                String resposta = scanner.nextLine();
                switch (resposta) {
                    case "0":
                        System.out.println("Opção Voltar selecionada.");
                        return;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } while (true);
        }else {
            for (var fornecedor : fornecedorDao.listarFornecedores()) {
                System.out.println(fornecedor);
            }
        }
    }


    public static void menuCliente(ClienteDAO clienteDao, int idCliente) {
        Scanner scanner = new Scanner(System.in);
        do {
            menuClear();
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Alterar Cliente");
            System.out.println("3 - Excluir Cliente");
            System.out.println("4 - Listar Clientes");
            System.out.println("0 - Voltar");
            System.out.print("O que deseja? ");
            String resposta = scanner.next();

            switch (resposta) {
                case "1":
                    System.out.println("Opção Cadastrar Cliente selecionada.");
                    cadastrarCliente(clienteDao, idCliente);
                    break;
                case "2":
                    System.out.println("Opção Alterar Cliente selecionada.");
                    alterarCliente(clienteDao);
                    break;
                case "3":
                    menuClear();
                    listarClientesMenu(clienteDao,false);
                    System.out.println("Qual Cliente você deseja apagar? ");
                    String respostaExcluirCliente = scanner.next();
                    clienteDao.excluirCliente(respostaExcluirCliente);
                    break;
                case "4":
                    System.out.println("Opção Listar Clientes selecionada");
                    listarClientesMenu(clienteDao,true);
                    break;
                case "0":
                    System.out.println("Opção Voltar selecionada.");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (true);
    }
    public static void cadastrarCliente(ClienteDAO clienteDao, int idCliente) {
        Scanner scanner = new Scanner(System.in);
        do {
            menuClear();
            System.out.println("Preencha o formulário");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Cpf: ");
            String cpf = scanner.nextLine();
            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();
            System.out.print("Sexo: ");
            String sexo = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Status: ");
            String status = scanner.nextLine();

            Cliente novoCliente = new Cliente(Integer.toString(clienteDao.getIdCliente()), nome, cpf, endereco, sexo, telefone, email, status);

            clienteDao.cadastrarClinte(novoCliente);
            System.out.print("Deseja adicionar outro cliente? (S/N): ");
            String resposta = scanner.next();

            if (!resposta.equalsIgnoreCase("S")) {
                break;
            }
        } while (true);
    }
    public static void alterarCliente(ClienteDAO clienteDao) {
        Scanner scanner = new Scanner(System.in);
        do {
            menuClear();
            listarClientesMenu(clienteDao, false);
            System.out.println("Id do cliente que deseja alterar");
            System.out.println("0 - Voltar");
            System.out.print("O que deseja? ");
            String resposta = scanner.next();

            switch (resposta) {
                case "0":
                    System.out.println("Opção Voltar selecionada.");
                    return;
                default:
                    var clientes = clienteDao.listarClientes();
                    Cliente clienteEncontrado = clientes.stream()
                            .filter(produto -> produto.getId().equals(resposta))
                            .findAny()
                            .orElse(null);
                    if (clienteEncontrado != null) {
                        menuClear();
                        System.out.println(clienteEncontrado);
                        System.out.println("O que deseja alterar?");
                        System.out.println("1 - Nome");
                        System.out.println("2 - Cpf");
                        System.out.println("3 - Endereço");
                        System.out.println("4 - Sexo");
                        System.out.println("5 - Telefone");
                        System.out.println("6 - Email");
                        System.out.println("7 - Status");
                        System.out.println("0 - Voltar");
                        System.out.print("O que deseja? ");
                        String respostaAlteracao = scanner.next();

                        switch (respostaAlteracao) {
                            case "1":
                                System.out.print("Qual o novo nome? ");
                                String novoNome = scanner.nextLine();
                                clienteEncontrado.setNome(novoNome);
                                break;
                            case "2":
                                System.out.print("Qual o novo cpf? ");
                                String novoCpf = scanner.nextLine();
                                clienteEncontrado.setCpf(novoCpf);
                                break;
                            case "3":
                                System.out.print("Qual a nova endereço? ");
                                String novoEndereco = scanner.nextLine();
                                clienteEncontrado.setEndereco(novoEndereco);
                                break;
                            case "4":
                                System.out.print("Qual o sexo? ");
                                String novoSexo = scanner.nextLine();
                                clienteEncontrado.setSexo(novoSexo);
                                break;
                            case "5":
                                System.out.print("Qual o novo telefone? ");
                                String novoTelefone = scanner.nextLine();
                                clienteEncontrado.setTelefone(novoTelefone);
                                break;
                            case "6":
                                System.out.print("Qual o novo email? ");
                                String novoEmail = scanner.nextLine();
                                clienteEncontrado.setEndereco(novoEmail);
                                break;
                            case "7":
                                System.out.print("Qual o novo status? ");
                                String novoStatus = scanner.nextLine();
                                clienteEncontrado.setStatus(novoStatus);
                                break;
                            case "0":
                                System.out.println("Opção Voltar selecionada.");
                                return;
                            default:
                                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                        }
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
            }
        } while (true);
    }
    public static void listarClientesMenu(ClienteDAO clienteDao, boolean Emenu) {
        if(Emenu){
            menuClear();
            Scanner scanner = new Scanner(System.in);
            do {
                for (var cliente : clienteDao.listarClientes()) {
                    System.out.println(cliente);
                }
                System.out.println("0 - Voltar");
                System.out.print("O que deseja? ");
                String resposta = scanner.next();
                switch (resposta) {
                    case "0":
                        System.out.println("Opção Voltar selecionada.");
                        return;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } while (true);
        }else {
            for (var cliente : clienteDao.listarClientes()) {
                System.out.println(cliente);
            }
        }
    }


    public static void menuProduto(ProdutoDAO produtoDao, int idProduto, ArrayList<Fornecedor> fornecedores, ArrayList<Estoque> estoques) {
        Scanner scanner = new Scanner(System.in);
        menuClear();
        do {
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Alterar Produto");
            System.out.println("3 - Excluir Produto");
            System.out.println("4 - Listar Produtos");
            System.out.println("5 - Comprar Produtos");
            System.out.println("6 - Listar Estoque");
            System.out.println("0 - Voltar");
            System.out.print("O que deseja? ");
            String resposta = scanner.next();

            switch (resposta) {
                case "1":
                    System.out.println("Opção Cadastrar Produto selecionada.");
                    cadastrarProduto(produtoDao, idProduto, null);
                    break;
                case "2":
                    System.out.println("Opção Alterar Produto selecionada.");
                    alterarProduto(produtoDao, null);
                    break;
                case "3":
                    System.out.println("Opção Excluir Produto selecionada.");
                    excluirProduto(produtoDao, fornecedores);
                    break;
                case "4":
                    System.out.println("Opção Listar Produtos selecionada");
                    listarProdutosMenu(produtoDao,true);
                    break;
                case "5":
                    System.out.println("Opção Comprar Produtos selecionada.");
                    ComprarDoFornecedor(produtoDao, fornecedores, estoques);
                    break;
                case "6":
                    System.out.println("Opção Listar Estoque selecionada");
                    listarEstoqueMenu(estoques);
                    break;
                case "0":
                    System.out.println("Opção Voltar selecionada.");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (true);
    }
    public static void cadastrarProduto(ProdutoDAO produtoDao, int idProduto, Fornecedor fornecedor) {
        Scanner scanner = new Scanner(System.in);
        do {
            menuClear();
            idProduto++;
            System.out.print("Digite o preço do Tênis: ");
            double preco = scanner.nextDouble();
            System.out.print("Qual o tamanho do Tênis: ");
            String tamanho = scanner.nextLine();
            System.out.print("Qual a cor do Tênis: ");
            String cor = scanner.nextLine();
            System.out.print("Qual a marca do Tênis: ");
            String marca = scanner.nextLine();
            System.out.print("Digite a categoria do Tênis: ");
            String tipo = scanner.nextLine();

            Produto novoProduto = new Produto(Integer.toString(produtoDao.getIdProduto()), preco, tamanho, cor, marca, tipo);
            produtoDao.cadastrarProduto(novoProduto);

            if(fornecedor != null)
                fornecedor.setProdutosAVenda(novoProduto);

            System.out.print("Deseja adicionar outro produto? (S/N): ");
            String resposta = scanner.next();

            if (!resposta.equalsIgnoreCase("S")) {
                break;
            }
        } while (true);
    }
    public static void alterarProduto(ProdutoDAO produtoDao, Fornecedor fornecedor) {
        Scanner scanner = new Scanner(System.in);
        do {
            menuClear();
            if (fornecedor != null)
                listarProdutosFornecedorMenu(fornecedor, false);
            else
                listarProdutosMenu(produtoDao, false);
            System.out.println("Id do produto que deseja alterar");
            System.out.println("0 - Voltar");
            System.out.print("O que deseja? ");
            String resposta = scanner.next();

            switch (resposta) {
                case "0":
                    System.out.println("Opção Voltar selecionada.");
                    return;
                default:
                    var produtos = produtoDao.listarProdutos();
                    Produto produtoEncontrado = produtos.stream()
                            .filter(produto -> produto.getId().equals(resposta))
                            .findAny()
                            .orElse(null);

                    if (produtoEncontrado != null) {
                        menuClear();
                        System.out.println(produtoEncontrado);
                        System.out.println("O que deseja alterar?");
                        System.out.println("1 - Preço");
                        System.out.println("2 - Tamanho");
                        System.out.println("3 - Cor");
                        System.out.println("4 - Marca");
                        System.out.println("5 - Tipo");
                        System.out.println("0 - Voltar");
                        System.out.print("O que deseja? ");
                        String respostaAlteracao = scanner.next();

                        switch (respostaAlteracao) {
                            case "1":
                                System.out.print("Qual o novo preço? ");
                                double novoPreco = scanner.nextDouble();
                                produtoEncontrado.setPreco(novoPreco);
                                break;
                            case "2":
                                System.out.print("Qual o novo tamanho? ");
                                String novoTamanho = scanner.nextLine();
                                produtoEncontrado.setTamanho(novoTamanho);
                                break;
                            case "3":
                                System.out.print("Qual a nova cor? ");
                                String novaCor = scanner.nextLine();
                                produtoEncontrado.setCor(novaCor);
                                break;
                            case "4":
                                System.out.print("Qual a nova marca? ");
                                String novaMarca = scanner.nextLine();
                                produtoEncontrado.setCor(novaMarca);
                                break;
                            case "5":
                                System.out.print("Qual o novo tipo? ");
                                String novoTipo = scanner.nextLine();
                                produtoEncontrado.setTipo(novoTipo);
                                break;
                            case "0":
                                System.out.println("Opção Voltar selecionada.");
                                return;
                            default:
                                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                        }
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
            }
        } while (true);
    }
    public static void excluirProduto(ProdutoDAO produtoDao, ArrayList<Fornecedor> fornecedores) {
        menuClear();
        Scanner scanner = new Scanner(System.in);
        listarProdutosMenu(produtoDao,false);
        System.out.println("Qual produto você deseja apagar? ");
        String respostaExcluirProduto = scanner.next();

        for (Fornecedor fornecedor : fornecedores) {
            var produtosAVenda = fornecedor.getProdutosAVenda();

            produtosAVenda.removeIf(produto -> produto.getId().equals(respostaExcluirProduto));
        }

        produtoDao.excluirProduto(respostaExcluirProduto);
    }
    public static void listarProdutosFornecedorMenu(Fornecedor fornecedor, boolean Emenu) {
        if(Emenu){
            menuClear();
            Scanner scanner = new Scanner(System.in);
            do {
                for (var produto : fornecedor.getProdutosAVenda()) {
                    System.out.println(produto);
                }
                System.out.println("0 - Voltar");
                System.out.print("O que deseja? ");
                String resposta = scanner.next();
                switch (resposta) {
                    case "0":
                        System.out.println("Opção Voltar selecionada.");
                        return;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } while (true);
        }else {
            for (var produto : fornecedor.getProdutosAVenda()) {
                System.out.println(produto);
            }
        }
    }
    public static void listarProdutosMenu(ProdutoDAO produtoDao, boolean Emenu) {
        if(Emenu){
            menuClear();
            Scanner scanner = new Scanner(System.in);
            do {
                for (var produto : produtoDao.listarProdutos()) {
                    System.out.println(produto);
                }
                System.out.println("0 - Voltar");
                System.out.print("O que deseja? ");
                String resposta = scanner.next();
                switch (resposta) {
                    case "0":
                        System.out.println("Opção Voltar selecionada.");
                        return;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } while (true);
        }else {
            for (var produto : produtoDao.listarProdutos()) {
                System.out.println(produto);
            }
        }
    }
    public static void ComprarDoFornecedor(ProdutoDAO produtoDao, ArrayList<Fornecedor> fornecedores, ArrayList<Estoque> estoques) {
        Scanner scanner = new Scanner(System.in);
        menuClear();
        do {
            listarProdutosMenu(produtoDao, false);
            System.out.println("Id do produto que deseja comprar");
            System.out.println("0 - Voltar");
            System.out.print("O que deseja? ");
            String resposta = scanner.next();

            switch (resposta) {
                case "0":
                    System.out.println("Opção Voltar selecionada.");
                    return;
                default:
                    var produtos = produtoDao.listarProdutos();
                    Produto produtoEncontrado = produtos.stream()
                            .filter(produto -> produto.getId().equals(resposta))
                            .findAny()
                            .orElse(null);

                    if (produtoEncontrado != null) {
                        var fornecedorEncontrado = false;
                        for (Fornecedor fornecedor : fornecedores) {
                            for (Produto produto : fornecedor.getProdutosAVenda()) {
                                if (produto.getId().equals(produtoEncontrado.getId())) {
                                    System.out.print("Quantas unidades você deseja? ");
                                    int quantidadeEscolhida = scanner.nextInt();

                                    Estoque estoque = new Estoque(produtoEncontrado.getId(), quantidadeEscolhida, 0, 999, produtoEncontrado);
                                    menuClear();
                                    estoques.add(estoque);
                                    menuClear();
                                    System.out.println("Compra realizada.");
                                    System.out.println(produtoEncontrado);
                                    fornecedorEncontrado = true;
                                    break;
                                }
                            }
                        }
                        if(!fornecedorEncontrado){
                            menuClear();
                            System.out.println("Não existe fornecedores para o " + produtoEncontrado);
                            break;
                        }
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
            }
        } while (true);
    }


    public static void menuVender(ArrayList<Estoque> estoques, ClienteDAO clienteDao, VendaDAO vendaDao, int idVenda){
        Scanner scanner = new Scanner(System.in);
        do {
            menuClear();
            listarEstoqueMenu(estoques);
            System.out.println("Id do produto que deseja vender");
            System.out.println("0 - Voltar");
            System.out.print("O que deseja? ");
            String respostaIdProduto = scanner.next();

            switch (respostaIdProduto) {
                case "0":
                    System.out.println("Opção Voltar selecionada.");
                    return;
                default:
                    Produto produtoEncontrado = null;
                    for (Estoque estoque : estoques) {
                        if (estoque.getProduto().getId().equals(respostaIdProduto)) {
                            produtoEncontrado = estoque.getProduto();
                            break;
                        }
                    }

                    if (produtoEncontrado != null) {
                        menuClear();
                        listarClientesMenu(clienteDao, false);
                        do {
                            System.out.println("Id do cliente que deseja comprar o produto");
                            System.out.println("0 - Voltar");
                            System.out.print("O que deseja? ");
                            String respostaIdCliente = scanner.next();

                            switch (respostaIdCliente) {
                                case "0":
                                    System.out.println("Opção Voltar selecionada.");
                                    return;
                                default:
                                    var clientes = clienteDao.getClientes();
                                    Cliente clienteEncontrado = clientes.stream()
                                            .filter(cliente -> cliente.getId().equals(respostaIdCliente))
                                            .findAny()
                                            .orElse(null);

                                    if (clienteEncontrado != null) {
                                        System.out.print("Quantas unidades? ");
                                        int quantidadeEscolhida = scanner.nextInt();

                                        Estoque estoqueEncontrado = estoques.stream()
                                                .filter(estoque -> estoque.getId().equals(respostaIdProduto))
                                                .findAny()
                                                .orElse(null);

                                        menuClear();
                                        var venda = new Venda(Integer.toString(idVenda), quantidadeEscolhida, produtoEncontrado.getPreco(), new Date(), produtoEncontrado, clienteEncontrado);

                                        if (estoqueEncontrado.getQtd() - quantidadeEscolhida < 0) {
                                            System.out.println("Quantidade insuficiente em estoque");
                                            break;
                                        }

                                        vendaDao.salvar(venda);
                                        System.out.println(venda);
                                    } else {
                                        System.out.println("Cliente não encontrado.");
                                    }
                            }
                        } while (true);

                    } else {
                        System.out.println("Produto não encontrado.");
                    }
            }
        } while (true);
    }

    public static void listarEstoqueMenu(List<Estoque> estoques) {
        for (var estoque : estoques) {
            System.out.println(estoque);
        }
    }
}

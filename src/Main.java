import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static Usuario usuarioLogado = null;
    public static ArrayList<Produto> produtos = new ArrayList<>();
    public static void main(String[] args) {
        menu();
    }

    public static void menu (){
        System.out.println("""
                === MENU ===
                1 - Login 
                2 - Cadastro
                3 - Sair
                """);
        int resposta = sc.nextInt();

        switch (resposta) {
            case 1 -> login();
            case 2 -> cadastro();
            case 3 -> System.exit(0);
            default -> {
                System.out.println("Esse numero não é uma opção válida");
                menu();
            }
        }
    }

    private static void cadastro() {
        System.out.println("Digite seu email: ");
        String email = sc.next();

        System.out.println("Digite seu nome:");
        String nome = sc.next();

        System.out.println("Crie uma senha: ");
        String senha = sc.next();

        Usuario usuario = new Usuario(email,nome ,senha);
        usuarios.add(usuario);

        menu();
    }

    public static void login () {
        System.out.println("Qual é seu email? ");
        String email = sc.next();

        System.out.println("Qual a sua senha?");
        String senha = sc.next();

        AtomicBoolean existe = new AtomicBoolean(false);

        usuarios.forEach(usuario -> {
            System.out.println(usuario.getEmail());
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)){
                existe.set(true);
                usuarioLogado = usuario;
                menuPrincipal () ;
            }
        });

        if(!existe.get()) {
            System.out.println("Erro. Você não está cadastrado.");
            menu();
        }

    }

    private static void menuPrincipal() {

        System.out.println("""
                === MENU PRINCIPAL===
                1 - Cadastrar Produto
                2 - Ver Produtos
                3 - Adicionar Produto ao Carrinho
                4 - Ver Carrinho
                5 - Ver meus Pedidos
                6 - Sair
                """);
        int opcao = sc.nextInt();

        switch (opcao){
            case 1 -> cadastrarProduto();
            case 2 -> verProdutos();
            case 3 -> adicionarCarrinho();
            case 4 -> verCarrinho ();
            case 5 -> verPedidos ();
            case 6 -> menu();
        }
    }

    private static void verPedidos() {
        System.out.println(usuarioLogado.getPedidos());
        menuPrincipal();
    }

    private static void verCarrinho() {
        System.out.println(usuarioLogado.getCarrinho() + "\n");
        System.out.println("""
                === CARRINHO ===
                1 - Esvaziar Carrinho
                2 - Tirar um Produto do Carrinho
                3 - Fazer Pedido
                4 - Voltar ao Menu
                """);
        int escolha = sc.nextInt();

        switch (escolha) {
            case 1 -> esvaziarCarrinho ();
            case 2 -> tirarProduto () ;
            case 3 -> fazerPedido () ;
            case 4 -> menuPrincipal();
            default -> {
                System.out.println("Numero invalido");
                verCarrinho();
            }
        }
    }

    private static void fazerPedido() {
        System.out.println("Digite a forma de pagamento: ");
        String forma = sc.next();
        double valorTotal = usuarioLogado.getCarrinho().getValorTotal();
        ArrayList <Produto> produtosPedidos =  usuarioLogado.getCarrinho().getProdutos();

        Pedido pedido = new Pedido(produtosPedidos, valorTotal , forma);

        usuarioLogado.getPedidos().add(pedido);

        usuarioLogado.getCarrinho().setProdutos(new ArrayList<>());
        usuarioLogado.getCarrinho().setValorTotal(0);

        System.out.println("Pedido feito");
        menuPrincipal();
    }

    private static void tirarProduto() {
        System.out.println("Codigo do Produto: ");
        String codigo = sc.next();

        AtomicBoolean existe = new AtomicBoolean(false);

        produtos.forEach(produto -> {
            if (codigo.equals(produto.getCodigo())){
                existe.set(true);
                usuarioLogado.getCarrinho().getProdutos().remove(produto);
                usuarioLogado.getCarrinho().setValorTotal(usuarioLogado.getCarrinho().getValorTotal()- produto.getPreco());
                verCarrinho();
            }
        });

        if (!existe.get()) {
            System.out.println("Produto nao encontrado no carrinho");
            verCarrinho();
        }
    }

    private static void esvaziarCarrinho() {
        usuarioLogado.getCarrinho().getProdutos().clear();
        usuarioLogado.getCarrinho().setValorTotal(0);
        System.out.println("Carrinho vazio");
        verCarrinho();
    }

    private static void adicionarCarrinho() {
        System.out.println("Qual o código do produto? ");
        String codigo = sc.next();

        AtomicReference<Boolean> adicionado = new AtomicReference<>(false);

        produtos.forEach(produto -> {
            if (codigo.equals(produto.getCodigo())){
                usuarioLogado.getCarrinho().getProdutos().add(produto);
                adicionado.set(true);
            }
        });
        usuarioLogado.getCarrinho().calculaTotal();
        if (!adicionado.get()) {
            System.out.println("Produto não existente ");
            menuPrincipal();
        }
        else {
            System.out.println("Produto adicionado com Sucesso");
            menuPrincipal();
        }
    }

    private static void verProdutos() {
        System.out.println(produtos);
        menuPrincipal();
    }

    private static void cadastrarProduto() {
        System.out.println("Nome: ");
        String nome = sc.next();

        System.out.println("Descrição: ");
        String descricao = sc.next();

        System.out.println("Preço: ");
        double preco = sc.nextDouble();

        System.out.println("Codigo: ");
        String codigo = sc.next();

        System.out.println("""
                1 - Perfume 
                2 - Maquiagem
                """);

        int escolha = sc.nextInt();

        switch (escolha){
            case 1 -> {
                System.out.println("Fragrancia");
                String fragrancia = sc.next();

                Perfume perfume = new Perfume(preco, nome, descricao, codigo,  fragrancia);
                produtos.add(perfume);
            }
            case 2 -> {
                System.out.println("Cor: ");
                String cor = sc.next();

                System.out.println("Categoria: ");
                String categoria = sc.next();

                Maquiagem maquiagem = new Maquiagem(preco, nome, descricao , codigo, cor ,  categoria );
                produtos.add(maquiagem);
            }
        }
        System.out.println("Produto Cadastrado com Sucesso ");
        menuPrincipal();
    }


}
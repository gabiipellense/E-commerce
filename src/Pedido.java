import java.util.ArrayList;

public class Pedido {
    private ArrayList<Produto> produtos = new ArrayList<>();
    private double valorTotal;
    private String formaPagamento;

    public Pedido(ArrayList<Produto> produtos, double valorTotal, String formaPagamento) {
        this.produtos = produtos;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return "Pedido" + "\n" +
                "Produtos: " + produtos + "\n" +
                "Valor Total: " + valorTotal + "\n" +
                "Forma de Pagamento: " + formaPagamento;
    }
}

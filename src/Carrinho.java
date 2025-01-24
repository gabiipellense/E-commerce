import java.util.ArrayList;

public class Carrinho {
    private ArrayList<Produto> produtos = new ArrayList<>();
    private double valorTotal;

    public Carrinho(ArrayList<Produto> produtos, double valorTotal) {
        this.produtos = produtos;
        this.valorTotal = valorTotal;
    }

    public Carrinho() {
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

    public void calculaTotal () {
        double somaTotal = this.getProdutos().stream().mapToDouble(Produto::getPreco).sum();
        setValorTotal(somaTotal);
    }
    @Override
    public String toString() {
        return "Carrinho" + "\n" +
                "Produto: " + produtos + "\n" +
                "Valor Total: " + valorTotal;
    }
}

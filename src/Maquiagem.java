public class Maquiagem extends Produto{
    private String cor ;
    private String categoria;

    public Maquiagem(double preco, String nome, String descricao, String codigo, String cor, String categoria) {
        super(preco, nome, descricao, codigo);
        this.cor = cor;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Cor: " + cor + "\n" +
                "Categoria: " + categoria;
    }
}

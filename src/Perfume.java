public class Perfume extends Produto{
    private String fragrancia ;

    public Perfume(double preco, String nome, String descricao, String codigo, String fragrancia) {
        super(preco, nome, descricao, codigo);
        this.fragrancia = fragrancia;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Fragrancia: " + fragrancia;
    }
}

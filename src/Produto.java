public abstract class Produto {

    private double preco ;
    private String nome ;
    private String descricao ;
    private String codigo ;

    public Produto(double preco, String nome, String descricao, String codigo) {
        this.preco = preco;
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return
                "Preco:" + preco + "\n" +
                "Nome: " + nome + "\n" +
                "Descricao: " + descricao + "\n" +
                "Codigo: " + codigo;
    }
}

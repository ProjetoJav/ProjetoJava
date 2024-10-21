public class Pedido {
    private int idPedido; // Atributo para armazenar o ID do pedido (int)
    private Servico servico; // Atributo para armazenar o serviço do pedido (objeto Servico)
    private Peca peca; // Atributo para armazenar a peça do pedido (objeto Peca)
    private Cliente cliente; // Atributo para armazenar o cliente do pedido (objeto Cliente)
    private double total; // Atributo para armazenar o valor total do pedido (double)


    // Construtor
    public Pedido(int idPedido, Servico servico, Peca peca, Cliente cliente, double total) {
        this.idPedido = idPedido; // Atribui o valor do ID do pedido recebido como parâmetro ao atributo idPedido
        this.servico = servico; // Atribui o valor do serviço recebido como parâmetro ao atributo servico
        this.peca = peca; // Atribui o valor da peça recebido como parâmetro ao atributo peca
        this.cliente = cliente; // Atribui o valor do cliente recebido como parâmetro ao atributo cliente
        this.total = total; // Atribui o valor do total recebido como parâmetro ao atributo total
    }

    // Getters e Setters
    public int getIdPedido() { // Método getter para o atributo idPedido
        return idPedido; // Retorna o valor do atributo idPedido
    }

    public void setIdPedido(int idPedido) { // Método setter para o atributo idPedido
        this.idPedido = idPedido; // Atribui o valor recebido como parâmetro ao atributo idPedido
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

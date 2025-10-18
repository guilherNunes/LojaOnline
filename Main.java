
interface ProcessadorPagamento {
    void processar_pagamento(double valor);
}


class SuperGateway {
    public void cobrar(double total) {
        System.out.println("✅ Pagamento de R$ " + total + " processado com sucesso via SuperGateway!");
    }
}

// Adaptador (Adapter)
class SuperGatewayAdapter implements ProcessadorPagamento {

    private SuperGateway superGateway;

    public SuperGatewayAdapter(SuperGateway superGateway) {
        this.superGateway = superGateway;
    }

    @Override
    public void processar_pagamento(double valor) {
        // Traduz o método esperado para o método real do SuperGateway
        superGateway.cobrar(valor);
    }
}

// Cliente (Client)
public class Main {

    private ProcessadorPagamento processadorPagamento;

    public Main(ProcessadorPagamento processadorPagamento) {
        this.processadorPagamento = processadorPagamento;
    }

    public void finalizarCompra(double valor) {
        System.out.println("🛒 Finalizando compra no valor de R$ " + valor);
        processadorPagamento.processar_pagamento(valor);
        System.out.println("🛍️ Compra concluída com sucesso!\n");
    }

    public static void main(String[] args) {
        // Cria o gateway original (incompatível)
        SuperGateway superGateway = new SuperGateway();

        // Cria o adaptador para permitir compatibilidade com a interface esperada
        ProcessadorPagamento adaptador = new SuperGatewayAdapter(superGateway);

        // Cliente (LojaOnline) continua funcionando normalmente
        Main loja = new Main(adaptador);

        // Teste de compra
        loja.finalizarCompra(299.99);
        loja.finalizarCompra(150.50);
    }
}

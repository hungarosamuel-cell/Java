package br.com.nexuscloud.main;

import br.com.nexuscloud.model.CapacidadeExcedidaException;
import br.com.nexuscloud.model.Cliente;
import br.com.nexuscloud.model.CloudManager;
import br.com.nexuscloud.model.Container;
import br.com.nexuscloud.model.Servidor;

public class MainCloud {
    // throws: exigido pelo compilador para o Passo 6 (checked declarada em alocarContainer) sem usar try-catch RA:25057253-2.
    public static void main(String[] args) throws CapacidadeExcedidaException {
        // 1
        CloudManager manager = new CloudManager();
        // 2
        Cliente cliente = new Cliente("111.222.333-44", "Wayne Enterprises");
        // 3
        Servidor servidor = new Servidor("192.168.0.1", 16.0, 5.50);
        // 4
        manager.registrarCliente(cliente);
        manager.provisionarServidor(cliente, servidor);
        // 5
        Container c1 = new Container("C-01", "App1", 10.0);
        Container c2 = new Container("C-02", "App2", 8.0);

        // 6 Teste Falha Unchecked: descomente para ver ServidorOfflineException (servidor nasce desligado) RA:25057253-2.
        // servidor.alocarContainer(c1);

        // 7 Linha do Passo 6 comentada; liga o servidor RA:25057253-2.
        servidor.ligar(); 

        // 8 Sucesso: 0.0 + 10.0 <= 16.0 RA:25057253-2.
        try {
            servidor.alocarContainer(c1);
            System.out.println("Container " + c1.getId() + " alocado com sucesso.");
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        // 9 Falha Checked: 10.0 + 8.0 = 18.0 > 16.0 -> try-catch obrigatório RA:25057253-2.
        try {
            servidor.alocarContainer(c2);
            System.out.println("Container " + c2.getId() + " alocado com sucesso.");
        } catch (CapacidadeExcedidaException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }

        // 10 Esperado: 5.5 RA:25057253-2.
        System.out.println("Fatura ativa: R$ " + manager.calcularFaturaAtiva(cliente));
    }
}

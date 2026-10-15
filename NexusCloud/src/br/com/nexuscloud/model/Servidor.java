package br.com.nexuscloud.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * RN01 - POJO do servidor. Identidade lógica = IP.
 * RN02 - Possui lista cronológica de containers.
 * RN03 - Motor de alocação com Fail-Fast.
 * RN04 - Cálculo de RAM em uso via Streams.
 */
public class Servidor {
    private String ip;
    private double precoPorHora;
    private double ramTotal;
    private boolean statusLigado;
    // RN02: ArrayList inicializado na declaração -> evita NullPointerException; mantém ordem de inserção RA:25057253-2.
    private List<Container> containers = new ArrayList<>();

    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = ip;
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
        this.statusLigado = false; // nasce desligado (exigido pelo Passo 6 do roteiro) RA:25057253-2.
    }

    public void ligar() { statusLigado = true; }
    public void desligar() { statusLigado = false; }

    //RN03 - Fail-Fast: valida ANTES de alterar o estado interno. RA:25057253-2.
    @throws ServidorOfflineException (unchecked);
    @throws CapacidadeExcedidaException (checked);

    public void alocarContainer(Container c) throws CapacidadeExcedidaException {
        Objects.requireNonNull(c, "Container não pode ser nulo.");

        // RN03.1 - Regra de Status RA:25057253-2.
        if (!statusLigado) {
            throw new ServidorOfflineException(
                "Erro: Não é possível alocar containers em um servidor desligado.");
        }
        // RN03.2 - Regra de Capacidade (RAM em uso + necessária > total) RA:25057253-2.
        if (calcularRamEmUso() + c.getRamNecessaria() > ramTotal) {
            throw new CapacidadeExcedidaException(
                "Falha Crítica: Memória RAM insuficiente no servidor " + ip + ".");
        }
        containers.add(c); // só chega aqui se passou nas duas validações RA:25057253-2.
    }

    // RN04 - Streams sem laço for: mapToDouble extrai a RAM e sum() totaliza. RA:25057253-2.
    public double calcularRamEmUso() {
        return containers.stream()
                .mapToDouble(Container::getRamNecessaria)
                .sum();
    }

    public String getIp() { return ip; }
    public double getPrecoPorHora() { return precoPorHora; }
    public double getRamTotal() { return ramTotal; }
    public boolean isStatusLigado() { return statusLigado; }

    public List<Container> getContainers() { return Collections.unmodifiableList(containers); }

    public void setPrecoPorHora(double precoPorHora) { this.precoPorHora = precoPorHora; }
    public void setRamTotal(double ramTotal) { this.ramTotal = ramTotal; }

    // RN01 (Ação): iguais se têm o mesmo IP RA:25057253-2.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return Objects.equals(ip, ((Servidor) obj).ip);
    }

    @Override
    public int hashCode() { return Objects.hash(ip); }
}

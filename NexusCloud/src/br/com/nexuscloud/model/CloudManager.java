package br.com.nexuscloud.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//RN02 - Banco de dados principal. RN04 - Auditoria e finanças. RA:25057253-2.

public class CloudManager {
    // RN02: Map baseado em Hash (HashMap); chave = Cliente; valor = Set (HashSet) -> sem duplicatas e sem ordem RA:25057253-2.
    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    public CloudManager() { }

    // Passo 4 do roteiro. putIfAbsent evita sobrescrever os servidores de um cliente já registrado. RA:25057253-2.
    public void registrarCliente(Cliente c) {
        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    // Passo 4 do roteiro. Cliente precisa estar registrado; caso contrário falha cedo (escolha de projeto). RA:25057253-2.*
    public void provisionarServidor(Cliente c, Servidor s) {
        Set<Servidor> servidores = bancoDeDados.get(c);
        if (servidores == null) {
            throw new IllegalArgumentException("Cliente não registrado: " + c.getCpf());
        }
        servidores.add(s); // Set ignora Servidor com IP já existente (equals/hashCode por IP) RA:25057253-2.
    }

    // RN04 - Streams sem for: filter (apenas servidores LIGADOS) -> mapToDouble (precoPorHora) -> sum. Cliente sem registro retorna 0.0. RA:25057253-2.
    public double calcularFaturaAtiva(Cliente c) {
        return bancoDeDados.getOrDefault(c, new HashSet<>()).stream()
                .filter(Servidor::isStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora)
                .sum();
    }
}

package br.com.nexuscloud.model;

import java.util.Objects;

// RN01 - POJO do cliente. Identidade lógica = CPF. RA:25057253-2.
public class Cliente {
    // RN01: atributos privados (encapsulamento) RA:25057253-2.
    private String cpf;
    private String nome;

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setNome(String nome) { this.nome = nome; }

    // RN01 (Ação): dois Clientes são iguais se têm o mesmo CPF RA:25057253-2.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return Objects.equals(cpf, ((Cliente) obj).cpf);
    }

    // Contrato equals/hashCode: usa o mesmo campo (cpf). Essencial, pois Cliente é chave de HashMap (RN02) RA:25057253-2.
    @Override
    public int hashCode() { return Objects.hash(cpf); }
}

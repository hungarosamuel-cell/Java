package br.com.nexuscloud.model;

import java.util.Objects;
//RN01 - POJO do container (aplicação). Identidade lógica = ID RA:25057253-2.
public class Container {
    private String id;
    private String nomeApp;
    private double ramNecessaria; 
    // consumida no RN03/RN04 RA:25057253-2.

    public Container(String id, String nomeApp, double ramNecessaria) {
        this.id = id;
        this.nomeApp = nomeApp;
        this.ramNecessaria = ramNecessaria;
    }

    public String getId() { return id; }
    public String getNomeApp() { return nomeApp; }
    public double getRamNecessaria() { return ramNecessaria; }
    public void setNomeApp(String nomeApp) { this.nomeApp = nomeApp; }
    public void setRamNecessaria(double ramNecessaria) { this.ramNecessaria = ramNecessaria; }

    // RN01 (Ação): iguais se têm o mesmo ID RA:25057253-2.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return Objects.equals(id, ((Container) obj).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}

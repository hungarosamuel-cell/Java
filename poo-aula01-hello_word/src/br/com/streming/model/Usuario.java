package br.com.streming.model;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private boolean ativo;
    private List<Video> historicoAssistidos;
    public void assitirVideo(Video v){};
}

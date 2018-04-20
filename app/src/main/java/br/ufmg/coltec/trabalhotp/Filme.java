package br.ufmg.coltec.trabalhotp;

import android.text.Editable;

/**
 * Created by a2016951782 on 04/04/18.
 */

public class Filme {

    private String nome;
    private Integer ano;
    private String diretor;
    private String genero;
    private String faixa;

    /**
     * Construtor
     *
     * @param nome    nome do local
     * @param ano     ID da foto do local (Retirado do R.drawable)
     * @param diretor Distância do local em KM
     * @param genero  Nota do local (1 a 5)
     * @param faixa   Nota do local (1 a 5)
     */
    public Filme(String nome, Integer ano, String diretor, String genero, String faixa) {
        this.nome = nome;
        this.ano = ano;
        this.diretor = diretor;
        this.genero = genero;
        this.faixa = faixa;
    }

    /**
     * Construtor padrão
     */
    public Filme() {
        this(null, null, null, null, null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(Editable name) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Editable text) {
        this.ano = ano;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(Editable text) {
        this.diretor = diretor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFaixa() {
        return faixa;
    }

    public void setFaixa(String faixa) {
        this.faixa = faixa;
    }
}

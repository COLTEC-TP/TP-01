package com.example.gui.tp;

/**
 * Created by Guilherme Assis on 22/04/2018.
 */

public class filmes {
    private String NOME;
    private String GENERO;
    private String DIRETOR;
    private String ANO;
    private String FAIXA;

    public filmes(String fNome,String fGenero, String fDiretor, String fAno, String fFaixa){
        NOME = fNome;
        GENERO = fGenero;
        DIRETOR = fDiretor;
        ANO = fAno;
        FAIXA = fFaixa;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String Nome) {
        NOME = Nome;
    }

    public String getGENERO() {
        return GENERO;
    }

    public void setGENERO (String Genero) {
        GENERO = Genero;
    }

    public String getDIRETOR() {
        return DIRETOR;
    }

    public void setDIRETOR(String Diretor) {
        DIRETOR = Diretor;
    }

    public String getANO() {
        return ANO;
    }

    public void setANO(String Ano) {
        ANO = Ano;
    }

    public String getFAIXA() {
        return FAIXA;
    }

    public void setFAIXA(String Faixa) {
        FAIXA = Faixa;
    }

}

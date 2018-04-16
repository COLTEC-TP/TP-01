package com.example.haasi.tp_01;


public class Filme {
    private Integer id;
    private String movie;
    private Integer type;
    private Integer rate;
    private String year;
    private String director;


    /**
     * Construtor
     * @param id Identificador da DataBase
     * @param movie Nome do filme
     * @param type Gênero do filme
     * @param rate Classificação Indicativa do filme
     * @param year Ano de Lançamento do filem
     * @param director Diretor do filme
     */

    public Filme(Integer id, String movie, Integer type, Integer rate, String year, String director) {
        this.id = id;
        this.movie = movie;
        this.type = type;
        this.rate = rate;
        this.year = year;
        this.director = director;
    }




    public Integer getId() {
        return id;
    }

    public Filme() {
        this(null, null, null, null, null, null);
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

}
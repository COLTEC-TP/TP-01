package com.example.haasi.tp_01;

public class Filme {
    private String movie;
    private String type;
    private Integer rate;
    private Integer year;
    private String director;
    /**
     * Construtor
     * @param movie Nome do filme
     * @param type Gênero do filme
     * @param rate Classificação Indicativa do filme
     * @param year Ano de Lançamento do filem
     * @param director Diretor do filme
     */

    public Filme(String movie, String type, Integer rate, Integer year, String director) {
        this.movie = movie;
        this.type = type;
        this.rate = rate;
        this.year = year;
        this.director = director;
    }

    /**
     * Construtor padrão
     */
    public Filme() {
        this(null, null, null, null, null);
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

}

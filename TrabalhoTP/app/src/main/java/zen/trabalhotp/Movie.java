package zen.trabalhotp;

public class Movie {

    private Integer id;
    private String name;
    private String genre;
    private String director;
    private Integer ratingRange;
    private Integer year;

    public Movie(String name, String genre, String director, Integer ratingRange, Integer year) {
        this.setName(name);
        this.setGenre(genre);
        this.setDirector(director);
        this.setRatingRange(ratingRange);
        this.setYear(year);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getRatingRange() {
        return ratingRange;
    }

    public void setRatingRange(Integer ratingRange) {
        this.ratingRange = ratingRange;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

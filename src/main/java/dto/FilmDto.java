package dto;

import entities.Genre;

import java.sql.Blob;
import java.sql.Time;
import java.util.List;

public class FilmDto {

    private int id;
    private String filmTitle;
    private String filmTitleUK;
    private String description;
    private String descriptionUK;
    private Time duration;
    private Blob poster;
    private List<Genre> genre;

    public FilmDto(int id, String filmTitle, String filmTitleUK, String description, String descriptionUK, Time duration, Blob poster, List<Genre> genre) {
        this.id = id;
        this.filmTitle = filmTitle;
        this.filmTitleUK = filmTitleUK;
        this.description = description;
        this.descriptionUK = descriptionUK;
        this.duration = duration;
        this.poster = poster;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getFilmTitleUK() {
        return filmTitleUK;
    }

    public void setFilmTitleUK(String filmTitleUK) {
        this.filmTitleUK = filmTitleUK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionUK() {
        return descriptionUK;
    }

    public void setDescriptionUK(String descriptionUK) {
        this.descriptionUK = descriptionUK;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Blob getPoster() {
        return poster;
    }

    public void setPoster(Blob poster) {
        this.poster = poster;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }
}

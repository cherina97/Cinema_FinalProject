package dto;

import entities.Genre;

import java.sql.Blob;
import java.sql.Time;
import java.util.List;

/**
 * The type Film dto.
 */
public class FilmDto {

    private int id;
    private String filmTitle;
    private String filmTitleUK;
    private String description;
    private String descriptionUK;
    private Time duration;
    private Blob poster;
    private List<Genre> genre;

    /**
     * Instantiates a new Film dto.
     *
     * @param id            the id
     * @param filmTitle     the film title
     * @param filmTitleUK   the film title uk
     * @param description   the description
     * @param descriptionUK the description uk
     * @param duration      the duration
     * @param poster        the poster
     * @param genre         the genre
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets film title.
     *
     * @return the film title
     */
    public String getFilmTitle() {
        return filmTitle;
    }

    /**
     * Sets film title.
     *
     * @param filmTitle the film title
     */
    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    /**
     * Gets film title uk.
     *
     * @return the film title uk
     */
    public String getFilmTitleUK() {
        return filmTitleUK;
    }

    /**
     * Sets film title uk.
     *
     * @param filmTitleUK the film title uk
     */
    public void setFilmTitleUK(String filmTitleUK) {
        this.filmTitleUK = filmTitleUK;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets description uk.
     *
     * @return the description uk
     */
    public String getDescriptionUK() {
        return descriptionUK;
    }

    /**
     * Sets description uk.
     *
     * @param descriptionUK the description uk
     */
    public void setDescriptionUK(String descriptionUK) {
        this.descriptionUK = descriptionUK;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public Time getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(Time duration) {
        this.duration = duration;
    }

    /**
     * Gets poster.
     *
     * @return the poster
     */
    public Blob getPoster() {
        return poster;
    }

    /**
     * Sets poster.
     *
     * @param poster the poster
     */
    public void setPoster(Blob poster) {
        this.poster = poster;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public List<Genre> getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }
}

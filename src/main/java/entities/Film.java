package entities;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

/**
 * The type Film.
 */
public class Film {
    private static final Logger LOG = LoggerFactory.getLogger(Film.class);

    private int id;
    private String filmTitle;
    private String filmTitleUK;
    private String description;
    private String descriptionUK;
    private Time duration;
    private Blob poster;

    /**
     * Of film.
     *
     * @param resultSet the result set
     * @return the film
     */
    public static Film of(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            String filmTitle = resultSet.getString("film_title");
            String filmTitleUK = resultSet.getString("film_title_uk");
            String description = resultSet.getString("description");
            String descriptionUK = resultSet.getString("description_uk");
            Time duration = resultSet.getTime("duration");

            return new Film.Builder()
                    .withId(id)
                    .withFilmTitle(filmTitle)
                    .withFilmTitleUK(filmTitleUK)
                    .withDescription(description)
                    .withDescriptionUK(descriptionUK)
                    .withDuration(duration)
                    .build();

        } catch (SQLException e) {
            LOG.error("SQLException in of method of UserDao Film", e);
        }
        //todo
        return null;
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private Film film;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            film = new Film();
        }

        /**
         * With id film . builder.
         *
         * @param id the id
         * @return the film . builder
         */
        public Film.Builder withId(int id) {
            film.id = id;
            return this;
        }

        /**
         * With film title film . builder.
         *
         * @param filmTitle the film title
         * @return the film . builder
         */
        public Film.Builder withFilmTitle(String filmTitle) {
            film.filmTitle = filmTitle;
            return this;
        }

        /**
         * With film title uk film . builder.
         *
         * @param filmTitleUK the film title uk
         * @return the film . builder
         */
        public Film.Builder withFilmTitleUK(String filmTitleUK) {
            film.filmTitleUK = filmTitleUK;
            return this;
        }

        /**
         * With description film . builder.
         *
         * @param description the description
         * @return the film . builder
         */
        public Film.Builder withDescription(String description) {
            film.description = description;
            return this;
        }

        /**
         * With description uk film . builder.
         *
         * @param descriptionUK the description uk
         * @return the film . builder
         */
        public Film.Builder withDescriptionUK(String descriptionUK) {
            film.descriptionUK = descriptionUK;
            return this;
        }

        /**
         * With duration film . builder.
         *
         * @param duration the duration
         * @return the film . builder
         */
        public Film.Builder withDuration(Time duration) {
            film.duration = duration;
            return this;
        }

        /**
         * With poster film . builder.
         *
         * @param poster the poster
         * @return the film . builder
         */
        public Film.Builder withPoster(Blob poster) {
            film.poster = poster;
            return this;
        }

        /**
         * Build film.
         *
         * @return the film
         */
        public Film build() {
            return film;
        }
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
}

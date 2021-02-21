package entities;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Film {
    private static final Logger LOG = LoggerFactory.getLogger(Film.class);

    private int id;
    private String filmTitle;
    private String filmTitleUK;
    private String description;
    private String descriptionUK;
    private Time duration;
    private Blob poster;

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

    public static class Builder {
        private Film film;

        public Builder() {
            film = new Film();
        }

        public Film.Builder withId(int id) {
            film.id = id;
            return this;
        }

        public Film.Builder withFilmTitle(String filmTitle) {
            film.filmTitle = filmTitle;
            return this;
        }

        public Film.Builder withFilmTitleUK(String filmTitleUK) {
            film.filmTitleUK = filmTitleUK;
            return this;
        }

        public Film.Builder withDescription(String description) {
            film.description = description;
            return this;
        }

        public Film.Builder withDescriptionUK(String descriptionUK) {
            film.descriptionUK = descriptionUK;
            return this;
        }

        public Film.Builder withDuration(Time duration) {
            film.duration = duration;
            return this;
        }

        public Film.Builder withPoster(Blob poster) {
            film.poster = poster;
            return this;
        }

        public Film build() {
            return film;
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getFilmTitleUK() {
        return filmTitleUK;
    }

    public void setFilmTitleUK(String filmTitleUK) {
        this.filmTitleUK = filmTitleUK;
    }

    public String getDescriptionUK() {
        return descriptionUK;
    }

    public void setDescriptionUK(String descriptionUK) {
        this.descriptionUK = descriptionUK;
    }
}

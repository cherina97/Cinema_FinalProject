package entities;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Genre.
 */
public class Genre {
    private static final Logger LOG = LoggerFactory.getLogger(Genre.class);

    private int id;
    private String genreName;
    private String genreNameUK;

    /**
     * Instantiates a new Genre.
     *
     * @param genreName   the genre name
     * @param genreNameUK the genre name uk
     */
    public Genre(String genreName, String genreNameUK) {
        this.genreName = genreName;
        this.genreNameUK = genreNameUK;
    }

    /**
     * Instantiates a new Genre.
     *
     * @param id          the id
     * @param genreName   the genre name
     * @param genreNameUK the genre name uk
     */
    public Genre(int id, String genreName, String genreNameUK) {
        this.id = id;
        this.genreName = genreName;
        this.genreNameUK = genreNameUK;
    }

    /**
     * Of genre.
     *
     * @param resultSet the result set
     * @return the genre
     */
    public static Genre of(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            String genreName = resultSet.getString("genre_name");
            String genreNameUK = resultSet.getString("genre_name_uk");
            return new Genre(id, genreName, genreNameUK);
        } catch (SQLException e) {
            LOG.error("SQLException in of method of Genre class", e);
        }
        //todo
        return null;
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
     * Gets genre name.
     *
     * @return the genre name
     */
    public String getGenreName() {
        return genreName;
    }

    /**
     * Sets genre name.
     *
     * @param genreName the genre name
     */
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    /**
     * Gets genre name uk.
     *
     * @return the genre name uk
     */
    public String getGenreNameUK() {
        return genreNameUK;
    }

    /**
     * Sets genre name uk.
     *
     * @param genreNameUK the genre name uk
     */
    public void setGenreNameUK(String genreNameUK) {
        this.genreNameUK = genreNameUK;
    }

    @Override
    public String toString() {
        return genreName;
    }
}

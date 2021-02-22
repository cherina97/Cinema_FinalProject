package services;

import daos.GenreDao;
import entities.Genre;

import java.util.List;

/**
 * The type Genre service.
 */
public class GenreService {

    private final GenreDao genreDao;
    private static GenreService genreService;

    /**
     * Instantiates a new Genre service.
     */
    public GenreService(){
        this.genreDao = new GenreDao();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static GenreService getInstance() {
        if (genreService == null) {
            genreService = new GenreService();
        }
        return genreService;
    }

    /**
     * Create genre genre.
     *
     * @param genre the genre
     * @return the genre
     */
    public Genre createGenre(Genre genre){
        return genreDao.create(genre);
    }

    /**
     * Read all genres list.
     *
     * @return the list
     */
    public List<Genre> readAllGenres(){
        return genreDao.readAll();
    }

    /**
     * Get genres by ids list.
     *
     * @param genresIds the genres ids
     * @return the list
     */
    public List<Genre> getGenresByIds(List<Integer> genresIds){
        return genreDao.getGenresByIds(genresIds);
    }

    /**
     * Gets genres by film id.
     *
     * @param filmId the film id
     * @return the genres by film id
     */
    public List<Genre> getGenresByFilmId(int filmId) {
        return genreDao.getGenresByFilmId(filmId);
    }
}

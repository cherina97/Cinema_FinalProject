package services;

import daos.FilmDao;
import daos.GenreDao;
import dto.FilmDto;
import entities.Film;
import entities.Genre;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import java.util.List;
import java.util.Optional;

/**
 * The type Film service.
 */
public class FilmService {

    private final FilmDao filmDao;
    private final GenreDao genreDao;
    private static FilmService filmService;

    /**
     * Instantiates a new Film service.
     */
    public FilmService(){
        this.filmDao = new FilmDao();
        this.genreDao = new GenreDao();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static FilmService getInstance() {
        if (filmService == null) {
            filmService = new FilmService();
        }
        return filmService;
    }

    /**
     * Create film film.
     *
     * @param film the film
     * @return the film
     */
    public Film createFilm(Film film){
        return filmDao.create(film);
    }

    /**
     * Read all films list.
     *
     * @return the list
     */
    public List<Film> readAllFilms(){
        return filmDao.readAll();
    }

    /**
     * Read all list.
     *
     * @param offset      the offset
     * @param noOfRecords the no of records
     * @return the list
     */
    public List<Film> readAll(int offset, int noOfRecords){
        return filmDao.readAll(offset, noOfRecords);
    }

    /**
     * Gets no of records.
     *
     * @return the no of records
     */
    public int getNoOfRecords() {
        return filmDao.getNoOfRecords();
    }

    /**
     * Get by id film.
     *
     * @param filmId the film id
     * @return the film
     */
    public Film getById(int filmId){
        return filmDao.getById(filmId);
    }

    /**
     * Remove film.
     *
     * @param id the id
     */
    public void removeFilm(int id){
        filmDao.remove(id);
    }

    /**
     * Update film film.
     *
     * @param film the film
     * @return the film
     */
    public Film updateFilm(Film film){
        return filmDao.update(film);
    }

    /**
     * Update poster.
     *
     * @param film the film
     */
    public void updatePoster(Film film){
        filmDao.updatePoster(film);
    }

    /**
     * Upload poster.
     *
     * @param id       the id
     * @param response the response
     */
    public void uploadPoster(int id, HttpServletResponse response){
        filmDao.uploadPoster(id, response);
    }

    /**
     * Gets blob from part.
     *
     * @param filePart the file part
     * @return the blob from part
     */
    public SerialBlob getBlobFromPart(Part filePart) {
        return filmDao.getBlobFromPart(filePart);
    }

    /**
     * Set genres for film.
     *
     * @param film   the film
     * @param genres the genres
     */
    public void setGenresForFilm(Film film, List<Genre> genres){
        filmDao.setGenresForFilm(film, genres);
    }

    /**
     * Update genres for film.
     *
     * @param film   the film
     * @param genres the genres
     */
    public void updateGenresForFilm(Film film, List<Genre> genres){
        filmDao.updateGenresForFilm(film, genres);
    }

    /**
     * Gets film with genres.
     *
     * @param filmId the film id
     * @return the film with genres
     */
    public FilmDto getFilmWithGenres(int filmId) {
        Film film = filmDao.getById(filmId);
        return new FilmDto(film.getId(),
                film.getFilmTitle(),
                film.getFilmTitleUK(),
                film.getDescription(),
                film.getDescriptionUK(),
                film.getDuration(),
                film.getPoster(),
                genreDao.getGenresByFilmId(film.getId()));
    }

    /**
     * Gets film by title.
     *
     * @param filmTitle the film title
     * @return the film by title
     */
    public Optional<Film> getFilmByTitle(String filmTitle) {
        return filmDao.getFilmByTitle(filmTitle);
    }
}

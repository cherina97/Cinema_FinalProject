package services;

import daos.FilmDao;
import daos.GenreDao;
import dto.FilmDto;
import entities.Film;
import entities.Genre;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import java.util.ArrayList;
import java.util.List;

public class FilmService {

    private final FilmDao filmDao;
    private final GenreDao genreDao;
    private static FilmService filmService;

    public FilmService(){
        this.filmDao = new FilmDao();
        this.genreDao = new GenreDao();
    }

    public static FilmService getInstance() {
        if (filmService == null) {
            filmService = new FilmService();
        }
        return filmService;
    }

    public Film createFilm(Film film){
        return filmDao.create(film);
    }

    public List<Film> readAllFilms(){
        return filmDao.readAll();
    }

    public List<Film> readAll(int offset, int noOfRecords){
        return filmDao.readAll(offset, noOfRecords);
    }

    public int getNoOfRecords() {
        return filmDao.getNoOfRecords();
    }

    public Film getById(int filmId){
        return filmDao.getById(filmId);
    }

    public void removeFilm(int id){
        filmDao.remove(id);
    }

    public void updateFilm(Film film){
        filmDao.update(film);
    }

    public void updatePoster(Film film){
        filmDao.updatePoster(film);
    }

    public void uploadPoster(int id, HttpServletResponse response){
        filmDao.uploadPoster(id, response);
    }

    public SerialBlob getBlobFromPart(Part filePart) {
        return filmDao.getBlobFromPart(filePart);
    }

    public void setGenresForFilm(Film film, List<Genre> genres){
        filmDao.setGenresForFilm(film, genres);
    }

    public FilmDto getFilmWithGenres(int filmId) {
        Film film = filmDao.getById(filmId);
        return new FilmDto(film.getId(), film.getFilmTitle(), film.getFilmTitleUK(), film.getDescription(),
                film.getDescriptionUK(), film.getDuration(), film.getPoster(), genreDao.getGenresByFilmId(film.getId()));
    }

    private List<FilmDto> defineFilmsGenres(List<Film> films) {
        List<FilmDto> filmsWithGenres = new ArrayList<>();
        for (Film f : films) {
            FilmDto cf = new FilmDto(f.getId(), f.getFilmTitle(), f.getFilmTitleUK(), f.getDescription(),
                    f.getDescriptionUK(), f.getDuration(), f.getPoster(), genreDao.getGenresByFilmId(f.getId()));
            filmsWithGenres.add(cf);
        }
        return filmsWithGenres;
    }

    public List<FilmDto> getAllFilmsWithTheirGenres(Genre genre) {
        List<Film> films = filmDao.readAllFilmsWhereGenreIdPresent(genre.getId());
        return defineFilmsGenres(films);
    }
}

package services;

import daos.FilmDao;
import entities.Film;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import java.util.List;

public class FilmService {

    private final FilmDao filmDao;
    private static FilmService filmService;

    public FilmService(){
        this.filmDao = new FilmDao();
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
}

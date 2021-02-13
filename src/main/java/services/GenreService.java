package services;

import daos.GenreDao;
import entities.Genre;

import java.util.List;

public class GenreService {

    private final GenreDao genreDao;
    private static GenreService genreService;

    public GenreService(){
        this.genreDao = new GenreDao();
    }

    public static GenreService getInstance() {
        if (genreService == null) {
            genreService = new GenreService();
        }
        return genreService;
    }

    public Genre createGenre(Genre genre){
        return genreDao.create(genre);
    }

    public List<Genre> readAllGenres(){
        return genreDao.readAll();
    }

//    public Genre getByName(String name){
//        return genreDao.getByName(name);
//    }

    public List<Genre> getGenresByIds(List<Integer> genresIds){
        return genreDao.getGenresByIds(genresIds);
    }
}

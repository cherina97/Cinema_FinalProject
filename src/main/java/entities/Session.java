package entities;

import java.sql.Time;

public class Session {

    private int id;
//        private int filmId;
    private Film film;
    private Time startAt;
    private String weekDay;

    public static class Builder {
        private Session session;

        public Builder() {
            session = new Session();
        }

        public Session.Builder withId(int id) {
            session.id = id;
            return this;
        }

        //        public Session.Builder withFilmId (int filmId){
//            session.filmId = filmId;
//            return this;
//        }
        public Session.Builder withFilm(Film film) {
            session.film = film;
            return this;
        }


        public Session.Builder withTimeStartAt(Time startAt) {
            session.startAt = startAt;
            return this;
        }

        public Session.Builder withWeekDay(String weekDay) {
            session.weekDay = weekDay;
            return this;
        }

        public Session build() {
            return session;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getFilmId() {
//        return filmId;
//    }
//
//    public void setFilmId(int filmId) {
//        this.filmId = filmId;
//    }


    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Time getStartAt() {
        return startAt;
    }

    public void setStartAt(Time startAt) {
        this.startAt = startAt;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }
}

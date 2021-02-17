package entities;

import java.sql.Date;
import java.sql.Time;

public class Session {

    private int id;
    private Film film;
    private Time startAt;
    private Date date;
    private int freePlaces;

    public static class Builder {
        private Session session;

        public Builder() {
            session = new Session();
        }

        public Session.Builder withId(int id) {
            session.id = id;
            return this;
        }

        public Session.Builder withFilm(Film film) {
            session.film = film;
            return this;
        }


        public Session.Builder withTimeStartAt(Time startAt) {
            session.startAt = startAt;
            return this;
        }

        public Session.Builder withDate(Date date){
            session.date = date;
            return this;
        }

        public Session.Builder withFreeSeats(int freeSeats){
            session.freePlaces = freeSeats;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }
}

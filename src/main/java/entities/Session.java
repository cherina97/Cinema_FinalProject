package entities;

import java.sql.Date;
import java.sql.Time;

/**
 * The type Session.
 */
public class Session {

    private int id;
    private Film film;
    private Time startAt;
    private Date date;
    private int freePlaces;

    /**
     * The type Builder.
     */
    public static class Builder {
        private Session session;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            session = new Session();
        }

        /**
         * With id session . builder.
         *
         * @param id the id
         * @return the session . builder
         */
        public Session.Builder withId(int id) {
            session.id = id;
            return this;
        }

        /**
         * With film session . builder.
         *
         * @param film the film
         * @return the session . builder
         */
        public Session.Builder withFilm(Film film) {
            session.film = film;
            return this;
        }


        /**
         * With time start at session . builder.
         *
         * @param startAt the start at
         * @return the session . builder
         */
        public Session.Builder withTimeStartAt(Time startAt) {
            session.startAt = startAt;
            return this;
        }

        /**
         * With date session . builder.
         *
         * @param date the date
         * @return the session . builder
         */
        public Session.Builder withDate(Date date){
            session.date = date;
            return this;
        }

        /**
         * With free seats session . builder.
         *
         * @param freeSeats the free seats
         * @return the session . builder
         */
        public Session.Builder withFreeSeats(int freeSeats){
            session.freePlaces = freeSeats;
            return this;
        }


        /**
         * Build session.
         *
         * @return the session
         */
        public Session build() {
            return session;
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
     * Gets film.
     *
     * @return the film
     */
    public Film getFilm() {
        return film;
    }

    /**
     * Sets film.
     *
     * @param film the film
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     * Gets start at.
     *
     * @return the start at
     */
    public Time getStartAt() {
        return startAt;
    }

    /**
     * Sets start at.
     *
     * @param startAt the start at
     */
    public void setStartAt(Time startAt) {
        this.startAt = startAt;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets free places.
     *
     * @return the free places
     */
    public int getFreePlaces() {
        return freePlaces;
    }

    /**
     * Sets free places.
     *
     * @param freePlaces the free places
     */
    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }
}

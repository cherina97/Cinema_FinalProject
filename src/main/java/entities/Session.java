package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Session {

    private int id;
    private String filmTitle;
    private String description;
    private Time startAt;
    private Time duration;
    private int tickets;

    public static Session of(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            String filmTitle = resultSet.getString("film_title");
            String description = resultSet.getString("description");
            Time startAt = resultSet.getTime("start_at");
            Time duration = resultSet.getTime("duration");
            int tickets = resultSet.getInt("tickets");

            return new Session.Builder()
                    .withId(id)
                    .withFilmTitle(filmTitle)
                    .withDescription(description)
                    .withTimeStartAt(startAt)
                    .withDuration(duration)
                    .withTickets(tickets)
                    .build();

        } catch (SQLException e) {
            throw new RuntimeException("Error");
        }
    }

    public static class Builder{
        private Session session;

        public Builder() {
            session = new Session();
        }

        public Session.Builder withId(int id){
            session.id = id;
            return this;
        }

        public Session.Builder withFilmTitle(String filmTitle){
            session.filmTitle = filmTitle;
            return this;
        }

        public Session.Builder withDescription(String description){
            session.description = description;
            return this;
        }

        public Session.Builder withTimeStartAt(Time startAt){
            session.startAt = startAt;
            return this;
        }

        public Session.Builder withDuration(Time duration){
            session.duration = duration;
            return this;
        }

        public Session.Builder withTickets(int tickets){
            session.tickets = tickets;
            return this;
        }

        public Session build(){
            return session;
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

    public Time getStartAt() {
        return startAt;
    }

    public void setStartAt(Time startAt) {
        this.startAt = startAt;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }
}

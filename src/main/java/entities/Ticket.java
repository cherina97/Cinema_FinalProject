package entities;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ticket {

    private int id;
    private int seatNumber;
    private BigDecimal price;
    private int userId;
    private int sessionId;
    private boolean isAvailable;

    public static Ticket of(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            int seatNumber = resultSet.getInt("seat_number");
            BigDecimal price = resultSet.getBigDecimal("price");
            int userId = resultSet.getInt("user_id");
            int sessionId = resultSet.getInt("session_id");

            return new Ticket.Builder()
                    .withId(id)
                    .withSeatNumber(seatNumber)
                    .withPrice(price)
                    .withUserId(userId)
                    .withSessionId(sessionId)
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException("Error");
        }
    }

    public static class Builder{
        private Ticket ticket;

        public Builder() {
            ticket = new Ticket();
        }

        public Ticket.Builder withId(int id){
            ticket.id = id;
            return this;
        }

        public Ticket.Builder withSeatNumber(int seatNumber){
            ticket.seatNumber = seatNumber;
            return this;
        }

        public Ticket.Builder withPrice(BigDecimal price){
            ticket.price = price;
            return this;
        }

        public Ticket.Builder withUserId(int userId){
            ticket.userId = userId;
            return this;
        }

        public Ticket.Builder withSessionId(int sessionId){
            ticket.sessionId = sessionId;
            return this;
        }

        public Ticket build(){
            return ticket;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

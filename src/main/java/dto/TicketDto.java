package dto;

import entities.Session;

import java.math.BigDecimal;

public class TicketDto {

    private int id;
    private int seatNumber;
    private BigDecimal price;
    private int userId;
    private Session session;

    public TicketDto(int id, int seatNumber, BigDecimal price, int userId, Session session) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.price = price;
        this.userId = userId;
        this.session = session;
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

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}

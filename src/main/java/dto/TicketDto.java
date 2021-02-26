package dto;

import entities.Session;

import java.math.BigDecimal;

/**
 * The type Ticket dto.
 */
public class TicketDto {

    private int id;
    private int seatNumber;
    private BigDecimal price;
    private int userId;
    private Session session;

    /**
     * Instantiates a new Ticket dto.
     *
     * @param id         the id
     * @param seatNumber the seat number
     * @param price      the price
     * @param userId     the user id
     * @param session    the session
     */
    public TicketDto(int id, int seatNumber, BigDecimal price, int userId, Session session) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.price = price;
        this.userId = userId;
        this.session = session;
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
     * Gets seat number.
     *
     * @return the seat number
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * Sets seat number.
     *
     * @param seatNumber the seat number
     */
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets session.
     *
     * @return the session
     */
    public Session getSession() {
        return session;
    }

    /**
     * Sets session.
     *
     * @param session the session
     */
    public void setSession(Session session) {
        this.session = session;
    }
}

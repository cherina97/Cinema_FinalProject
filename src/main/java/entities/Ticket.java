package entities;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Ticket.
 */
public class Ticket {
    private static final Logger LOG = LoggerFactory.getLogger(Ticket.class);

    private int id;
    private int seatNumber;
    private BigDecimal price;
    private int userId;
    private int sessionId;

    /**
     * Of ticket.
     *
     * @param resultSet the result set
     * @return the ticket
     */
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
            LOG.error("SQLException in of method of Ticket class", e);
        }
        //todo
        return null;
    }

    /**
     * The type Builder.
     */
    public static class Builder{
        private final Ticket ticket;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            ticket = new Ticket();
        }

        /**
         * With id ticket . builder.
         *
         * @param id the id
         * @return the ticket . builder
         */
        public Ticket.Builder withId(int id){
            ticket.id = id;
            return this;
        }

        /**
         * With seat number ticket . builder.
         *
         * @param seatNumber the seat number
         * @return the ticket . builder
         */
        public Ticket.Builder withSeatNumber(int seatNumber){
            ticket.seatNumber = seatNumber;
            return this;
        }

        /**
         * With price ticket . builder.
         *
         * @param price the price
         * @return the ticket . builder
         */
        public Ticket.Builder withPrice(BigDecimal price){
            ticket.price = price;
            return this;
        }

        /**
         * With user id ticket . builder.
         *
         * @param userId the user id
         * @return the ticket . builder
         */
        public Ticket.Builder withUserId(int userId){
            ticket.userId = userId;
            return this;
        }

        /**
         * With session id ticket . builder.
         *
         * @param sessionId the session id
         * @return the ticket . builder
         */
        public Ticket.Builder withSessionId(int sessionId){
            ticket.sessionId = sessionId;
            return this;
        }

        /**
         * Build ticket.
         *
         * @return the ticket
         */
        public Ticket build(){
            return ticket;
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
     * Gets session id.
     *
     * @return the session id
     */
    public int getSessionId() {
        return sessionId;
    }

    /**
     * Sets session id.
     *
     * @param sessionId the session id
     */
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatNumber=" + seatNumber +
                '}';
    }
}

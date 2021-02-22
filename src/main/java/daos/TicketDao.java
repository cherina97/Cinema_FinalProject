package daos;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import entities.Ticket;
import entities.User;
import utils.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Ticket dao.
 */
public class TicketDao implements CRUD<Ticket> {
    private static final Logger LOG = LoggerFactory.getLogger(TicketDao.class);

    private static final String INSERT_TICKET =
            "INSERT INTO tickets (seat_number, price, session_id) VALUES (?, ?, ?);";
    private static final String READ_ALL_TICKETS = "SELECT * FROM tickets";
    private static final String READ_TICKETS_BY_SESSION = "SELECT * FROM tickets WHERE session_id = ?";
    private static final String GET_TICKETS_BY_SEATS =
            "SELECT * FROM tickets WHERE seat_number IN (?) AND session_id = ?";
    private static final String SET_TICKETS_FOR_USER = "UPDATE tickets SET user_id = ? WHERE id IN (?)";
    private static final String DELETE_BY_ID = "DELETE FROM tickets WHERE id = ?";
    private static final String GET_TICKETS_BY_USER = "SELECT * FROM tickets WHERE user_id = ?";
    private static final String GET_TICKET_BY_ID = "SELECT * FROM tickets WHERE id = ?";
    private final Connection connection;

    /**
     * Instantiates a new Ticket dao.
     */
    public TicketDao() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }


    @Override
    public Ticket create(Ticket ticket) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, ticket.getSeatNumber());
            preparedStatement.setBigDecimal(2, ticket.getPrice());
            preparedStatement.setInt(3, ticket.getSessionId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            ticket.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            LOG.error("SQLException in create method of TicketDao class", e);
        }
        return ticket;
    }

    @Override
    public List<Ticket> readAll() {
        List<Ticket> ticketList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_TICKETS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ticketList.add(Ticket.of(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("SQLException in readAll method of TicketDao class", e);
        }
        return ticketList;
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("SQLException in remove method of TicketDao class", e);
        }
    }

    @Override
    public Ticket update(Ticket ticket) {
        //todo
        return null;
    }

    /**
     * Read all tickets by session id list.
     *
     * @param sessionId the session id
     * @return the list
     */
    public List<Ticket> readAllTicketsBySessionId(int sessionId) {
        List<Ticket> ticketList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_TICKETS_BY_SESSION)) {
            preparedStatement.setInt(1, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ticketList.add(Ticket.of(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("SQLException in readAllTicketsBySessionId method of TicketDao class", e);
        }
        return ticketList;
    }

    /**
     * Gets tickets by seats.
     *
     * @param checked   the checked
     * @param sessionId the session id
     * @return the tickets by seats
     */
    public List<Ticket> getTicketsBySeats(List<Integer> checked, int sessionId) {
        List<Ticket> checkedTickets = new ArrayList<>();
        try {
            String sqlIN = checked.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",", "(", ")"));

            String sql = GET_TICKETS_BY_SEATS.replace("(?)", sqlIN);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                checkedTickets.add(Ticket.of(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("SQLException in getTicketsBySeats method of TicketDao class", e);
        }
        return checkedTickets;
    }

    /**
     * Sets user for tickets.
     *
     * @param ticketsBySeats the tickets by seats
     * @param user           the user
     */
    public void setUserForTickets(List<Ticket> ticketsBySeats, User user) {
        try {
            String sqlIN = ticketsBySeats.stream()
                    .map(Ticket::getId)
                    .map(String::valueOf)
                    .collect(Collectors.joining(",", "(", ")"));

            String sql = SET_TICKETS_FOR_USER.replace("(?)", sqlIN);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error("SQLException in setUserForTickets method of TicketDao class", e);
        }
    }

    /**
     * Gets tickets by user.
     *
     * @param user the user
     * @return the tickets by user
     */
    public List<Ticket> getTicketsByUser(User user) {
        List<Ticket> ticketList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TICKETS_BY_USER);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ticketList.add(Ticket.of(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("SQLException in getTicketsByUser method of TicketDao class", e);
        }
        return ticketList;
    }

    /**
     * Gets by id.
     *
     * @param ticketId the ticket id
     * @return the by id
     */
    public Ticket getById(int ticketId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TICKET_BY_ID)) {
            preparedStatement.setInt(1, ticketId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Ticket.of(resultSet);
            }
        } catch (SQLException e) {
            LOG.error("SQLException in getById method of TicketDao class", e);
        }
        //todo
        return null;
    }

    /**
     * Create tickets for session.
     *
     * @param sessionId the session id
     */
    public void createTicketsForSession(int sessionId) {
        try {
            connection.setAutoCommit(false);
            for (int i = 1; i <= 72; i++) {
                create(new Ticket.Builder()
                        .withSeatNumber(i)
                        .withSessionId(sessionId)
                        .withPrice(BigDecimal.valueOf(50))
                        .build());
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOG.error("SQLException in createTicketsForSession method of TicketDao class", e);
        }

    }
}

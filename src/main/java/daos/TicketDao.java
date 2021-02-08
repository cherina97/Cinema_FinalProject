package daos;

import entities.Ticket;
import entities.User;
import utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketDao implements CRUD<Ticket>{
    private static final String INSERT_TICKET =
            "INSERT INTO tickets (seat_number, price, session_id) VALUES (?, ?, ?);";
    private static final String READ_ALL_TICKETS = "SELECT * FROM tickets";
    private static final String READ_TICKETS_BY_SESSION = "SELECT * FROM tickets WHERE session_id = ?";
    private static final String GET_TICKETS_BY_SEATS =
            "SELECT * FROM tickets WHERE seat_number IN (?) AND session_id = ?";
    private static final String SET_TICKETS_FOR_USER = "UPDATE tickets SET user_id = ? WHERE id IN (?)";
    private final Connection connection;

    public TicketDao() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }


    @Override
    public Ticket create(Ticket ticket) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setInt(1, ticket.getSeatNumber());
            preparedStatement.setBigDecimal(2, ticket.getPrice());
            preparedStatement.setInt(3, ticket.getSessionId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            ticket.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return ticketList;
    }

    public List<Ticket> readAllTicketsBySessionId(int sessionId) {
        List<Ticket> ticketList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_TICKETS_BY_SESSION)) {
            preparedStatement.setInt(1, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ticketList.add(Ticket.of(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketList;
    }

    public List<Ticket> getTicketsBySeats(List<Integer> checked, int sessionId) {
        List <Ticket> checkedTickets = new ArrayList<>();
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
            e.printStackTrace();
        }
        return checkedTickets;
    }

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
            e.printStackTrace();
        }


    }
}

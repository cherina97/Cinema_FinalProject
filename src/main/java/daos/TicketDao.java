package daos;

import entities.Ticket;
import utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements CRUD<Ticket>{
    private static final String INSERT_TICKET =
            "INSERT INTO tickets (seat_number, price, session_id) VALUES (?, ?, ?);";
    private static final String READ_ALL_TICKETS = "SELECT * FROM tickets";
    private static final int DEFAULT_NUMBER_OF_SEATS = 5;
    private static final String INSERT_TICKET_FOR_SESSION =
            "INSERT INTO tickets (id, seat_number, price, session_id) VALUES (?, ?, ?, ?);";
    private static final String READ_TICKETS_BY_SESSION = "SELECT * FROM tickets WHERE session_id = ?";
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


//    public void createTicketsForSession(int sessionId) {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET_FOR_SESSION, Statement.RETURN_GENERATED_KEYS)) {
//
//            for (int i = 1; i <= DEFAULT_NUMBER_OF_SEATS; i++) {
//                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//                generatedKeys.next();
//                preparedStatement.setInt(1, generatedKeys.getInt(1));
//
//                preparedStatement.setInt(2, i);
//                preparedStatement.setDouble(3, 50.0);
//                preparedStatement.setInt(4, sessionId);
//                preparedStatement.executeUpdate();
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}

package entities;

import java.sql.Time;
import java.util.List;

public class Session {

    private int id;
    private String filmTitle;
    private String description;
    private Time startAt;
    private Time duration;
    private List <Ticket> seats;


}

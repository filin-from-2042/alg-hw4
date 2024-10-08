package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketTests {

    @Test
    public void when_firstPensionSecondOther_then_firstGreater(){
        Ticket first = new Ticket(Ticket.Type.PENSION);
        Ticket second = new Ticket(Ticket.Type.OTHER);

        assertTrue(first.compareTo(second) > 0);
    }

    @Test
    public void when_bothPension_then_firstGreater(){
        Ticket first = new Ticket(Ticket.Type.PENSION);
        Ticket second = new Ticket(Ticket.Type.PENSION);

        assertTrue(first.compareTo(second) > 0);
    }

    @Test
    public void when_bothOther_then_firstGreater(){
        Ticket first = new Ticket(Ticket.Type.OTHER);
        Ticket second = new Ticket(Ticket.Type.OTHER);

        assertTrue(first.compareTo(second) > 0);
    }
}

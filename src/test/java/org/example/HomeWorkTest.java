package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();
    TicketManager manager = homeWork.managerFabric();

    @BeforeEach
    public void setUp(){
        Ticket other = new Ticket(Ticket.Type.OTHER);
        manager.add(other);
        other = new Ticket(Ticket.Type.OTHER);
        manager.add(other);
        other = new Ticket(Ticket.Type.OTHER);
        manager.add(other);
        other = new Ticket(Ticket.Type.OTHER);
        manager.add(other);
        other = new Ticket(Ticket.Type.OTHER);
        manager.add(other);
        other = new Ticket(Ticket.Type.OTHER);
        manager.add(other);
        other = new Ticket(Ticket.Type.OTHER);
        manager.add(other);
    }

    @Test
    void when_othersOnly_then_returnFirstRegister() {
        HomeWork homeWork = new HomeWork();
        TicketManager manager = homeWork.managerFabric();
        Ticket other1 = new Ticket(Ticket.Type.OTHER);
        manager.add(other1);
        Ticket other2 = new Ticket(Ticket.Type.OTHER);
        manager.add(other2);
        Ticket other3 = new Ticket(Ticket.Type.OTHER);
        manager.add(other3);

        Ticket next = manager.next();

        assertEquals(other1, next);
    }

    @Test
    void when_pensionSingle_then_returnFirst() {
        Ticket pension = new Ticket(Ticket.Type.PENSION);
        manager.add(pension);

        Ticket next = manager.next();

        assertEquals(pension, next);
    }

    @Test
    void when_pensionSeveral_then_returnFirstRegister() {
        Ticket pension1 = new Ticket(Ticket.Type.PENSION);
        manager.add(pension1);
        Ticket pension2 = new Ticket(Ticket.Type.PENSION);
        manager.add(pension2);
        Ticket pension3 = new Ticket(Ticket.Type.PENSION);
        manager.add(pension3);

        Ticket next = manager.next();

        assertEquals(pension1, next);
    }

    @Test
    public void when_addNull_then_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> manager.add(null));
    }

    @Test
    void check() {
        List<Integer> expectedQueue = generateQueue(1, 4);
        List<String> pairs = generatePairs(expectedQueue);
        assertEquals(expectedQueue, homeWork.check(pairs));
    }

    private List<String> generatePairs(List<Integer> expectedQueue) {
        List<String> pairs = new ArrayList<>();
        Function<Integer, Integer> map = (x) -> (x < 0 || x >= expectedQueue.size()) ? 0 : expectedQueue.get(x);

        for (int i = 0;
             i < expectedQueue.size(); i++) {
            pairs.add(String.format("%d:%d", map.apply(i - 1), map.apply(i + 1)));
        }
        Collections.shuffle(pairs);
        return pairs;
    }

    private List<Integer> generateQueue(int seed, int length) {
        return new Random(seed)
                .ints(1, length * 100)
                .limit(length)
                .boxed()
                .collect(Collectors.toList());
    }


}
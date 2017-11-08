package org.workshop;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flight {

    private final Map<String, Seat> seats = new HashMap<>();
    private final String flightNo;
    private final String origin;
    private final String destination;


    public Flight(String flightNo, List<Seat> seats, String origin, String destination) {
        this.flightNo = flightNo;
        if (seats.size() == 0) {
            throw new IllegalArgumentException("Flight requires at least one seat");
        }
        for (Seat seat : seats) {
            this.seats.put(seat.getSeatNo(), seat);
        }
        this.origin = origin;
        this.destination = destination;
    }

    public int getAvailableSeats() {
        return (int) seats.values().stream().filter(seat -> !seat.isBooked()).count();
    }

    public Seat getCheapestSeat() {
//        return seats.values().stream().reduce((seat, seat2) -> (seat.getPrice() > seat2.getPrice())? seat2:seat).get();
        return seats.values().stream().min(
            Comparator.comparingInt(Seat::getPrice)).get();
    }

    public void book(String seatNo) {
        Seat seat = seats.get(seatNo);
        if (seat.isBooked()) {
            throw new AlreadyBookedException();
        }
        seat.setBooked(true);
    }

    public String getFlightNo() {
        return flightNo;
    }

    public int getAverageAvailablePrice() {
        int total = 0;
        int count = 0;
        for (Seat seat : seats.values()) {
            if (!seat.isBooked()) {
                total += seat.getPrice();
                count++;
            }
        }
        if (count == 0) {
            throw new NoAvailableSeatsException();
        }
        return total / count;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }
}

package org.workshop;

import java.util.LinkedList;
import java.util.List;

public class FlightBuilder {
    private static int DEFAULT_FLIGHT_NO = 100;
    private static final int DEFAULT_PRICE = 100;
    private static final String DEFAULT_ORIGIN = "NYC";
    private static final String DEFAULT_DESTINATION = "SFO";

    private final String flightNo;
    private List<Seat> seats = new LinkedList<>();
    private int seatNo = 1;
    private String origin = DEFAULT_ORIGIN ;
    private String destination = DEFAULT_DESTINATION ;

    public FlightBuilder(String flightNo) {
        this.flightNo = flightNo;
    }

    public FlightBuilder() {
        this(generateDefaultFlightNo());
    }

    public FlightBuilder seat() {
        seats.add(new Seat(defaultSeatNo(), DEFAULT_PRICE));
        return this;
    }

    private static String generateDefaultFlightNo() {
        DEFAULT_FLIGHT_NO ++;
        return "AA" + DEFAULT_FLIGHT_NO;
    }
    private String defaultSeatNo() {
        return "A" + (seatNo++);
    }

    public Flight build() {
        if (seats.size() == 0) {
            seat();
        }
        return new Flight(flightNo, seats, origin, destination);
    }

    public FlightBuilder seat(String seatNo) {
        seats.add(new Seat(seatNo, DEFAULT_PRICE));
        return this;
    }

    public FlightBuilder seat(int price) {
        seats.add(new Seat(defaultSeatNo(), price));
        return this;
    }

    public FlightBuilder seat(int price, boolean booked) {
        Seat seat = new Seat(defaultSeatNo(), price);
        seat.setBooked(booked);
        seats.add(seat);
        return this;
    }

    public FlightBuilder origin(String origin) {
        this.origin = origin;
        return this;
    }

    public FlightBuilder destination(String destination) {
        this.destination = destination;
        return this;
    }

}

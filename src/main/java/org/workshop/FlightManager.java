package org.workshop;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FlightManager {

    private Map<String, Flight> _flights = new LinkedHashMap<>();

    public void addFlights(Flight... flights) {
        for (Flight flight : flights) {
            this._flights.put(flight.getFlightNo(), flight);
        }
    }

    public Flight getFlight(String flightNo) {
        Flight flight = _flights.get(flightNo);
        if (flight == null) {
            throw new FlightNotFoundException();
        }
        return flight;
    }

    public List<Flight> getAvailableFlightsBetweenTwoDestinations(String origin,
        String destination) {
        List<Flight> availableFlights = new ArrayList<>();

        for (String flightNo : _flights.keySet()) {
            Flight flight = _flights.get(flightNo);
            if ((flight.getOrigin().equals(origin)) && (flight.getDestination()
                .equals(destination))) {
                availableFlights.add(flight);
            }
        }
        return availableFlights;
    }
}

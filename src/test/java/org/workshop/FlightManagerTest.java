package org.workshop;

import java.util.List;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FlightManagerTest {

    FlightManager manager = new FlightManager();

    @Test
    public void should_provide_available_seats() throws Exception {
        //given
        Flight flight = new FlightBuilder("AA101").seat().build();
        manager.addFlight(flight);

        //expect
        assertEquals(flight, manager.getFlight("AA101"));
    }

    @Test
    public void should_fail_when_flight_not_found() throws Exception {
        //given
        try {
            //when
            manager.getFlight("UA999");
            //then
            fail();
        } catch (FlightNotFoundException e) {
        }
    }

    @Test
    public void should_provide_a_list_of_available_flights_between_a_destination_pair() {
        // given
        Flight flight1 = new FlightBuilder("AA01").seat().build();
        Flight flight2 = new FlightBuilder("AA02").seat().build();
        List<Flight> expectedFlightList = asList(flight1, flight2);
        // when
        List<Flight> flights = manager.getAvailableFlightsBetweenTwoDestinations("SFO", "NYC");

        // then
        assertThat(flights).isEqualTo(expectedFlightList);
    }
}

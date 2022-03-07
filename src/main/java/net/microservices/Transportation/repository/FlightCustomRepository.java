package net.microservices.Transportation.repository;

import net.microservices.Transportation.model.Flight;
import reactor.core.publisher.Flux;


public interface FlightCustomRepository
{
    Flux<Flight> findProperties(String flightsCode,
                                String flightsCountry,
                                String flightsOrigin,
                                String flightsDestination,
                                String flightsCapacity,
                                String flightsVendor,
                                Integer cost,
                                String createdBy);
}

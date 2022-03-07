package net.microservices.Transportation.repository;

import net.microservices.Transportation.model.Flight;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface FlightRepository extends ReactiveMongoRepository<Flight,String>
{
    Flux<Flight> findByflightsCodeIgnoreCase(String code);
    Flux<Flight> findByStatus(Boolean b);
}

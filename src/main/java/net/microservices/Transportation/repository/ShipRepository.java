package net.microservices.Transportation.repository;

import net.microservices.Transportation.model.Ship;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ShipRepository extends ReactiveMongoRepository<Ship,String>
{
    Flux<Ship> findByshipsCodeIgnoreCase(String code);
    Flux<Ship> findByStatus(Boolean b);
}

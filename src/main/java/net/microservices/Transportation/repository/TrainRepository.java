package net.microservices.Transportation.repository;

import net.microservices.Transportation.model.Train;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface TrainRepository  extends ReactiveMongoRepository<Train,String>
{

    Flux<Train> findBytrainsCodeIgnoreCase(String code);
    Flux<Train> findByStatus(Boolean b);
}

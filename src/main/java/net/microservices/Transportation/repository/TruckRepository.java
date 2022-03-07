package net.microservices.Transportation.repository;

import net.microservices.Transportation.model.Truck;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface TruckRepository extends ReactiveMongoRepository<Truck,String>
{

    Flux<Truck> findBytrucksCodeIgnoreCase(String code);
    Flux<Truck> findByStatus(Boolean b);

}

//    Flux<Truck> findBycommodityNameIgnoreCase(String name);
//    Flux<Truck> findBycreatedByIgnoreCase(String name);


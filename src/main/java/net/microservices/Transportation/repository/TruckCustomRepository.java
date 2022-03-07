package net.microservices.Transportation.repository;


import net.microservices.Transportation.model.Truck;
import reactor.core.publisher.Flux;


public interface TruckCustomRepository
{
        Flux<Truck> findProperties(String trucksCode,
                                   String trucksCountry,
                                   String trucksOrigin,
                                   String trucksDestination,
                                   String trucksCapacity,
                                   String trucksVendor,
                                   Integer cost,
                                   String createdBy);

}

package net.microservices.Transportation.repository;

import net.microservices.Transportation.model.Train;
import reactor.core.publisher.Flux;


public interface TrainCustomRepository
{
    Flux<Train> findProperties(String trainsCode,
                               String trainsCountry,
                               String trainsOrigin,
                               String trainsDestination,
                               String trainsCapacity,
                               String trainsVendor,
                               Integer cost,
                               String createdBy);
}

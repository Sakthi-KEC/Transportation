package net.microservices.Transportation.repository;

import net.microservices.Transportation.model.Ship;
import reactor.core.publisher.Flux;


public interface ShipCustomRepository
{
    Flux<Ship> findProperties(String shipsCode,
                              String shipsCountry,
                              String shipsOrigin,
                              String shipsDestination,
                              String shipsCapacity,
                              String shipsVendor,
                              Integer cost,
                              String createdBy);
}


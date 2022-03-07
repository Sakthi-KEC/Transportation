package net.microservices.Transportation.service;

import net.microservices.Transportation.appUtil.ShipAppUtil;
import net.microservices.Transportation.dto.ShipDto;
import net.microservices.Transportation.exception.CustomException;
import net.microservices.Transportation.repository.ShipCustomRepository;
import net.microservices.Transportation.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShipService
{
    @Autowired
    private ShipRepository shipRepo;

    @Autowired
    private ShipCustomRepository customRepo;


    // Adding Truck to the Repository
    public Mono<ShipDto> addShip(Mono<ShipDto> ship)
    {
        return  ship.map(ShipAppUtil::DtoToEntity)
                .flatMap(shipRepo::insert)
                .map(ShipAppUtil::EntityToDto);
    }

    // Editing Truck by updating the old record and inserting the new record
    public Mono<ShipDto> editShip(Mono<ShipDto> ship, String id)
    {
        Mono<ShipDto> r = shipRepo.findById(id).map(ShipAppUtil::EntityToDto);

        shipRepo.findById(id)
                .flatMap(p -> r.map(ShipAppUtil::DtoToEntity))
                .doOnNext(e -> e.setStatus(false))
                .flatMap(shipRepo::save)
                .map(ShipAppUtil::EntityToDto).subscribe();

        return ship.map(ShipAppUtil::DtoToEntity)
                .flatMap(shipRepo::insert)
                .map(ShipAppUtil::EntityToDto);
    }

    // Updating the Saved Status for the searched Truck
    public Mono<ShipDto> updateSearchedShip(Mono<ShipDto> ship)
    {
        return  ship.map(ShipAppUtil::DtoToEntity)
                .flatMap(shipRepo::save)
                .map(ShipAppUtil::EntityToDto);
    }

    // Getting all Truck from the Repository
    public Flux<ShipDto> getAllShipByStatus()
    {
        return shipRepo.findByStatus(true).map(ShipAppUtil::EntityToDto);
    }

    // Getting All Truck by dynamically giving any of the fields
    public Flux<ShipDto> getShipByDynamicSearch(String shipsCode, String shipsCountry, String shipsOrigin, String shipsDestination,
                                                String shipsCapacity, String shipsVendor, Integer cost, String createdBy)
    {
        return customRepo.findProperties(shipsCode,shipsCountry,shipsOrigin,shipsDestination,shipsCapacity,shipsVendor,cost,createdBy)
                .filter(a->a.getStatus()==true)
                .map(ShipAppUtil::EntityToDto);
    }

    // Getting all the groupCodes of Searched Truck
    public Mono<List<String>> getAllShipSavedSearchCode()
    {
        return shipRepo.findAll()
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(ShipAppUtil::EntityToDto)
                .map(a->a.getShipsCode())
                .distinct()
                .collect(Collectors.toList());
    }

    // Getting all searched Truck which saved status is updated
    public Flux<ShipDto> getSavedShipByCode(String code)
    {
        return shipRepo.findByshipsCodeIgnoreCase(code)
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(ShipAppUtil::EntityToDto)
                .switchIfEmpty(Mono.defer(()-> Mono.error(new CustomException("Invalid Ship Code"))));
    }

}

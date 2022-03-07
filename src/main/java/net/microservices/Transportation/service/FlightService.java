package net.microservices.Transportation.service;

import net.microservices.Transportation.appUtil.FlightAppUtil;
import net.microservices.Transportation.dto.FlightDto;
import net.microservices.Transportation.exception.CustomException;
import net.microservices.Transportation.repository.FlightCustomRepository;
import net.microservices.Transportation.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FlightService
{
    @Autowired
    private FlightRepository flightRepo;

    @Autowired
    private FlightCustomRepository customRepo;


    // Adding Truck to the Repository
    public Mono<FlightDto> addFlight(Mono<FlightDto> flight)
    {
        return  flight.map(FlightAppUtil::DtoToEntity)
                .flatMap(flightRepo::insert)
                .map(FlightAppUtil::EntityToDto);
    }

    // Editing Truck by updating the old record and inserting the new record
    public Mono<FlightDto> editFlight(Mono<FlightDto> flight, String id)
    {
        Mono<FlightDto> r = flightRepo.findById(id).map(FlightAppUtil::EntityToDto);

        flightRepo.findById(id)
                .flatMap(p -> r.map(FlightAppUtil::DtoToEntity))
                .doOnNext(e -> e.setStatus(false))
                .flatMap(flightRepo::save)
                .map(FlightAppUtil::EntityToDto).subscribe();

        return flight.map(FlightAppUtil::DtoToEntity)
                .flatMap(flightRepo::insert)
                .map(FlightAppUtil::EntityToDto);
    }

    // Updating the Saved Status for the searched Truck
    public Mono<FlightDto> updateSearchedFlight(Mono<FlightDto> flight)
    {
        return  flight.map(FlightAppUtil::DtoToEntity)
                .flatMap(flightRepo::save)
                .map(FlightAppUtil::EntityToDto);
    }

    // Getting all Truck from the Repository
    public Flux<FlightDto> getAllFlightByStatus()
    {
        return flightRepo.findByStatus(true).map(FlightAppUtil::EntityToDto);
    }

    // Getting All Truck by dynamically giving any of the fields
    public Flux<FlightDto> getFlightByDynamicSearch(String flightCode, String flightCountry, String flightOrigin, String flightDestination,
                                             String flightCapacity, String flightVendor, Integer cost, String createdBy)
    {
        return customRepo.findProperties(flightCode,flightCountry,flightOrigin,flightDestination,flightCapacity,flightVendor,cost,createdBy)
                .filter(a->a.getStatus()==true)
                .map(FlightAppUtil::EntityToDto);
    }

    // Getting all the groupCodes of Searched Truck
    public Mono<List<String>> getAllFlightSavedSearchCode()
    {
        return flightRepo.findAll()
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(FlightAppUtil::EntityToDto)
                .map(a->a.getFlightsCode())
                .distinct()
                .collect(Collectors.toList());
    }

    // Getting all searched Truck which saved status is updated
    public Flux<FlightDto> getSavedFlightByCode(String code)
    {
        return flightRepo.findByflightsCodeIgnoreCase(code)
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(FlightAppUtil::EntityToDto)
                .switchIfEmpty(Mono.defer(()-> Mono.error(new CustomException("Invalid Flight Code"))));
    }
}

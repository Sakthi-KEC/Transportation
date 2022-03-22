package net.microservices.Transportation.controller;


import net.microservices.Transportation.dto.FlightDto;
import net.microservices.Transportation.dto.ShipDto;
import net.microservices.Transportation.dto.TrainDto;
import net.microservices.Transportation.dto.TruckDto;
import net.microservices.Transportation.service.FlightService;
import net.microservices.Transportation.service.ShipService;
import net.microservices.Transportation.service.TrainService;
import net.microservices.Transportation.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transportation")

public class TransportationController
{

    @Autowired
    private TruckService truckService;
    @Autowired
    private TrainService trainService;
    @Autowired
    private ShipService shipService;
    @Autowired
    private FlightService flightService;


//// Truck

    @PostMapping("/truck")
    public Mono<TruckDto> addTruck(@RequestBody Mono<TruckDto> truck)
    {
        return truckService.addTruck(truck);
    }

    @PutMapping("/truck/edit/{id}")
    public Mono<TruckDto> editTruck(@RequestBody Mono<TruckDto> truck, @PathVariable String id)
    {
        return truckService.editTruck(truck,id);
    }

    @PutMapping("/truck/savedsearch")
    public Mono<TruckDto> updateSearchedTruck(@RequestBody Mono<TruckDto> truck)
    {
        return truckService.updateSearchedTruck(truck);
    }

    @GetMapping("/truck")
    public Flux<TruckDto> getAllTruckByStatus()
    {
        return truckService.getAllTruckByStatus();
    }

    @GetMapping("/truck/search")
    public Flux<TruckDto> getTruckByDynamicSearch(@RequestParam(required = false) String trucksCode,
                                                  @RequestParam(required = false) String trucksCountry,
                                                  @RequestParam(required = false) String trucksOrigin,
                                                  @RequestParam(required = false) String trucksDestination,
                                                  @RequestParam(required = false) String trucksCapacity,
                                                  @RequestParam(required = false) String trucksVendor,
                                                  @RequestParam(required = false) Integer cost,
                                                  @RequestParam(required = false) String createdBy)
    {
        return truckService.getTruckByDynamicSearch(trucksCode,trucksCountry,trucksOrigin,trucksDestination,trucksCapacity,trucksVendor,cost,createdBy);
    }

    @GetMapping("/truck/savedsearch/codes")
    public Mono<List<String>> getAllTruckSavedSearchCode()
    {
        return truckService.getAllTruckSavedSearchCode();
    }

    @GetMapping("/truck/saved/{code}")
    public Flux<TruckDto> getSavedTruckByCode(@PathVariable String code)
    {
        return truckService.getSavedTruckByCode(code);
    }


//// Train


    @PostMapping("/train")
    public Mono<TrainDto> addTrain(@RequestBody Mono<TrainDto> train)
    {
        return trainService.addTrain(train);
    }

    @PutMapping("/train/edit/{id}")
    public Mono<TrainDto> editTrain(@RequestBody Mono<TrainDto> train, @PathVariable String id)
    {
        return trainService.editTrain(train,id);
    }

    @PutMapping("/train/savedsearch")
    public Mono<TrainDto> updateSearchedTrain(@RequestBody Mono<TrainDto> train)
    {
        return trainService.updateSearchedTrain(train);
    }

    @GetMapping("/train")
    public Flux<TrainDto> getAllTrainByStatus()
    {
        return trainService.getAllTrainByStatus();
    }

    @GetMapping("/train/search")
    public Flux<TrainDto> getTrainByDynamicSearch(@RequestParam(required = false) String trainsCode,
                                                  @RequestParam(required = false) String trainsCountry,
                                                  @RequestParam(required = false) String trainsOrigin,
                                                  @RequestParam(required = false) String trainsDestination,
                                                  @RequestParam(required = false) String trainsCapacity,
                                                  @RequestParam(required = false) String trainsVendor,
                                                  @RequestParam(required = false) Integer cost,
                                                  @RequestParam(required = false) String createdBy)
    {
        return trainService.getTrainByDynamicSearch(trainsCode,trainsCountry,trainsOrigin,trainsDestination,trainsCapacity,trainsVendor,cost,createdBy);
    }

    @GetMapping("/train/savedsearch/codes")
    public Mono<List<String>> getAllTrainSavedSearchCode()
    {
        return trainService.getAllTrainSavedSearchCode();
    }

    @GetMapping("/train/saved/{code}")
    public Flux<TrainDto> getSavedTrainByCode(@PathVariable String code)
    {
        return trainService.getSavedTrainByCode(code);
    }


//// Ship


    @PostMapping("/ship")
    public Mono<ShipDto> addShip(@RequestBody Mono<ShipDto> ship)
    {
        return shipService.addShip(ship);
    }

    @PutMapping("/ship/edit/{id}")
    public Mono<ShipDto> editShip(@RequestBody Mono<ShipDto> ship, @PathVariable String id)
    {
        return shipService.editShip(ship,id);
    }

    @PutMapping("/ship/savedsearch")
    public Mono<ShipDto> updateSearchedShip(@RequestBody Mono<ShipDto> ship)
    {
        return shipService.updateSearchedShip(ship);
    }

    @GetMapping("/ship")
    public Flux<ShipDto> getAllShipByStatus()
    {
        return shipService.getAllShipByStatus();
    }

    @GetMapping("/ship/search")
    public Flux<ShipDto> getShipByDynamicSearch(@RequestParam(required = false) String shipsCode,
                                                @RequestParam(required = false) String shipsCountry,
                                                @RequestParam(required = false) String shipsOrigin,
                                                @RequestParam(required = false) String shipsDestination,
                                                @RequestParam(required = false) String shipsCapacity,
                                                @RequestParam(required = false) String shipsVendor,
                                                @RequestParam(required = false) Integer cost,
                                                @RequestParam(required = false) String createdBy)
    {
        return shipService.getShipByDynamicSearch(shipsCode,shipsCountry,shipsOrigin,shipsDestination,shipsCapacity,shipsVendor,cost,createdBy);
    }

    @GetMapping("/ship/savedsearch/codes")
    public Mono<List<String>> getAllShipSavedSearchCode()
    {
        return shipService.getAllShipSavedSearchCode();
    }

    @GetMapping("/ship/saved/{code}")
    public Flux<ShipDto> getSavedShipByCode(@PathVariable String code)
    {
        return shipService.getSavedShipByCode(code);
    }


//// Flight


    @PostMapping("/flight")
    public Mono<FlightDto> addFlight(@RequestBody Mono<FlightDto> flight)
    {
        return flightService.addFlight(flight);
    }

    @PutMapping("/flight/edit/{id}")
    public Mono<FlightDto> editFlight(@RequestBody Mono<FlightDto> flight, @PathVariable String id)
    {
        return flightService.editFlight(flight,id);
    }

    @PutMapping("/flight/savedsearch")
    public Mono<FlightDto> updateSearchedFlight(@RequestBody Mono<FlightDto> flight)
    {
        return flightService.updateSearchedFlight(flight);
    }

    @GetMapping("/flight")
    public Flux<FlightDto> getAllFlightByStatus()
    {
        return flightService.getAllFlightByStatus();
    }

    @GetMapping("/flight/search")
    public Flux<FlightDto> getFlightByDynamicSearch(@RequestParam(required = false) String flightsCode,
                                                    @RequestParam(required = false) String flightsCountry,
                                                    @RequestParam(required = false) String flightsOrigin,
                                                    @RequestParam(required = false) String flightsDestination,
                                                    @RequestParam(required = false) String flightsCapacity,
                                                    @RequestParam(required = false) String flightsVendor,
                                                    @RequestParam(required = false) Integer cost,
                                                    @RequestParam(required = false) String createdBy)
    {
        return flightService.getFlightByDynamicSearch(flightsCode,flightsCountry,flightsOrigin,flightsDestination,flightsCapacity,flightsVendor,cost,createdBy);
    }

    @GetMapping("/flight/savedsearch/codes")
    public Mono<List<String>> getAllFlightSavedSearchCode()
    {
        return flightService.getAllFlightSavedSearchCode();
    }

    @GetMapping("/flight/saved/{code}")
    public Flux<FlightDto> getSavedFlightByCode(@PathVariable String code)
    {
        return flightService.getSavedFlightByCode(code);
    }

}

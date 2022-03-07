package net.microservices.Transportation.service;

import net.microservices.Transportation.appUtil.TruckAppUtil;
import net.microservices.Transportation.dto.TruckDto;
import net.microservices.Transportation.exception.CustomException;
import net.microservices.Transportation.repository.TruckCustomRepository;
import net.microservices.Transportation.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TruckService
{

    @Autowired
    private TruckRepository truckRepo;

    @Autowired
    private TruckCustomRepository customRepo;


    // Adding Truck to the Repository
    public Mono<TruckDto> addTruck(Mono<TruckDto> truck)
    {
        return  truck.map(TruckAppUtil::DtoToEntity)
                .flatMap(truckRepo::insert)
                .map(TruckAppUtil::EntityToDto);
    }

    // Editing Truck by updating the old record and inserting the new record
    public Mono<TruckDto> editTruck(Mono<TruckDto> truck, String id)
    {
        Mono<TruckDto> r = truckRepo.findById(id).map(TruckAppUtil::EntityToDto);

        truckRepo.findById(id)
                .flatMap(p -> r.map(TruckAppUtil::DtoToEntity))
                .doOnNext(e -> e.setStatus(false))
                .flatMap(truckRepo::save)
                .map(TruckAppUtil::EntityToDto).subscribe();

        return truck.map(TruckAppUtil::DtoToEntity)
                .flatMap(truckRepo::insert)
                .map(TruckAppUtil::EntityToDto);
    }

    // Updating the Saved Status for the searched Truck
    public Mono<TruckDto> updateSearchedTruck(Mono<TruckDto> truck)
    {
        return  truck.map(TruckAppUtil::DtoToEntity)
                .flatMap(truckRepo::save)
                .map(TruckAppUtil::EntityToDto);
    }

    // Getting all Truck from the Repository
    public Flux<TruckDto> getAllTruckByStatus()
    {
        return truckRepo.findByStatus(true).map(TruckAppUtil::EntityToDto);
    }

    // Getting All Truck by dynamically giving any of the fields
    public Flux<TruckDto> getTruckByDynamicSearch(String trucksCode, String trucksCountry, String trucksOrigin, String trucksDestination,
                                                  String trucksCapacity, String trucksVendor, Integer cost, String createdBy)
    {
        return customRepo.findProperties(trucksCode,trucksCountry,trucksOrigin,trucksDestination,trucksCapacity,trucksVendor,cost,createdBy)
                .filter(a->a.getStatus()==true)
                .map(TruckAppUtil::EntityToDto);
    }

    // Getting all the groupCodes of Searched Truck
    public Mono<List<String>> getAllTruckSavedSearchCode()
    {
        return truckRepo.findAll()
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(TruckAppUtil::EntityToDto)
                .map(a->a.getTrucksCode())
                .distinct()
                .collect(Collectors.toList());
    }

    // Getting all searched Truck which saved status is updated
    public Flux<TruckDto> getSavedTruckByCode(String code)
    {
        return truckRepo.findBytrucksCodeIgnoreCase(code)
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(TruckAppUtil::EntityToDto)
                .switchIfEmpty(Mono.defer(()-> Mono.error(new CustomException("Invalid Truck Code"))));
    }

}




//    // Getting all Truck from the Repository
//    public Flux<TruckDto> getAllCommodityByStatus()
//    {
//        return truckRepo.findAll().map(AppUtil::EntityToDto)
//    }
//
//    // Get Truck by Id (commodityCode)
//    public Mono<TruckDto> getCommodityById(String id)
//    {
//
//        return truckRepo.findById(id)
//                .map(AppUtil::EntityToDto)
//                .switchIfEmpty(Mono.defer(()-> Mono.error(new CustomException("Invalid Commodity Code"))));
//    }
//
//    // Getting all Truck by commodityName
//    public Flux<TruckDto> getCommodityByName(String name)
//    {
//        return truckRepo.findBycommodityNameIgnoreCase(name)
//                .filter(a->a.isStatus())
//                .map(AppUtil::EntityToDto)
//                .switchIfEmpty(Mono.defer(()-> Mono.error(new CustomException("Invalid Commodity Name"))));
//
//    }
//
//    // Getting all Truck by commodityGroupCode
//    public Flux<TruckDto> getCommodityByCommodityGroupCode(String code)
//    {
//        return truckRepo.findBycommodityGroupCodeIgnoreCase(code)
//                .filter(a->a.isStatus())
//                .map(AppUtil::EntityToDto)
//                .switchIfEmpty(Mono.defer(()-> Mono.error(new CustomException("Invalid Commodity Group Code"))));
//    }
//
//    // Getting all Truck by createdBy
//    public Flux<TruckDto> getCommodityByCreatedBy(String name)
//    {
//        return truckRepo.findBycreatedByIgnoreCase(name)
//                .filter(a->a.isStatus())
//                .map(AppUtil::EntityToDto)
//                .switchIfEmpty(Mono.defer(()-> Mono.error(new CustomException("Invalid Commodity Name"))));
//    }



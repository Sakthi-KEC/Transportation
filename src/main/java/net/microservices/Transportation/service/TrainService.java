package net.microservices.Transportation.service;

import net.microservices.Transportation.appUtil.TrainAppUtil;
import net.microservices.Transportation.dto.TrainDto;
import net.microservices.Transportation.exception.CustomException;
import net.microservices.Transportation.repository.TrainCustomRepository;
import net.microservices.Transportation.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TrainService
{
    @Autowired
    private TrainRepository trainRepo;

    @Autowired
    private TrainCustomRepository customRepo;


    // Adding Truck to the Repository
    public Mono<TrainDto> addTrain(Mono<TrainDto> train)
    {
        return  train.map(TrainAppUtil::DtoToEntity)
                .flatMap(trainRepo::insert)
                .map(TrainAppUtil::EntityToDto);
    }

    // Editing Truck by updating the old record and inserting the new record
    public Mono<TrainDto> editTrain(Mono<TrainDto> train, String id)
    {
        Mono<TrainDto> r = trainRepo.findById(id).map(TrainAppUtil::EntityToDto);

        trainRepo.findById(id)
                .flatMap(p -> r.map(TrainAppUtil::DtoToEntity))
                .doOnNext(e -> e.setStatus(false))
                .flatMap(trainRepo::save)
                .map(TrainAppUtil::EntityToDto).subscribe();

        return train.map(TrainAppUtil::DtoToEntity)
                .flatMap(trainRepo::insert)
                .map(TrainAppUtil::EntityToDto);
    }

    // Updating the Saved Status for the searched Truck
    public Mono<TrainDto> updateSearchedTrain(Mono<TrainDto> train)
    {
        return  train.map(TrainAppUtil::DtoToEntity)
                .flatMap(trainRepo::save)
                .map(TrainAppUtil::EntityToDto);
    }

    // Getting all Truck from the Repository
    public Flux<TrainDto> getAllTrainByStatus()
    {
        return trainRepo.findByStatus(true).map(TrainAppUtil::EntityToDto);
    }

    // Getting All Truck by dynamically giving any of the fields
    public Flux<TrainDto> getTrainByDynamicSearch(String trainsCode, String trainsCountry, String trainsOrigin, String trainsDestination,
                                                  String trainsCapacity, String trainsVendor, Integer cost, String createdBy)
    {
        return customRepo.findProperties(trainsCode,trainsCountry,trainsOrigin,trainsDestination,trainsCapacity,trainsVendor,cost,createdBy)
                .filter(a->a.getStatus()==true)
                .map(TrainAppUtil::EntityToDto);
    }

    // Getting all the groupCodes of Searched Truck
    public Mono<List<String>> getAllTrainSavedSearchCode()
    {
        return trainRepo.findAll()
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(TrainAppUtil::EntityToDto)
                .map(a->a.getTrainsCode())
                .distinct()
                .collect(Collectors.toList());
    }

    // Getting all searched Truck which saved status is updated
    public Flux<TrainDto> getSavedTrainByCode(String code)
    {
        return trainRepo.findBytrainsCodeIgnoreCase(code)
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(TrainAppUtil::EntityToDto)
                .switchIfEmpty(Mono.defer(()-> Mono.error(new CustomException("Invalid Train Code"))));
    }
}

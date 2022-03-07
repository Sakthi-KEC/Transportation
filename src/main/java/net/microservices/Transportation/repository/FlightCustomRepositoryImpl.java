package net.microservices.Transportation.repository;

import net.microservices.Transportation.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;


@Service
public class FlightCustomRepositoryImpl implements FlightCustomRepository
{
    @Autowired
    public ReactiveMongoTemplate template;

    @Override
    public Flux<Flight> findProperties(String flightsCode,
                                       String flightsCountry,
                                       String flightsOrigin,
                                       String flightsDestination,
                                       String flightsCapacity,
                                       String flightsVendor,
                                       Integer cost,
                                       String createdBy)
    {

        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();

        if (flightsCode != null && !flightsCode.isEmpty())
            criteria.add(Criteria.where("flightsCode").is(flightsCode));

        if (flightsCountry != null && !flightsCountry.isEmpty())
            criteria.add(Criteria.where("flightsCountry").is(flightsCountry));

        if (flightsOrigin != null && !flightsOrigin.isEmpty())
            criteria.add(Criteria.where("flightsOrigin").is(flightsOrigin));

        if (flightsDestination != null && !flightsDestination.isEmpty())
            criteria.add(Criteria.where("flightDestination").is(flightsDestination));

        if (flightsCapacity != null && !flightsCapacity.isEmpty())
            criteria.add(Criteria.where("flightsCapacity").is(flightsCapacity));

        if (flightsVendor != null && !flightsVendor.isEmpty())
            criteria.add(Criteria.where("flightsVendor").is(flightsVendor));

        if (cost != null && !cost.toString().isEmpty())
            criteria.add(Criteria.where("cost").is(cost));

        if (createdBy != null && !createdBy.isEmpty())
            criteria.add(Criteria.where("createdBy").is(createdBy));

        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

        return template.find(query, Flight.class);
    }
}

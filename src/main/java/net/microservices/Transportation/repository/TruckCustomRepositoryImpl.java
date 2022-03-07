package net.microservices.Transportation.repository;

import net.microservices.Transportation.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;


@Service
public class TruckCustomRepositoryImpl implements TruckCustomRepository
{
    @Autowired
    public ReactiveMongoTemplate template;

   @Override
    public Flux<Truck> findProperties(String trucksCode,
                                      String trucksCountry,
                                      String trucksOrigin,
                                      String trucksDestination,
                                      String trucksCapacity,
                                      String trucksVendor,
                                      Integer cost,
                                      String createdBy)
    {

        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();

        if (trucksCode != null && !trucksCode.isEmpty())
            criteria.add(Criteria.where("trucksCode").is(trucksCode));

        if (trucksCountry != null && !trucksCountry.isEmpty())
            criteria.add(Criteria.where("trucksCountry").is(trucksCountry));

        if (trucksOrigin != null && !trucksOrigin.isEmpty())
            criteria.add(Criteria.where("trucksOrigin").is(trucksOrigin));

        if (trucksDestination != null && !trucksDestination.isEmpty())
            criteria.add(Criteria.where("trucksDestination").is(trucksDestination));

        if (trucksCapacity != null && !trucksCapacity.isEmpty())
            criteria.add(Criteria.where("trucksCapacity").is(trucksCapacity));

        if (trucksVendor != null && !trucksVendor.isEmpty())
            criteria.add(Criteria.where("trucksVendor").is(trucksVendor));

        if (cost != null && !cost.toString().isEmpty())
            criteria.add(Criteria.where("cost").is(cost));

        if (createdBy != null && !createdBy.toString().isEmpty())
            criteria.add(Criteria.where("createdBy").is(createdBy));

        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

    return template.find(query, Truck.class);
    }
}

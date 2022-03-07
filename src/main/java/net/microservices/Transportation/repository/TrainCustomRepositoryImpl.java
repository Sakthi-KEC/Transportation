package net.microservices.Transportation.repository;

import net.microservices.Transportation.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;


@Service
public class TrainCustomRepositoryImpl implements TrainCustomRepository
{
    @Autowired
    public ReactiveMongoTemplate template;

    @Override
    public Flux<Train> findProperties(String trainsCode,
                                      String trainsCountry,
                                      String trainsOrigin,
                                      String trainsDestination,
                                      String trainsCapacity,
                                      String trainsVendor,
                                      Integer cost,
                                      String createdBy)
    {

        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();

        if (trainsCode != null && !trainsCode.isEmpty())
            criteria.add(Criteria.where("trainsCode").is(trainsCode));

        if (trainsCountry != null && !trainsCountry.isEmpty())
            criteria.add(Criteria.where("trainsCountry").is(trainsCountry));

        if (trainsOrigin != null && !trainsOrigin.isEmpty())
            criteria.add(Criteria.where("trainsOrigin").is(trainsOrigin));

        if (trainsDestination != null && !trainsDestination.isEmpty())
            criteria.add(Criteria.where("trainsDestination").is(trainsDestination));

        if (trainsCapacity != null && !trainsCapacity.isEmpty())
            criteria.add(Criteria.where("trainsCapacity").is(trainsCapacity));

        if (trainsVendor != null && !trainsVendor.isEmpty())
            criteria.add(Criteria.where("trainsVendor").is(trainsVendor));

        if (cost != null && !cost.toString().isEmpty())
            criteria.add(Criteria.where("cost").is(cost));

        if (createdBy != null && !createdBy.isEmpty())
            criteria.add(Criteria.where("createdBy").is(createdBy));

        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

        return template.find(query, Train.class);
    }
}

package net.microservices.Transportation.repository;

import net.microservices.Transportation.model.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;


@Service
public class ShipCustomRepositoryImpl implements ShipCustomRepository
{
    @Autowired
    public ReactiveMongoTemplate template;

    @Override
    public Flux<Ship> findProperties(String shipsCode,
                                     String shipsCountry,
                                     String shipsOrigin,
                                     String shipsDestination,
                                     String shipsCapacity,
                                     String shipsVendor,
                                     Integer cost,
                                     String createdBy)
    {

        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();

        if (shipsCode != null && !shipsCode.isEmpty())
            criteria.add(Criteria.where("shipsCode").is(shipsCode));

        if (shipsCountry != null && !shipsCountry.isEmpty())
            criteria.add(Criteria.where("shipsCountry").is(shipsCountry));

        if (shipsOrigin != null && !shipsOrigin.isEmpty())
            criteria.add(Criteria.where("shipsOrigin").is(shipsOrigin));

        if (shipsDestination != null && !shipsDestination.isEmpty())
            criteria.add(Criteria.where("shipsDestination").is(shipsDestination));

        if (shipsCapacity != null && !shipsCapacity.isEmpty())
            criteria.add(Criteria.where("shipsCapacity").is(shipsCapacity));

        if (shipsVendor != null && !shipsVendor.isEmpty())
            criteria.add(Criteria.where("shipsVendor").is(shipsVendor));

        if (cost != null && !cost.toString().isEmpty())
            criteria.add(Criteria.where("cost").is(cost));

        if (createdBy != null && !createdBy.isEmpty())
            criteria.add(Criteria.where("createdBy").is(createdBy));

        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

        return template.find(query, Ship.class);
    }
}

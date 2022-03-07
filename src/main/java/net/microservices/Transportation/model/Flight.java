package net.microservices.Transportation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "flight")
public class Flight
{
    @Id
    private String id;

    private String flightsCode;
    private String flightsCountry;
    private String flightsOrigin;
    private String flightsDestination;
    private String flightsCapacity;
    private String flightsVendor;
    private Integer cost;
    private Boolean status;
    private Boolean saved;
    private String createdBy;
    private String createdDate;
}

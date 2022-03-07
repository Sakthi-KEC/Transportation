package net.microservices.Transportation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ship")
public class Ship
{
    @Id
    private String id;

    private String shipsCode;
    private String shipsCountry;
    private String shipsOrigin;
    private String shipsDestination;
    private String shipsCapacity;
    private String shipsVendor;
    private Integer cost;
    private Boolean status;
    private Boolean saved;
    private String createdBy;
    private String createdDate;
}

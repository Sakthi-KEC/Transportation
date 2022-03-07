package net.microservices.Transportation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipDto
{
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

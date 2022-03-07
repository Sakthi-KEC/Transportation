package net.microservices.Transportation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto
{
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

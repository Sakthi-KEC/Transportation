package net.microservices.Transportation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "train")
public class TrainDto
{
    private String id;
    private String trainsCode;
    private String trainsCountry;
    private String trainsOrigin;
    private String trainsDestination;
    private String trainsCapacity;
    private String trainsVendor;
    private Integer cost;
    private Boolean status;
    private Boolean saved;
    private String createdBy;
    private String createdDate;
}

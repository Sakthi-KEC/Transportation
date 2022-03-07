package net.microservices.Transportation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "train")
public class Train
{
    @Id
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

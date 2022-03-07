package net.microservices.Transportation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "truck")
public class Truck
{
	@Id
	private String id;

	private String trucksCode;
	private String trucksCountry;
	private String trucksOrigin;
	private String trucksDestination;
	private String trucksCapacity;
	private String trucksVendor;
	private Integer cost;
	private Boolean status;
	private Boolean saved;
	private String createdBy;
	private String createdDate;

}

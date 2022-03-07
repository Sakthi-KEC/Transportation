package net.microservices.Transportation.appUtil;

import net.microservices.Transportation.dto.FlightDto;
import net.microservices.Transportation.model.Flight;
import org.springframework.beans.BeanUtils;


public class FlightAppUtil
{
    public static Flight DtoToEntity(FlightDto dto)
    {
        Flight entity=new Flight();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    public static FlightDto EntityToDto(Flight entity)
    {
        FlightDto dto=new FlightDto();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }
}

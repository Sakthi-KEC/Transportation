package net.microservices.Transportation.appUtil;

import net.microservices.Transportation.dto.TruckDto;
import net.microservices.Transportation.model.Truck;
import org.springframework.beans.BeanUtils;


public class TruckAppUtil
{

    public static Truck DtoToEntity(TruckDto dto)
    {
        Truck entity=new Truck();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    public static TruckDto EntityToDto(Truck entity)
    {
        TruckDto dto=new TruckDto();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }

}

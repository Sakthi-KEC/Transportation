package net.microservices.Transportation.appUtil;

import net.microservices.Transportation.dto.ShipDto;
import net.microservices.Transportation.model.Ship;
import org.springframework.beans.BeanUtils;


public class ShipAppUtil
{
    public static Ship DtoToEntity(ShipDto dto)
    {
        Ship entity=new Ship();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    public static ShipDto EntityToDto(Ship entity)
    {
        ShipDto dto=new ShipDto();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }
}

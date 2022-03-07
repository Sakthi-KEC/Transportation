package net.microservices.Transportation.appUtil;

import net.microservices.Transportation.dto.TrainDto;
import net.microservices.Transportation.model.Train;
import org.springframework.beans.BeanUtils;


public class TrainAppUtil
{
    public static Train DtoToEntity(TrainDto dto)
    {
        Train entity=new Train();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    public static TrainDto EntityToDto(Train entity)
    {
        TrainDto dto=new TrainDto();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }
}

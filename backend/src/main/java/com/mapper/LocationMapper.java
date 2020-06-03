package com.mapper;

import com.entities.LocationEntity;
import com.entities.ProgramEntity;
import com.input.LocationInput;
import com.input.ProgramInput;
import com.model.LocationJson;

public class LocationMapper {

    public static LocationEntity locationToEntity(LocationInput location) {
        return LocationEntity.builder()
                .country(location.getCountry())
                .city(location.getCity())
                .build();
    }

    public static LocationJson entityToLocation(LocationEntity entity) {
        return LocationJson.builder()
                .id(entity.getId())
                .country(entity.getCountry())
                .city(entity.getCity())
                .build();
    }

    public static LocationEntity updateLocation(LocationEntity entity, LocationInput location){
        entity.setCountry(location.getCountry());
        entity.setInterval(location.getCity());
        return entity;
    }
}

package com.protean.legislativetracker.zidane.legiscan;

import com.protean.legislativetracker.zidane.utilities.ArgumentValidator;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LegiscanModelMapper {

    private static final Logger log = LoggerFactory.getLogger(LegiscanModelMapper.class);

    public static <T> T modelToLegiscan(Object valueToMap, Class<T> destinationType) {
        return modelToLegiscan(valueToMap, destinationType, new ModelMapper());
    }

    public static <T> T modelToLegiscan(Object valueToMap, Class<T> destinationType, ModelMapper mapper) {

        ArgumentValidator.validateArgument(valueToMap == null, "Value to map must not be null", log);
        ArgumentValidator.validateArgument(destinationType == null, "Destination type must not be null", log);

        return mapper.map(valueToMap, destinationType);
    }

    public static <T> List<T> modelListToLegiscan(List<?> valuesToMap, Class<T> destinationType) {

        ArgumentValidator.validateArgument(valuesToMap == null, "Values to map must not be null", log);
        ArgumentValidator.validateArgument(destinationType == null, "Destination type must not be null", log);

        List<T> destinationList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (Object o : valuesToMap) {
            if(o != null) { destinationList.add(mapper.map(o, destinationType)); }
        }
        return destinationList;
    }
}

package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {


    public static <T> T convertObjectMapper(String stringJson, Class<T> mapper){

        try {
            return new ObjectMapper().readValue(stringJson, mapper);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}

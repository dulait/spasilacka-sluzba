package utils;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public static final JsonSerializer<LocalDate> serializer = (src, typeOfSrc, context) -> new JsonPrimitive(src.format(formatter));

    public static final JsonDeserializer<LocalDate> deserializer = (json, typeOfT, context) -> {
        try {
            return LocalDate.parse(json.getAsString(), formatter);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    };
}

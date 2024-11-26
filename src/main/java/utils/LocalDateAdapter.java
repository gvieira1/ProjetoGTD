package utils;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        if (src == null) {
            return JsonNull.INSTANCE;  // Se o valor for null, retorna JsonNull
        }
        return new JsonPrimitive(src.format(formatter)); // Serializa para string no formato yyyy-MM-dd
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull() || json.getAsString().isEmpty()) {
            return null;  // Retorna null se o valor for nulo ou uma string vazia
        }
        return LocalDate.parse(json.getAsString(), formatter); // Converte de volta para LocalDate
    }
}

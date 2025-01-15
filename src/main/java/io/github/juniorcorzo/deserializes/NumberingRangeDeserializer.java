package io.github.juniorcorzo.deserializes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.juniorcorzo.dto.numberingRange.NumberingRangeDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NumberingRangeDeserializer extends JsonDeserializer<NumberingRangeDTO> {
    @Override
    public NumberingRangeDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        Integer id = node.hasNonNull("id") ? node.get("id").asInt() : null;
        String document = node.hasNonNull("document") ? node.get("document").asText() : null;
        String prefix = node.hasNonNull("prefix") ? node.get("prefix").asText() : null;
        long from = node.hasNonNull("from") ? node.get("from").asLong() : 0;
        long to = node.hasNonNull("to") ? node.get("to").asLong() : 0;
        long current = node.hasNonNull("current") ? node.get("current").asLong() : 0;
        String resolutionNumber = node.hasNonNull("resolution_number") ? node.get("resolution_number").asText() : null;
        LocalDate startDate = node.hasNonNull("start_date") ? LocalDate.parse(node.get("start_date").asText()) : null;
        LocalDate endDate = node.hasNonNull("end_date") ? LocalDate.parse(node.get("end_date").asText()) : null;
        String months = node.hasNonNull("months") ? node.get("months").asText() : null;
        String technicalKey = node.hasNonNull("technical_key") ? node.get("technical_key").asText() : null;
        boolean isExpired = node.hasNonNull("is_expired") && node.get("is_expired").asBoolean();
        int isActive = node.hasNonNull("is_active") ? node.get("is_active").asInt() : 0;
        LocalDateTime createdAt = node.hasNonNull("created_at") ? LocalDateTime.parse(node.get("created_at").asText()) : null;
        LocalDateTime updatedAt = node.hasNonNull("updated_at") ? LocalDateTime.parse(node.get("updated_at").asText()) : null;

        return new NumberingRangeDTO(id, document, prefix, from, to, current, resolutionNumber, startDate, endDate, months, technicalKey, isExpired, isActive, createdAt, updatedAt);
    }
}

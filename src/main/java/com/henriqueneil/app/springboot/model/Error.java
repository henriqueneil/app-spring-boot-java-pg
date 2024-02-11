package com.henriqueneil.app.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private Integer code;
    private String message;
}

/**
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Paths;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ChaseEntityMapperImpl.class, ActivityClassMapperImpl.class })
class ChaseEntityMapperTest {

    private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

    @Autowired
    private ChaseEntityMapper mapper;

    @ParameterizedTest(name = "Testing: {0}")
    @CsvFileSource(numLinesToSkip = 1, resources = "/test-data/chase-entity-mapper/success-scenarios.csv")
    void whenMappingEntity_givenValidEntity_thenShouldSuccess(final String testName) throws Exception {

        var fileName = String.join("-", testName.split(" "))
                .concat(".json").toLowerCase();
        var input = new ClassPathResource("test-data/chase-entity-mapper/input/".concat(fileName));
        var output = new ClassPathResource("test-data/chase-entity-mapper/output/".concat(fileName));

        try (var inputIs = input.getInputStream()) {

            var entity = OBJECT_MAPPER.readValue(inputIs, ChaseEntity.class);
            var expected = Files.readString(Paths.get(output.getURI()));

            var result = mapper.transform(entity);
            JSONAssert.assertEquals(expected, OBJECT_MAPPER.writeValueAsString(result), true);
        }
    }

    private static ObjectMapper createObjectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }
}

**/

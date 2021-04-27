package org.openjfx.config;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;

@Data
@Component
@NoArgsConstructor
public class DatabaseConfig {
    @JsonProperty
    private String url;

    @JsonProperty
    private String userName;

    @JsonProperty
    private String password;

    @SneakyThrows
    public static DatabaseConfig getInstance() {

        XmlMapper mapper = new XmlMapper();
        return mapper.readValue(new File("src/main/resources/config.xml"),DatabaseConfig.class);
    }
}

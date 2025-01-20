package com.tookscan.tookscan.core.config;

import com.tookscan.tookscan.core.utility.Snowflake;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowflakeConfig {
    @Value("${snowflake.node-id}")
    private long nodeId;

    @Value("${snowflake.epoch}")
    private String epoch;
    @Bean
    public Snowflake snowflake() {
        long customEpoch = Instant.parse(epoch).toEpochMilli();
        return new Snowflake(nodeId, customEpoch);
    }
}

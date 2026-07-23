package com.beyond.MKX.common.redis.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "spring.redis")
@Getter
@Setter
public class CommonRedisProps {
    private String host;
    private int port;
    private Duration timeout = Duration.ofSeconds(3);
}

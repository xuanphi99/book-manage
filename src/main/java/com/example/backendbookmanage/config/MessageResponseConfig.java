package com.example.backendbookmanage.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
public class MessageResponseConfig {
    private Map<String, String> params;
}
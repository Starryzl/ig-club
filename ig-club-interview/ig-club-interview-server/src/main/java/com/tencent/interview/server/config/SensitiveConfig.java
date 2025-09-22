package com.tencent.interview.server.config;

import com.tencent.interview.server.sensitive.WordContext;
import com.tencent.interview.server.sensitive.WordFilter;
import com.tencent.interview.server.service.SensitiveWordsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SensitiveConfig {
    @Bean
    public WordContext wordContext(SensitiveWordsService service) {
        return new WordContext(true, service);
    }

    @Bean
    public WordFilter wordFilter(WordContext wordContext) {
        return new WordFilter(wordContext);
    }

}

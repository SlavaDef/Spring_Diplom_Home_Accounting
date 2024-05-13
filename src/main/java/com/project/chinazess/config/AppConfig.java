package com.project.chinazess.config;

import com.project.chinazess.models.Count;
import com.project.chinazess.service.CountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner demo(final CountService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {

                Count count = new Count();
               // count.setCount(115112L);
                service.addCount(count);



            }
        };
    }
}

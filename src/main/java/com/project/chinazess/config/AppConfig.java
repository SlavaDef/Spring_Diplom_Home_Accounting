package com.project.chinazess.config;

import com.project.chinazess.models.Bonus;
import com.project.chinazess.models.Count;
import com.project.chinazess.service.BonusService;
import com.project.chinazess.service.CountService;
import com.project.chinazess.service.SalaryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.project.chinazess.Util.getRandomLong;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**") // якщо йде запит на такі файли
                .addResourceLocations("classpath:/static/"); //  кажемо де шукати
    }

    @Bean
    public CommandLineRunner demo(final CountService service, BonusService bonusService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {

                Count count = new Count();
                // count.setCount(115112L);
                service.addCount(count);

                for (int i = 0; i < 10; i++) {
                    Bonus bonus = new Bonus(getRandomLong(),"something"+i);
                    bonus.setCount(count);
                    bonus.setDate(LocalDate.of(2024, 1+i, 2+i));
                    bonusService.addBonus(bonus);

                }


            }
        };
    }
}

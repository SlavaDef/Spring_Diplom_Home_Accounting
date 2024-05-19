package com.project.chinazess.config;

import com.project.chinazess.models.*;
import com.project.chinazess.service.*;
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
    public CommandLineRunner demo(final CountService service, BonusService bonusService,
                                  SalaryService salaryService,
                                  AnotherService anotherService, PresentsService presentsService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {

                Count count = new Count();
                // count.setCount(115112L);
                service.addCount(count);

                for (int i = 0; i < 10; i++) {
                    Bonus bonus = new Bonus(getRandomLong(),"something"+i);
                    bonus.setCount(count);
                    bonus.setDate(LocalDate.of(2024, 5, 10+i));
                    bonusService.addBonus(bonus);

                }
                for (int i = 0; i < 10; i++) {
                    Salary salary = new Salary(getRandomLong(),"something"+i);
                    salary .setCount(count);
                    salary .setDate(LocalDate.of(2024, 1+i, 10+i));
                    salaryService.addSalary(salary);

                }

                for (int i = 0; i < 10; i++) {
                    Another another = new Another(getRandomLong(),"something"+i);
                    another.setCount(count);
                    another.setDate(LocalDate.of(2024, 1+i, 2+i));
                    anotherService.addAnother(another);

                }
                for (int i = 0; i < 10; i++) {
                    Presents presents = new Presents(getRandomLong(),"something"+i);
                    presents.setCount(count);
                    presents.setDate(LocalDate.of(2024, 1+i, 2+i));
                    presentsService.addPresent(presents);

                }


            }
        };
    }
}

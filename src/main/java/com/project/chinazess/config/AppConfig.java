package com.project.chinazess.config;

import com.fasterxml.jackson.databind.util.Converter;
import com.project.chinazess.models.*;
import com.project.chinazess.service.*;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.convert.ApplicationConversionService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.core.convert.ConversionService;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.project.chinazess.Util.getRandomLong;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**") // якщо йде запит на такі файли
                .addResourceLocations("classpath:/static/"); //  кажемо де шукати
    }

  /*  @Bean
    public ConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        DateTimeFormatterRegistrar dateTimeFormatterRegistrar = new DateTimeFormatterRegistrar();
        dateTimeFormatterRegistrar.setUseIsoFormat(true);
        dateTimeFormatterRegistrar.registerFormatters(conversionService);

        return conversionService;
    } */



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

                for (int i = 0; i < 25; i++) {
                    Bonus bonus = new Bonus(getRandomLong(),"something"+i);
                    bonus.setCount(count);
                    bonus.setDate(LocalDate.of(2024, 6, 1+i));
                  //  bonus.setDate2(LocalDate.of(2024, 6, 1));
                   // bonus.setTime(LocalDateTime.now());
                   // bonus.setTime(LocalTime.now());
                    bonusService.addBonus(bonus);

                }
                for (int i = 0; i < 20; i++) {
                    Salary salary = new Salary(getRandomLong(),"something"+i);
                    salary .setCount(count);
                    salary .setDate(LocalDate.of(2024, 6, 6+i));
                    salaryService.addSalary(salary);

                }

                for (int i = 0; i < 25; i++) {
                    Another another = new Another(getRandomLong(),"something"+i);
                    another.setCount(count);
                    another.setDate(LocalDate.of(2024, 6, 1+i));
                    anotherService.addAnother(another);

                }
                for (int i = 0; i < 25; i++) {
                    Presents presents = new Presents(getRandomLong(),"something"+i);
                    presents.setCount(count);
                    presents.setDate(LocalDate.of(2024, 6, 1+i));
                    presentsService.addPresent(presents);

                }


            }
        };
    }
}

package com.project.chinazess;

import com.project.chinazess.models.Bonus;
import com.project.chinazess.models.Count;
import com.project.chinazess.service.BonusService;
import com.project.chinazess.service.CountService;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
public class Util {

    public Util() {
    }

    private CountService service;
    private BonusService bonusService;

    public static Long getRandomLong() {
        List<Long> list = Arrays.asList(1500L, 850L, 900L, 500L, 400L, 300L, 200L, 2500L, 5000L, 8000L);
        return list.get(ThreadLocalRandom.current().nextInt(10));
    }

    }



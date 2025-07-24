package com.duckstar;

import com.duckstar.domain.Week;
import com.duckstar.service.WeekService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class TestDB {

    @Autowired
    EntityManager em;
    @Autowired
    private WeekService weekService;

    @Test
    public void test() throws Exception {
        Week currentWeek = weekService.getCurrentWeek();
    }
}

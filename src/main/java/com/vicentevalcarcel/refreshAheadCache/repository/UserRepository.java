package com.vicentevalcarcel.refreshAheadCache.repository;

import com.vicentevalcarcel.refreshAheadCache.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

//FAKE CLASS
@Log4j2
@Component
public class UserRepository {

    public List<User> findAllValids1() {
        log.info("EXECUTED SLOW QUERY");
        try {
            Thread.sleep(30_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return List.of(new User(1), new User(2));
    }

    public List<User> findAllValidsWithParams(String str, Boolean bool) {
        log.info("EXECUTED SLOW QUERY WITH PARAMS");
        try {
            Thread.sleep(15_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return List.of(new User(1), new User(2));
    }
}

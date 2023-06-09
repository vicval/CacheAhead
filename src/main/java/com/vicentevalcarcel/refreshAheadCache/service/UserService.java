package com.vicentevalcarcel.refreshAheadCache.service;

import com.vicentevalcarcel.refreshAheadCache.model.User;
import com.vicentevalcarcel.refreshAheadCache.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "slowQuery1", cacheManager = "simpleCacheManagerRefreshAhead")
    public List<User> findAllValidos() {
        return userRepository.findAllValids1();
    }

    @Cacheable(value = "slowQuery2", key = "{#str, #bool}", cacheManager ="simpleCacheManagerRefreshAhead")
    public List<User> findAllValidosWithParams(String str, Boolean bool) {
        return userRepository.findAllValidsWithParams(str, bool);
    }
}
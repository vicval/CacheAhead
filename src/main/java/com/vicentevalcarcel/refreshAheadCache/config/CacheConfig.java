package com.vicentevalcarcel.refreshAheadCache.config;


import com.github.benmanes.caffeine.cache.Caffeine;
import com.vicentevalcarcel.refreshAheadCache.model.User;
import com.vicentevalcarcel.refreshAheadCache.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Log4j2
@Configuration
@EnableCaching
public class CacheConfig {

    private final UserRepository userRepository;
    public CacheConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Value("${shortCacheInSeconds}")
    Integer shortCacheInSeconds;

    @Value("${isCacheEnable}")
    Boolean isCacheEnable;

    @Bean
    public Caffeine caffeineConfig() {
        log.info("caffeineConfig");
        if(isCacheEnable==null || isCacheEnable==false){
            return null;
        }
        return Caffeine.newBuilder()
                .expireAfterWrite(shortCacheInSeconds, TimeUnit.SECONDS)
                .removalListener((key, value, cause) -> {
                    System.out.println("caffeineConfig [REMOVED] KEY:"+key+", VALUE:"+value+", CAUSE:"+cause);
                })
                .evictionListener((key, value, cause) -> {
                    System.out.println("caffeineConfig [EVICT] KEY:"+key+", VALUE:"+value+", CAUSE:"+cause);
                });
    }

    @Bean
    @Primary
    public CacheManager cacheManager(Caffeine caffeine) {
        log.info("cacheManager");
        if(isCacheEnable==null || isCacheEnable==false){
            NoOpCacheManager noOpCacheManager = new NoOpCacheManager();
            return noOpCacheManager;
        }
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

    @Bean
    public CacheManager simpleCacheManagerRefreshAhead(){
        log.info("EXECUTED simpleCacheManagerRefreshAhead");
        if(isCacheEnable==null || isCacheEnable==false){
            NoOpCacheManager noOpCacheManager = new NoOpCacheManager();
            return noOpCacheManager;
        }
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(

                        new CaffeineCache("slowQuery1",
                                Caffeine.newBuilder()
                                        .maximumSize(10)
                                        .removalListener((key, value, cause) -> {
                                            log.info("slowQuery1 [REMOVED] KEY:"+key+", VALUE:"+value+", CAUSE:"+cause);
                                        }).evictionListener((key, value, cause) -> {
                                            log.info("slowQuery1 [EVICT] KEY:"+key+", VALUE:"+value+", CAUSE:"+cause);
                                        })
                                        .refreshAfterWrite(shortCacheInSeconds, TimeUnit.SECONDS)
                                        .build(key2 -> {
                                            log.info("slowQuery1 [START]");
                                            List<User> allValidos = userRepository.findAllValids1();
                                            log.info("slowQuery1 [END]");
                                            return allValidos;
                                        })),

                        new CaffeineCache("slowQuery2WithParams",
                                Caffeine.newBuilder()
                                        .maximumSize(10)
                                        .removalListener((key, value, cause) -> {
                                            log.info("slowQuery2WithParams [REMOVED] KEY:"+key+", VALUE:"+value+", CAUSE:"+cause);
                                        }).evictionListener((key, value, cause) -> {
                                            log.info("slowQuery2WithParams [EVICT] KEY:"+key+", VALUE:"+value+", CAUSE:"+cause);
                                        })
                                        .refreshAfterWrite(shortCacheInSeconds, TimeUnit.SECONDS)
                                        .build(
                                                key2 -> {
                                                    log.info("slowQuery2WithParams [START]");
                                                    System.out.println(key2);
                                                    ArrayList params = (ArrayList) key2;
                                                    List<User> allValidos = userRepository.findAllValidsWithParams((String)params.get(0),(Boolean)params.get(1));
                                                    log.info("slowQuery2WithParams [END]");
                                                    return allValidos;
                                                }
                                        ))
                )
        );

        return cacheManager;
    }

}
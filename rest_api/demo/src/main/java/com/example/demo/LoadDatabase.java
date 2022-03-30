//package com.example.demo;
//import com.example.demo.Entities.Item;
//import com.example.demo.Repositories.ItemRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//class LoadDatabase {
//
//
//    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//    @Bean
//    CommandLineRunner initDatabase(ItemRepository repository) {
//
//        return args -> {
//            log.info("Preloading " + repository.save(new Item(1,"My first item")));
//            log.info("Preloading " + repository.save(new Item()));
//        };
//    }
//}
package com.roomrent.backend;

import com.roomrent.backend.model.User;
import com.roomrent.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository, BCryptPasswordEncoder encoder) {
        return args -> {
            if (repository.findByEmail("admin@roomrent.com").isEmpty()) {
                User admin = new User();
                admin.setNome("JoaquimAdmin");
                admin.setEmail("admin@roomrent.com");
                // Aqui o encoder gera o Hash automaticamente
                admin.setPassword(encoder.encode("admin123")); 
                admin.setRole("ADMIN");
                admin.setAprovado(true);
                repository.save(admin);
                System.out.println("Administrador criado com sucesso!");
            }
        };
    }
}
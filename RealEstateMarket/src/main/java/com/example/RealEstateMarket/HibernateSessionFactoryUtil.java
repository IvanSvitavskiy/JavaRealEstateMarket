package com.example.RealEstateMarket;

import com.example.RealEstateMarket.Models.Building;
import com.example.RealEstateMarket.Models.Image;
import com.example.RealEstateMarket.Models.Role;
import com.example.RealEstateMarket.Models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HibernateSessionFactoryUtil {
    @Bean(name="entityManagerFactory")
    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();

            configuration.addAnnotatedClass(Building.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Role.class);
            configuration.addAnnotatedClass(Image.class);


            return configuration.buildSessionFactory(
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("cannot create db session");
        }
    }
}

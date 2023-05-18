package com.example.RealEstateMarket.Services;

import com.example.RealEstateMarket.Models.Role;
import com.example.RealEstateMarket.Models.User;
import com.example.RealEstateMarket.Repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean createUser(User user){
        if(userRepository.findByEmail(user.getEmail()) != null){
            return false;
        }
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        return true;
    }
}

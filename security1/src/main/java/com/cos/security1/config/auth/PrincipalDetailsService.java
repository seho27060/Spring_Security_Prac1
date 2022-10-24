package com.cos.security1.config.auth;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("/login");
// login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername 실행
// 자동 실행임. 정해진 규친
@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("login "+username);
        User userEntity = userRepository.findByUsername(username);
        System.out.println("find "+userEntity);

        if(userEntity != null){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}

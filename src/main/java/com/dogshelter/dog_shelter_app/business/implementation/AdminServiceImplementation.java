package com.dogshelter.dog_shelter_app.business.implementation;

import com.dogshelter.dog_shelter_app.business.AdminService;
import com.dogshelter.dog_shelter_app.persistance.AdminRepository;
import com.dogshelter.dog_shelter_app.persistance.entity.AdminEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AdminServiceImplementation implements AdminService, UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Long saveAdmin(AdminEntity adminEntity) {
        adminEntity.setPassword(bCryptPasswordEncoder.encode(adminEntity.getPassword()));
        return adminRepository.save(adminEntity).getId();
    }

    @Override
    public AdminEntity findByUsername(String username) {
        return adminRepository.findByUsername(username)
                .orElseThrow(() ->new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AdminEntity> opt = adminRepository.findByUsername(username);
        org.springframework.security.core.userdetails.User springUser = null;
        if(opt.isEmpty()){
            throw new UsernameNotFoundException("User with username: "+username+" not found");
        }else{
            AdminEntity adminEntity = opt.get();
            Set<String> roles = adminEntity.getRoles();
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for(String role:roles){
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
            }
            springUser = new org.springframework.security.core.userdetails.User(
                    username,
                    adminEntity.getPassword(),
                    grantedAuthorities
            );
        }
        return springUser;
    }
}

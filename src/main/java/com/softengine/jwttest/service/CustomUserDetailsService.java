package com.softengine.jwttest.service;

import com.softengine.jwttest.entity.Privilege;
import com.softengine.jwttest.entity.Role;
import com.softengine.jwttest.entity.User;
import com.softengine.jwttest.repository.RoleRepository;
import com.softengine.jwttest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    //region Description
//    @Autowired private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        User user=userRepository.findByUserName(userName);
//        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<>());
//    }
    //endregion

    private final UserRepository userRepository;

    private final UserRepository service;

    private final RoleRepository roleRepository;

    public CustomUserDetailsService(UserRepository userRepository, UserRepository service, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.service = service;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), true, true, true, true,
                    getGrantedAuthorities(user.getPrivileges()));
        }
//            roleRepository.findByName("ROLE_USER")

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassword(), true, true, true,
                true, getGrantedAuthorities(user.getPrivileges()));
    }



    private List<GrantedAuthority> getGrantedAuthorities(Collection<Privilege> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Privilege privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+privilege.getRole().getName()));
        }
        return authorities;
    }
}

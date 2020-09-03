//package com.softengine.jwttest.config;
//
//import com.softengine.jwttest.entity.Privilege;
//import com.softengine.jwttest.entity.Role;
//import com.softengine.jwttest.entity.User;
//import com.softengine.jwttest.repository.RoleRepository;
//import com.softengine.jwttest.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//@Service("userDetailsService")
//@Transactional
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserRepository service;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName)
//      throws UsernameNotFoundException {
//
//        User user = userRepository.findByUserName(userName);
//        if (user == null) {
//            return new org.springframework.security.core.userdetails.User(" ", " ", true, true, true, true,
//              getAuthorities( roleRepository.findByName("ROLE_USER"))));
//        }
//
//        return new org.springframework.security.core.userdetails.User(
//          user.getEmail(), user.getPassword(), true, true, true,
//          true, getAuthorities(user.getRoles()));
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(
//      Collection<Role> roles) {
//
//        return getGrantedAuthorities(getPrivileges(roles));
//    }
//
//    private List<String> getPrivileges(Collection<Role> roles) {
//
//        List<String> privileges = new ArrayList<>();
//        List<Privilege> collection = new ArrayList<>();
//        for (Role role : roles) {
//            collection.addAll(role.getPrivileges());
//        }
//        for (Privilege item : collection) {
//
//        }
//        return privileges;
//    }
//
//    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String privilege : privileges) {
//            authorities.add(new SimpleGrantedAuthority(privilege));
//        }
//        return authorities;
//    }
//}
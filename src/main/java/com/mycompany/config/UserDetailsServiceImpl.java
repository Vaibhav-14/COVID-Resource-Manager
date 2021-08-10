package com.mycompany.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

import com.mycompany.dao.IUserFunctionDAO;
 
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private IUserFunctionDAO userDao;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        com.mycompany.entity.User user = userDao.findByUsername(username);
        System.out.println(user);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
 
}

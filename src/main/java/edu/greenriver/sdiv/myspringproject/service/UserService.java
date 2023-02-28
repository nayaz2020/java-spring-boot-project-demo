package edu.greenriver.sdiv.myspringproject.service;


import edu.greenriver.sdiv.myspringproject.dbs.IUserRepository;
import edu.greenriver.sdiv.myspringproject.models.Permission;
import edu.greenriver.sdiv.myspringproject.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author nemat
 * @version 1.2
 * UserService class reports status of the user
 *
 */
@Service
public class UserService implements UserDetailsService
{
    private IUserRepository repo;
    private PasswordEncoder passwordEncoder;

    /**
     * @param repo is the user repository
     */
    public UserService(IUserRepository repo)
    {
        this.repo = repo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    /**
     * @param newUser the bounded data to be saved
     *
     */
    public void saveUser(User newUser)
    {

        Permission userAuth = new Permission(0,"user",newUser);
        newUser.setPermissions(new ArrayList<>());
        newUser.getPermissions().add(userAuth);

        String encodedPassword = this.passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        repo.save(newUser);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = repo.findByUsername(username);
        if(user != null)
        {
            return user;
        }

        throw new UsernameNotFoundException("Username not found");

    }

    @Override
    public String toString() {
        return "UserService{" +
                "repo=" + repo +
                '}';
    }
}

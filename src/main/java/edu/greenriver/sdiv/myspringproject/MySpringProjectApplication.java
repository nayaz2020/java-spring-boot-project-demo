package edu.greenriver.sdiv.myspringproject;

import edu.greenriver.sdiv.myspringproject.dbs.IResumeRepository;
import edu.greenriver.sdiv.myspringproject.dbs.IUserRepository;
import edu.greenriver.sdiv.myspringproject.models.Permission;
import edu.greenriver.sdiv.myspringproject.models.ResumeData;
import edu.greenriver.sdiv.myspringproject.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * @author nemat
 * @version 1.2
 * main driver class for the Application
 */
@SpringBootApplication
public class MySpringProjectApplication
{
    /**
     * @param args no argument
     */
    public static void main(String[] args)
    {
        ApplicationContext context = SpringApplication.run(MySpringProjectApplication.class, args);
        IResumeRepository repo = context.getBean(IResumeRepository.class);

        IUserRepository userRepo = context.getBean(IUserRepository.class);
        BCryptPasswordEncoder encoder = context.getBean(BCryptPasswordEncoder.class);

        // add a few objects
        ResumeData[] education = {
                ResumeData.builder().activity("HighSchool").years("2013").graduated(true).subject("General").level("advance").
                        build(),
                ResumeData.builder().activity("Associates").years("2018").graduated(true).subject("IT").level("basic").
                        build(),
                ResumeData.builder().activity("Bachelor").years("2021").graduated(false).subject("Application Dev").level("intermediate").
                        build(),
                ResumeData.builder().activity("Udemy").years("2021").graduated(false).subject("Spring Boot").level("intermediate").
                        build(),
                ResumeData.builder().activity("Linkedin").years("2019").graduated(false).subject("Python").level("intermediate").
                        build()
        };
        repo.saveAll(Arrays.asList(education));

       //test admin account
        User admin = User.builder()
                .username("admin")
                .password(encoder.encode("password"))
                .build();

        Permission adminRole = new Permission(0,"admin", admin);
        admin.setPermissions(new ArrayList<>());
        admin.getPermissions().add(adminRole);
        userRepo.save(admin);


        //test User Account
        User user = User.builder()
                .username("user")
                .password(encoder.encode("password"))
                .build();

        Permission userRole = new Permission(0,"user", user);
        user.setPermissions(new ArrayList<>());
        user.getPermissions().add(userRole);
        userRepo.save(user);



    }
}

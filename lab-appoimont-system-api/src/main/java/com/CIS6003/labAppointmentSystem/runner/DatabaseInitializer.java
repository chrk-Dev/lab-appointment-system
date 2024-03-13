package com.CIS6003.labAppointmentSystem.runner;

import com.CIS6003.labAppointmentSystem.security.WebSecurityConfig;
import com.CIS6003.labAppointmentSystem.service.UserService;
import com.CIS6003.labAppointmentSystem.model.Doctor;
import com.CIS6003.labAppointmentSystem.model.User;
import com.CIS6003.labAppointmentSystem.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final DoctorService doctorService;

    @Override
    public void run(String... args) {
        if (!userService.getUsers().isEmpty()) {
            return;
        }
        USERS.forEach(user -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
        });

        Doctors.forEach(doc -> {
            doc.setPassword(passwordEncoder.encode(doc.getPassword()));
            doctorService.saveDoctor(doc);
        });

        log.info("Database initialized");
    }

    private static final List<User> USERS = Arrays.asList(new User("admin", "admin", "Admin", "admin@mycompany.com", WebSecurityConfig.ADMIN), new User("user", "user", "User", "user@mycompany.com", WebSecurityConfig.USER));

    private static final List<Doctor> Doctors = Arrays.asList(new Doctor("dr who", "docur1", "pass1", "dentis", "docc"));
}

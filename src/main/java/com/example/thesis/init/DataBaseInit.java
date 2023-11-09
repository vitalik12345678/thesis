package com.example.thesis.init;

import com.example.thesis.entity.Role;
import com.example.thesis.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataBaseInit {

    private final RoleRepository repository;

    @EventListener
    public void initRoles (ContextRefreshedEvent event) {
        if (repository.findByName("teacher").isEmpty()) {
            var teacherRole = new Role();
            teacherRole.setName("teacher");
            repository.save(teacherRole);
        }
        if (repository.findByName("student").isEmpty()) {
            var studentRole = new Role();
            studentRole.setName("student");
            repository.save(studentRole);
        }
        if (repository.findByName("HoD").isEmpty()) {
            var HoD = new Role();
            HoD.setName("HoD");
            repository.save(HoD);
        }
        if (repository.findByName("PS").isEmpty()) {
            var PS = new Role();
            PS.setName("PS");
            repository.save(PS);
        }
    }


}

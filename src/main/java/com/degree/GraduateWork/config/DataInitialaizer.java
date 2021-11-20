package com.degree.GraduateWork.config;

import com.degree.GraduateWork.models.Request;
import com.degree.GraduateWork.models.RequestStatus;
import com.degree.GraduateWork.models.Role;
import com.degree.GraduateWork.models.User;
import com.degree.GraduateWork.service.interfaces.RequestService;
import com.degree.GraduateWork.service.interfaces.RequestStatusService;
import com.degree.GraduateWork.service.interfaces.RoleService;
import com.degree.GraduateWork.service.interfaces.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitialaizer {
    private UserService userService;
    private RoleService roleService;
    private RequestService requestService;
    private RequestStatusService requestStatusService;

    public DataInitialaizer (UserService userService, RoleService roleService, RequestService requestService, RequestStatusService requestStatusService) {
        this.userService = userService;
        this.roleService = roleService;
        this.requestService = requestService;
        this.requestStatusService = requestStatusService;
    }

    @PostConstruct
    public void Init() {
        Set<Role> allRoles = new HashSet<>();
        allRoles.add(new Role("ADMIN"));
        allRoles.add(new Role("USER"));
        allRoles.add(new Role("SUPERVISOR"));
        roleService.createRoles(allRoles);

        Set<RequestStatus> allStatuses = new HashSet<>();
        allStatuses.add(new RequestStatus("В обработке."));
        allStatuses.add(new RequestStatus("Одобрено."));
        allStatuses.add(new RequestStatus("Отклонено."));
        requestStatusService.createRequestStatus(allStatuses);

        User admin = new User("admin", "admin", "admin@mail.ru", "admin","admin");
        admin.setRoles("ADMIN, USER");
        userService.createUser(admin);

        User user = new User("user", "user", "user@mail.ru", "user", "PostTwo");
        user.setRoles("USER");
        userService.createUser(user);

        User secondUser = new User("secondUser", "second", "user2@mail.ru", "second", "TechSupport");
        secondUser.setRoles("USER");
        userService.createUser(secondUser);

        User supervisor = new User("Сергей", "Тестов", "super@mail.ru", "super", "PostThree");
        supervisor.setRoles("SUPERVISOR, USER");
        userService.createUser(supervisor);

        Request first = new Request("Компуктер", 2, 100000, "Нужно купить 2 компа новым сотрудникам.", user);
        first.setRequestStatus("В обработке.");
        requestService.createRequest(first);

        Request second = new Request("Монитор", 3, 30000,"Для новых челиков нужно 3 монитора", secondUser);
        second.setRequestStatus("В обработке.");
        requestService.createRequest(second);
    }


}

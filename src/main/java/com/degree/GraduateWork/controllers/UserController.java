package com.degree.GraduateWork.controllers;

import com.degree.GraduateWork.models.Request;
import com.degree.GraduateWork.models.User;
import com.degree.GraduateWork.service.interfaces.RequestService;
import com.degree.GraduateWork.service.interfaces.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class UserController {

    private final RequestService requestService;
    private final UserService userService;

    public UserController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    @GetMapping
    public String getUserPage(){
        return "user";
    }

    @GetMapping("/requestList")
    public String getAllRequests(Model model) {
        Iterable<Request> requests = requestService.getAllRequests();
        model.addAttribute("requests", requests);
        return "requestList";
    }

    @GetMapping("/newRequest")
    public String toNewRequest() {
        return "newRequest";
    }

    @PostMapping("/newRequest")
    public String createNewRequest(@RequestParam String goods,
                                   @RequestParam Integer quantity,
                                   @RequestParam Integer price,
                                   Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        Request request = new Request(goods,quantity, price);
        request.setRequestStatus("В обработке.");
        request.setUser(user);
        requestService.createRequest(request);
        return "redirect:/user/requestList";
    }

}

package com.degree.GraduateWork.controllers;

import com.degree.GraduateWork.models.Request;
import com.degree.GraduateWork.models.User;
import com.degree.GraduateWork.service.interfaces.RequestService;
import com.degree.GraduateWork.service.interfaces.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController {
    private Integer requestCount = 0;
    private final RequestService requestService;
    private final UserService userService;

    public SupervisorController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    @GetMapping
    public String getSupervisorPage(Model model, Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        model.addAttribute("personName", user.getName());
        Iterable<Request> iterator = requestService.getAllRequests();
        List<Request> list = new ArrayList<>();
        iterator.forEach(list :: add);
        if (requestCount == 0) {
            requestCount = list.size();
            model.addAttribute("unreadRequests", requestCount);
        } else {
            int temp = list.size() - requestCount;
            model.addAttribute("unreadRequests", temp);
        }
        return "supervisor";
    }

    @GetMapping("/supervisorList")
    public String getSupervisorList(Model model) {
        Iterable<Request> requests = requestService.getAllRequests();
        model.addAttribute("requests", requests);
        return "supervisorList";
    }

    @GetMapping("/supervisorList/{id}")
    public String requestDetail(@PathVariable Long id, Model model) {
        Optional<Request> request = requestService.getRequestById(id);
        ArrayList<Request> res = new ArrayList<>();
        request.ifPresent(res :: add);
        model.addAttribute("res", res);
        return "supervisorDetail";
    }

    @GetMapping("/supervisorList/{id}/edit")
    public String requestStatusEdit(@PathVariable Long id, Model model) {
        Optional<Request> request = requestService.getRequestById(id);
        ArrayList<Request> res = new ArrayList<>();
        request.ifPresent(res :: add);
        model.addAttribute("requests", res);
        return "supervisorEdit";
    }

    @PostMapping("/supervisorList/{id}/edit")
    public String requestStatusEditPost(
            @RequestParam String action,
            @PathVariable Long id,
            Model model) {

        Request edited = requestService.getRequestById(id).orElseThrow();
        edited.setRequestStatus(action);
        requestService.updateRequest(edited);
        return "redirect:/supervisor/supervisorList";
    }
}

package web.controller;

import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printUsers(Model model) {
        model.addAttribute("users", userService.getListUsers());
        return "users";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(User user, Model model) {
        model.addAttribute("user", user);
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        return "user-info";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long userId) {
        userService.deleteUserById(userId);
        return "redirect:/";
    }
}

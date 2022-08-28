package kata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import kata.model.User;
import kata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("table")
    public List<User> allUsers() {
        return userService.getUsers();
    }

    @GetMapping("/")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("newUser", new User());
        return "users";
    }

    @PostMapping("/")
    public String setNewUser(@ModelAttribute("newUser") User user) {
        userService.setUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/")
    public String deleteUser(@RequestParam("name") Long id) {
        userService.removeUser(id);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String editUser(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("userByID", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String patchUser(@ModelAttribute("userByID") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

}

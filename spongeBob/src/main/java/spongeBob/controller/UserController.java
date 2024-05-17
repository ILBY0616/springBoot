package spongeBob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spongeBob.pojo.User;
import spongeBob.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loginRegister")
    public String loginRegister(Model model) {
        model.addAttribute("user", new User());
        return "loginRegister";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpServletRequest request) {
        User queryUser = userService.findUserByEmail(user.getEmail());
        HttpSession session = request.getSession();
        if (queryUser != null && queryUser.getPassword().equals(user.getPassword())) {
            session.setAttribute("user", queryUser);
            return "redirect:/homePage";
        } else {
            return "redirect:/loginRegister";
        }
    }

    @GetMapping("/userCenter")
    public String index(Model model) {
        List<User> allUser = userService.findAllUser();
        model.addAttribute("allUser", allUser);
        return "userCenter";
    }

}

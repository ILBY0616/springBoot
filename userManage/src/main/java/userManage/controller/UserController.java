package userManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import userManage.pojo.User;
import userManage.service.UserService;

import java.util.List;

@Controller
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/toAddUser")
    public String toAddUser() {
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(User user) {
        Boolean flag = userService.addUser(user);
        if (flag) {
            System.out.println("添加成功");
        }
        return "redirect:/";
    }

    @GetMapping("/deleteUser/{uid}")
    public String deleteUser(@PathVariable("uid") String uid) {
        Boolean flag = userService.deleteUser(Integer.parseInt(uid));
        if (flag) {
            System.out.println("删除成功");
        }
        return "redirect:/";
    }

    @GetMapping("/toUpdateUser/{uid}")
    public String toUpdateUser(@PathVariable("uid") String uid, Model model) {
        User user = userService.findUser(Integer.parseInt(uid));
        if (user != null) {
            System.out.println("查询成功");
            model.addAttribute("user", user);
        }
        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user) {
        Boolean flag = userService.updateUser(user);
        if (flag) {
            System.out.println("修改成功");
        }
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model) {
        List<User> allUser = userService.findAllUser();
        if (!allUser.isEmpty()) {
            System.out.println("查询所有成功");
            model.addAttribute("allUser", allUser);
        }
        return "index";
    }
}

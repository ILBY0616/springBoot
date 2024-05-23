package scoreManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scoreManage.pojo.Score;

import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ScoreController {

    List<Score> scoreList = new ArrayList<>();

    public String info() {
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
            return "主机地址：" + inetAddress.getHostAddress() + " 主机名称：" + inetAddress.getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("message", info());
        return "index";
    }

    @RequestMapping("add")
    public String add(Model model) {
        model.addAttribute("message", info());
        return "add";
    }

    @RequestMapping("addScore")
    public @ResponseBody String addScore(@Valid Score score, BindingResult bindingResult) {
        StringBuilder message = new StringBuilder();
        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                message.append(objectError.getDefaultMessage());
            }
        } else {
            message.append("添加成功");
            scoreList.add(score);
        }
        return message.toString();
    }

    @RequestMapping("find")
    public String find(Model model) {
        model.addAttribute("message", info());
        model.addAttribute("scoreList", scoreList);
        return "find";
    }

}

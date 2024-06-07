package wareManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wareManage.entity.Ware;
import wareManage.service.WareService;

import java.net.InetAddress;
import java.util.List;

@Controller
public class WareController {
    final WareService wareService;

    @Autowired
    public WareController(WareService wareService) {
        this.wareService = wareService;
    }

    public String info() {
        try {
            InetAddress add = InetAddress.getLocalHost();
            return "主机地址：" + add.getHostAddress() + " 主机名称：" + add.getHostName();
        } catch (Exception e) {
            System.out.println("Exception" + e);
            return "绝密地址绝密名称";
        }
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("backMessage", info());
        return "index";
    }

    @RequestMapping("/addWareRequest")
    public String addWareRequest(Model model) {
        model.addAttribute("backMessage", info());
        return "addWareRequest";
    }

    @RequestMapping("/addWareResponse")
    public String addWareResponse(Model model, Ware ware) {
        if (wareService.addWare(ware) == 1) {
            model.addAttribute("message", "增加成功");
        } else {
            model.addAttribute("message", "增加失败");
        }
        model.addAttribute("backMessage", info());
        return "addWareResponse";
    }

    @RequestMapping("/deleteWareRequest")
    public String deleteWareRequest(Model model) {
        model.addAttribute("backMessage", info());
        return "deleteWareRequest";
    }

    @RequestMapping("/deleteWareResponse")
    public String deleteWareResponse(Model model, int id) {
        if (wareService.deleteWareById(id) == 1) {
            model.addAttribute("message", "删除成功");
        } else {
            model.addAttribute("message", "删除失败");
        }
        model.addAttribute("backMessage", info());
        return "deleteWareResponse";
    }

    @RequestMapping("/updateWareRequest")
    public String updateWareRequest(Model model) {
        model.addAttribute("backMessage", info());
        return "updateWareRequest";
    }

    @RequestMapping("/updateWareResponse")
    public String updateWareResponse(Model model, Ware ware) {
        if (wareService.updateWareById(ware) == 1) {
            model.addAttribute("message", "修改成功");
        } else {
            model.addAttribute("message", "修改失败");
        }
        model.addAttribute("backMessage", info());
        return "updateWareResponse";
    }

    @RequestMapping("/findWareRequest")
    public String findWareRequest(Model model) {
        model.addAttribute("backMessage", info());
        return "findWareRequest";
    }

    @RequestMapping("/findWareResponse")
    public String findWareResponse(Model model, String attribute, String value) {
        List<Ware> wareList = wareService.findWareByAttribute(attribute, value);
        model.addAttribute("wareList", wareList);
        model.addAttribute("backMessage", info());
        return "findWareResponse";
    }

    @RequestMapping("/findAllWare")
    public String findAllWare(Model model) {
        List<Ware> wareList = wareService.findAllWare();
        model.addAttribute("wareList", wareList);
        model.addAttribute("backMessage", info());
        return "findWareResponse";
    }
}

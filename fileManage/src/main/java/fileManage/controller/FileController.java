package fileManage.controller;

import fileManage.util.QRCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Controller
public class FileController {

    public String info() {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
            return "主机地址：" + inetAddress.getHostAddress() + " 主机名称：" + inetAddress.getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("message", info());
        return "/index";
    }

    @RequestMapping("/upload")
    public String upload(Model model) {
        model.addAttribute("message", info());
        return "/upload";
    }

    @RequestMapping("/uploadFile")
    public @ResponseBody String uploadFile(@RequestParam("fileName") List<MultipartFile> files) {
        String message = "";
        if (files.isEmpty()) {
            message = "没有上传的任务";
        }else {
            String path = "D:\\IDEA\\WorkPlace\\springBoot\\fileManage\\src\\main\\resources\\static\\image\\upload";
            int i = 0;
            for (MultipartFile file : files) {
                i++;
                String fileName = file.getOriginalFilename();
                int size = (int) file.getSize();
                System.out.println(fileName + size);
                if (file.isEmpty() || fileName == null) {
                    String tip = "第" + i + "个文件上传为空\n";
                    message = String.format("%s%s", message, tip);
                } else {
                    File dest = new File(path, fileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    try {
                        file.transferTo(dest);
                        String tip = "第" + i + "个文件上传成功\n";
                        message = String.format("%s%s", message, tip);
                    } catch (Exception e) {
                        System.out.println("Exception: " + e.getMessage());
                        String tip = "第" + i + "个文件上传失败\n";
                        message = String.format("%s%s", message, tip);
                    }
                }
            }
        }
        return message;
    }

    @RequestMapping("/download")
    public String download(Model model) {
        model.addAttribute("message", info());
        return "/download";
    }

    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response,@RequestParam("fileName") String fileName) {
        String path = "D:\\IDEA\\WorkPlace\\springBoot\\fileManage\\src\\main\\resources\\static\\image\\download";
        File file = new File(path, fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            }catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("file download" + fileName);
                try{
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                }catch (IOException i){
                    System.out.println("Error: " + i.getMessage());
                }
            }
        }
    }

    @RequestMapping("/qrcode")
    public String qrcode(Model model) {
        model.addAttribute("message", info());
        return "/qrcode";
    }

    @RequestMapping("newQrcode")
    public void newQRCode(HttpServletResponse response) {
        try {
            QRCode.createQRCode(response,200,200,10,"L","jpg","https://www.huas.edu.cn/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}

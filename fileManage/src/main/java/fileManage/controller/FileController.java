package fileManage.controller;

import fileManage.util.QRCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Controller
public class FileController {

    public String info() {
        InetAddress inetAddress;
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
        String message = "提示信息：";
        if (files.isEmpty()) {
            String tip = "没有上传的任务";
            message = String.format("%s%n%s", message, tip);
        } else {
            String path = "D:\\IDEA\\WorkPlace\\springBoot\\fileManage\\src\\main\\resources\\static\\image\\upload";
            int i = 0;
            for (MultipartFile file : files) {
                i++;
                String fileName = file.getOriginalFilename();
                int size = (int) file.getSize();
                System.out.println(fileName + size);
                if (file.isEmpty() || fileName == null) {
                    String tip = "第" + i + "个文件上传为空";
                    message = String.format("%s%n%s", message, tip);
                } else {
                    File dest = new File(path, fileName);
                    if (!dest.getParentFile().exists()) {
                        boolean flag = dest.getParentFile().mkdirs();
                        if (!flag) {
                            System.out.println("目录创建失败");
                        }
                    }
                    try {
                        file.transferTo(dest);
                        String tip = "第" + i + "个文件上传成功";
                        message = String.format("%s%n%s", message, tip);
                    } catch (Exception e) {
                        System.out.println("Exception: " + e.getMessage());
                        String tip = "第" + i + "个文件上传失败";
                        message = String.format("%s%n%s", message, tip);
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
    public void downloadFile(HttpServletResponse response, @RequestParam("fileName") String fileName) {
        String path = "D:\\IDEA\\WorkPlace\\springBoot\\fileManage\\src\\main\\resources\\static\\image\\download";
        File file = new File(path, fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os;
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("file download" + fileName);
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException i) {
                    System.out.println("Error: " + i.getMessage());
                }
            }
        }
    }

    @RequestMapping("/generate")
    public String generate(Model model) {
        model.addAttribute("message", info());
        return "generate";
    }

    @RequestMapping("generateQRCode")
    public void generateQRCode(HttpServletResponse response,
                               @RequestParam int width,
                               @RequestParam int height,
                               @RequestParam int margin,
                               @RequestParam String level,
                               @RequestParam String format,
                               @RequestParam String content,
                               @RequestParam String foregroundColor,
                               @RequestParam String backgroundColor,
                               @RequestParam(required = false) MultipartFile frontImage) {
        try {
            // 读取前景色和背景色
            Color fgColor = Color.decode(foregroundColor);
            Color bgColor = Color.decode(backgroundColor);

            // 读取嵌入的图像
            BufferedImage backImage = null;
            if (frontImage != null && !frontImage.isEmpty()) {
                backImage = ImageIO.read(frontImage.getInputStream());
            }

            // 生成二维码并输出到响应流
            QRCode.generateQRCode(response, width, height, margin, level, format, content, fgColor, bgColor, backImage);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}

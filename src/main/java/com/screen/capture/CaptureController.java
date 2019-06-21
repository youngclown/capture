package com.screen.capture;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class CaptureController {

    @ResponseBody
    @RequestMapping("/")
    public void uploadingImg(HttpServletResponse res) {
        String fileNm = "sample";
        String ext = "png";
        System.setProperty("java.awt.headless", "false");
        try {
            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage image = robot.createScreenCapture(rectangle);
            image.setRGB(0, 0, 100);
            res.setContentType("image/" + ext);
            res.setHeader("Content-Disposition", "inline;filename=" + fileNm);
            ImageIO.write(image, ext, res.getOutputStream());
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }
}

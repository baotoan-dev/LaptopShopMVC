package vn.hbtoan.laptopshop.controller.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;
import java.util.HashMap;

import vn.hbtoan.laptopshop.service.ImageSerivce;

@Controller
@RequestMapping("/upload")
public class ImageController {

    @Autowired
    private ImageSerivce imageSerivce;

    @PostMapping("/image")
    @ResponseBody
    public Map<String, String> uploadImage(@RequestParam("file") MultipartFile image) {
        String imageUrl = imageSerivce.uploadImage(image);
        Map<String, String> response = new HashMap<>();
        if (imageUrl != null) {
            response.put("url", imageUrl);
        } else {
            response.put("error", "Upload failed");
        }
        return response;
    }
}

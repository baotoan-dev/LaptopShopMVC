package vn.hbtoan.laptopshop.controller.Error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("error/404")
    public String error404() {
        return "error/404";
    }

    @GetMapping("error/403")
    public String error403() {
        return "error/403";
    }

    @GetMapping("error/default")
    public String errorDefault() {
        return "error/default";
    }
}

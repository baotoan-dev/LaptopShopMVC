package vn.hbtoan.laptopshop.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @RequestMapping ("/admin")
    public String adminDashboard() {
        return "admin/dashboard/show";
    }
}

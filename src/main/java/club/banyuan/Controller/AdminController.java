package club.banyuan.Controller;

import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class AdminController {
    @Autowired
    private BlogService blogService;

    @GetMapping(value = "/user/{username}/admin")
    public String showAdmin(@PathVariable String username,
                            @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "5") Integer size,
                            Model model) {

        PageInfo pageInfo = blogService.getBlogsByUserName(page, size, username);
        model.addAttribute("blogs", pageInfo);
        return "admin";
    }
}


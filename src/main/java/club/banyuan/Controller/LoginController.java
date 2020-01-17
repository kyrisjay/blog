package club.banyuan.Controller;

import club.banyuan.bean.User;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping
    String showLoginHTML(@RequestParam(required = false) String next,
                         HttpSession session) {
        if (next != null) {
            session.setAttribute("NEXT_URI", next);
        }
        return "login";
    }

    @PostMapping
    String login(@RequestParam String username,
                 @RequestParam String password,
                 HttpSession session) throws UnsupportedEncodingException {

        //1.get form data(username password)
        //2.dao-->username:password
        User user = userService.findUserByName(username);
        //3.check
        if (username != null && password.equals(user.getPassword())) {
            session.setAttribute("CURRENT_User", user);//字段值 字段值下的内容
            // 4. redirect
            // 4.1 如果带有next参数，重定向到next
            String nextUri = (String) session.getAttribute("NEXT_URI");
            if (nextUri != null) {
                return "redirect:" + nextUri;
            }
            // 4.2 不带next表示直接访问的login，那么跳转到user的controller
            return "redirect:/user/" + URLEncoder.encode(username, "UTF-8");
        } else {
            //failed
        }
        return "";
    }

}

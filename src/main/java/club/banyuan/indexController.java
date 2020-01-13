package club.banyuan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class indexController {
    @GetMapping("/{username:[a-z0-9_]+}")
    @ResponseBody
    String getUserBlog(@PathVariable String username){
            return String.format("user %s",username);
    }

}
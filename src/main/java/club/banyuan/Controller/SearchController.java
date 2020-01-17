package club.banyuan.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class SearchController {
    @GetMapping("/search")
    @ResponseBody
    String search(@RequestParam String key){
        return key;
    }
}

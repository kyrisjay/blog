package club.banyuan;



import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test/{username}")
public class TestController {
    @GetMapping
    String paraHandler (
            @PathVariable String username,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
//        String json = Json
//                .add("name", username)
//                .add("page", page)
//                .add("size", size)
//                .build().toString();
        return "json";
    }
}

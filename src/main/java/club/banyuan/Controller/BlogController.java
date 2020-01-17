package club.banyuan.Controller;


import club.banyuan.bean.Blog;
import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.CommentService;
import club.banyuan.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class BlogController {

    private UserService userService;
    private BlogService blogService;
    private CommentService commentService;

    @Autowired
    public BlogController(UserService userService, BlogService blogService, CommentService commentService) {
        this.userService = userService;
        this.blogService = blogService;
        this.commentService = commentService;
    }

    //博客创建
    @GetMapping(value = "/blogs/create")
    public String showCreatePage(HttpSession session,
                                 HttpServletRequest request) {
        //判断用户是否已经登录
        User currentUser = (User) session.getAttribute("CURRENT_User");
        if (currentUser != null) {
            return "create";
        } else {
            //跳转到login
           // String currentUri = request.getRequestURI();
            return "redirect:/login?next=/blogs/create" ;
            // redirect:/login?next=/blogs/create
        }

    }

    @PostMapping(value = "/blogs")
    public String createBlog(Blog blog,
                             HttpSession session) {
        User currentUser = (User) session.getAttribute("CURRENT_User");

        blog.setUserId(currentUser.getId());
        blogService.createBlog(blog);
        return "redirect:/blogs/" + blog.getId();
    }


    //某个博客
    @GetMapping("/blogs/{blogId}")
    String getBlog(@PathVariable Integer blogId, Model model) {
        Blog blog = blogService.getBlogByBlogId(blogId);
        List<Comment> comments = commentService.findBlogComments(blogId);
        model.addAttribute("blog", blog);
        model.addAttribute("comments", comments);
        return "item";
    }

    //分页
    @GetMapping("/user/{username}")
    String getUserBlog(@PathVariable String username,
                       @RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "5") Integer size,
                       Model model) {

        PageInfo pageInfo = blogService.getBlogsByUserName(page, size, username);
        User user = userService.findUserByName(username);
        model.addAttribute("user", user);
        model.addAttribute("blogs", pageInfo);
        return "list";
    }

}
package club.banyuan.dao;

import club.banyuan.bean.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {
    List<Blog>  selectBlogByUserName(String username);
    List<Blog> selectBlogByUsernameWithPageInfo(String username, Integer offset, Integer limit);
    Blog selectBlogById(Integer id);
    void insertBlog(Blog blog);
}

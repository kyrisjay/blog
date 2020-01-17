package club.banyuan.dao;

import club.banyuan.bean.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    List<Comment> selectCommentByBlogId(Integer blogId);
    void insertComment(String content, Integer userId, Integer blogId);
}

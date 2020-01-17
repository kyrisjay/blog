package club.banyuan.service;

import club.banyuan.bean.Comment;
import club.banyuan.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public List<Comment> findBlogComments(Integer blogId) {

        return commentDao.selectCommentByBlogId(blogId);
    }

    public void addComment(Comment comment) {
        commentDao.insertComment(comment.getContent(),comment.getUserId(),comment.getBlogId());
    }
}

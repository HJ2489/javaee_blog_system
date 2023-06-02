package com.torch.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.torch.dao.CommentMapper;
import com.torch.dao.StatisticMapper;
import com.torch.model.domain.Comment;
import com.torch.model.domain.Statistic;
import com.torch.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Torch
 * @Date: 2023/06/02 15:29
 * @Description: ICommentService的实现类
 */
@Service
@Transactional
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private StatisticMapper statisticMapper;
    @Override
    public PageInfo<Comment> getComments(Integer aid, int page, int count) {
        // 根据文章id查询所有评论数据
        PageHelper.startPage(page, count);
        List<Comment> commentList = commentMapper.selectCommentWithPage(aid);

        PageInfo<Comment> commentPageInfo = new PageInfo<>(commentList);

        return commentPageInfo;
    }


    @Override
    public void pushComment(Comment comment){
        commentMapper.pushComment(comment);
        // 更新文章评论数据量
        Statistic statistic = statisticMapper.selectStatisticWithArticleId(comment.getArticleId());
        statistic.setCommentsNum(statistic.getCommentsNum()+1);
        statisticMapper.updateArticleCommentsWithId(statistic);
    }
}

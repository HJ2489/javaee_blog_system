package com.torch.service;

import com.github.pagehelper.PageInfo;
import com.torch.model.domain.Comment;

/**
 * @author: Torch
 * @Date: 2023/06/02 15:25
 * @Description:
 */
public interface ICommentService {
    /**
     * 获取文章下的评论，查询所有评论，也进行分页展示
     * @param aid 文章id
     * @param page
     * @param count
     * @return 返回评论
     */
    public PageInfo<Comment> getComments(Integer aid, int page, int count);
}

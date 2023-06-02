package com.torch.service;

import com.torch.model.ResponseData.StaticticsBo;
import com.torch.model.domain.Article;
import com.torch.model.domain.Comment;

import java.util.List;

/**
 * @author: Torch
 * @Date: 2023/06/02 20:41
 * @Description:
 */
public interface ISiteService {
    // 最新收到的评论
    public List<Comment> recentComments(int count);

    // 最新发表的文章
    public List<Article> recentArticles(int count);

    // 获取后台统计数据
    public StaticticsBo getStatistics();

    // 更新某个文章的统计数据
    public void updateStatistics(Article article);
}

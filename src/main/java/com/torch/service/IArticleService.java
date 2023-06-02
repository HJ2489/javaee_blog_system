package com.torch.service;

import com.github.pagehelper.PageInfo;
import com.torch.model.domain.Article;

import java.util.List;

/**
 * @author: Torch
 * @Date: 2023/06/01 21:03
 * @Description: 实现文章分页，查询文章列表，需要是要mybatis的pagehelper插件
 */
public interface IArticleService {
    /**
     * 分页查询文章列表
     * @param page
     * @param count
     * @return
     */
    public PageInfo<Article> selectArticleWithPage(Integer page, Integer count);


    /**
     * 统计热度前10的文章信息
     * @return
     */
    public List<Article> getHeatArticles();
}

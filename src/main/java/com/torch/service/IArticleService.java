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

    /**
     * 根据id查询单个文章详情
     * @param id
     * @return
     */
    public Article selectArticleWithId(Integer id);

    /**
     * 发布文章
     * @param article
     */
    public void publish(Article article);


    /**
     * 根据主键更新文章
     * @param article
     */
    public void updateArticleWithId(Article article);


    /**
     * 删除文章
     * @param id
     */
    public void deleteArticleWithId(int id);
}

package com.torch.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.torch.dao.ArticleMapper;
import com.torch.dao.StatisticMapper;
import com.torch.model.domain.Article;
import com.torch.model.domain.Statistic;
import com.torch.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Torch
 * @Date: 2023/06/01 21:13
 * @Description:
 */
@Service
@Transactional  // 加上事务控制依赖注解
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private StatisticMapper statisticMapper;

    // 分页查询文章列表
    @Override
    public PageInfo<Article> selectArticleWithPage(Integer page, Integer count) {
        // page是页码，count是设置的每页展示的条数
        PageHelper.startPage(page, count);
        // selectArticleWithPage只能查询到t_article中的数据，对于点击量和评论数需要通过selectStatisticWithArticleId查询得到
        List<Article> articleList = articleMapper.selectArticleWithPage();
        // 封装文章统计数据
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            Statistic statistic = statisticMapper.selectStatisticWithArticleId(article.getId());
            article.setHits(statistic.getHits());
            article.setCommentsNum(statistic.getCommentsNum());
        }
        PageInfo<Article> pageInfo=new PageInfo<>(articleList);
        return pageInfo;
    }

    // 统计热度前10的文章信息
    @Override
    public List<Article> getHeatArticles( ) {
        List<Statistic> list = statisticMapper.getStatistic();
        List<Article> articlelist=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Article article = articleMapper.selectArticleWithId(list.get(i).getArticleId());
            article.setHits(list.get(i).getHits());
            article.setCommentsNum(list.get(i).getCommentsNum());
            articlelist.add(article);
            if(i>=9){
                break;
            }
        }
        return articlelist;
    }
}
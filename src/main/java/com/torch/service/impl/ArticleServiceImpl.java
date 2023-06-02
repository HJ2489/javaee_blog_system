package com.torch.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.torch.dao.ArticleMapper;
import com.torch.dao.CommentMapper;
import com.torch.dao.StatisticMapper;
import com.torch.model.domain.Article;
import com.torch.model.domain.Statistic;
import com.torch.service.IArticleService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private CommentMapper commentMapper;

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

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Article selectArticleWithId(Integer id) {
        // 需要引入redis做缓存处理
        Article article = null;
        // 从redis里面拿数据
        Object o =  redisTemplate.opsForValue().get("article_" + id);
        // 判断这个数据是否有数据存在
        if(o != null) {
            article = (Article) o;
        } else {
            // 如果没有数据那么需要查询数据库
            article = articleMapper.selectArticleWithId(id);
            // 判断这个文章是否有数据
            if(article != null) {
                // 以key value 形式存储 所以set的时候 (key,value)
                redisTemplate.opsForValue().set("article_" + id, article);
            }
        }
        return article;
    }


    // 发布文章
    @Override
    public void publish(Article article) {
        // 去除表情
        article.setContent(EmojiParser.parseToAliases(article.getContent()));
        article.setCreated(new Date());
        article.setHits(0);
        article.setCommentsNum(0);
        // 插入文章，同时插入文章统计数据
        articleMapper.publishArticle(article);
        statisticMapper.addStatistic(article);
    }


    // 更新文章
    @Override
    public void updateArticleWithId(Article article) {
        article.setModified(new Date());
        // 通过文章的id对文章信息进行修改
        articleMapper.updateArticleWithId(article);
        // 修改文章之后，需要对redis缓存数据进行更新
        redisTemplate.delete("article_" + article.getId());
    }




    // 删除文章
    @Override
    public void deleteArticleWithId(int id) {
        // 删除文章的同时，删除对应的缓存
        articleMapper.deleteArticleWithId(id);
        redisTemplate.delete("article_" + id);
        // 同时删除对应文章的统计数据
        statisticMapper.deleteStatisticWithId(id);
        // 同时删除对应文章的评论数据
        commentMapper.deleteCommentWithId(id);
    }

}

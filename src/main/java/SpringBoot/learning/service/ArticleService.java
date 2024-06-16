package SpringBoot.learning.service;

import SpringBoot.learning.pojo.Article;
import SpringBoot.learning.pojo.PageBean;

import java.util.List;

public interface ArticleService {
    void add(Article article);



    //条件分页列表
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}

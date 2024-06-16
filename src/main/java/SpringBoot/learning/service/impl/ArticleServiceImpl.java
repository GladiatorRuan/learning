package SpringBoot.learning.service.impl;

import SpringBoot.learning.mapper.ArticleMapper;
import SpringBoot.learning.pojo.Article;
import SpringBoot.learning.pojo.PageBean;
import SpringBoot.learning.service.ArticleService;
import SpringBoot.learning.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService  {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map =  ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {



        //创建pageBean对象
        PageBean<Article> pb = new PageBean<>();

        //开启分页查询
        PageHelper.startPage(pageNum, pageSize);

        //调用mapper

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
//        System.out.println("查询创建者ID="+ userId +"的文章");

        List<Article> as =  articleMapper.list(userId,categoryId,state);
//        System.out.println("查询到的文章内容：\n"+ as);

        Page<Article> p = (Page<Article>) as;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

}

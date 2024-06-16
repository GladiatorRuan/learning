package SpringBoot.learning.controller;

import SpringBoot.learning.pojo.Article;
import SpringBoot.learning.pojo.PageBean;
import SpringBoot.learning.pojo.Result;
import SpringBoot.learning.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 传入文章内容
     * @param article
     * @return
     */
    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }


    /**
     * 文章列表（条件分页）
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum
            , Integer pageSize
            , @RequestParam(required = false) Integer categoryId
            , @RequestParam(required = false) String state){

        PageBean<Article> pb = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pb);

    }


}

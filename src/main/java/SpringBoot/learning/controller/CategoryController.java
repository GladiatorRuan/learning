package SpringBoot.learning.controller;

import SpringBoot.learning.pojo.Category;
import SpringBoot.learning.pojo.Result;
import SpringBoot.learning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Result add(@RequestBody Category category){
        categoryService.add(category);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Category category){
        categoryService.update(category);
        return Result.success();
    }

}

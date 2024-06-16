package SpringBoot.learning.service;

import SpringBoot.learning.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);
    void update(Category category);

    List<Category> list();

    Category findById(Integer id);
}

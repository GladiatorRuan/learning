package SpringBoot.learning.service.impl;

import SpringBoot.learning.mapper.CategoryMapper;
import SpringBoot.learning.pojo.Category;
import SpringBoot.learning.service.CategoryService;
import SpringBoot.learning.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }


}

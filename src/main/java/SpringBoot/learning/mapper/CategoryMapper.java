package SpringBoot.learning.mapper;

import SpringBoot.learning.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CategoryMapper {

    @Insert("insert into category(category_name, category_alias, create_user,create_time, update_time)" +
    "values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);


    @Update("update category set category_name=#{categoryName}, category_alias=#{categoryAlias} , update_time=now() where id=#{id}")
    void update(Category category);
}

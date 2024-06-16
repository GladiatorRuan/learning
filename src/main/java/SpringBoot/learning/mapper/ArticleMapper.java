package SpringBoot.learning.mapper;

import SpringBoot.learning.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {
        @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time)"
                +" values(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
        void add(Article article);

        @Select("select * from article where create_user = #{createUser}")
        Article articleInfo(Integer createUser);


        List<Article> list(Integer userId, Integer categoryId, String state);
}

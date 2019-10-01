package guru.springfamework.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class CategoryMapperTest {

    CategoryMapper categoryMapper = guru.springfamework.mapper.CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() {


        Category category = new Category();
        category.setId(1L);
        category.setName("Joe");

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(Long.valueOf(1L), categoryDTO.getId());
        assertEquals("Joe", categoryDTO.getName());


    }
}

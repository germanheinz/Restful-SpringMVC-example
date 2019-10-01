package guru.springfamework.services;


import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.mapper.CategoryMapper;
import guru.springfamework.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class CategoryServiceTest {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getAllCategories() {

        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTO = categoryService.getAllCategories();

        assertEquals(3, categoryDTO.size());
    }

    @Test
    public void getCategoryByName() {

        Category cat1 = new Category();
        cat1.setName("Fruits");
        cat1.setId(1L);

        //when(categoryService.getCategoryByName("Fruits")).thenReturn(CategoryDTO);

        when(categoryRepository.findByName(anyString())).thenReturn(cat1);

        CategoryDTO CategoryDTO = categoryService.getCategoryByName("Fruits");

        assertEquals("Fruits", CategoryDTO.getName());


    }
}

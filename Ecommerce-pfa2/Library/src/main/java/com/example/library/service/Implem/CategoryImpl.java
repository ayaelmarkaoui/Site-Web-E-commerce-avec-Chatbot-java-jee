package com.example.library.service.Implem;

import com.example.library.entities.Category;
import com.example.library.repositories.CategoryRepository;
import com.example.library.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        try{
            Category cat=new Category(category.getName());
            return categoryRepository.save(cat);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category update(Category category) {
        Category catUpdate=null;
        try{
            catUpdate=categoryRepository.findById(category.getId()).get();
            catUpdate.setName(category.getName());
            catUpdate.set_deleted(category.is_deleted());
            catUpdate.set_activated(catUpdate.is_activated());

        }catch (Exception e){
            e.printStackTrace();
        }
        return categoryRepository.save(catUpdate);

    }

    @Override
    public void deleteById(Long id) {
            Category cat=categoryRepository.getById(id);
            cat.set_deleted(true);
            cat.set_activated(false);
            categoryRepository.save(cat);

    }

    @Override
    public void enabledById(Long id) {
        Category cat=categoryRepository.getById(id);
        cat.set_activated(true);
        cat.set_deleted(false);
        categoryRepository.save(cat);
    }

    @Override
    public List<Category> findAllByActivated() {
        return categoryRepository.findAllByActivated();
    }
}

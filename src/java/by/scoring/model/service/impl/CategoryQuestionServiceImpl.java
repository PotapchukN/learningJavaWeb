package by.scoring.model.service.impl;

import by.scoring.model.repository.CategoryQuestionRepository;
import by.scoring.model.entity.CategoryQuestion;
import by.scoring.model.service.ICategoryQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryQuestionServiceImpl implements ICategoryQuestionService {

    @Autowired
    private CategoryQuestionRepository categoryQuestionRepository;
    @Override
    public void addCategoryQuestion(CategoryQuestion category) {
        categoryQuestionRepository.save(category);}

    @Override
    public void updateCategoryQuestion(CategoryQuestion category) {
        categoryQuestionRepository.save(category);}
    @Override
    public void removeCategoryQuestion(long id) {
        categoryQuestionRepository.delete(id);}

    @Override
    public CategoryQuestion getCategoryQuestionById(long id) {return categoryQuestionRepository.getOne(id);}

    @Override
    public List<CategoryQuestion> listCategoryQuestion() {return categoryQuestionRepository.findAll();}

    @Override
    public CategoryQuestion findByName(String name) {return categoryQuestionRepository.findByName(name);}
}

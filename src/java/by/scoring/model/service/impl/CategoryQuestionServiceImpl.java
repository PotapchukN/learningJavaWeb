package by.scoring.model.service.impl;

import by.scoring.model.dao.CategoryQuestionDao;
import by.scoring.model.entity.CategoryQuestion;
import by.scoring.model.service.ICategoryQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryQuestionServiceImpl implements ICategoryQuestionService {

    @Autowired
    private CategoryQuestionDao categoryQuestionDao;
    @Override
    public void addCategoryQuestion(CategoryQuestion category) {categoryQuestionDao.save(category);}

    @Override
    public void updateCategoryQuestion(CategoryQuestion category) {categoryQuestionDao.save(category);}
    @Override
    public void removeCategoryQuestion(long id) {categoryQuestionDao.delete(id);}

    @Override
    public CategoryQuestion getCategoryQuestionById(long id) {return categoryQuestionDao.getOne(id);}

    @Override
    public List<CategoryQuestion> listCategoryQuestion() {return categoryQuestionDao.findAll();}

    @Override
    public CategoryQuestion findByName(String name) {return categoryQuestionDao.findByName(name);}
}

package by.scoring.model.service;


import by.scoring.model.entity.CategoryQuestion;

import java.util.List;

public interface ICategoryQuestionService {

    void addCategoryQuestion(CategoryQuestion category);

    void updateCategoryQuestion(CategoryQuestion category);

    void removeCategoryQuestion(long id);

    CategoryQuestion getCategoryQuestionById(long id);

    List<CategoryQuestion> listCategoryQuestion();

    CategoryQuestion findByName(String name);
}

package by.scoring.controller;

import by.scoring.controller.diagramInfo.Risk;
import by.scoring.model.entity.UserAnswers;
import by.scoring.model.entity.UserMoney;
import by.scoring.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.scoring.controller.diagramInfo.DiagramInfo.averageScore;
import static by.scoring.controller.diagramInfo.DiagramInfo.maxScore;
import static by.scoring.controller.diagramInfo.DiagramInfo.minScore;

@Controller
public class RatingController {

    @Autowired
    IUserService userService;

    @Autowired
    IAnswerService answerService;

    @Autowired
    ICategoryQuestionService categoryQuestionService;

    @Autowired
    IGeneralScoreService generalScoreService;

    @Autowired
    IQuestionsService questionsService;

    @Autowired
    IUserAnswersService userAnswersService;

    @Autowired
    IUserMoneyService userMoneyService;


    //    Обработка личной информации
    @RequestMapping(value = "/rating/general-info", method = RequestMethod.POST)
    public String listGeneralInfo(Model model,
                                  @RequestParam("age") String age,
                                  @RequestParam("citizenship") String citizenship,
                                  @RequestParam("registration") String registration,
                                  @RequestParam("housing") String housing,
                                  @RequestParam("sp") String sp,
                                  @RequestParam("children") String children,
                                  @RequestParam("army") String army) {


        UserAnswers userAnswers = new UserAnswers();
        userAnswers.setUser(userService.getCurrentUser());
        userAnswers.setQuestion_id(1);
        userAnswers.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(1), age));
        userAnswers.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(1), age).getScore());
        if(age.equals("Меньше 20 и больше 60")){
            userAnswers.setRisk(Risk.AGE.getRisk_factor());
        }
        userAnswersService.addUserAnswers(userAnswers);

        UserAnswers userAnswers2 = new UserAnswers();
        userAnswers2.setUser(userService.getCurrentUser());
        userAnswers2.setQuestion_id(2);
        userAnswers2.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(2), citizenship));
        userAnswers2.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(2), citizenship).getScore());
        if(citizenship.equals("Нет")){
            userAnswers2.setRisk(Risk.CITIZENSHIP.getRisk_factor());
        }
        userAnswersService.addUserAnswers(userAnswers2);

        UserAnswers userAnswers3 = new UserAnswers();
        userAnswers3.setUser(userService.getCurrentUser());
        userAnswers3.setQuestion_id(3);
        userAnswers3.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(3), registration));
        userAnswers3.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(3), registration).getScore());
        if(registration.equals("Нет")){
            userAnswers3.setRisk(Risk.REGISTRATION.getRisk_factor());
        }
        userAnswersService.addUserAnswers(userAnswers3);

        UserAnswers userAnswers4 = new UserAnswers();
        userAnswers4.setUser(userService.getCurrentUser());
        userAnswers4.setQuestion_id(4);
        userAnswers4.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(4), housing));
        userAnswers4.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(4), housing).getScore());
        if(housing.equals("Съемное жилье")){
            userAnswers4.setRisk(Risk.HOUSING.getRisk_factor());
        }
        userAnswersService.addUserAnswers(userAnswers4);

        UserAnswers userAnswers5 = new UserAnswers();
        userAnswers5.setUser(userService.getCurrentUser());
        userAnswers5.setQuestion_id(5);
        userAnswers5.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(5), sp));
        userAnswers5.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(5), sp).getScore());
        if(sp.equals("Холост/не замужем")|| sp.equals("В разводе") ){
            userAnswers5.setRisk(Risk.SP.getRisk_factor());
        }
        userAnswersService.addUserAnswers(userAnswers5);

        UserAnswers userAnswers6 = new UserAnswers();
        userAnswers6.setUser(userService.getCurrentUser());
        userAnswers6.setQuestion_id(6);
        userAnswers6.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(6), children));
        userAnswers6.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(6), children).getScore());
        userAnswersService.addUserAnswers(userAnswers6);

        UserAnswers userAnswers7 = new UserAnswers();
        userAnswers7.setUser(userService.getCurrentUser());
        userAnswers7.setQuestion_id(7);
        userAnswers7.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(7), army));
        userAnswers7.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(7), army).getScore());
        if(army.equals("Отсрочка")){
            userAnswers7.setRisk(Risk.ARMY.getRisk_factor());
        }
        userAnswersService.addUserAnswers(userAnswers7);


        model.addAttribute("isAdmin", false);
        model.addAttribute("isLogin", true);
        model.addAttribute("success_general_info", true);
        model.addAttribute("anketStart", true);
        return "/credit/rating";
    }

    //    Обработка информации о работе и образовании
    @RequestMapping(value = "/rating/job-info", method = RequestMethod.POST)
    public String listJobInfo(Model model,
                                  @RequestParam("education") String education,
                                  @RequestParam("kindOfActivity") String kindOfActivity,
                                  @RequestParam("skillLevel") String skillLevel,
                                  @RequestParam("experience") String experience) {


        UserAnswers userAnswers = new UserAnswers();
        userAnswers.setUser(userService.getCurrentUser());
        userAnswers.setQuestion_id(8);
        userAnswers.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(8), education));
        userAnswers.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(8), education).getScore());
        if(education.equals("Среднее")){
            userAnswers.setRisk(Risk.EDUCATION.getRisk_factor());
        }
        userAnswersService.addUserAnswers(userAnswers);

        UserAnswers userAnswers2 = new UserAnswers();
        userAnswers2.setUser(userService.getCurrentUser());
        userAnswers2.setQuestion_id(9);
        userAnswers2.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(9), kindOfActivity));
        userAnswers2.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(9), kindOfActivity).getScore());
        if(kindOfActivity.equals("Пенсионер") || kindOfActivity.equals("Иное")){
            userAnswers2.setRisk(Risk.KIND_OF_ACTIVITY.getRisk_factor());
        }
        userAnswersService.addUserAnswers(userAnswers2);

        UserAnswers userAnswers3 = new UserAnswers();
        userAnswers3.setUser(userService.getCurrentUser());
        userAnswers3.setQuestion_id(10);
        userAnswers3.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(10), skillLevel));
        userAnswers3.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(10), skillLevel).getScore());
        if(skillLevel.equals("Отсутствует")){
            userAnswers3.setRisk(Risk.SKILL_LEVEL.getRisk_factor());
        }
        userAnswersService.addUserAnswers(userAnswers3);

        UserAnswers userAnswers4 = new UserAnswers();
        userAnswers4.setUser(userService.getCurrentUser());
        userAnswers4.setQuestion_id(11);
        userAnswers4.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(11), experience));
        userAnswers4.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(11), experience).getScore());
        if(experience.equals("Среднее")){
            userAnswers4.setRisk(Risk.EXPERIENCE.getRisk_factor());
        }
        userAnswersService.addUserAnswers(userAnswers4);


        model.addAttribute("isAdmin", false);
        model.addAttribute("isLogin", true);
        model.addAttribute("success_job_info", true);
        model.addAttribute("anketStart", true);
        return "/credit/rating";
    }

    //    Обработка информации о работе и образовании
    @RequestMapping(value = "/rating/credit-info", method = RequestMethod.POST)
    public String listCreditInfo(Model model,
                              @RequestParam("credit") String credit,
                              @RequestParam("loan_payments") Float summOfCredit,
                              @RequestParam("clientOurBank") String clientOurBank) {


        UserAnswers userAnswers = new UserAnswers();
        userAnswers.setUser(userService.getCurrentUser());
        userAnswers.setQuestion_id(12);
        userAnswers.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(12), credit));
        userAnswers.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(12), credit).getScore());
        if(credit.equals("Да")){
            userAnswers.setRisk(Risk.CREDIT.getRisk_factor());
        }
        userAnswersService.addUserAnswers(userAnswers);

        UserMoney userMoney = new UserMoney();
        userMoney.setUser(userService.getCurrentUser());
        userMoney.setConsumption(summOfCredit);
        userMoney.setIncome(0f);
        userMoneyService.addUserMoney(userMoney);

        UserAnswers userAnswers3 = new UserAnswers();
        userAnswers3.setUser(userService.getCurrentUser());
        userAnswers3.setQuestion_id(14);
        userAnswers3.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(14), clientOurBank));
        userAnswers3.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(14), clientOurBank).getScore());
        userAnswersService.addUserAnswers(userAnswers3);


        model.addAttribute("isAdmin", false);
        model.addAttribute("isLogin", true);
        model.addAttribute("success_credit_info", true);
        model.addAttribute("anketStart", true);
        return "/credit/rating";
    }

    //    Обработка информации о доходах и расходах
    @RequestMapping(value = "/rating/income-info", method = RequestMethod.POST)
    public String listIncomeInfo(Model model,
                                 @RequestParam("general_income") Float general_income,
                                 @RequestParam("communal_payments") Float communal_payments,
                                 @RequestParam("rent_house") Float  rent_house,
                                 @RequestParam("additional_payments") Float additional_payments) {

        UserMoney userMoney = userMoneyService.findByUser(userService.getCurrentUser());
        Float allConsumption = userMoney.getConsumption() +
                communal_payments +
                rent_house +
                additional_payments;
        userMoney.setConsumption(allConsumption);
        userMoney.setIncome(general_income);
        userMoney.setUser(userService.getCurrentUser());
        userMoneyService.updateUserMoney(userMoney);


        model.addAttribute("isAdmin", false);
        model.addAttribute("isLogin", true);
        model.addAttribute("success_income_info", true);
        model.addAttribute("anketStart", true);
        return "/credit/rating";
    }

    //    Обработка информации об имуществе
    @RequestMapping(value = "/rating/holding-info", method = RequestMethod.POST)
    public String listHoldingInfo(Model model,
                                 @RequestParam("flat") String flat,
                                 @RequestParam("car") String car) {

        UserAnswers userAnswers = new UserAnswers();
        userAnswers.setUser(userService.getCurrentUser());
        userAnswers.setQuestion_id(19);
        userAnswers.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(19), flat));
        userAnswers.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(19), flat).getScore());
        userAnswersService.addUserAnswers(userAnswers);

        UserAnswers userAnswers2 = new UserAnswers();
        userAnswers2.setUser(userService.getCurrentUser());
        userAnswers2.setQuestion_id(20);
        userAnswers2.setAnswers( answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(20), car));
        userAnswers2.setScore_for_user(answerService.findByQuestionsAndAnswer(questionsService.getQuestionsById(20), car).getScore());
        if(flat.equals("Нет") && car.equals("Нет")){
            userAnswers2.setRisk(Risk.FLAT.getRisk_factor());
        }
        userAnswersService.addUserAnswers(userAnswers2);


        List<UserAnswers> userAnswersList = userAnswersService.findAllByUser(userService.getCurrentUser());
        int user_score=0;
        for(UserAnswers x :userAnswersList){
            user_score+=x.getScore_for_user();
        }

        UserMoney userMoney = userMoneyService.findByUser(userService.getCurrentUser());
        userMoney.setConsumption(userMoney.getConsumption());
        userMoney.setIncome(userMoney.getIncome());
        userMoney.setUser(userService.getCurrentUser());
        userMoney.setScore(user_score);
        userMoneyService.updateUserMoney(userMoney);

//        ФАКТОРЫ
        List<String> factors = new ArrayList<>();
        for(UserAnswers user_info :userAnswersList){
            factors.add(user_info.getRisk());
        }
        factors.removeIf(Objects::isNull);

        if(user_score<=minScore){
            model.addAttribute("red", true);
        }else if(user_score<=averageScore){
            model.addAttribute("yellow", true);
        }else if(user_score<=maxScore){
            model.addAttribute("green", true);
        }

        model.addAttribute("isAdmin", false);
        model.addAttribute("isLogin", true);
        model.addAttribute("success_holding_info", true);
        model.addAttribute("score", user_score);
        model.addAttribute("isAnket", true);

        if(factors.size()>=1) {model.addAttribute("listFactors", factors);}
        if(factors.size()==0) {model.addAttribute("successlistFactors", true);}

        return "/credit/rating";
    }


    @RequestMapping(value = "/rating/openAnket", method = RequestMethod.GET ,params=("openAnket"))
    public String openAnketSoon(Model model) {

        userAnswersService.removeAllByUser(userService.getCurrentUser());

        userMoneyService.removeUserMoney(userMoneyService.findByUser(userService.getCurrentUser()).getId());

        model.addAttribute("anketStart", true);
        model.addAttribute("running_success", true);
        model.addAttribute("isAdmin", false);
        model.addAttribute("isLogin", true);
        model.addAttribute("isAnket", false);
        return "/credit/rating";
    }

}

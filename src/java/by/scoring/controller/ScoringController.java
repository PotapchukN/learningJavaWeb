package by.scoring.controller;

import by.scoring.model.entity.UserAnswers;
import by.scoring.model.entity.UserMoney;
import by.scoring.model.service.ICallService;
import by.scoring.model.service.IUserAnswersService;
import by.scoring.model.service.IUserMoneyService;
import by.scoring.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.scoring.controller.diagramInfo.DiagramInfo.averageScore;
import static by.scoring.controller.diagramInfo.DiagramInfo.maxScore;
import static by.scoring.controller.diagramInfo.DiagramInfo.minScore;

@Controller
public class ScoringController {

    @Autowired
    IUserService userService;

    @Autowired
    IUserAnswersService userAnswersService;

    @Autowired
    IUserMoneyService userMoneyService;

    @Autowired
    ICallService callService;

    //    Вывести курс валют
    @RequestMapping(value = {"/", "scoring"}, method = RequestMethod.GET)
    public String listMain(Model model) {

        boolean isAdmin = false;
        boolean isLogin = false;
        if (userService.getCurrentUser() != null) {
            isLogin = true;
            isAdmin = userService.getCurrentUser().getRole().equals("ROLE_ADMIN");
        }
        model.addAttribute("isAnket", false);

        if (isLogin) {
            List<UserMoney> users = userMoneyService.listUserMoney();
            for (UserMoney x : users) {
                if (x.getUser().getId().equals(userService.getCurrentUser().getId())) {
                    model.addAttribute("isAnket", true);
                }
            }
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isLogin", isLogin);
        return "/scoring";
    }

    //   Переход на страничку скоринг-анкеты
    @RequestMapping(value = "/scoring/credit-rating", method = RequestMethod.GET)
    public String PageAnket(Model model) {
        //      Переход, если первый раз заполняет
        if (userAnswersService.findAllByUser(userService.getCurrentUser()).isEmpty()) {
            model.addAttribute("anketStart", true);
            //       Переход, если заполнил уже анкету
        } else {
            model.addAttribute("success_holding_info", true);

            //        ФАКТОРЫ, которые повлияли на результат
            List<UserAnswers> userAnswersList = userAnswersService.findAllByUser(userService.getCurrentUser());
            List<String> factors = new ArrayList<>();
            for (UserAnswers x : userAnswersList) {
                factors.add(x.getRisk());
            }
            factors.removeIf(Objects::isNull);

            //        Узнать балл анкеты
            UserMoney userMoney = new UserMoney();
            int score = userMoneyService.findByUser(userService.getCurrentUser()).getScore();


            if (score <= minScore) {
                model.addAttribute("red", true);
            } else if (score <= averageScore) {
                model.addAttribute("yellow", true);
            } else if (score <= maxScore) {
                model.addAttribute("green", true);
            }

            if (factors.size() >= 1) {
                model.addAttribute("listFactors", factors);
            }
            if (factors.size() == 0) {
                model.addAttribute("successlistFactors", true);
            }
            model.addAttribute("score", score);
            model.addAttribute("isAnket", true);
        }
        model.addAttribute("isLogin", true);
        //        чтобы только 1 блок открылся
        model.addAttribute("running_success", true);
        return "/credit/rating";
    }




}

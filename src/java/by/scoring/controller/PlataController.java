package by.scoring.controller;

import by.scoring.model.entity.CreditInfo;
import by.scoring.model.entity.UserAnswers;
import by.scoring.model.entity.UserMoney;
import by.scoring.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static by.scoring.controller.diagramInfo.DiagramInfo.averageScore;
import static by.scoring.controller.diagramInfo.DiagramInfo.minScore;

@Controller
public class PlataController {

    @Autowired
    IUserService userService;

    @Autowired
    IUserAnswersService userAnswersService;

    @Autowired
    IUserMoneyService userMoneyService;

    @Autowired
    ICreditInfoService creditInfoService;

private static final int budget_minimum = 210;

private int numberOfChildren(List<UserAnswers> listAnswers){
    for(UserAnswers user_answer:listAnswers){
        if(user_answer.getQuestion_id()==6){
            switch (user_answer.getAnswers().getAnswer()) {
                case "Нет":
                    return 0;
                case "Один":
                    return 1;
                case "Два":
                    return  2;
                default:
                    return  3;
            }
        }
    }
    return 3;
}

private Double calculateMaxSumOnTheLoan(Float income, Float consumption, int children, Float percent, int year){

    //Чистый доход
    Float NETincome = income-consumption-budget_minimum-children*budget_minimum;

    //Платежеспособность
    Double payment = NETincome*0.7*year*12;

    //Максимальная сумма по кредиту
    return payment/(1+(((year*12 + 1)*percent)/2400));

}

private List<UserMoney> chooseUsersWithSuccessScore(){

    List<UserMoney> list_users = userMoneyService.listUserMoney();
    List<UserMoney> listSuccessUser = new ArrayList<>();
    for(UserMoney user_info:list_users){
        if(user_info.getScore()>minScore){
            listSuccessUser.add(user_info);
        }
    }
    return listSuccessUser;
}

    //Переход на страницу платежеспособности
    @RequestMapping(value = "/solvency", method = RequestMethod.GET)
    public String PageSolvency(Model model) {

        boolean isAdmin = false;
        boolean isLogin = false;
        if (userService.getCurrentUser() != null) {
            isLogin = true;
            isAdmin = userService.getCurrentUser().getRole().equals("ROLE_ADMIN");
        }

        if(isAdmin){
            model.addAttribute("listPaymantForUsers", chooseUsersWithSuccessScore());
        }

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("countOfCredits", creditInfoService.listCreditInfo().size());
        model.addAttribute("isLogin", isLogin);
        model.addAttribute("listCredits", creditInfoService.listCreditInfo());
        return "/solvency/solvency";
    }

    /**Рассчитать платежеспособность пользователя*/

    @RequestMapping(value = "/solvency/calculate", method = RequestMethod.POST)
    public String Calculate(Model model,
                            @RequestParam("cred") String credit,
                            @RequestParam("year") int year) {



       Float income =  userMoneyService.findByUser(userService.getCurrentUser()).getIncome();
       Float consumption = userMoneyService.findByUser(userService.getCurrentUser()).getConsumption();

       List<UserAnswers> listAnswers = userAnswersService.findAllByUser(userService.getCurrentUser());
       int children = numberOfChildren(listAnswers);

       CreditInfo creditInfo = creditInfoService.findByType(credit);
       Float percent =  creditInfo.getPercent();

       Double maxSum = calculateMaxSumOnTheLoan(income, consumption, children, percent, year);
       String maxSummCredit  = String.format("%.2f", maxSum);

        model.addAttribute("maxSummCredit", maxSummCredit);
        model.addAttribute("creditType", credit);
        model.addAttribute("countOfCredits", creditInfoService.listCreditInfo().size());
        model.addAttribute("isAdmin", false);
        model.addAttribute("isLogin", true);
        model.addAttribute("listCredits", creditInfoService.listCreditInfo());
        return "/solvency/solvency";
    }


//    Рассчитать для юзера АДМИНОМ
    @RequestMapping(value = "/solvency/calculate/{id}", method = RequestMethod.POST)
    public String Calculate(Model model,
                            @PathVariable("id") long id,
                            @RequestParam("cred") String credit,
                            @RequestParam("year") int year) {



        Float income =  userMoneyService.findByUser(userService.getCurrentUser()).getIncome();
        Float consumption = userMoneyService.findByUser(userService.getCurrentUser()).getConsumption();

        List<UserAnswers> listAnswers = userAnswersService.findAllByUser(userService.getCurrentUser());
        int children = numberOfChildren(listAnswers);

        CreditInfo creditInfo = creditInfoService.findByType(credit);
        Float percent =  creditInfo.getPercent();

        Double maxSum = calculateMaxSumOnTheLoan(income, consumption, children, percent, year);
        String maxSummCredit  = String.format("%.2f", maxSum);

        String fio = userService.findById(id).getName()+userService.findById(id).getSurname();


        model.addAttribute("adminCalculate", true);
        model.addAttribute("maxSummCredit", maxSummCredit);
        model.addAttribute("creditType", credit);
        model.addAttribute("countOfCredits", creditInfoService.listCreditInfo().size());
        model.addAttribute("isAdmin", true);
        model.addAttribute("year", year);
        model.addAttribute("listCredits", creditInfoService.listCreditInfo());
        model.addAttribute("fio", fio);
        model.addAttribute("listPaymantForUsers", chooseUsersWithSuccessScore());

        return "/solvency/solvency";
    }

    //    Рассчитать для юзера АДМИНОМ
    @RequestMapping(value = "/solvency/calculate/{id}", method = RequestMethod.GET,params = "lala")
    public String Calculate0(Model model,
                            @PathVariable("id") long id) {

        String fio = userService.findById(id).getName()+""+userService.findById(id).getSurname();

        model.addAttribute("fio", fio);
        model.addAttribute("Calculate0", true);
        model.addAttribute("id",id);
        model.addAttribute("countOfCredits", creditInfoService.listCreditInfo().size());
        model.addAttribute("isAdmin", true);
        model.addAttribute("listPaymantForUsers", chooseUsersWithSuccessScore());
        model.addAttribute("listCredits", creditInfoService.listCreditInfo());
        return "/solvency/solvency";
    }

}

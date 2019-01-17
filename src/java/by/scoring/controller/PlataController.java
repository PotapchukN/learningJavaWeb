package by.scoring.controller;

import by.scoring.model.entity.Answers;
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

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.scoring.controller.MainFunctions.minScore;

@Controller
public class PlataController {

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

    @Autowired
    ICreditInfoService creditInfoService;


    //Переход на страницу платежеспособности
    @RequestMapping(value = "/solvency", method = RequestMethod.GET)
    public String PageSolvency(Model model) {

        boolean isAdmin = false;
        boolean isLogin = false;
        if (userService.getCurrentUser() != null) {
            isLogin = true;
            isAdmin = userService.getCurrentUser().getRole().equals("ROLE_ADMIN");
        }
        model.addAttribute("isAdmin", isAdmin);

        if(isAdmin){
            List<UserMoney> lu = userMoneyService.listUserMoney();
            List<UserMoney> listSuccessUser = new ArrayList<>();
            for(UserMoney x:lu){
                if(x.getScore()>817){
                    listSuccessUser.add(x);
                }
            }


            model.addAttribute("listPaymantForUsers", listSuccessUser);


        }
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
       int children = 0;
       int minForPerson = 210;

       List<UserAnswers> listAnswers = userAnswersService.findAllByUser(userService.getCurrentUser());
       for(UserAnswers x:listAnswers){
            if(x.getQuestion_id()==6){
                switch (x.getAnswers().getAnswer()) {
                    case "Нет":
                        children = 0;
                        break;
                    case "Один":
                        children = 1;
                        break;
                    case "Два":
                        children = 2;
                        break;
                    default:
                        children = 3;
                        break;
                }
            }
        }
        CreditInfo creditInfo = creditInfoService.findByType(credit);
        Float percent =  creditInfo.getPercent();

        //Чистый доход

        Float NETincome = income-consumption-minForPerson-children*minForPerson;

        //Платежеспособность

        Double payment = NETincome*0.7*year*12;

        Double maxSumm =payment/(1+(((year*12 + 1)*percent)/2400));
        String maxSummCredit  = String.format("%.2f", maxSumm);

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



        Float income =  userMoneyService.findByUser(userService.findById(id)).getIncome();
        System.out.println(income);
        Float consumption = userMoneyService.findByUser(userService.findById(id)).getConsumption();
        System.out.println(consumption);
        int children = 0;
        int minForPerson = 210;
        List<UserAnswers> listAnswers = userAnswersService.findAllByUser(userService.findById(id));
        for(UserAnswers x:listAnswers){
            if(x.getQuestion_id()==6){
                if(x.getAnswers().getAnswer().equals("Нет")){children=0;}
                else if(x.getAnswers().getAnswer().equals("Один")){children=1;}
                else if(x.getAnswers().getAnswer().equals("Два")){children=2;}
                else{children=3;}
            }
        }
        CreditInfo creditInfo = creditInfoService.findByType(credit);
        Float percent =  creditInfo.getPercent();
System.out.println(children + "CHILF+DREN");
        Float NETincome = income-consumption-minForPerson-(children*minForPerson); //Чистый доход
        Double payment = NETincome*0.7*year*12; //Платежность
        System.out.println(NETincome);
        System.out.println(payment);
        Double maxSumm =payment/(1+(((year*12 + 1)*percent)/2400));
        String maxSummCredit  = String.format("%.2f", maxSumm);

        model.addAttribute("adminCalculate", true);
        model.addAttribute("maxSummCredit", maxSummCredit);
        model.addAttribute("creditType", credit);
        model.addAttribute("countOfCredits", creditInfoService.listCreditInfo().size());
        model.addAttribute("isAdmin", true);

        String fio = userService.findById(id).getName()+userService.findById(id).getSurname();
        model.addAttribute("fio", fio);
        List<UserMoney> lu = userMoneyService.listUserMoney();
        List<UserMoney> listSuccessUser = new ArrayList<>();
        for(UserMoney x:lu){
            if(x.getScore()>minScore){
                listSuccessUser.add(x);
            }
        }


        model.addAttribute("listPaymantForUsers", listSuccessUser);
        model.addAttribute("year", year);

        model.addAttribute("listCredits", creditInfoService.listCreditInfo());
        return "/solvency/solvency";
    }

    //    Рассчитать для юзера АДМИНОМ
    @RequestMapping(value = "/solvency/calculate/{id}", method = RequestMethod.GET,params = "lala")
    public String Calculate0(Model model,
                            @PathVariable("id") long id) {



        model.addAttribute("Calculate0", true);
        model.addAttribute("id",id);
        model.addAttribute("countOfCredits", creditInfoService.listCreditInfo().size());
        model.addAttribute("isAdmin", true);

        String fio = userService.findById(id).getName()+userService.findById(id).getSurname();
        model.addAttribute("fio", fio);
        List<UserMoney> lu = userMoneyService.listUserMoney();
        List<UserMoney> listSuccessUser = new ArrayList<>();
        for(UserMoney x:lu){
            if(x.getScore()>minScore){
                listSuccessUser.add(x);
            }
        }


        model.addAttribute("listPaymantForUsers", listSuccessUser);


        model.addAttribute("listCredits", creditInfoService.listCreditInfo());
        return "/solvency/solvency";
    }

}

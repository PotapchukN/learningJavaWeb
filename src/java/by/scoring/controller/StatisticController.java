package by.scoring.controller;

import by.scoring.model.entity.*;
import by.scoring.model.service.*;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.util.calendar.LocalGregorianCalendar;

import javax.validation.constraints.Null;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static by.scoring.controller.MainFunctions.*;

@Controller
public class StatisticController {

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
    ICreditInfoService creditInfoService;

    @Autowired
    IUserMoneyService userMoneyService;

    @Autowired
    IBidsService bidService;

    /** Информация для построения статистики */

    public Map<String, Integer> circleDiagramm( List<UserMoney> listMoney, List<UserAnswers> listAnswers) {

        // FOR Donut

        int redRisk = 0;
        int averageRisk = 0;
        int greenRisk = 0;

        for (UserMoney x : listMoney) {
            if (x.getScore() <= minScore) {
                redRisk++;
            }
            if (x.getScore() <= averageScore && x.getScore() > minScore) {
                averageRisk++;
            }
            if (x.getScore() <= maxScore && x.getScore() > averageScore) {
                greenRisk++;
            }
        }

        // FOR Bar

        // 1. factors

        List<String> factors = new ArrayList<>();
        for (UserAnswers x : listAnswers) {
            factors.add(x.getRisk());
        }
        factors.removeIf(Objects::isNull);

        int age = 0, citizenship = 0, registration = 0, housing = 0, sp = 0, army = 0,
                education = 0, kindOfActivity = 0, skillLevel = 0, experience = 0,
                credit = 0, flat = 0;

        // 2. info for factors from BD

        for (String x : factors) {
            if (x.equals("Возраст: менее 20 и более 60 лет считается рискованным возрастом для банка")) {
                age++;
            }
            if (x.equals("Отсутствие гражданства Республики Беларусь")) {
                citizenship++;
            }
            if (x.equals("Отсутствие прописки")) {
                registration++;
            }
            if (x.equals("Проживание в съемном жилье негативно влияет на кредитный рейтинг, т.к. значительная часть вашего дохода уходит на оплату аренды. " +
                    "Когда у заемщика нет собственного жилья, лучшим вариантом считается проживание у родственников.")) {
                housing++;
            }
            if (x.equals("Семейное положение: как правило семейные люди более ответственно подходят к" +
                    "формированию бюджета ")) {
                sp++;
            }
            if (x.equals("Отсрочка: негативно влияет на результат анкеты")) {
                army++;
            }

            if (x.equals("Уровень образования")) {
                education++;
            }

            if (x.equals("Род деятельности")) {
                kindOfActivity++;
            }

            if (x.equals("Отсутствие квалификации")) {
                skillLevel++;
            }

            if (x.equals("Стаж на текущем месте работы: небольшой стаж несколько снижает Вашу привлекательность в качестве заемщика для банка")) {
                experience++;
            }

            if (x.equals("Наличие кредита")) {
                credit++;
            }

            if (x.equals("Отсутствие недвижимого имущества несколько снижает итоговый результат")) {
                flat++;
            }
        }
        Map<String, Integer> attr = new HashMap<String, Integer>();
        attr.put("age",age);
        attr.put("citizenship", citizenship);
        attr.put("registration", registration);
        attr.put("housing", housing);
        attr.put("sp", sp);
        attr.put("army", army);
        attr.put("education", education);
        attr.put("kindOfActivity", kindOfActivity);
        attr.put("skillLevel", skillLevel);
        attr.put("experience", experience);
        attr.put("credit", credit);
        attr.put("flat", flat);
        attr.put("redRisk", redRisk);
        attr.put("averageRisk", averageRisk);
        attr.put("greenRisk", greenRisk);

        return attr;
    }
   //Переход на страницу статистики
    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public String PageStatistic(Model model) {

        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);

        List<UserMoney> l= userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        Map<String,Integer> attr = circleDiagramm( l, listAnswers);

        model.addAllAttributes(attr);
        System.out.println(attr);
        return "/admin/statistic";
    }

// <-----Выбрать плохих клиентов------>
    @RequestMapping(value = "/statistic/badClients", method = RequestMethod.GET ,params=("showBadClients"))
    public String showBadClients(Model model) {

        List <UserMoney> listUsers = userMoneyService.listUserMoney();
        List<UserMoney> listBadUsers = new ArrayList<>();

        for(UserMoney x:listUsers){
            if(x.getScore()<=minScore) {
                listBadUsers.add(x);
            }
        }

        model.addAttribute("listBadUsers", listBadUsers);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);

        List<UserMoney> l= userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        Map<String,Integer> attr = circleDiagramm( l, listAnswers);
        model.addAllAttributes(attr);

        return "/admin/statistic";
    }

    // <-----Выбрать удовлетворительных клиентов------>
    @RequestMapping(value = "/statistic/averageClients", method = RequestMethod.GET ,params=("showAverageClients"))
    public String showAverageClients(Model model) {

        List <UserMoney> listUsers = userMoneyService.listUserMoney();
        List<UserMoney> listAverageUsers = new ArrayList<>();

        for(UserMoney x:listUsers){
            if(x.getScore()>minScore && x.getScore() <=averageScore) {
                listAverageUsers.add(x);
            }
        }

        model.addAttribute("listAverageUsers", listAverageUsers);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);

        List<UserMoney> l= userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        Map<String,Integer> attr = circleDiagramm( l, listAnswers);
        model.addAllAttributes(attr);
        return "/admin/statistic";
    }

    // <-----Выбрать хороших клиентов------>
    @RequestMapping(value = "/statistic/goodClients", method = RequestMethod.GET ,params=("showGoodClients"))
    public String showGoodClients(Model model) {

        List<UserMoney> listUsers = userMoneyService.listUserMoney();
        List<UserMoney> listGoodUsers = new ArrayList<>();

        for(UserMoney x:listUsers){
            if(x.getScore()>averageScore) {
                listGoodUsers.add(x);
            }
        }

        model.addAttribute("listGoodUsers", listGoodUsers);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);

        List<UserMoney> l= userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        Map<String,Integer> attr = circleDiagramm( l, listAnswers);
        model.addAllAttributes(attr);
        return "/admin/statistic";
    }

    @RequestMapping(value="/statistic/anketClientBad/{id}",
            params=("showAnketClientBad"),
            method=RequestMethod.GET)
    public String showAnketClientBad(Model model, @PathVariable("id") long id){

        List<UserAnswers> listUserAnswers = userAnswersService.findAllByUser(userService.findById(id));

        List <UserMoney> listUsers = userMoneyService.listUserMoney();
        List<UserMoney> listBadUsers = new ArrayList<>();

        for(UserMoney x:listUsers){
            if(x.getScore()<=minScore) {
                listBadUsers.add(x);
            }
        }

        model.addAttribute("listBadUsers", listBadUsers);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);
        model.addAttribute("listUserAnswers", listUserAnswers);

        List<UserMoney> l= userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        Map<String,Integer> attr = circleDiagramm( l, listAnswers);
        model.addAllAttributes(attr);
        return "/admin/statistic";
    }

    @RequestMapping(value="/statistic/anketClientAverage/{id}",
            params=("showAnketClientAverage"),
            method=RequestMethod.GET)
    public String showAnketClientAverage(Model model, @PathVariable("id") long id){

        List<UserAnswers> listUserAnswers = userAnswersService.findAllByUser(userService.findById(id));



        List <UserMoney> listUsers = userMoneyService.listUserMoney();
        List<UserMoney> listAverageUsers = new ArrayList<>();

        for(UserMoney x:listUsers){
            if(x.getScore()>minScore && x.getScore() <=averageScore) {
                listAverageUsers.add(x);
            }
        }



        List<UserMoney> l= userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        Map<String,Integer> attr = circleDiagramm( l, listAnswers);
        model.addAllAttributes(attr);

        model.addAttribute("listAverageUsers", listAverageUsers);

        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);
        model.addAttribute("listUserAnswers", listUserAnswers);
        return "/admin/statistic";
    }

    @RequestMapping(value="/statistic/anketClientGood/{id}",
            params=("showAnketClientGood"),
            method=RequestMethod.GET)
    public String showAnketClientGood(Model model, @PathVariable("id") long id){

        List<UserAnswers> listUserAnswers = userAnswersService.findAllByUser(userService.findById(id));

        model.addAttribute("listUserAnswers", listUserAnswers);

        List <UserMoney> listUsers = userMoneyService.listUserMoney();
        List<UserMoney> listGoodUsers = new ArrayList<>();

        for(UserMoney x:listUsers){
            if(x.getScore()>averageScore) {
                listGoodUsers.add(x);
            }
        }

        model.addAttribute("listGoodUsers", listGoodUsers);

        List<UserMoney> l= userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        Map<String,Integer> attr = circleDiagramm( l, listAnswers);
        model.addAllAttributes(attr);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);

        return "/admin/statistic";
    }



    @RequestMapping(value="/statistic/sendBid/{id}",
            params=("btnSendBid"),
            method=RequestMethod.POST)
    public String sendBind(Model model, @PathVariable("id") long id,
                           @RequestParam(value ="name1", required = false) String name1,
                           @RequestParam(value ="name2", required = false) String name2,
                           @RequestParam(value ="name3", required = false) String name3,
                           @RequestParam(value ="period1", required = false) Float period1,
                           @RequestParam(value ="period2", required = false) Float period2,
                           @RequestParam(value ="period3", required = false) Float period3,
                           @RequestParam(value ="guarantor1", required = false) String guarantor1,
                           @RequestParam(value ="guarantor2", required = false) String guarantor2,
                           @RequestParam(value ="guarantor3", required = false) String guarantor3,
                           @RequestParam(value ="sum1", required = false) Float sum1,
                           @RequestParam(value ="sum2", required = false) Float sum2,
                           @RequestParam(value ="sum3", required = false) Float sum3,
                           @RequestParam("date") Date date,
                           @RequestParam("time") String time
                           ){


        Bid bid = new Bid();
        bid.setUser(userService.findById(id));
        bid.setCredit(creditInfoService.findByType(name1));
        bid.setMaxSum(sum1);
        bid.setMaxTerm(period1);
        if(StringUtils.isNullOrEmpty(guarantor1)){bid.setGuarantor("N");}else{
            bid.setGuarantor("Y");
        }
        bid.setTime(LocalTime.parse(time).format(DateTimeFormatter.ofPattern("HH:mm")));
       System.out.println("Дата1: "+date);
        bid.setDate(date);
        bidService.addBid(bid);

        Bid bid2 = new Bid();
        bid2.setUser(userService.findById(id));
        bid2.setCredit(creditInfoService.findByType(name2));
        bid2.setMaxSum(sum2);
        bid2.setMaxTerm(period2);
        if(StringUtils.isNullOrEmpty(guarantor2)){
            bid2.setGuarantor("N");}
            else{
            bid2.setGuarantor("Y");
            }
        bid2.setTime(LocalTime.parse(time).format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("Дата2: "+date);
        bid2.setDate(date);
        bidService.addBid(bid2);

        Bid bid3 = new Bid();
        bid3.setUser(userService.findById(id));
        bid3.setCredit(creditInfoService.findByType(name3));
        bid3.setMaxSum(sum3);
        bid3.setMaxTerm(period3);
        if(StringUtils.isNullOrEmpty(guarantor3)){
            bid3.setGuarantor("N");
        }else{
            bid3.setGuarantor("Y");
            }
        bid3.setTime(LocalTime.parse(time).format(DateTimeFormatter.ofPattern("HH:mm")));
        bid3.setDate(date);
        bidService.addBid(bid3);

        model.addAttribute("sendBid", true);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);

        List<UserMoney> l= userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        Map<String,Integer> attr = circleDiagramm( l, listAnswers);
        model.addAllAttributes(attr);



        List<UserAnswers> listUserAnswers = userAnswersService.findAllByUser(userService.findById(id));
        model.addAttribute("listUserAnswers", listUserAnswers);

//        List <UserMoney> listUsers = userMoneyService.listUserMoney();
//        List<UserMoney> listGoodUsers = new ArrayList<>();
//
//        for(UserMoney x:listUsers){
//            if(x.getScore()>averageScore) {
//                listGoodUsers.add(x);
//            }
//        }
//
//        model.addAttribute("listGoodUsers", listGoodUsers);
        return "/admin/statistic";
    }


    @RequestMapping(value="/statistic/showBidForm/{id}",
            params=("btnShowBidForm"),
            method=RequestMethod.GET)
    public String showBidForm(Model model, @PathVariable("id") long id){

        String fio = userService.findById(id).getName() + " " + userService.findById(id).getSurname();

        model.addAttribute("fio",fio);
        model.addAttribute("listCredit", creditInfoService.listCreditInfo());
        model.addAttribute("id",id);
        model.addAttribute("showBidForm", true);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);

        List<UserMoney> l= userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        Map<String,Integer> attr = circleDiagramm( l, listAnswers);
        model.addAllAttributes(attr);


//        List<UserAnswers> listUserAnswers = userAnswersService.findAllByUser(userService.findById(id));
//        model.addAttribute("listUserAnswers", listUserAnswers);

//        List <UserMoney> listUsers = userMoneyService.listUserMoney();
//        List<UserMoney> listGoodUsers = new ArrayList<>();
//
//        for(UserMoney x:listUsers){
//            if(x.getScore()>averageScore) {
//                listGoodUsers.add(x);
//            }
//        }
//
//        model.addAttribute("listGoodUsers", listGoodUsers);
        return "/admin/statistic";
    }
}

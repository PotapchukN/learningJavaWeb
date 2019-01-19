package by.scoring.controller;

import by.scoring.controller.diagramInfo.DiagramInfo;
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

import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static by.scoring.controller.diagramInfo.DiagramInfo.*;

@Controller
public class StatisticController {

    @Autowired
    IUserService userService;

    @Autowired
    IUserAnswersService userAnswersService;

    @Autowired
    ICreditInfoService creditInfoService;

    @Autowired
    IUserMoneyService userMoneyService;

    @Autowired
    IBidsService bidService;

    private DiagramInfo diagramInfo = new DiagramInfo();

   //Переход на страницу статистики
    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public String PageStatistic(Model model) {

        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);

        List<UserMoney> listUsers= userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        Map<String,Integer> mapInfoForHistogrammAndDonut = diagramInfo.makeDiagramms( listUsers, listAnswers);
        model.addAllAttributes(mapInfoForHistogrammAndDonut);
        return "/admin/statistic";
    }

    /** Выбрать неподходящих клиентов */
    @RequestMapping(value = "/statistic/badClients", method = RequestMethod.GET ,params=("showBadClients"))
    public String showBadClients(Model model) {

        List <UserMoney> listUsers = userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        List<UserMoney> listBadUsers = new ArrayList<>();

        for(UserMoney user_info:listUsers){
            if(user_info.getScore()<=minScore) {
                listBadUsers.add(user_info);
            }
        }

        Map<String,Integer> mapInfoForHistogrammAndDonut = diagramInfo.makeDiagramms( listUsers, listAnswers);

        model.addAttribute("listBadUsers", listBadUsers);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);
        model.addAllAttributes(mapInfoForHistogrammAndDonut);

        return "/admin/statistic";
    }

    /**Выбрать удовлетворительных клиентов */
    @RequestMapping(value = "/statistic/averageClients", method = RequestMethod.GET ,params=("showAverageClients"))
    public String showAverageClients(Model model) {

        List <UserMoney> listUsers = userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        List<UserMoney> listAverageUsers = new ArrayList<>();

        for(UserMoney user_info:listUsers){
            if(user_info.getScore()>minScore && user_info.getScore() <=averageScore) {
                listAverageUsers.add(user_info);
            }
        }

        Map<String,Integer> mapInfoForHistogrammAndDonut = diagramInfo.makeDiagramms( listUsers, listAnswers);

        model.addAllAttributes(mapInfoForHistogrammAndDonut);
        model.addAttribute("listAverageUsers", listAverageUsers);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);

        return "/admin/statistic";
    }

    /**Выбрать хороших клиентов*/
    @RequestMapping(value = "/statistic/goodClients", method = RequestMethod.GET ,params=("showGoodClients"))
    public String showGoodClients(Model model) {

        List<UserMoney> listUsers = userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();


        List<UserMoney> listGoodUsers = new ArrayList<>();

        for(UserMoney user_info:listUsers){
            if(user_info.getScore()>averageScore) {
                listGoodUsers.add(user_info);
            }
        }

        model.addAttribute("listGoodUsers", listGoodUsers);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);

        Map<String,Integer> mapInfoForHistogrammAndDonut = diagramInfo.makeDiagramms( listUsers, listAnswers);
        model.addAllAttributes(mapInfoForHistogrammAndDonut);
        return "/admin/statistic";
    }

    /** Показать анкеты по категориям ползователей */
    @RequestMapping(value="/statistic/anketClientBad/{id}",
            params=("showAnketClientBad"),
            method=RequestMethod.GET)
    public String showAnketClientBad(Model model, @PathVariable("id") long id){

        List<UserAnswers> listUserAnswers = userAnswersService.findAllByUser(userService.findById(id));
        List<UserMoney> listUsers = userMoneyService.listUserMoney();
        List<UserMoney> listBadUsers = new ArrayList<>();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        for(UserMoney user_info:listUsers){
            if(user_info.getScore()<=minScore) {
                listBadUsers.add(user_info);
            }
        }

        Map<String,Integer> mapInfoForHistogrammAndDonut = diagramInfo.makeDiagramms( listUsers, listAnswers);

        model.addAllAttributes(mapInfoForHistogrammAndDonut);
        model.addAttribute("listBadUsers", listBadUsers);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);
        model.addAttribute("listUserAnswers", listUserAnswers);

        return "/admin/statistic";
    }

    @RequestMapping(value="/statistic/anketClientAverage/{id}",
            params=("showAnketClientAverage"),
            method=RequestMethod.GET)
    public String showAnketClientAverage(Model model, @PathVariable("id") long id){

        List<UserAnswers> listUserAnswers = userAnswersService.findAllByUser(userService.findById(id));
        List <UserMoney> listUsers = userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();
        List<UserMoney> listAverageUsers = new ArrayList<>();

        for(UserMoney user_info:listUsers){
            if(user_info.getScore()>minScore && user_info.getScore() <=averageScore) {
                listAverageUsers.add(user_info);
            }
        }

        Map<String,Integer> mapInfoForHistogrammAndDonut = diagramInfo.makeDiagramms( listUsers, listAnswers);

        model.addAllAttributes(mapInfoForHistogrammAndDonut);
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
        List <UserMoney> listUsers = userMoneyService.listUserMoney();
        List<UserMoney> listGoodUsers = new ArrayList<>();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();

        for(UserMoney user_info:listUsers){
            if(user_info.getScore()>averageScore) {
                listGoodUsers.add(user_info);
            }
        }

        Map<String,Integer> mapInfoForHistogrammAndDonut = diagramInfo.makeDiagramms( listUsers, listAnswers);

        model.addAttribute("listGoodUsers", listGoodUsers);
        model.addAttribute("listUserAnswers", listUserAnswers);
        model.addAllAttributes(mapInfoForHistogrammAndDonut);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);

        return "/admin/statistic";
    }

    /** Отправить пользователю заявку на кредит*/
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

        List<UserAnswers> listUserAnswers = userAnswersService.findAllByUser(userService.findById(id));
        List<UserMoney> listUsers= userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();
        Map<String,Integer> mapInfoForHistogrammAndDonut = diagramInfo.makeDiagramms( listUsers, listAnswers);

        Bid bid = new Bid();
        bid.setUser(userService.findById(id));
        bid.setCredit(creditInfoService.findByType(name1));
        bid.setMaxSum(sum1);
        bid.setMaxTerm(period1);
        if(StringUtils.isNullOrEmpty(guarantor1)){
            bid.setGuarantor("N");
        }else{
            bid.setGuarantor("Y");
        }
        bid.setTime(LocalTime.parse(time).format(DateTimeFormatter.ofPattern("HH:mm")));
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
        model.addAllAttributes(mapInfoForHistogrammAndDonut);
        model.addAttribute("listUserAnswers", listUserAnswers);

        return "/admin/statistic";
    }


    @RequestMapping(value="/statistic/showBidForm/{id}",
            params=("btnShowBidForm"),
            method=RequestMethod.GET)
    public String showBidForm(Model model, @PathVariable("id") long id){

        String fio = userService.findById(id).getName() + " " + userService.findById(id).getSurname();
        List<UserMoney> listUsers= userMoneyService.listUserMoney();
        List<UserAnswers> listAnswers = userAnswersService.listUserAnswers();
        Map<String,Integer> mapInfoForHistogrammAndDonut = diagramInfo.makeDiagramms( listUsers, listAnswers);

        model.addAttribute("fio",fio);
        model.addAttribute("listCredit", creditInfoService.listCreditInfo());
        model.addAttribute("id",id);
        model.addAttribute("showBidForm", true);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isLogin", false);
        model.addAllAttributes(mapInfoForHistogrammAndDonut);

        return "/admin/statistic";
    }
}

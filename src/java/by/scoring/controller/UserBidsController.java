package by.scoring.controller;

import by.scoring.model.entity.Bid;
import by.scoring.model.entity.UserAnswers;
import by.scoring.model.entity.UserMoney;
import by.scoring.model.service.*;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class UserBidsController {

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

    //Переход на страницу заявок
    @RequestMapping(value = "/bids", method = RequestMethod.GET)
    public String PageStatistic(Model model) {

        List<Bid> listBids =  bidService.findByUser(userService.getCurrentUser());
        Date date = new Date();
        String bidTime = null;
           for(Bid x:listBids){
              if(!StringUtils.isNullOrEmpty(x.getDate().toString())){
                  date = x.getDate();
               }
               if(!StringUtils.isNullOrEmpty(x.getTime())){
                   bidTime = x.getTime();
               }
               if(x.getGuarantor().equals("N")){
                   x.setGuarantor("Нет");
               }else if (x.getGuarantor().equals("Y")){
                   x.setGuarantor("Да");
               }
           }

        SimpleDateFormat dt1 = new SimpleDateFormat("dd.MM.yyyy");
        String bidDate = dt1.format(date);

//        SimpleDateFormat dt2 = new SimpleDateFormat("hh:mm");
//        String bidTime = dt2.format(time);

        model.addAttribute("isAdmin", false);
        model.addAttribute("isLogin", true);
        model.addAttribute("listBids", listBids);
        model.addAttribute("bidDate", bidDate);
        model.addAttribute("bidTime", bidTime);
        return "/bids/bids";
    }
}

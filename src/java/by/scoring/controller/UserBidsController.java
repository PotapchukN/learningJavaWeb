package by.scoring.controller;

import by.scoring.model.entity.Bid;
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

@Controller
public class UserBidsController {

    @Autowired
    IUserService userService;

    @Autowired
    IBidsService bidService;

    //Переход на страницу заявок, пришедших от администратора пользователю
    @RequestMapping(value = "/bids", method = RequestMethod.GET)
    public String PageStatistic(Model model) {

        List<Bid> listBids =  bidService.findByUser(userService.getCurrentUser());
        Date date = new Date();
        String bidTime = null;

        for(Bid bid:listBids){
              if(!StringUtils.isNullOrEmpty(bid.getDate().toString())){
                  date = bid.getDate();
               }
               if(!StringUtils.isNullOrEmpty(bid.getTime())){
                   bidTime = bid.getTime();
               }
               if(bid.getGuarantor().equals("N")){
                   bid.setGuarantor("Нет");
               }else if (bid.getGuarantor().equals("Y")){
                   bid.setGuarantor("Да");
               }
           }

        SimpleDateFormat dt1 = new SimpleDateFormat("dd.MM.yyyy");
        String bidDate = dt1.format(date);


        model.addAttribute("isAdmin", false);
        model.addAttribute("isLogin", true);
        model.addAttribute("listBids", listBids);
        model.addAttribute("bidDate", bidDate);
        model.addAttribute("bidTime", bidTime);
        return "/bids/bids";
    }
}

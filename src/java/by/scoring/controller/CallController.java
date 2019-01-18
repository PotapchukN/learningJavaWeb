package by.scoring.controller;

import by.scoring.model.entity.Calls;
import by.scoring.model.entity.UserMoney;
import by.scoring.model.service.ICallService;
import by.scoring.model.service.IUserAnswersService;
import by.scoring.model.service.IUserMoneyService;
import by.scoring.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CallController {

    @Autowired
    IUserService userService;

    @Autowired
    IUserAnswersService userAnswersService;

    @Autowired
    IUserMoneyService userMoneyService;

    @Autowired
    ICallService callService;


    //Переход на страничку звонков
    @RequestMapping(value = "/calls/all", method = RequestMethod.POST)
    public String callsFoAdmin(Model model) {


        model.addAttribute("isAdmin", true);
        model.addAttribute("listCalls", callService.listCalls());

        return "admin/calls";

    }

    //Удаление звонков
    @RequestMapping(value = "/call/remove/{id}", method = RequestMethod.GET, params = ("btnRemoveCall"))
    public String callsRemove(Model model, @PathVariable("id") long id) {

        callService.removeCall(id);

        model.addAttribute("isAdmin", true);
        model.addAttribute("listCalls", callService.listCalls());

        return "admin/calls";

    }


    //    Звонки
    @RequestMapping(value = "/calls/get", method = RequestMethod.POST)
    public String calls(Model model,
                        @RequestParam("topic") String topic,
                        @RequestParam("name") String name,
                        @RequestParam("phone") String phone,
                        @RequestParam("email") String email
    ) {

        Calls call = new Calls();
        call.setEmail(email);
        call.setName(name);
        call.setPhone(phone);
        call.setTopic(topic);
        callService.addCall(call);

        model.addAttribute("successCall", true);
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

}

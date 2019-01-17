package by.scoring.controller;

import by.scoring.model.entity.UserAnswers;
import by.scoring.model.entity.UserMoney;
import org.springframework.ui.Model;

import java.util.*;

class MainFunctions {

    static final int minScore = 817;
    static final int maxScore = 1500;
    static final int averageScore = 1150;

//    public Map<String, Integer> circleDiagramm( List<UserMoney> listMoney, List<UserAnswers> listAnswers) {
//
////       <------------------------------FOR---------Donut------------------------->
//        int redRisk = 0;
//        int averageRisk = 0;
//        int greenRisk = 0;
//
//        for (UserMoney x : listMoney) {
//            if (x.getScore() <= minScore) {
//                redRisk++;
//            }
//            if (x.getScore() <= averageScore && x.getScore() > minScore) {
//                averageRisk++;
//            }
//            if (x.getScore() <= maxScore && x.getScore() > averageScore) {
//                greenRisk++;
//            }
//        }
//
////       <--------------------------------FOR-------Bar------------------------->
//
//        //<---------factors---------------------->
//        List<String> factors = new ArrayList<>();
//        for (UserAnswers x : listAnswers) {
//            factors.add(x.getRisk());
//        }
//        factors.removeIf(Objects::isNull);
//
//        int age = 0, citizenship = 0, registration = 0, housing = 0, sp = 0, army = 0,
//                education = 0, kindOfActivity = 0, skillLevel = 0, experience = 0,
//                credit = 0, flat = 0;
//
//        for (String x : factors) {
//            if (x.equals("Возраст: менее 20 и более 60 лет считается рискованным возрастом для банка")) {
//                age++;
//            }
//            if (x.equals("Отсутствие гражданства Республики Беларусь")) {
//                citizenship++;
//            }
//            if (x.equals("Отсутствие прописки")) {
//                registration++;
//            }
//            if (x.equals("Проживание в съемном жилье негативно влияет на кредитный рейтинг, т.к. значительная часть вашего дохода уходит на оплату аренды. " +
//                    "Когда у заемщика нет собственного жилья, лучшим вариантом считается проживание у родственников.")) {
//                housing++;
//            }
//            if (x.equals("Семейное положение: как правило семейные люди более ответственно подходят к" +
//                    "формированию бюджета ")) {
//                sp++;
//            }
//            if (x.equals("Отсрочка: негативно влияет на результат анкеты")) {
//                army++;
//            }
//
//            if (x.equals("Уровень образования")) {
//                education++;
//            }
//
//            if (x.equals("Род деятельности")) {
//                kindOfActivity++;
//            }
//
//            if (x.equals("Отсутствие квалификации")) {
//                skillLevel++;
//            }
//
//            if (x.equals("Стаж на текущем месте работы: небольшой стаж несколько снижает Вашу привлекательность в качестве заемщика для банка")) {
//                experience++;
//            }
//
//            if (x.equals("Наличие кредита")) {
//                credit++;
//            }
//
//            if (x.equals("Отсутствие недвижимого имущества несколько снижает итоговый результат")) {
//                flat++;
//            }
//        }
//        Map<String, Integer> attr = new HashMap<String, Integer>();
//        attr.put("age",age);
//        attr.put("citizenship", citizenship);
//        attr.put("registration", registration);
//        attr.put("housing", housing);
//        attr.put("sp", sp);
//        attr.put("army", army);
//        attr.put("education", education);
//        attr.put("kindOfActivity", kindOfActivity);
//        attr.put("skillLevel", skillLevel);
//        attr.put("experience", experience);
//        attr.put("credit", credit);
//        attr.put("flat", flat);
//        attr.put("redRisk", redRisk);
//        attr.put("averageRisk", averageRisk);
//        attr.put("greenRisk", greenRisk);
//
//        return attr;
//    }
}

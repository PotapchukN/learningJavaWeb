package by.scoring.controller.diagramInfo;

import by.scoring.model.entity.UserAnswers;
import by.scoring.model.entity.UserMoney;

import java.util.*;

public class DiagramInfo {

    public static final int minScore = 817;
    public static final int maxScore = 1500;
    public static final int averageScore = 1150;

    /** Информация для построения статистики */
    public Map<String, Integer> makeDiagramms( List<UserMoney> listMoney, List<UserAnswers> listAnswers) {

//       <------------------------------For-Donut------------------------->
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

//       <--------------------------------For-Bar------------------------->

        //<---------factors---------------------->
        List<String> factors = new ArrayList<>();
        for (UserAnswers x : listAnswers) {
            factors.add(x.getRisk());
        }
        factors.removeIf(Objects::isNull);

        int age = 0, citizenship = 0, registration = 0, housing = 0, sp = 0, army = 0,
                education = 0, kindOfActivity = 0, skillLevel = 0, experience = 0,
                credit = 0, flat = 0;

        for (String risk_factor : factors) {
            if (risk_factor.equals(Risk.AGE.getRisk_factor())) {
                age++;
            }
            if (risk_factor.equals(Risk.CITIZENSHIP.getRisk_factor())) {
                citizenship++;
            }
            if (risk_factor.equals(Risk.REGISTRATION.getRisk_factor())) {
                registration++;
            }
            if (risk_factor.equals(Risk.HOUSING.getRisk_factor())){
                housing++;
            }
            if (risk_factor.equals(Risk.SP.getRisk_factor())) {
                sp++;
            }
            if (risk_factor.equals(Risk.ARMY.getRisk_factor())) {
                army++;
            }

            if (risk_factor.equals(Risk.EDUCATION.getRisk_factor())) {
                education++;
            }

            if (risk_factor.equals(Risk.KIND_OF_ACTIVITY.getRisk_factor())) {
                kindOfActivity++;
            }

            if (risk_factor.equals(Risk.SKILL_LEVEL.getRisk_factor())) {
                skillLevel++;
            }

            if (risk_factor.equals(Risk.EXPERIENCE.getRisk_factor())) {
                experience++;
            }

            if (risk_factor.equals(Risk.CREDIT.getRisk_factor())) {
                credit++;
            }

            if (risk_factor.equals(Risk.FLAT.getRisk_factor())) {
                flat++;
            }
        }
        Map<String, Integer> attr = new HashMap<>();
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
}

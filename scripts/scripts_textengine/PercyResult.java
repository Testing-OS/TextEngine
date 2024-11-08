package scripts_textengine;

import scripts_techniques.Config;
import scripts_techniques.Selenium.Teststep;


import static scripts_textengine.MainPercy2.WebPage_getResult;
import static scripts_textengine.TextEngine.selfdriver;

public class PercyResult {
    public static void main(String[] args) {
        getPercyResult(Config.PercyToken);
    }
    public static void getPercyResult(String token){
        TextEngine.nom = TextEngine.nomparcours("Resultat_Percy");

        Teststep t = new Teststep("getResult", "getResult" ,"getResult.htm", "Resultat_Percy", token);
        Config.compteur_instance = 2;

        WebPage_getResult(selfdriver,t);
        Config.compteur_instance = 1;

    }
}

package scripts_textengine;

import java.io.IOException;

public class MainDesktop {
    public static void main(String[] args) throws IOException, InterruptedException {
        calc();
        //blocnote();
        //actionbytextCalc();

    }

    public static void calc() throws IOException, InterruptedException {
        TextEngine.initDesktop("Microsoft.WindowsCalculator_8wekyb3d8bbwe!App", "Calculatrice");
        TextEngine.clickbyxpath("//*[@ClassName='ApplicationFrameWindow'][@Name='Calculatrice']/Window[@ClassName='Windows.UI.Core.CoreWindow'][@Name='Calculatrice']/Custom[@AutomationId='NavView']/Group[@ClassName='LandmarkTarget']/Group[@Name='Pavé numérique'][@AutomationId='NumberPad']/Button[@Name='Sept'][@AutomationId='num7Button']");
        TextEngine.clickbyAutomationID("num4Button");
        TextEngine.clickbyname("Plus");
        TextEngine.clickbytext("5");
        TextEngine.clickbyname("Est égal à");
    }
    public static void actionbytextCalc() throws IOException, InterruptedException {
        TextEngine.initDesktop("Microsoft.WindowsCalculator_8wekyb3d8bbwe!App", "Calculatrice 2");
        TextEngine.clickbytext("9|Pavé numérique");
        TextEngine.clickbytext("plus");
        TextEngine.clickbyindex("2", "2");
        TextEngine.clickbytext("égal");
        TextEngine.clickbytext("Ouvrir Navigation");
        TextEngine.clickbytext("Scientifique");

    }
    public static void blocnote() throws IOException, InterruptedException {
        TextEngine.initDesktop("C:\\WINDOWS\\system32\\notepad.exe", "bloc note");
        TextEngine.clickbyname("Fichier");
        TextEngine.clickbyAutomationID("4");
        TextEngine.sendkeysbyxpath("//*[@ClassName='ToolbarWindow32']", "C:\\javaproject\\TextEnginewithWAD");
        TextEngine.sendkeysbytext("Nom du fichier :", "WAD");
        TextEngine.clickbyname("Enregistrer");

    }
}

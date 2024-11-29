package scripts_textengine.TNR;

import java.io.IOException;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import scripts_techniques.Config;

import static scripts_textengine.TextEngine.*;
public class Main {
	static String url = "http://www.test.kalios-saas.com/automationpractice/index.html";
	@BeforeEach
	public void setUp() {
		sa = new SoftAssertions();
	}
	
	@AfterEach
	public void assertAlll(){
	        sa.assertAll();
	}

	@Test
	public void clickbytext_element_button_avec_texte() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_element_button_avec_texte");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 1");
		close("");
	}
	
	@Test
	public void clickbytext_element_button_avec_value() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_element_button_avec_value");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 2");
		close("");
	}
	
	@Test
	public void clickbytext_element_button_avec_title() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_element_button_avec_title");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 3");
		close("");
	}
	
	@Test
	public void clickbytext_element_button_avec_aria_label() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_element_button_avec_texte");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 4");
		close("");
	}
	
	@Test
	public void clickbytext_element_button_avec_alt() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_element_button_avec_alt");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 5");
		close("");
	}
	
	@Test
	public void clickbytext_element_button_avec_partie_du_texte() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_element_button_avec_partie_du_texte");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 6");
		close("");
	}
	
	@Test
	public void clickbytext_element_a_suivi_dun_href() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_element_a_suivi_dun_href");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 7");
		close("");
	}
	
	@Test
	public void clickbytext_element_input_type_button() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_element_input_type_button");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 8");
		close("");
	}
	
	@Test
	public void clickbytext_element_input_type_submit() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_element_input_type_button");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 9");
		close("");
	}
	
	@Test
	public void clickbytext_parent_button_avec_text() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_parent_button_avec_text");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 11");
		close("");
	}

	@Test
	public void clickbytext_parent_button_avec_title() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_parent_button_avec_title");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Button 12");
		close("");
	}
	
	@Test
	public void clickbytext_parent_button_avec_value() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_parent_button_avec_value");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 13");
		close("");
	}
	
	@Test
	public void clickbytext_element_clickable_avec_text() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_element_clickable_avec_text");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 14");
		close("");
	}
	
	@Test
	public void clickbytext_element_clickable_avec_title() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_element_clickable_avec_title");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 15");
		close("");
	}
	
	@Test
	public void clickbytext_element_clickable_avec_value() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_element_clickable_avec_value");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 16");
		close("");
	}
	
	@Test
	public void clickbytext_test_avec_context_a_gauche_1() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_test_avec_context_a_gauche_1");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Réservation|Billets un jour");
		close("");
	}
	
	@Test
	public void clickbytext_test_avec_context_a_gauche_2() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_test_avec_context_a_gauche_2");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Réservation|Billets une semaine");
		close("");
	}
	
	@Test
	public void clickbytext_test_avec_context_a_gauche_3() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_test_avec_context_a_gauche_3");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Réservation|Billets un mois");
		close("");
	}
	
	@Test
	public void clickbytext_test_avec_context_au_dessus_1() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_test_avec_context_au_dessus_1");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Click|Context1");
		close("");
	}
	
	@Test
	public void clickbytext_test_avec_context_au_dessus_2() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_test_avec_context_au_dessus_2");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Click|Context2");
		close("");
	}
	
	@Test
	public void clickbytext_test_avec_context_au_dessus_3() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_test_avec_context_au_dessus_3");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Click|Context3");
		close("");
	}
	
	@Test
	public void clickbytext_test_avec_context_et_propriete_au_meme_niveau() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_test_avec_context_et_propriete_au_meme_niveau");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Appuie|Contextbis");
		close("");
	}
	
	@Test
	public void clickbytext_test_avec_context_et_propriete_au_meme_niveau_2() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_test_avec_context_et_propriete_au_meme_niveau_2");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Appuie|Contextter");
		close("");
	}
	
	@Test
	public void clickbytext_texte_minuscule() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_texte_minuscule");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 20");
		close("");
	}
	
	@Test
	public void clickbytext_texte_majuscule() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_texte_majuscule");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 21");
		close("");
	}
	
	@Test
	public void clickbytext_texte_qui_alterne_maj_min() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_texte_qui_alterne_maj_min");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 22");
		close("");
	}
	
	@Test
	public void clickbytext_moitie_des_lettres_maj() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_moitie_des_lettres_maj");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 23");
		close("");
	}
	
	@Test
	public void clickbytext_texte_maj_avec_une_lettre_min() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_texte_maj_avec_une_lettre_min");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 24");
		close("");
	}
	
	@Test
	public void clickbytext_deux_espaces() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_deux_espaces");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 25");
		close("");
	}
	
	@Test
	public void clickbytext_plusieurs_espaces() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_plusieurs_espaces");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 26");
		close("");
	}
	
	@Test
	public void clickbytext_espace_au_milieu_du_mot() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_espace_au_milieu_du_mot");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 27");
		close("");
	}
	
	@Test
	public void clickbytext_plusieurs_espaces_dans_le_mot() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_plusieurs_espaces_dans_le_mot");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 28");
		close("");
	}
	
	@Test
	public void clickbytext_espaces_a_plusieurs_endroits_du_mot() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_espaces_a_plusieurs_endroits_du_mot");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 29");
		close("");
	}
	
	@Test
	public void clickbytext_espaces_avant_et_apres() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_espaces_avant_et_apres");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 30");
		close("");
	}
	
	@Test
	public void clickbytext_texte_sans_espace() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_texte_sans_espace");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 31");
		close("");
	}
	
	@Test
	public void clickbytext_texte_minuscule_avec_espaces() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_texte_minuscule_avec_espaces");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 32");
		close("");
	}
	
	@Test
	public void clickbytext_texte_majuscule_avec_espaces() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_texte_majuscule_avec_espaces");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 33");
		close("");
	}
	
	@Test
	public void clickbytext_texte_minuscule_avec_espaces_aux_extremites() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_texte_minuscule_avec_espaces_aux_extremites");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 34");
		close("");
	}
	
	@Test
	public void clickbytext_texte_majuscule_avec_espaces_aux_extremites() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_texte_majuscule_avec_espaces_aux_extremites");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 35");
		close("");
	}

	@Test
	public void clickbytext_majuscules_minuscules_espaces() throws IOException, InterruptedException {
		initWeb(url, "clickbytext_majuscules_minuscules_espaces");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 36");
		close("");
	}
	
	@Test
	public void checkbytext_texte_minuscule() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_texte_minuscule");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 20", "check 20");
		close("");
	}
	
	@Test
	public void checkbytext_texte_majuscule() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_texte_majuscule");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 21", "CHECK 21");
		close("");
	}
	
	@Test
	public void checkbytext_texte_qui_alterne_maj_min() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_texte_qui_alterne_maj_min");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 22", "ChEcK 22");
		close("");
	}
	
	@Test
	public void checkbytext_moitie_des_lettres_maj() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_moitie_des_lettres_maj");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 23", "CHeCK 23");
		close("");
	}
	
	@Test
	public void checkbytext_texte_maj_avec_une_lettre_min() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_texte_maj_avec_une_lettre_min");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 24", "cHECK 24");
		close("");
	}
	
	@Test
	public void checkbytext_deux_espaces() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_deux_espaces");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 25", "Check  25");
		close("");
	}
	
	@Test
	public void checkbytext_plusieurs_espaces() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_plusieurs_espaces");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 26", "Check    26");
		close("");
	}
	
	@Test
	public void checkbytext_espace_au_milieu_du_mot() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_espace_au_milieu_du_mot");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 27", "Che ck 27");
		close("");
	}
	
	@Test
	public void checkbytext_plusieurs_espaces_dans_le_mot() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_texte_minuscule");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 28", "Ch  eck 28");
		close("");
	}
	
	@Test
	public void checkbytext_espaces_a_plusieurs_endroits_du_mot() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_espaces_a_plusieurs_endroits_du_mot");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 29", "Ch ec k 29");
		close("");
	}
	
	@Test
	public void checkbytext_espaces_avant_et_apres() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_espaces_avant_et_apres");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 30", "   Check 30   ");
		close("");
	}
	
	@Test
	public void checkbytext_texte_sans_espace() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_texte_sans_espace");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 31", "Check31");
		close("");
	}
	
	@Test
	public void checkbytext_texte_minuscule_avec_espaces() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_texte_minuscule_avec_espaces");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 32", "c he ck   32");
		close("");
	}
	
	@Test
	public void checkbytext_texte_majuscule_avec_espaces() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_texte_majuscule_avec_espaces");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 33", "CH  E CK   33");
		close("");
	}
	
	@Test
	public void checkbytext_texte_minuscule_avec_espaces_aux_extremites() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_texte_minuscule_avec_espaces_aux_extremites");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 34", "   check 34   ");
		close("");
	}
	
	@Test
	public void checkbytext_texte_majuscule_avec_espaces_aux_extremites() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_texte_majuscule_avec_espaces_aux_extremites");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 35", "   CHECK 35    ");
		close("");
	}
	
	@Test
	public void checkbytext_majuscules_minuscules_espaces() throws IOException, InterruptedException {
		initWeb(url, "checkbytext_majuscules_minuscules_espaces");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 36", " CH   e cK 36  ");
		close("");
	}
	
	@Test
	public void checkCSV() throws IOException, InterruptedException {
		initWeb(url, "checkcsv");
		checkcsv("", "JournaldesVentes.csv|" + Config.additional_files + "/JournalDesVentes1.csv");
		close("");
	}
	
	@Test
	public void checkPDF() throws IOException, InterruptedException {
		initWeb(url, "checkpdf");
		checkpdf("", Config.additional_files + "/facturepdf.pdf|facturetxt.txt");
		close("");
	}

	@Test
	public void executerBAT() throws IOException, InterruptedException {
		initWeb(url, "executerbat");
		executer_bat("", "fichiercommand.bat");
		close("");
	}

	@Test
	public void waitLoadingComplete() throws IOException, InterruptedException {
		initWeb(url, "waitloadincomplete");
		waitloadingcomplete("Automation practice", "");
		close("");
	}
	
	@Test
	public void checkInnerText() throws IOException, InterruptedException {
		initWeb(url, "checkinnertext");
		checkinnertext("Automation practice", "WELCOME TO AUTOMATION PRACTICE");
		close("");
	}
	
	@Test
	public void activateFrame() throws IOException, InterruptedException {
		initWeb(url, "activateframe");
		selectbytext("Select Component", "ActivateFrame");
		activateframe("", "0");
		clickbytext("bouton");
		desactivateframe("", "");
		clickbytext("Click me");
		close("");
	}
	
	@Test
	public void activateTab() throws IOException, InterruptedException {
		initWeb(url, "activatetab");
		selectbytext("Select Component", "ActivateTab");
		activatetab("", "Activate Tab");
		clickbytext("bouton");
		sendkeysbytext("input", "test");
		close("");
	}
	
	@Test
	public void checkById() throws IOException, InterruptedException {
		initWeb(url, "checkbyid");
		selectbytext("Select Component", "CheckByIdNameXpath");
		checkbyid("checkid", "Prénom");
		close("");
	}
	
	@Test
	public void checkByName() throws IOException, InterruptedException {
		initWeb(url, "checkbyname");
		selectbytext("Select Component", "CheckByIdNameXpath");
		checkbyname("checkname", "Nom");
		close("");
	}
	
	@Test
	public void checkByXpath() throws IOException, InterruptedException {
		initWeb(url, "checkbyxpath");
		selectbytext("Select Component", "CheckByIdNameXpath");
		checkbyxpath("//*[@id='colonnexpath']//li/input", "E-mail");
		close("");
	}
	
	@Test
	public void clickbyid_bouton() throws IOException, InterruptedException {
		initWeb(url, "clickbyid_bouton");
		selectbytext("Select Component", "ClickByIdNameXpath");
		clickbyid("boutonid");
		close("");
	}
	
	@Test
	public void clickbyname_bouton() throws IOException, InterruptedException {
		initWeb(url, "clickbyname_bouton");
		selectbytext("Select Component", "ClickByIdNameXpath");
		clickbyname("boutonname");
		close("");
	}
	
	@Test
	public void clickbyxpath_bouton() throws IOException, InterruptedException {
		initWeb(url, "clickbyxpath_bouton");
		selectbytext("Select Component", "ClickByIdNameXpath");
		clickbyxpath("//*[@id=\"colonnexpath\"]/div/li[1]/span/button");
		close("");
	}
	
	@Test
	public void sendkeysById() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbyid");
		selectbytext("Select Component", "SendKeysByIdNameXpath");
		sendkeysbyid("inputtextid", "test");
		close("");
	}
	
	@Test
	public void sendkeysByName() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbyname");
		selectbytext("Select Component", "SendKeysByIdNameXpath");
		sendkeysbyname("inputtextname", "test");
		close("");
	}
	
	@Test
	public void sendkeysByXpath() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbyxpath");
		selectbytext("Select Component", "SendKeysByIdNameXpath");
		sendkeysbyxpath("//*[@id=\"colonnexpath\"]/div/li/label/input", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_texte_minuscule() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_texte_minuscule");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 30", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_texte_majuscule() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_texte_majuscule");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 31", "test");
		close("");
	}

	@Test
	public void sendkeysbytext_texte_qui_alterne_maj_min() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_texte_qui_alterne_maj_min");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 32", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_moitie_des_lettres_maj() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_moitie_des_lettres_maj");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 33", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_texte_maj_avec_une_lettre_min() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_texte_maj_avec_une_lettre_min");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 34", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_deux_espaces() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_deux_espaces");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 35", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_plusieurs_espaces() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_plusieurs_espaces");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 36", "test");
		close("");
	}

	@Test
	public void sendkeysbytext_espace_au_milieu_du_mot() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_espace_au_milieu_du_mot");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 37", "test");
		close("");
	}

	@Test
	public void sendkeysbytext_plusieurs_espaces_dans_le_mot() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_texte_minuscule");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 38", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_espaces_a_plusieurs_endroits_du_mot() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_espaces_a_plusieurs_endroits_du_mot");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 39", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_espaces_avant_et_apres() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_espaces_avant_et_apres");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 40", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_texte_sans_espace() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_texte_sans_espace");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 41", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_texte_minuscule_avec_espaces() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_texte_minuscule_avec_espaces");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 42", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_texte_majuscule_avec_espaces() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_texte_majuscule_avec_espaces");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 43", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_texte_minuscule_avec_espaces_aux_extremites() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_texte_minuscule_avec_espaces_aux_extremites");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 44", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_texte_majuscule_avec_espaces_aux_extremites() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_texte_majuscule_avec_espaces_aux_extremites");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 45", "test");
		close("");
	}
	
	@Test
	public void sendkeysbytext_majuscules_minuscules_espaces() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbytext_majuscules_minuscules_espaces");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("Sendkey 46", "test");
		close("");
	}
	
	@Test
	public void clickTable() throws IOException, InterruptedException {
		initWeb(url, "clicktable");
		selectbytext("Select Component", "Table");
		clicktable("Table", "Action|SEO tags");
		close("");
	}
	
	@Test
	public void sendKeysTable() throws IOException, InterruptedException {
		initWeb(url, "sendkeystable");
		selectbytext("Select Component", "Table");
		sendkeystable("Table", "Quantity|Holden Charles|5");
		close("");
	}
	
	@Test
	public void checkTable() throws IOException, InterruptedException {
		initWeb(url, "checktable");
		selectbytext("Select Component", "Table");
		checktable("Table", "Quantity|Holden Charles|0");
		close("");
	}
	@Test
	public void Azure1TestCase() throws IOException, InterruptedException {
		initWeb(url, "Azure1TestCase");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Valider");
		close("");
		UploadInAzureDevOps("Azure1");
	}
	@Test
	public void Azure2TestCase() throws IOException, InterruptedException {
		initWeb(url, "Azure1TestCase");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Valider");
		close("");
		initWeb(url, "Azure2TestCase");
		selectbytext("Select Component", "SendKeysByText");
		sendkeysbytext("sendkey2", "kalios");
		close("");
		UploadInAzureDevOps("Azure2");
	}

}

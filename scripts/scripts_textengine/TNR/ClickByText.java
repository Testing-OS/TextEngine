package scripts_textengine.TNR;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import static scripts_textengine.TextEngine.*;

public class ClickByText {
	static String url = "http://www.test.kalios-saas.com/automationpractice/index.html";
	public static void main(String[] args) throws IOException, InterruptedException {
		ClickByText clickByText = new ClickByText();
		clickByText.clickByText();
	}
	@Test
	public void clickByText() throws IOException, InterruptedException {
		element_button_avec_texte();
		ChangeParcours("element_button_avec_value");
		element_button_avec_value();
		ChangeParcours("element_button_avec_title");
		element_button_avec_title();
		ChangeParcours("element_button_avec_aria_label");
		element_button_avec_aria_label();
		ChangeParcours("element_button_avec_alt");
		element_button_avec_alt();
		ChangeParcours("element_button_avec_partie_du_texte");
		element_button_avec_partie_du_texte();
		ChangeParcours("element_a_suivi_dun_href");
		element_a_suivi_dun_href();
		ChangeParcours("element_input_type_button");
		element_input_type_button();
		ChangeParcours("element_input_type_submit");
		element_input_type_submit();
		ChangeParcours("parent_button_avec_text");
		parent_button_avec_text();
		ChangeParcours("parent_button_avec_title");
		parent_button_avec_title();
		ChangeParcours("parent_button_avec_value");
		parent_button_avec_value();
		ChangeParcours("element_clickable_avec_text");
		element_clickable_avec_text();
		ChangeParcours("element_clickable_avec_title");
		element_clickable_avec_title();
		ChangeParcours("element_clickable_avec_value");
		element_clickable_avec_value();
		ChangeParcours("test_avec_context_a_gauche_1");
		test_avec_context_a_gauche_1();
		ChangeParcours("test_avec_context_a_gauche_2");
		test_avec_context_a_gauche_2();
		ChangeParcours("test_avec_context_a_gauche_3");
		test_avec_context_a_gauche_3();
		ChangeParcours("test_avec_context_au_dessus_1");
		test_avec_context_au_dessus_1();
		ChangeParcours("test_avec_context_au_dessus_2");
		test_avec_context_au_dessus_2();
		ChangeParcours("test_avec_context_au_dessus_3");
		test_avec_context_au_dessus_3();
		ChangeParcours("test_avec_context_et_propriete_au_meme_niveau");
		test_avec_context_et_propriete_au_meme_niveau();
		ChangeParcours("test_avec_context_et_propriete_au_meme_niveau_2");
		test_avec_context_et_propriete_au_meme_niveau_2();
		ChangeParcours("texte_minuscule");
		texte_minuscule();
		ChangeParcours("texte_majuscule");
		texte_majuscule();
		ChangeParcours("texte_qui_alterne_maj_min");
		texte_qui_alterne_maj_min();
		ChangeParcours("moitie_des_lettres_maj");
		moitie_des_lettres_maj();
		ChangeParcours("texte_maj_avec_une_lettre_min");
		texte_maj_avec_une_lettre_min();
		ChangeParcours("deux_espaces");
		deux_espaces();
		ChangeParcours("plusieurs_espaces");
		plusieurs_espaces();
		ChangeParcours("espace_au_milieu_du_mot");
		espace_au_milieu_du_mot();
		ChangeParcours("plusieurs_espaces_dans_le_mot");
		plusieurs_espaces_dans_le_mot();
		ChangeParcours("espaces_a_plusieurs_endroits_du_mot");
		espaces_a_plusieurs_endroits_du_mot();
		ChangeParcours("espaces_avant_et_apres");
		espaces_avant_et_apres();
		ChangeParcours("texte_sans_espace");
		texte_sans_espace();
		ChangeParcours("texte_minuscule_avec_espaces");
		texte_minuscule_avec_espaces();
		ChangeParcours("texte_majuscule_avec_espaces");
		texte_majuscule_avec_espaces();
		ChangeParcours("texte_minuscule_avec_espaces_aux_extremites");
		texte_minuscule_avec_espaces_aux_extremites();
		ChangeParcours("texte_majuscule_avec_espaces_aux_extremites");
		texte_majuscule_avec_espaces_aux_extremites();
		ChangeParcours("majuscules_minuscules_espaces");
		majuscules_minuscules_espaces();
		sa.assertAll();
	}
	
	public static void element_button_avec_texte() throws IOException, InterruptedException {
		initWeb(url, "element_button_avec_texte");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 1");
		close("");
	}
	
	public static void element_button_avec_value() throws IOException, InterruptedException {
		initWeb(url, "element_button_avec_value");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 2");
		close("");
	}
	
	public static void element_button_avec_title() throws IOException, InterruptedException {
		initWeb(url, "element_button_avec_title");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 3");
		close("");
	}
	
	public static void element_button_avec_aria_label() throws IOException, InterruptedException {
		initWeb(url, "element_button_avec_texte");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 4");
		close("");
	}
	
	public static void element_button_avec_alt() throws IOException, InterruptedException {
		initWeb(url, "element_button_avec_alt");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 5");
		close("");
	}
	
	public static void element_button_avec_partie_du_texte() throws IOException, InterruptedException {
		initWeb(url, "element_button_avec_partie_du_texte");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 6");
		close("");
	}
	
	public static void element_a_suivi_dun_href() throws IOException, InterruptedException {
		initWeb(url, "element_a_suivi_dun_href");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 7");
		close("");
	}
	
	public static void element_input_type_button() throws IOException, InterruptedException {
		initWeb(url, "element_input_type_button");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 8");
		close("");
	}
	
	public static void element_input_type_submit() throws IOException, InterruptedException {
		initWeb(url, "element_input_type_button");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 9");
		close("");
	}
	
	public static void parent_button_avec_text() throws IOException, InterruptedException {
		initWeb(url, "parent_button_avec_text");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 11");
		close("");
	}
	
	public static void parent_button_avec_title() throws IOException, InterruptedException {
		initWeb(url, "parent_button_avec_title");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Button 12");
		close("");
	}
	
	public static void parent_button_avec_value() throws IOException, InterruptedException {
		initWeb(url, "parent_button_avec_value");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 13");
		close("");
	}
	
	public static void element_clickable_avec_text() throws IOException, InterruptedException {
		initWeb(url, "element_clickable_avec_text");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 14");
		close("");
	}
	
	public static void element_clickable_avec_title() throws IOException, InterruptedException {
		initWeb(url, "element_clickable_avec_title");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 15");
		close("");
	}
	
	public static void element_clickable_avec_value() throws IOException, InterruptedException {
		initWeb(url, "element_clickable_avec_value");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 16");
		close("");
	}
	
	public static void test_avec_context_a_gauche_1() throws IOException, InterruptedException {
		initWeb(url, "test_avec_context_a_gauche_1");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Réservation|Billets un jour");
		close("");
	}
	
	public static void test_avec_context_a_gauche_2() throws IOException, InterruptedException {
		initWeb(url, "test_avec_context_a_gauche_2");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Réservation|Billets une semaine");
		close("");
	}
	
	public static void test_avec_context_a_gauche_3() throws IOException, InterruptedException {
		initWeb(url, "test_avec_context_a_gauche_3");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Réservation|Billets un mois");
		close("");
	}
	
	public static void test_avec_context_au_dessus_1() throws IOException, InterruptedException {
		initWeb(url, "test_avec_context_au_dessus_1");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Click|Context1");
		close("");
	}
	
	public static void test_avec_context_au_dessus_2() throws IOException, InterruptedException {
		initWeb(url, "test_avec_context_au_dessus_2");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Click|Context2");
		close("");
	}
	
	public static void test_avec_context_au_dessus_3() throws IOException, InterruptedException {
		initWeb(url, "test_avec_context_au_dessus_3");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Click|Context3");
		close("");
	}
	
	public static void test_avec_context_et_propriete_au_meme_niveau() throws IOException, InterruptedException {
		initWeb(url, "test_avec_context_et_propriete_au_meme_niveau");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Appuie|Contextbis");
		close("");
	}
	
	public static void test_avec_context_et_propriete_au_meme_niveau_2() throws IOException, InterruptedException {
		initWeb(url, "test_avec_context_et_propriete_au_meme_niveau_2");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Appuie|Contextter");
		close("");
	}
	
	public static void texte_minuscule() throws IOException, InterruptedException {
		initWeb(url, "texte_minuscule");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 20");
		close("");
	}
	
	public static void texte_majuscule() throws IOException, InterruptedException {
		initWeb(url, "texte_majuscule");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 21");
		close("");
	}
	
	public static void texte_qui_alterne_maj_min() throws IOException, InterruptedException {
		initWeb(url, "texte_qui_alterne_maj_min");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 22");
		close("");
	}
	
	public static void moitie_des_lettres_maj() throws IOException, InterruptedException {
		initWeb(url, "moitie_des_lettres_maj");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 23");
		close("");
	}
	
	public static void texte_maj_avec_une_lettre_min() throws IOException, InterruptedException {
		initWeb(url, "texte_maj_avec_une_lettre_min");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 24");
		close("");
	}
	
	public static void deux_espaces() throws IOException, InterruptedException {
		initWeb(url, "deux_espaces");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 25");
		close("");
	}
	
	public static void plusieurs_espaces() throws IOException, InterruptedException {
		initWeb(url, "plusieurs_espaces");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 26");
		close("");
	}
	
	public static void espace_au_milieu_du_mot() throws IOException, InterruptedException {
		initWeb(url, "espace_au_milieu_du_mot");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 27");
		close("");
	}
	
	public static void plusieurs_espaces_dans_le_mot() throws IOException, InterruptedException {
		initWeb(url, "texte_minuscule");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 28");
		close("");
	}
	
	public static void espaces_a_plusieurs_endroits_du_mot() throws IOException, InterruptedException {
		initWeb(url, "espaces_a_plusieurs_endroits_du_mot");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 29");
		close("");
	}
	
	public static void espaces_avant_et_apres() throws IOException, InterruptedException {
		initWeb(url, "espaces_avant_et_apres");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 30");
		close("");
	}
	
	public static void texte_sans_espace() throws IOException, InterruptedException {
		initWeb(url, "texte_sans_espace");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 31");
		close("");
	}
	
	public static void texte_minuscule_avec_espaces() throws IOException, InterruptedException {
		initWeb(url, "texte_minuscule_avec_espaces");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 32");
		close("");
	}
	
	public static void texte_majuscule_avec_espaces() throws IOException, InterruptedException {
		initWeb(url, "texte_majuscule_avec_espaces");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 33");
		close("");
	}
	
	public static void texte_minuscule_avec_espaces_aux_extremites() throws IOException, InterruptedException {
		initWeb(url, "texte_minuscule_avec_espaces_aux_extremites");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 34");
		close("");
	}
	
	public static void texte_majuscule_avec_espaces_aux_extremites() throws IOException, InterruptedException {
		initWeb(url, "texte_majuscule_avec_espaces_aux_extremites");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 35");
		close("");
	}
	
	public static void majuscules_minuscules_espaces() throws IOException, InterruptedException {
		initWeb(url, "majuscules_minuscules_espaces");
		selectbytext("Select Component", "ClickByText");
		clickbytext("Bouton 36");
		close("");
	}
}

package scripts_textengine.TNR;

import static scripts_textengine.TextEngine.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class CheckByText {
	static String url = "http://www.test.kalios-saas.com/automationpractice/index.html";

	public static void main(String[] args) throws IOException, InterruptedException {
		CheckByText checkByText = new CheckByText();
		checkByText.checkByText();
	}
	@Test
	public void checkByText() throws IOException, InterruptedException {
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
	public static void texte_minuscule() throws IOException, InterruptedException {
		initWeb(url, "texte_minuscule");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 20", "check 20");
		close("");
	}
	
	public static void texte_majuscule() throws IOException, InterruptedException {
		initWeb(url, "texte_majuscule");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 21", "CHECK 21");
		close("");
	}
	
	public static void texte_qui_alterne_maj_min() throws IOException, InterruptedException {
		initWeb(url, "texte_qui_alterne_maj_min");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 22", "ChEcK 22");
		close("");
	}
	
	public static void moitie_des_lettres_maj() throws IOException, InterruptedException {
		initWeb(url, "moitie_des_lettres_maj");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 23", "CHeCK 23");
		close("");
	}
	
	public static void texte_maj_avec_une_lettre_min() throws IOException, InterruptedException {
		initWeb(url, "texte_maj_avec_une_lettre_min");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 24", "cHECK 24");
		close("");
	}
	
	public static void deux_espaces() throws IOException, InterruptedException {
		initWeb(url, "deux_espaces");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 25", "Check  25");
		close("");
	}
	
	public static void plusieurs_espaces() throws IOException, InterruptedException {
		initWeb(url, "plusieurs_espaces");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 26", "Check    26");
		close("");
	}
	
	public static void espace_au_milieu_du_mot() throws IOException, InterruptedException {
		initWeb(url, "espace_au_milieu_du_mot");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 27", "Che ck 27");
		close("");
	}
	
	public static void plusieurs_espaces_dans_le_mot() throws IOException, InterruptedException {
		initWeb(url, "texte_minuscule");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 28", "Ch  eck 28");
		close("");
	}
	
	public static void espaces_a_plusieurs_endroits_du_mot() throws IOException, InterruptedException {
		initWeb(url, "espaces_a_plusieurs_endroits_du_mot");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 29", "Ch ec k 29");
		close("");
	}
	
	public static void espaces_avant_et_apres() throws IOException, InterruptedException {
		initWeb(url, "espaces_avant_et_apres");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 30", "   Check 30   ");
		close("");
	}
	
	public static void texte_sans_espace() throws IOException, InterruptedException {
		initWeb(url, "texte_sans_espace");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 31", "Check31");
		close("");
	}
	
	public static void texte_minuscule_avec_espaces() throws IOException, InterruptedException {
		initWeb(url, "texte_minuscule_avec_espaces");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 32", "c he ck   32");
		close("");
	}
	
	public static void texte_majuscule_avec_espaces() throws IOException, InterruptedException {
		initWeb(url, "texte_majuscule_avec_espaces");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 33", "CH  E CK   33");
		close("");
	}
	
	public static void texte_minuscule_avec_espaces_aux_extremites() throws IOException, InterruptedException {
		initWeb(url, "texte_minuscule_avec_espaces_aux_extremites");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 34", "   check 34   ");
		close("");
	}
	
	public static void texte_majuscule_avec_espaces_aux_extremites() throws IOException, InterruptedException {
		initWeb(url, "texte_majuscule_avec_espaces_aux_extremites");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 35", "   CHECK 35    ");
		close("");
	}
	
	public static void majuscules_minuscules_espaces() throws IOException, InterruptedException {
		initWeb(url, "majuscules_minuscules_espaces");
		selectbytext("Select Component", "CheckByText");
		checkbytext("Check 36", " CH   e cK 36  ");
		close("");
	}
}

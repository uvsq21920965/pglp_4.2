package fr.uvsq21920965.pglp42.specificCommande;
/**
 * Addition Classe.
 * @author Sarra Belmahdi.
 *
 */
public class Addition implements  SpecificCommand {

  /**
   * Constructeur.
   */
  public Addition() {
	}

  /**
   * methode pour aditionner deux entiers.
   */
  public int apply(int a, int b) {
    return a+b;
  }
}
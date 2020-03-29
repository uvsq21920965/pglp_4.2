package fr.uvsq21920965.pglp42;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import fr.uvsq21920965.pglp42.genericCommand.GenericCommand;
import fr.uvsq21920965.pglp42.genericCommand.Quit;
import fr.uvsq21920965.pglp42.genericCommand.Undo;

/**
 * Interpreteur Class pour traiter generiques Commands.
 * @author Sarra Belmahdi.
 */
public class Interpreteur {
  /**
   * pile pour empiler les operandes.
   */
  private Stack<Integer> operandesPile;

  /**
   * pile pour empiler l'historiques des piles.
   */
  private Stack<Stack<Integer>> allPiles;

  /**
   * map pour enregistrer les geniriques commandes.
   */
  private Map<String, GenericCommand> gCommands;

  /**
   * Constructeur.
   * @param operandesPileAtt pile pour empiler les operandes.
   */
  public Interpreteur(final Stack<Integer> operandesPileAtt) {
    operandesPile = operandesPileAtt;
    allPiles = new Stack<Stack<Integer>>();
    gCommands = new HashMap<String, GenericCommand>();
    gCommands.put("exit", new Quit());
    gCommands.put("undo", new Undo(operandesPile, allPiles));
  }

  /**
   * methode pour acceder à la pile actuelle.
   * @return la pile des operandes.
   */
  public Stack<Integer> getOperandesPile() {
    return operandesPile;
  }

  /**
   * methode pour executer les generiques commandes selon l'operation.
   * @param operation le nom de la commande genirique.
   */
  public void interprete(final String operation) {
    GenericCommand gc = gCommands.get(operation);
    gc.apply();
  }

  /**
   * methode pour stocker une nouvelle pile dans l'historique.
   * @param operandesPileA la pile à stocker dans l'historique.
   */
  public  void  storeLastOperation(final Stack<Integer> operandesPileA) {
    Stack<Integer> operandesPileCopie = new Stack<Integer>();
    for (int i :operandesPileA) {
      operandesPileCopie.push(i);
    }
    allPiles.push(operandesPileCopie);
  }
}

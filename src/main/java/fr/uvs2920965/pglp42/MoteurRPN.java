package fr.uvs2920965.pglp42;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import fr.uvs2920965.pglp42.specificCommande.Addition;
import fr.uvs2920965.pglp42.specificCommande.Division;
import fr.uvs2920965.pglp42.specificCommande.Multiplication;
import fr.uvs2920965.pglp42.specificCommande.Soustraction;
import fr.uvs2920965.pglp42.specificCommande.SpecificCommand;

public class MoteurRPN extends Interpreteur{

  private Stack<Integer> operandesPile;
  private Map<String,SpecificCommand> sCommands;
  
  public MoteurRPN(final Stack<Integer> operandesPileAtt) {
    super(operandesPileAtt);
    operandesPile=operandesPileAtt;
    sCommands=new HashMap<String , SpecificCommand>();
    sCommands.put("+",new Addition());
    sCommands.put("-",new Soustraction());
    sCommands.put("*",new Multiplication());
    sCommands.put("/",new Division());
  }

  public Stack<Integer> getOperandesPile() {
    return operandesPile;
  }
	// afficher le contenu de la pile
	public String getAllOperandes() {
		String all = "[";
		if (!operandesPile.isEmpty()) {
			for (int i =0 ; i<operandesPile.size() ; i++) {
				if(i==0) {
					all += operandesPile.get(i);
				}
				else {
				all += " ,"+operandesPile.get(i);
				}
			}
		}
		return all+"]";
	}
 
  public void stockage(final int a) {
    this.getOperandesPile().push(a);
   this.storeLastOperation(this.getOperandesPile());
  }
  
//appliquer une operation
	public void calcule( String op) throws Exception {
		int operande1, operande2;
		SpecificCommand operation = null;
		if (operandesPile.size() >= 2) {
			operande1 = operandesPile.pop();
			operande2 = operandesPile.pop();
			operation=sCommands.get(op);
			stockage(operation.apply(operande1, operande2));
		} else
			throw new ExpressionException("expression arithmethique non valide");
	}
}

package blocksworld;
import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class Action implements Literal {
    private int number;
    private String name;
    private ArrayList<Condition> preConditions;
    private ArrayList<Condition> postConditions;
    
    public Action (int number, String name) {
        this.number = number;
        this.name = name;
        preConditions = new ArrayList<Condition>();
        postConditions = new ArrayList<Condition>();
    }

    @Override
    public String getIntValue(int level) {
        return (generateLevel(level)+""+"0"+""+number+"");

    }
    
    public int generateLevel(int level) {
        return 100+level;
    }
    
    public void addPreCondition(Condition cond) {
        preConditions.add(cond);
    }
    
    public void addPostCondition(Condition cond) {
        postConditions.add(cond);
    }
    
    public String getName() {
        return name;
    }
    
    public ArrayList<Condition> getPreConditions() {
        return preConditions;
    }
    public ArrayList<Condition> getPostConditions() {
        return postConditions;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public boolean belongsToPreConditions(int cond) {
        for (Condition aux : preConditions) {
            if (aux.getNumber() == cond) {
                return true;
            }
        }
        return false;
    }
    
    public boolean belongsToPostConditions(int cond) {
        for (Condition aux : postConditions) {
            if (aux.getNumber() == cond) {
                return true;
            }
        }
        return false;
    }
    
}

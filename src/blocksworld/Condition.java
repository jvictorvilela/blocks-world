package blocksworld;

/**
 *
 * @author victor
 */
public class Condition implements Literal{
    private int number;
    private String name;
    private boolean value;
    public Condition(int number, String name) {
        this.number = number;
        this.name = name;
        if (this.name.contains("~")) {
            value = false;
            this.name = this.name.replace("~", "");
        } else {
            value = true;
        }
    }
    
    @Override
    public String getIntValue(int level) {
        return (generateLevel(level)+""+"1"+number+"");
    }
    
    public int generateLevel(int level) {
        return 100+level;
    }
    
    public String getName() {
        return name;
    }
    
    public int getNumber() {
        return number;
    }
    
    public boolean getValue() {
        return value;
    }
}

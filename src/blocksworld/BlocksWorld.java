package blocksworld;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class BlocksWorld {
    ArrayList<Action> actions;
    ArrayList<Condition> conditions;
    ArrayList<Condition> initialStates;
    ArrayList<Condition> finalStates;
    ArrayList<Condition> atualConditions;
    public BlocksWorld(ArrayList<Action> actions, ArrayList<Condition> conditions, ArrayList<Condition> initialStates, ArrayList<Condition> finalStates) throws IOException, InterruptedException {
        this.actions = actions;
        this.conditions = conditions;
        this.initialStates = initialStates;
        this.finalStates = finalStates;
        
        int i = 1;
        boolean aux;
        long time = System.currentTimeMillis();
        do {
            aux = solver(generateClauses(i));
            
            if (aux == false) {
                System.out.println("Trying level "+i+" : UNSAT");
            } else {
                System.out.println("Trying level "+i+" : SAT");
                System.out.println();
                time = System.currentTimeMillis() - time;
                printSolution(i);
                printTime(time);
               
            }
                    
            i++;
        } while (!aux);
        
        
    }
    
    private void printTime(long time) {
        int horas = 0;
        int minutos = 0;
        float segundos = 0;
        
        while (time - 3600000 >= 0) {
            time -= 3600000;
            horas++;
        }
        
        while (time - 60000 >= 0) {
            time -= 60000;
            minutos++;
        }
        
        segundos = (float)time/1000;

        System.out.println();
        System.out.println("tempo [h:m:s]: "+horas+":"+minutos+":"+segundos);
    }
    
    private ArrayList<String> generateClauses(int level) throws IOException { // gera as clausulas e coloca dentro de um array de clausulas
        
        ArrayList<String> clauses = new ArrayList<String>();
        
        clauses.add("");
        
        // gerando clausulas das condições iniciais
        for (Condition aux : conditions) {
            if (belongsTo(aux, initialStates)) {
                clauses.add(aux.getIntValue(1)+" 0");
            } else {
                clauses.add("-"+aux.getIntValue(1)+" 0");
            }
        }
        
        // iniciando loop
        for (int i = 1; i < level; i++) {
            // garantindo que apenas uma ação seja feita por level
            String clauseLine = "";
            for (int j = 0; j < actions.size(); j++) { // escrevendo clausula com todas as ações possíveis
                clauseLine += actions.get(j).getIntValue(i+1);
                if (!(j == actions.size()-1)) { // colocando espaços entre os literais 
                    clauseLine += " ";
                } else {
                    clauseLine += " 0";
                }
            }
            clauses.add(clauseLine);

            
            
            // garantindoho que para uma ação ser realizada, suas pre-condições precisam ser verdadeiras
            for (Action aux : actions) {
                for (Condition aux2 : conditions) {
                    // verifica se a condição pertence as pre-condições da ação
                    if (aux.belongsToPreConditions(aux2.getNumber())) {
                        if (aux2.getValue() == false) {
                            clauses.add("-"+aux.getIntValue(i+1)+" -"+aux2.getIntValue(i)+" 0");
                        } else {
                            clauses.add("-"+aux.getIntValue(i+1)+" "+aux2.getIntValue(i)+" 0");
                        }
                    // caso não pertença, cria novas clausulas repetindo as condições anteriores (a -> (c <-> cn-1)))
                    } else {
                        if (!aux.belongsToPostConditions(aux2.getNumber())) {
                            clauses.add("-"+aux.getIntValue(i+1)+" -"+aux2.getIntValue(i+1)+" "+aux2.getIntValue(i)+" 0");
                            clauses.add("-"+aux.getIntValue(i+1)+" -"+aux2.getIntValue(i)+" "+aux2.getIntValue(i+1)+" 0");
                        }
                    }
                }
            }
            
            // garantindo que para uma ação ser realizada, suas pos-condições precisam ser verdadeiras
            for (Action aux : actions) {
                for (Condition aux2 : aux.getPostConditions()) {
                    if (aux2.getValue() == false) {
                        clauses.add("-"+aux.getIntValue(i+1)+" -"+aux2.getIntValue(i+1)+" 0");
                    } else {
                        clauses.add("-"+aux.getIntValue(i+1)+" "+aux2.getIntValue(i+1)+" 0");   
                    }
                }
            }

            //

        }
        
        // percorrendo o array com todas as clausulas finais
        for (Condition aux : finalStates) {
            if (aux.getValue() == false) {
                clauses.add("-"+aux.getIntValue(level)+" 0");
            } else {
                clauses.add(aux.getIntValue(level)+" 0");
            }    
        }
        
        clauses.set(0, "p cnf "+conditions.get(conditions.size()-1).getIntValue(level)+" "+(clauses.size()-1));
        
        // apagar depois
        System.out.println(clauses.size());
        
        return clauses;
        
    }
    
    private boolean solver(ArrayList<String> clauses) throws IOException, InterruptedException {
        FileWriter arqW = new FileWriter("sat");
        PrintWriter write = new PrintWriter(arqW);
        
        for (String aux : clauses) {
            write.printf("%s%n", aux);
        }
        
        write.close();
        arqW.close();
        
        Runtime run = Runtime.getRuntime();
        run.exec("./glucose_static sat sat-out").waitFor();
        
        FileReader arq = new FileReader("sat-out");
        BufferedReader reader = new BufferedReader(arq);
        
        String line = reader.readLine();

        if ("UNSAT".equals(line)) {
            reader.close();
            arq.close();
            return false;
        } else {
            reader.close();
            arq.close();
            return true;
        }
        
    }
    
    private void printSolution(int lvl) throws FileNotFoundException, IOException {
        String[] valuation;
        
        FileReader arq = new FileReader("sat-out");
        BufferedReader reader = new BufferedReader(arq);
        String line = reader.readLine();
        String[] solution = new String[lvl-1];
        
        valuation = line.split(" ");
        
        for (int i = 0; i < valuation.length; i++) {
            if (!valuation[i].contains("-")) {
                String[] num = valuation[i].split("");
                if (!"0".equals(num[0])) {
                    if ("0".equals(num[3])) {
                        String level = num[1]+num[2];
                        String numAction = "";
                        for (int j=4; j < num.length; j++) {
                            numAction += num[j];
                        }
                        solution[Integer.parseInt(level)-2] = getActionName(numAction);
                        //System.out.println(level+"_"+getActionName(numAction));
                    }
                }
            }
        }
        for (int i = 0; i < solution.length; i++) {
            System.out.println(i+1+"_"+solution[i]);
        }
        arq.close();
    }
    
    
    private String getActionName(String number) {
        for (Action aux : actions) {
            if (aux.getNumber() == Integer.parseInt(number)) {
                return aux.getName();
            }
        }
        return null;
    }
    
    private boolean belongsTo(Condition cond, ArrayList<Condition> array) { // verifica se cond pertence ao array
        for (Condition aux : array) {
            if (aux.getName().equals(cond.getName())) {
                return true;
            }
        }
        return false;
    }
}

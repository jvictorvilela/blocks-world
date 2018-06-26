package blocksworld;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Victor Vilela
 */
public class Main {
    static public ArrayList<Action> actions;
    static public ArrayList<Condition> conditions;
    static public ArrayList<Condition> initialStates;
    static public ArrayList<Condition> finalStates;
    
    // método que lê o arquivo e mapeia as ações e condições;
    public static Condition searchCondition(String name) {
        name = name.replace("~", "");
        for (Condition aux : conditions) {
            if (aux.getName().equals(name)) {
                return aux;
            }
        }
        return null;
    }
    
    public static void readAndMap(String archive) throws FileNotFoundException, IOException {
        actions = new ArrayList<Action>();
        conditions = new ArrayList<Condition>();
        initialStates = new ArrayList<Condition>();
        finalStates = new ArrayList<Condition>();
        
        BufferedReader br = new BufferedReader(new FileReader(archive));
        String linha;
        while(br.ready()){
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    linha = br.readLine();
                    if (!linha.isEmpty()) {
                        actions.add(new Action(actions.size(), linha));
                    } else {
                        linha = br.readLine();
                        for (String aux : linha.split(";")) { // preenchendo array de estados iniciais
                            Condition cond = searchCondition(aux);
                            if (cond == null) {
                                System.out.println("teste :D");
                            }
                            initialStates.add(new Condition(cond.getNumber(), aux));
                        }
                        linha = br.readLine();
                        for (String aux : linha.split(";")) { // preenchendo array de estados finais
                            Condition cond = searchCondition(aux);
                            finalStates.add(new Condition(cond.getNumber(), aux));
                        }
                        
                        break;
                    }
                }
                
                if (i == 1) {
                    linha = br.readLine();
                    for (String aux : linha.split(";")) {
                        // faz uma busca pelo array de conditions para saber se essa condição já existe
                        Condition cond = searchCondition(aux);
                        if (cond == null) { // caso a condição não exista
                            cond = new Condition(conditions.size(), aux);
                            conditions.add(cond); // adiciona a condição nova ao array de condições
                        }
                        actions.get(actions.size()-1).addPreCondition(new Condition(cond.getNumber(), aux)); // adiciona a condição como pre-condição da ultima ação adicionada no array
                    }
                }
                
                if (i == 2) {
                    linha = br.readLine();
                    for (String aux : linha.split(";")) {
                        // faz uma busca pelo array de conditions para saber se essa condição já existe
                        Condition cond = searchCondition(aux);
                        if (cond == null) { // caso a condição não exista
                            cond = new Condition(conditions.size(), aux);
                            conditions.add(cond); // adiciona a condição nova ao array de condições
                        }
                        actions.get(actions.size()-1).addPostCondition(new Condition(cond.getNumber(), aux)); // adiciona a condição como pre-condição da ultima ação adicionada no array
                    }
                }
            }
        }
        br.close();
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        
        
//        Scanner input = new Scanner(System.in);
//        
//        System.out.println("caminho do arquivo de entrada: ");
//        try {
//            readAndMap(input.nextLine());
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        BlocksWorld world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        
        
        ////// apagar depois...
        
        BlocksWorld world;

        
      
        ///
        System.out.println("Blocks 11-1 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-11-1.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///
        ///
        System.out.println("Blocks 11-2 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-11-2.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///
        ///
        System.out.println("Blocks 12-0 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-12-0.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///
        ///
        System.out.println("Blocks 12-1 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-12-1.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///
        ///
        System.out.println("Blocks 12-2 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-12-2.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///
        ///
        System.out.println("Blocks 13-0 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-13-0.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///
        ///
        System.out.println("Blocks 13-1 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-13-1.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///
        ///
        System.out.println("Blocks 14-0 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-14-0.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///
        ///
        System.out.println("Blocks 14-1 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-14-1.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///
        ///
        System.out.println("Blocks 15-0 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-15-0.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///
        ///
        System.out.println("Blocks 15-1 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-15-1.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///
        ///
        System.out.println("Blocks 16-1 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-16-1.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///
        ///
        System.out.println("Blocks 16-2 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-16-2.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///
        ///
        System.out.println("Blocks 17-0 ------------------------------------------");
        try {
            readAndMap("/home/victor/Área de Trabalho/Faculdade/Lógica/ trabalho 01/Mundo dos blocos/blocks-17-0.strips");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        world = new BlocksWorld(actions, conditions, initialStates, finalStates);
        ///

        
        
        
    }
    
}

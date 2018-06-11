import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    ArrayList<Double> output = new ArrayList<Double>();

    public void ReadFromFile(String in_file) {
        File file = new File(in_file);
        double another_one;
        try {
            Scanner scn = new Scanner(file);
            while (scn.hasNext()) {
                if(scn.hasNextDouble()){
                    another_one = scn.nextDouble();
                    output.add(another_one);
                }else{
                    scn.next();
                }
            }
            scn.close();
        }catch (FileNotFoundException e){
            System.out.println("Nie wykryto pliku");
        }
    }


    public ArrayList<Double> getOutput(){
        return output;
    }
}

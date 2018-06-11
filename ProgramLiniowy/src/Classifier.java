import java.util.ArrayList;

public class Classifier {
    ArrayList<Double> a_coeff = new ArrayList<Double>();
    ArrayList<Double> b_coeff = new ArrayList<Double>();
    ArrayList<Double> c_coeff = new ArrayList<Double>();
    public void classifier(ArrayList<Double> input){
        for(int i=0 ; i<input.size() ; ++i){
            if(i<=input.size()/3){
                a_coeff.add(input.get(i));
            }
            else if(i>input.size()/3 && i<=2*input.size()/3){
                b_coeff.add(input.get(i));
            }
            else if(i>2*input.size()/3){
                c_coeff.add(input.get(i));
            }
        }
    }
    /*
    public ArrayList<Double> getA_coeff(){
        return a_coeff;
    }

    public ArrayList<Double> getB_coeff(){
        return b_coeff;
    }

    public ArrayList<Double> getC_coeff(){
        return c_coeff;
    }*/
}

import java.util.ArrayList;

public class EquationSolver {
    ArrayList<Double[]> currentpoint = new ArrayList<Double[]>();

    public ArrayList<Double[]> eq_eq_solver(DualEquation eq1, DualEquation eq2){
        double x = 0;
        double y = 0;
        x =     (eq1.getC_param()*eq2.getB_param() -
                eq1.getB_param()*eq2.getC_param())
                /
                (eq1.getA_param()*eq2.getB_param() -
                eq1.getB_param()*eq2.getA_param());

        y =     (eq1.getA_param()*eq2.getC_param() -
                eq1.getC_param()*eq2.getA_param())
                /
                (eq1.getA_param()*eq2.getB_param() -
                eq1.getB_param()*eq2.getA_param());
    Double[] tmp = {x, y};

    currentpoint.add(tmp);
    return currentpoint;

    }


    public ArrayList<Double[]> eq_ax_solver(DualEquation eq){
        double x = 0;
        double y = 0;
        x = eq.getC_param()/eq.getA_param();
        Double[] tmp = {x,y};
        currentpoint.add(tmp);
        return currentpoint;
    }

    public ArrayList<Double[]> eq_ay_solver(DualEquation eq){
        double x = 0;
        double y = 0;
        y = eq.getC_param()/eq.getB_param();
        Double[] tmp = {x,y};
        currentpoint.add(tmp);
        return currentpoint;
    }

}

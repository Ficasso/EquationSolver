import java.util.ArrayList;

import static java.lang.Math.abs;

public class Main {

    public static void main(String[] args) {
        ArrayList<Double> output = new ArrayList<Double>();
        ArrayList<Double> a_coeff = new ArrayList<Double>();
        ArrayList<Double> b_coeff = new ArrayList<Double>();
        ArrayList<Double> c_coeff = new ArrayList<Double>();
        ArrayList<DualEquation> eq_list = new ArrayList<DualEquation>();
        ArrayList<Double[]> point_list = new ArrayList<Double[]>();
        ArrayList<Double[]> confirmed_points = new ArrayList<Double[]>();
        Reader read = new Reader();
        read.ReadFromFile("C:\\plik.txt");
        output = read.getOutput();
        Classifier classify = new Classifier();
        classify.classifier(output);
        a_coeff = classify.a_coeff;
        b_coeff = classify.b_coeff;
        c_coeff = classify.c_coeff;

        for (int i = 0; i < a_coeff.size() - 1; ++i) {
            DualEquation tmp_eq = new DualEquation();
            tmp_eq.dualequation(a_coeff.get(i), b_coeff.get(i), c_coeff.get(i));
            eq_list.add(tmp_eq);
        }
        EquationSolver solver = new EquationSolver();


        for (int i = 0; i < eq_list.size(); ++i) {
            for (int j = 0; j < eq_list.size(); ++j) {
                if (i == j) {
                    continue;
                }
                solver.eq_eq_solver(eq_list.get(i), eq_list.get(j));
            }
            solver.eq_ax_solver(eq_list.get(i));

            solver.eq_ay_solver(eq_list.get(i));
        }


        for (int i = 0; i < solver.currentpoint.size(); ++i) {
            point_list.add(solver.currentpoint.get(i));
        }


        for (int i = 0; i < point_list.size(); ++i) {
            if (point_list.get(i)[0] > 99999999 ||
                    point_list.get(i)[0] < 0 ||
                    point_list.get(i)[1] > 99999999 ||
                    point_list.get(i)[1] < 0) {
                point_list.remove(i);
            }
        }


            int realized_equations;
            for (int i = 0; i < point_list.size(); ++i) {
                realized_equations = 0;
                for (int j = 0; j < eq_list.size(); ++j) {
                    if (eq_list.get(j).getA_param() * point_list.get(i)[0] + eq_list.get(j).getB_param() * point_list.get(i)[1] <= eq_list.get(j).getC_param()) {
                        realized_equations++;
                    }
                }
                if (realized_equations == 4) {
                    confirmed_points.add(point_list.get(i));
                }
            }

            for (int i = 0; i < confirmed_points.size(); ++i) {
                for (int j = 0; j < confirmed_points.size(); ++j) {
                    if (i == j) {
                        continue;
                    }
                    if (confirmed_points.get(i)[0].equals(confirmed_points.get(j)[0]) && confirmed_points.get(i)[1].equals(confirmed_points.get(j)[1])) {
                        confirmed_points.remove(j);
                    }
                }
            }


            Value value = new Value();
            double best_value = -99999999;
            int best_point=-1;
            for (int i = 0; i < confirmed_points.size(); ++i) {
                if (best_value < value.pointValue(confirmed_points.get(i), a_coeff.get(a_coeff.size() - 1), b_coeff.get(b_coeff.size() - 1))) {
                    best_value = value.pointValue(confirmed_points.get(i), a_coeff.get(a_coeff.size() - 1), b_coeff.get(b_coeff.size() - 1));
                    best_point = i;
                }
            }

//////////////////////////////////////////////////////////////////////////////////
            ArrayList<Integer> result = new ArrayList<Integer>();
            double temporary_value;
            for(int i=0 ; i<eq_list.size() ; ++i){
                temporary_value=
                        confirmed_points.get(best_point)[0]*eq_list.get(i).getA_param() +
                        confirmed_points.get(best_point)[1]*eq_list.get(i).getB_param();
                if(abs(temporary_value-eq_list.get(i).getC_param())<0.0001){
                    result.add(i);
                }
            }


            EquationSolver last_solver = new EquationSolver();
            DualEquation last_equation1 = new DualEquation();
            DualEquation last_equation2 = new DualEquation();
            last_equation1.dualequation(a_coeff.get(result.get(0)),a_coeff.get(result.get(1)),a_coeff.get(a_coeff.size()-1));
            last_equation2.dualequation(b_coeff.get(result.get(0)),b_coeff.get(result.get(1)),b_coeff.get(a_coeff.size()-1));
            last_solver.eq_eq_solver(last_equation1,last_equation2);




////////////////////////////////////FINISH

        //////////////////////WYPISYWANIE PUNKTOW
        System.out.println("Lista punktów ograniczających zbiór rozwiązań dopuszczalnych dla PD:");
        for (int i=0; i<confirmed_points.size(); ++i) {
            System.out.println("Punkt " + (i+1) + ": [" + confirmed_points.get(i)[0] + ", " + confirmed_points.get(i)[1] + "]");
        }

        System.out.println("Punkt realizujący optimum PP:");
        System.out.print("G(  ");
        int x=0;
        for(int i=0 ; i<result.size() ; ++i){
            for(int j=x ; j<eq_list.size() ; ++j){
                if(result.get(i)==j){
                    System.out.print((last_solver.currentpoint.get(0)[i]) + "  ");
                    ++x;
                    break;
                }else{
                    System.out.print("0  ");
                }
            }
        }
        System.out.println(")");
        System.out.println("Wartość minimalna:");
        System.out.println(best_value);

    }
}


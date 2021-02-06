package Oblig1;

import java.util.Date;

public class Oving1 {
    public Oving1() {}

    public double regnPotens (double grunntall, int eksponent) {
        if (eksponent == 0) return 1;
        else return grunntall * regnPotens(grunntall, eksponent - 1);
    }

    public double regnPotens2(double grunntall, int eksponent){
        if (eksponent == 0) {
            return 1;
        } else if (eksponent % 2 == 0){
            return regnPotens2(grunntall*grunntall, eksponent/2);
        } else {
            return grunntall * regnPotens2(grunntall*grunntall, (eksponent-1)/2);
        }
    }

    public double tidAlg1(double grunntall, int eksponent) {
        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            regnPotens(grunntall, eksponent);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        return tid;
    }

    public double tidAlg2(double grunntall, int eksponent) {
        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            regnPotens2(grunntall, eksponent);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        return tid;
    }

    public double tidMathPow(double grunntall, int eksponent) {
        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            Math.pow(grunntall, eksponent);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        return tid;
    }


}

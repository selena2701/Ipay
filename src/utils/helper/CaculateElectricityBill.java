package utils.helper;

import java.text.NumberFormat;

public class CaculateElectricityBill {
    public static int Caculate(int consume, double vat){
        int money=0;
        int qtt, tt;
        while(consume!=0) {
            if (consume > 400) {
                qtt = consume - 400;
                tt = qtt * 2927;
                money += tt;
                consume=consume-qtt;
            }
            if (consume > 300) {
                qtt = consume - 300;
                tt = qtt * 2834;
                money += tt;
                consume=consume-qtt;
            }
            if (consume > 200) {
                qtt = consume - 200;
                tt = qtt * 2536;
                money += tt;
                consume=consume-qtt;
            }
            if (consume > 100) {
                qtt = consume - 100;
                tt = qtt * 2014;
                money += tt;
                consume=consume-qtt;

            }
            if (consume > 50) {
                qtt = consume - 50;
                tt = qtt * 1734;
                money += tt;
                consume=consume-qtt;

            }
            if (consume > 0) {
                qtt = consume;
                tt = qtt * 1678;
                money += tt;
                consume=consume-qtt;
            }
        }
        return (int) (money*(1+vat));
    }
}

import java.util.Scanner;

public class mass {

    public static int counterSum(String str) {
        int count = 0;
        String regex ="\\+-|\\--|\\*-|\\*|\\/-|\\-|\\+|\\/";
        String[] arr = str.startsWith("-")? str.substring(1).split(regex): str.split(regex);
        String[] arrOp = str.split("[\\d]+");
        int[] arrNum = new int[arr.length];
        arrNum[0] = str.startsWith("-")? Integer.parseInt(arr[0])*-1:
                Integer.parseInt(arr[0]);
        for(int i =1; i<arr.length; i++){
            if("*-".equals(arrOp[i])||"/-".equals(arrOp[i])
                    ||"+-".equals(arrOp[i])||"--".equals(arrOp[i])){
                arrNum[i] = Integer.parseInt(arr[i])*-1;
                arrOp[i]=arrOp[i].substring(0, 1);
            }else
                arrNum[i] = Integer.parseInt(arr[i]);
        }

        if (str.indexOf("*") != -1 || str.indexOf("/") != -1) {
            for (int i = 1; i < arrOp.length; i++) {
                switch (arrOp[i]) {
                    case "*":
                        count = arrNum[i - 1] * arrNum[i];
                        arrOp[i] = i>1?arrOp[i-1]:"+";
                        arrNum[i - 1] = 0;
                        arrNum[i] = count;
                        break;
                    case "/":
                        count = arrNum[i - 1] / arrNum[i];
                        arrOp[i] = i>1?arrOp[i-1]:"+";
                        arrNum[i - 1] = 0;
                        arrNum[i] = count;
                        break;
                }
            }
        }
        count = arrNum[0];
        for (int i = 1; i < arrOp.length; i++) {
            switch (arrOp[i]) {
                case "+":
                    count += arrNum[i];
                    break;
                case "-":
                    count -= arrNum[i];
                    break;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        Scanner inNumber = new Scanner(System.in);
        System.out.println("请输入需要计算的式子：");
        String string = inNumber.nextLine();
        String formula = string;
        inNumber.close();
        while(string.indexOf("(")!=-1){
            int indexEnd = string.indexOf(")");
            int indexString = string.lastIndexOf("(", indexEnd);
            String strIn = string.substring(indexString+1, indexEnd);
            int sum = counterSum(strIn);
            strIn = String.valueOf(sum);
            StringBuffer strb = new StringBuffer(string);
            strb.replace(indexString, indexEnd+1, strIn);
            string = strb.toString();
        }
        int sum = counterSum(string);
        System.out.println(formula+"="+sum);
    }
}

public class Calculator {

    public static void calculate(int val1, String operator, int val2) {
        int result = 0;
        if (operator.equals("/") && val2 == 0) System.out.print("Сan't divide by 0");
        else {
            switch (operator) {
                case "+" -> result = val1 + val2;
                case "-" -> result = val1 - val2;
                case "*" -> result = val1 * val2;
                case "/" -> result = val1 / val2;
            }
            System.out.print("Result is: " + result);
        }
    }

    public static boolean isInt(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

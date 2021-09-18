public class Main {
    public static void main(String[] args) {
        Parser.parse(args);
        int val1 = Integer.parseInt(args[0]);
        String operator = args[1];
        int val2 = Integer.parseInt(args[2]);
        Calculator.calculate(val1, operator, val2);
    }
}

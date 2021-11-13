public class Parser {
    public static void parse(String[] args) {
        if (args.length != 3) {
            System.out.print("Invalid number of arguments");
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        String operator = args[1];
        if (!Calculator.isInt(args[0]) || !Calculator.isInt(args[2])) {
            System.out.print("First or third argument is not a number");
            throw new IllegalArgumentException("First or third argument is not a number");
        }

        if (!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/")) {
            System.out.print("Operator is not supported");
            throw new IllegalArgumentException("Operator is not supported");
        }
    }
}

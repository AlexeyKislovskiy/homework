public class Parser {
    public static void parse(String[] args) {
        String operator = args[1];
        if (args.length != 3) {
            System.out.print("Invalid number of arguments");
            System.exit(1);
        }
        if (!Calculator.isInt(args[0]) || !Calculator.isInt(args[2])) {
            System.out.print("First or third argument is not a number");
            System.exit(2);
        }

        if (!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/")) {
            System.out.print("Operator is not supported");
            System.exit(3);
        }
    }
}

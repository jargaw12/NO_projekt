import java.util.HashMap;
import java.util.Map;

public class Counter {
    private Map<String, Integer> operators = new HashMap<>();
    private Map<String, Integer> operands = new HashMap<>();

    public void addOperator(String operator) {
        if (operators.containsKey(operator)) {
            operators.put(operator, operators.get(operator) + 1);
        } else {
            operators.put(operator, 1);
        }
    }

    public void addOperand(String operand) {
        if (operands.containsKey(operand)) {
            operands.put(operand, operands.get(operand) + 1);
        } else {
            operands.put(operand, 1);
        }
    }

    public void printOperators() {
        System.out.println("Operatory:");
        System.out.println(operators.toString());
//        operators.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public void printOperands() {
        System.out.println("Operandy:");
        System.out.println(operands.toString());
//        operands.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public int getUniqueOperatorsCount() {
        return operators.size();
    }

    public int getUniqueOperandsCount() {
        return operands.size();
    }

    public int getOperatorsCount() {
        return operators.values().stream().reduce(0, Integer::sum);
    }

    public int getOperandsCount() {
        return operands.values().stream().reduce(0, Integer::sum);
    }

    public int getDictionary() {
        return getUniqueOperatorsCount() + getUniqueOperandsCount();
    }

    public int getLength() {
        return getOperatorsCount() + getOperandsCount();
    }

    public double getCapacity() {
        return getLength() * log2(getDictionary());
    }

    public double getDifficulty() {
        return (getUniqueOperatorsCount() * getOperandsCount()) / (2.0 * getUniqueOperandsCount());
    }

    public double getWorkload() {
        return getCapacity() * getDifficulty();
    }

    public double expectedErrorCount1() {
        return getCapacity() / 3000.0;
    }

    public double expectedErrorCount2() {
        return Math.pow(getWorkload(), (2.0 / 3.0)) / 3000.0;
    }

    public void printStatistics() {
        printOperators();
        printOperands();
        System.out.println("Liczba unikalnych operatorów: " + getUniqueOperatorsCount());
        System.out.println("Liczba unikalnych operandów: " + getUniqueOperandsCount());
        System.out.println("Lączna liczba operatorów: " + getOperatorsCount());
        System.out.println("Lączna liczba operandów: " + getOperandsCount());
        System.out.println("Słownik: " + getDictionary());
        System.out.println("Długość: " + getLength());
        System.out.println("Objętość: " + getCapacity());
        System.out.println("Trudność programu: " + getDifficulty());
        System.out.println("Wymagany nakład pracy: " + getWorkload());
        System.out.println("Przewidywana liczba błędów1: " + expectedErrorCount1());
        System.out.println("Przewidywana liczba błędów2: " + expectedErrorCount2());
    }

    private static double log2(int n) {
        return (Math.log(n) / Math.log(2));
    }

}

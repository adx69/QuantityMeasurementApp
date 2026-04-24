import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class QuantityMeasurementApp {

    // ===== UNIT ENUM =====
    enum Unit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(0.393701 / 12.0);

        private final double toFeet;

        Unit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toBase(double value) {
            return value * toFeet;
        }

        public double fromBase(double baseValue) {
            return baseValue / toFeet;
        }
    }

    // ===== GENERIC QUANTITY CLASS =====
    static class Quantity {
        private final Double value;
        private final Unit unit;

        public Quantity(Double value, Unit unit) {
            if (value == null || !Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        public double toBase() {
            return unit.toBase(value);
        }

        public boolean isEqual(Quantity other) {
            if (other == null)
                throw new IllegalArgumentException("Other cannot be null");
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        // ===== UC5: CONVERSION =====
        public static double convert(double value, Unit source, Unit target) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }
            if (source == null || target == null) {
                throw new IllegalArgumentException("Units cannot be null");
            }

            double base = source.toBase(value);
            return target.fromBase(base);
        }

        // ===== UC6: ADD (default → first operand unit) =====
        public Quantity add(Quantity other) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }

            double sumBase = this.toBase() + other.toBase();
            double resultValue = this.unit.fromBase(sumBase);

            return new Quantity(resultValue, this.unit);
        }

        // ===== UC7: ADD WITH TARGET UNIT =====
        public Quantity add(Quantity other, Unit targetUnit) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            // Convert both → base (feet)
            double sumBase = this.toBase() + other.toBase();

            // Convert → target unit
            double resultValue = targetUnit.fromBase(sumBase);

            return new Quantity(resultValue, targetUnit);
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ===== UC4: Two-Pointer Palindrome =====
    public static boolean isPalindrome(String input) {
        if (input == null)
            throw new IllegalArgumentException("Input cannot be null");

        char[] chars = input.toCharArray();
        int left = 0, right = chars.length - 1;

        while (left < right) {
            if (chars[left] != chars[right])
                return false;
            left++;
            right--;
        }
        return true;
    }

    // ===== UC5: Stack Palindrome =====
    public static boolean isPalindromeUsingStack(String input) {
        if (input == null)
            throw new IllegalArgumentException("Input cannot be null");

        Stack<Character> stack = new Stack<>();
        for (char ch : input.toCharArray())
            stack.push(ch);

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != stack.pop())
                return false;
        }
        return true;
    }

    // ===== UC6: Queue + Stack Palindrome =====
    public static boolean isPalindromeUsingQueueAndStack(String input) {
        if (input == null)
            throw new IllegalArgumentException("Input cannot be null");

        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        for (char ch : input.toCharArray()) {
            queue.add(ch);
            stack.push(ch);
        }

        while (!queue.isEmpty()) {
            if (queue.remove() != stack.pop())
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        // ===== Equality =====
        Quantity q1 = new Quantity(1.0, Unit.YARD);
        Quantity q2 = new Quantity(3.0, Unit.FEET);
        System.out.println("Yard vs Feet equality: " + q1.isEqual(q2));

        // ===== Conversion =====
        System.out.println("1 Foot in Inches: " + Quantity.convert(1.0, Unit.FEET, Unit.INCH));
        System.out.println("1 Yard in Inches: " + Quantity.convert(1.0, Unit.YARD, Unit.INCH));
        System.out.println("30.48 cm in Feet: " + Quantity.convert(30.48, Unit.CM, Unit.FEET));

        // ===== UC6 ADD =====
        Quantity f1 = new Quantity(1.0, Unit.FEET);
        Quantity i1 = new Quantity(12.0, Unit.INCH);
        System.out.println("1 foot + 12 inch = " + f1.add(i1));

        // ===== UC7 ADD WITH TARGET UNIT =====
        Quantity resultInYard = f1.add(i1, Unit.YARD);
        System.out.println("1 foot + 12 inch in YARD = " + resultInYard);

        // ===== Palindrome Tests =====
        System.out.println("Two-pointer 'madam'? " + isPalindrome("madam"));
        System.out.println("Stack 'level'? " + isPalindromeUsingStack("level"));
        System.out.println("Queue+Stack 'racecar'? " + isPalindromeUsingQueueAndStack("racecar"));
    }
}
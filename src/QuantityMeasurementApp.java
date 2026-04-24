import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

// ===== UC8: STANDALONE ENUM (TOP LEVEL) =====
enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CM(0.393701 / 12.0);

    private final double toFeet;

    LengthUnit(double toFeet) {
        this.toFeet = toFeet;
    }

    // Convert to base unit (feet)
    public double toBase(double value) {
        return value * toFeet;
    }

    // Convert from base unit (feet)
    public double fromBase(double baseValue) {
        return baseValue / toFeet;
    }
}

// ===== MAIN CLASS =====
public class QuantityMeasurementApp {

    // ===== GENERIC QUANTITY CLASS =====
    static class Quantity {
        private final Double value;
        private final LengthUnit unit;

        public Quantity(Double value, LengthUnit unit) {
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

        // ===== CONVERSION =====
        public static double convert(double value, LengthUnit source, LengthUnit target) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }
            if (source == null || target == null) {
                throw new IllegalArgumentException("Units cannot be null");
            }

            double base = source.toBase(value);
            return target.fromBase(base);
        }

        // ===== UC6 ADD =====
        public Quantity add(Quantity other) {
            if (other == null) {
                throw new IllegalArgumentException("Other cannot be null");
            }

            double sumBase = this.toBase() + other.toBase();
            double result = this.unit.fromBase(sumBase);

            return new Quantity(result, this.unit);
        }

        // ===== UC7 ADD WITH TARGET UNIT =====
        public Quantity add(Quantity other, LengthUnit targetUnit) {
            if (other == null) {
                throw new IllegalArgumentException("Other cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sumBase = this.toBase() + other.toBase();
            double result = targetUnit.fromBase(sumBase);

            return new Quantity(result, targetUnit);
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
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);
        System.out.println("Yard vs Feet equality: " + q1.isEqual(q2));

        // ===== Conversion =====
        System.out.println("1 Foot in Inches: " + Quantity.convert(1.0, LengthUnit.FEET, LengthUnit.INCH));
        System.out.println("1 Yard in Inches: " + Quantity.convert(1.0, LengthUnit.YARD, LengthUnit.INCH));
        System.out.println("30.48 cm in Feet: " + Quantity.convert(30.48, LengthUnit.CM, LengthUnit.FEET));

        // ===== Addition =====
        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity i1 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println("Default Add: " + f1.add(i1));
        System.out.println("Add in YARD: " + f1.add(i1, LengthUnit.YARD));

        // ===== Palindrome Tests =====
        System.out.println("Two-pointer 'madam'? " + isPalindrome("madam"));
        System.out.println("Stack 'level'? " + isPalindromeUsingStack("level"));
        System.out.println("Queue+Stack 'racecar'? " + isPalindromeUsingQueueAndStack("racecar"));
    }
}
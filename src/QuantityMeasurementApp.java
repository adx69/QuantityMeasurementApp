import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class QuantityMeasurementApp {

    // ===== UC3: Generic Quantity (DRY) =====
    enum Unit {
        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double toFeet;

        Unit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toBase(double value) {
            return value * toFeet;
        }
    }

    static class Quantity {
        private final Double value;
        private final Unit unit;

        public Quantity(Double value, Unit unit) {
            if (value == null)
                throw new IllegalArgumentException("Value cannot be null");
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");
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
    }

    // ===== UC4: Two-Pointer Palindrome =====
    public static boolean isPalindrome(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        char[] chars = input.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // ===== UC5: Stack-Based Palindrome =====
    public static boolean isPalindromeUsingStack(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        Stack<Character> stack = new Stack<>();

        for (char ch : input.toCharArray()) {
            stack.push(ch);
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != stack.pop()) {
                return false;
            }
        }
        return true;
    }

    // ===== UC6: Queue + Stack Palindrome =====
    public static boolean isPalindromeUsingQueueAndStack(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        // Add characters to both structures
        for (char ch : input.toCharArray()) {
            queue.add(ch); // FIFO
            stack.push(ch); // LIFO
        }

        // Compare dequeue vs pop
        while (!queue.isEmpty()) {
            if (queue.remove() != stack.pop()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        // ===== UC1, UC2, UC3 Tests =====
        Quantity q1 = new Quantity(5.0, Unit.FEET);
        Quantity q2 = new Quantity(5.0, Unit.FEET);

        Quantity q3 = new Quantity(12.0, Unit.INCH);
        Quantity q4 = new Quantity(1.0, Unit.FEET);

        System.out.println("Feet equality: " + q1.isEqual(q2));
        System.out.println("Feet vs Inches equality: " + q3.isEqual(q4));

        // ===== UC4 Test =====
        System.out.println("Two-pointer 'madam'? " + isPalindrome("madam"));
        System.out.println("Two-pointer 'hello'? " + isPalindrome("hello"));

        // ===== UC5 Test =====
        System.out.println("Stack 'level'? " + isPalindromeUsingStack("level"));
        System.out.println("Stack 'world'? " + isPalindromeUsingStack("world"));

        // ===== UC6 Test =====
        System.out.println("Queue+Stack 'racecar'? " + isPalindromeUsingQueueAndStack("racecar"));
        System.out.println("Queue+Stack 'java'? " + isPalindromeUsingQueueAndStack("java"));
    }
}
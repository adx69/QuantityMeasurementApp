public class QuantityMeasurementApp {

    // ===== UC3 CODE (UNCHANGED) =====
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

    // ===== UC4 CODE (NEW) =====

    public static boolean isPalindrome(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        char[] chars = input.toCharArray(); // convert to char[]

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

    public static void main(String[] args) {

        // ===== UC3 TEST =====
        Quantity q1 = new Quantity(5.0, Unit.FEET);
        Quantity q2 = new Quantity(5.0, Unit.FEET);

        Quantity q3 = new Quantity(12.0, Unit.INCH);
        Quantity q4 = new Quantity(1.0, Unit.FEET);

        System.out.println("Feet equality: " + q1.isEqual(q2));
        System.out.println("Feet vs Inches equality: " + q3.isEqual(q4));

        // ===== UC4 TEST =====
        String test1 = "madam";
        String test2 = "hello";

        System.out.println("Is 'madam' palindrome? " + isPalindrome(test1));
        System.out.println("Is 'hello' palindrome? " + isPalindrome(test2));
    }
}
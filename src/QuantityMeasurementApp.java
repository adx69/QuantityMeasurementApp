public class QuantityMeasurementApp {

    // Feet Class
    static class Feet {
        public boolean isEqual(Double value1, Double value2) {
            if (value1 == null || value2 == null) {
                throw new IllegalArgumentException("Feet values cannot be null");
            }
            return value1.equals(value2);
        }
    }

    // Inches Class
    static class Inches {
        public boolean isEqual(Double value1, Double value2) {
            if (value1 == null || value2 == null) {
                throw new IllegalArgumentException("Inch values cannot be null");
            }
            return value1.equals(value2);
        }
    }

    // Static method for Feet comparison
    public static boolean compareFeet(Double value1, Double value2) {
        Feet feet = new Feet();
        return feet.isEqual(value1, value2);
    }

    // Static method for Inches comparison
    public static boolean compareInches(Double value1, Double value2) {
        Inches inches = new Inches();
        return inches.isEqual(value1, value2);
    }

    public static void main(String[] args) {

        // Hardcoded values (as per UC)
        Double feetValue1 = 5.0;
        Double feetValue2 = 5.0;

        Double inchValue1 = 12.0;
        Double inchValue2 = 12.0;

        boolean feetResult = compareFeet(feetValue1, feetValue2);
        boolean inchResult = compareInches(inchValue1, inchValue2);

        System.out.println("Feet equality: " + feetResult);
        System.out.println("Inches equality: " + inchResult);
    }
}
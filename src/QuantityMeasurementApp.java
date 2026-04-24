public class QuantityMeasurementApp {

    // Method to check equality of two feet values
    public static boolean isEqual(Double value1, Double value2) {
        if (value1 == null || value2 == null) {
            throw new IllegalArgumentException("Values cannot be null");
        }
        return value1.equals(value2);
    }

    public static void main(String[] args) {

        Double value1 = 5.0;
        Double value2 = 5.0;

        boolean result = isEqual(value1, value2);

        System.out.println("Are values equal? " + result);
    }
}
public class QuantityMeasurementApp {

    // Enum for Units
    enum Unit {
        FEET(1.0),
        INCH(1.0 / 12.0); // 1 inch = 1/12 feet

        private final double toFeet;

        Unit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toBase(double value) {
            return value * toFeet;
        }
    }

    // Generic Quantity Class (DRY Applied)
    static class Quantity {
        private final Double value;
        private final Unit unit;

        public Quantity(Double value, Unit unit) {
            if (value == null) {
                throw new IllegalArgumentException("Value cannot be null");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (Feet)
        public double toBase() {
            return unit.toBase(value);
        }

        // Equality check with conversion
        public boolean isEqual(Quantity other) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }
    }

    public static void main(String[] args) {

        // Same unit comparison (UC1, UC2 behavior preserved)
        Quantity q1 = new Quantity(5.0, Unit.FEET);
        Quantity q2 = new Quantity(5.0, Unit.FEET);

        // Cross unit comparison (NEW in UC3)
        Quantity q3 = new Quantity(12.0, Unit.INCH);
        Quantity q4 = new Quantity(1.0, Unit.FEET);

        System.out.println("Feet equality: " + q1.isEqual(q2));
        System.out.println("Feet vs Inches equality: " + q3.isEqual(q4));
    }
}
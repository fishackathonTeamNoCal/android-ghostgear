package com.fishhackathon.ghostgear.models;

/**
 * Holds the input data about a net.
 */
public class NetInput {
    private static final double EPSILON = 0.0001;

    public static final class NumberRange {
        public final double min;  // Inclusive
        public final double max;  // Exclusive.

        public NumberRange(double min, double max) {
            this.min = min;
            this.max = max;
        }
    }

    public enum HandMeasurement {
        LESS_THAN_ONE_FINGER(new NumberRange(0, 2.5)),
        ONE_FINGER(new NumberRange(2.5, 4.2)),
        TWO_FINGERS(new NumberRange(4.2, 5.8)),
        THREE_FINGERS(new NumberRange(5.8, 7.6)),
        FOUR_FINGERS(new NumberRange(7.6, 9.5)),
        FIST(new NumberRange(9.5, 12.5)),
        CLASPED_FIST(new NumberRange(12.5, 16.6)),
        OPEN_HAND(new NumberRange(16.6, 23.0)),
        GREATER_THAN_OPEN_HAND(new NumberRange(23.0, Double.MAX_VALUE));

        private final NumberRange numberRange;
        HandMeasurement(NumberRange numberRange) {
            this.numberRange = numberRange;
        }
    }

    public /*@Nullable*/ Color color;
    public /*@Nullable*/ NumberRange meshSize;  // in cm
    public /*@Nullable*/ Integer numberOfStrands;
    public /*@Nullable*/ Double twineDiameter;  // in mm

    // Provide setters using either mm or HandMeasurement. For any other fields just set the field
    // directly.
    public void setMeshSize(HandMeasurement handMeasurement) {
        this.meshSize = handMeasurement.numberRange;
    }

    public void setExactMeshSize(double meshSize) {
        this.meshSize = new NumberRange(meshSize, meshSize + EPSILON);
    }

    /**
     * Returns a single mesh size. If we have a range specified, returns the average.
     */
    public Double getSingleMeshSize() {
        if (meshSize == null) {
            return null;
        }
        if (meshSize.max - meshSize.min < 2 * EPSILON) {
            return meshSize.min;
        }
        return (meshSize.min + meshSize.max) / 2;
    }
}

package com.fishhackathon.ghostgear.models;

/**
 * Holds the input data about a net.
 */
public class NetInput {
    public static final class NumberRange {
        public final double min;  // Inclusive

        // TODO: Do we actually want max to be exclusive? It means we can't specify a specific
        // number by having equal min and max.
        public final double max;  // Exclusive.

        public NumberRange(double min, double max) {
            this.min = min;
            this.max = max;
        }
    }

    public enum Color {
        BLACK,
        BLUE,
        BROWN,
        CLEAR,
        GREEN,
        GREY,
        RED,
        WHITE
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

    public Color color;
    public NumberRange meshSize;  // in cm
    public int numberOfStrands;
    public NumberRange twineDiameter;  // in mm

    // Provide a setter when using hand measurements. For anything else just set the field directly.
    public void setMeshSize(HandMeasurement handMeasurement) {
        this.meshSize = handMeasurement.numberRange;
    }

    // TODO: Is there a user-friendly way of setting twine-diameter? Maybe we'll have to ask
    // them to provide mm but provide them with references like in the ghost_net_id_guide.pdf.
}

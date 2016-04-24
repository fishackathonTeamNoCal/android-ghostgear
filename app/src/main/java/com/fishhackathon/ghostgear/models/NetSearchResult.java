package com.fishhackathon.ghostgear.models;

public class NetSearchResult {
    public final Color color;
    public final Double meshSize;  // in cm
    public final /*@Nullable*/ Integer numberOfStrands;
    public final Double twineDiameter;  // in mm
    public final String netCode;
    public final String origin;

    public NetSearchResult(
            Color color,
            Double meshSize,
            /*@Nullable*/ Integer numberOfStrands,
            Double twineDiameter,
            String netCode,
            String origin) {
        this.color = color;
        this.meshSize = meshSize;
        this.numberOfStrands = numberOfStrands;
        this.twineDiameter = twineDiameter;
        this.netCode = netCode;
        this.origin = origin;
    }
}

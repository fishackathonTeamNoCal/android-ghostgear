package com.fishhackathon.ghostgear.models;

/**
 * Represents a Net. Includes the NetInput from the user and, if available, the data
 * returned by the Search API.
 */
public class Net {
    public NetInput netInput;
    public String netCode;
    public String origin;

    // TODO: Add fields for photos and net use.

    public Net(NetInput netInput, String netCode, String origin) {
        this.netInput = netInput;
        this.netCode = netCode;
        this.origin = origin;
    }
}

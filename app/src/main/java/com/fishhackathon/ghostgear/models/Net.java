package com.fishhackathon.ghostgear.models;

/**
 * Represents a Net. Includes the NetInput from the user and, if available, the data
 * returned by the Search API.
 */
public class Net {
    public NetInput netInput;
    public /*@Nullable*/ NetSearchResult netSearchResult;

    public Net(NetInput netInput, /*@Nullable*/ NetSearchResult netSearchResult) {
        this.netInput = netInput;
        this.netSearchResult = netSearchResult;
    }
}

package com.fishhackathon.ghostgear.models;

import com.fishhackathon.ghostgear.network.ParseUtils;

import java.util.Date;

public class NetReport {
    public static class Animal {
        public enum Type {
            CRAB,
            CROCODILE,
            DOLPHIN,
            DUGONG,
            FISH,
            SEA_SNAKE,
            SHARK,
            RAY,
            SWORDFISH,
            TURTLE,
            WHALE,
            OTHER;

            /**
             * Output the Animal type as it's represented in Parse.
             */
            public String toParseString() {
                return ParseUtils.upperCaseFirstLetter(toString());
            }
        }

        public /*@Nullable*/ Type type;

        // todo: Should this be an enum? With options like "injured"?
        public /*@Nullable*/ Boolean alive;

        public Animal(/*@Nullable*/ Type type, /*@Nullable*/ boolean alive) {
            this.type = type;
            this.alive = alive;
        }
    }

    public Net net;

    // TODO: Hardcoded names for now
    public /*@Nullable*/ String firstName = "Jason";
    public /*@Nullable*/ String lastName = "Chen";
    public /*@Nullable*/ String emailAddress;
    public /*@Nullable*/ String country;
    public /*@Nullable*/ String island;
    public /*@Nullable*/ Date timestamp;
    public /*@Nullable*/ Double latitude;
    public /*@Nullable*/ Double longitude;
    public /*@Nullable*/ String overallPhotoFile;
    public /*@Nullable*/ String closeupPhotoFile;

    // todo: We should probably actually have a List<Animal>
    public /*@Nullable*/ Animal animal;
    public /*@Nullable*/ String netConstruction;
    public /*@Nullable*/ String typeOfTwine;
    public /*@Nullable*/ String typeOfMaterial;

}

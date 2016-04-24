package com.fishhackathon.ghostgear.network;

import com.fishhackathon.ghostgear.models.Net;
import com.fishhackathon.ghostgear.models.NetInput;
import com.fishhackathon.ghostgear.models.NetReport;
import com.fishhackathon.ghostgear.models.NetSearchResult;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import java.io.File;

/**
 * Send reports to the backend.
 *
 * TODO: Test this!!
 */
public final class ReportingApi {
    private static final String NET_REPORT = "NetReport";

    // Column names
    private static final String COLOR = "color";
    private static final String MESH_SIZE = "meshSize";
    private static final String TWINE_DIAMETER = "twineDiameter";
    private static final String NUMBER_OF_STRANDS = "numberOfStrands";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL_ADDRESS = "emailAddress";
    private static final String COUNTRY = "country";
    private static final String ISLAND = "island";
    private static final String TIMESTAMP = "timestamp";
    private static final String LOCATION = "location";
    private static final String OVERALL_PHOTO = "overallPhoto";
    private static final String CLOSEUP_PHOTO = "closeupPhoto";
    private static final String ANIMAL_ALIVE = "animalAlive";
    private static final String ANIMAL_EXISTS = "animalExists";
    private static final String ANIMAL_TYPE = "animalType";
    private static final String NET_CONSTRUCTION = "netConstruction";
    private static final String TYPE_OF_TWINE = "typeOfTwine";
    private static final String TYPE_OF_MATERIAL = "typeOfMaterial";
    private static final String NET_CODE = "netCode";

    private ReportingApi() {};

    public static void report(NetReport netReport) {
        ParseObject parseNetReport = new ParseObject(NET_REPORT);
        Net net = netReport.net;

        // If there was a NetSearchResult, use it. Otherwise use the NetInput.
        NetSearchResult netSearchResult = net.netSearchResult;
        if (netSearchResult != null) {
            parseNetReport.put(COLOR, netSearchResult.color.toParseString());
            parseNetReport.put(MESH_SIZE, netSearchResult.meshSize);
            parseNetReport.put(TWINE_DIAMETER, netSearchResult.twineDiameter);
            ParseUtils.putIfNotNull(parseNetReport,
                    NUMBER_OF_STRANDS, netSearchResult.numberOfStrands);
            parseNetReport.put(NET_CODE, netSearchResult.netCode);
        } else {

            NetInput netInput = net.netInput;
            ParseUtils.putIfNotNull(parseNetReport, COLOR, netInput.color.toParseString());

            // TODO: Is it okay to force mesh size into a number?
            ParseUtils.putIfNotNull(parseNetReport, MESH_SIZE, netInput.getSingleMeshSize());
            ParseUtils.putIfNotNull(parseNetReport, TWINE_DIAMETER, netInput.twineDiameter);
            ParseUtils.putIfNotNull(parseNetReport, NUMBER_OF_STRANDS, netInput.numberOfStrands);
        }

        ParseUtils.putIfNotNull(parseNetReport, FIRST_NAME, netReport.firstName);
        ParseUtils.putIfNotNull(parseNetReport, LAST_NAME, netReport.lastName);
        ParseUtils.putIfNotNull(parseNetReport, EMAIL_ADDRESS, netReport.emailAddress);
        ParseUtils.putIfNotNull(parseNetReport, COUNTRY, netReport.country);
        ParseUtils.putIfNotNull(parseNetReport, ISLAND, netReport.island);
        ParseUtils.putIfNotNull(parseNetReport, TIMESTAMP, netReport.timestamp);

        if (netReport.latitude != null && netReport.longitude != null) {
            parseNetReport.put(LOCATION,
                    new ParseGeoPoint(netReport.latitude, netReport.longitude));
        }

        // TODO: Does this work???
        if (netReport.overallPhotoFile != null) {
            parseNetReport.put(OVERALL_PHOTO, new ParseFile(new File(netReport.overallPhotoFile)));
        }
        if (netReport.closeupPhotoFile != null) {
            parseNetReport.put(CLOSEUP_PHOTO, new ParseFile(new File(netReport.closeupPhotoFile)));
        }

        if (netReport.animal != null) {
            parseNetReport.put(ANIMAL_EXISTS, true);
            ParseUtils.putIfNotNull(parseNetReport, ANIMAL_ALIVE, netReport.animal.alive);
            ParseUtils.putIfNotNull(parseNetReport,
                    ANIMAL_TYPE, netReport.animal.type.toParseString());
        } else {
            parseNetReport.put(ANIMAL_EXISTS, false);
        }

        ParseUtils.putIfNotNull(parseNetReport, NET_CONSTRUCTION, netReport.netConstruction);
        ParseUtils.putIfNotNull(parseNetReport, TYPE_OF_TWINE, netReport.typeOfTwine);
        ParseUtils.putIfNotNull(parseNetReport, TYPE_OF_MATERIAL, netReport.typeOfMaterial);

        parseNetReport.saveInBackground();
    }
}

package com.fishhackathon.ghostgear.network;

import android.util.Log;

import com.fishhackathon.ghostgear.models.Color;
import com.fishhackathon.ghostgear.models.NetInput;
import com.fishhackathon.ghostgear.models.NetSearchResult;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Search for information about nets.
 */
public final class SearchApi {
    private static final String TAG = SearchApi.class.getSimpleName();

    private static final String NETS_TABLE = "nets";

    // The columns of NETS_TABLE
    private static final String COLOR = "color";
    private static final String MESH_SIZE = "meshSize";
    private static final String NET_CODE = "netCode";
    private static final String NUMBER_OF_STRANDS = "numberOfStrands";
    private static final String ORIGIN = "origin";
    private static final String TWINE_DIAMETER = "twineDiameter";

    // TODO: Add columns for photos and net use.

    private SearchApi() {};

    public interface SearchCallback {
        void done(List<NetSearchResult> netSearchResults);
        void error();
    }

    public static void search(final NetInput netInput, final SearchCallback searchCallback) {
        ParseQuery<ParseObject> query = ParseQuery
                .getQuery(NETS_TABLE);
        if (netInput.color != null) {
            query = query.whereEqualTo(COLOR, netInput.color.toParseString());
        }
        if (netInput.meshSize != null) {
            query = query
                    .whereGreaterThanOrEqualTo(MESH_SIZE, netInput.meshSize.min)
                    .whereLessThan(MESH_SIZE, netInput.meshSize.max);
        }
        if (netInput.numberOfStrands != null) {
            query = query.whereEqualTo(NUMBER_OF_STRANDS, netInput.numberOfStrands);
        }
        if (netInput.twineDiameter != null) {
            query = query.whereEqualTo(TWINE_DIAMETER, netInput.twineDiameter);
        }
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    List<NetSearchResult> netSearchResults = new ArrayList<>();
                    for (ParseObject parseObject : objects) {

                        // todo: I'm sure there's a cleaner way to handle this...
                        Integer numberOfStrands = parseObject.getInt(TWINE_DIAMETER);
                        if (numberOfStrands == 0) {
                            numberOfStrands = null;
                        }
                        netSearchResults.add(new NetSearchResult(
                                Color.valueOf(parseObject.getString(COLOR).toUpperCase()),
                                parseObject.getDouble(MESH_SIZE),
                                numberOfStrands,
                                parseObject.getDouble(TWINE_DIAMETER),
                                parseObject.getString(NET_CODE),
                                parseObject.getString(ORIGIN)));
                    }
                    searchCallback.done(netSearchResults);
                } else {
                    Log.e(TAG, "Error querying Parse", e);
                    searchCallback.error();
                }
            }
        });
    }
}

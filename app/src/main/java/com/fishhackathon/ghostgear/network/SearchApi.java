package com.fishhackathon.ghostgear.network;

import android.util.Log;

import com.fishhackathon.ghostgear.models.Net;
import com.fishhackathon.ghostgear.models.NetInput;
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

    // The rows of NETS_TABLE
    private static final String COLOR = "color";
    private static final String MESH_SIZE = "meshSize";
    private static final String NET_CODE = "netCode";
    private static final String NUMBER_OF_STRANDS = "numberOfStrands";
    private static final String ORIGIN = "origin";
    private static final String TWINE_DIAMETER = "twineDiameter";

    // TODO: Add columns for photos and net use.

    private SearchApi() {};

    public interface SearchCallback {
        public void done(List<Net> nets);

        // TODO: Do we want to provide an error callback?
    }

    public static void search(final NetInput netInput, final SearchCallback searchCallback) {
        ParseQuery<ParseObject> query = ParseQuery
                .getQuery(NETS_TABLE)
                .whereMatches(COLOR, netInput.color.toString(), "i")
                .whereGreaterThanOrEqualTo(MESH_SIZE, netInput.meshSize.min)
                .whereLessThan(MESH_SIZE, netInput.meshSize.max)
                .whereEqualTo(NUMBER_OF_STRANDS, netInput.numberOfStrands)
                .whereGreaterThanOrEqualTo(TWINE_DIAMETER, netInput.twineDiameter.min)
                .whereLessThan(TWINE_DIAMETER, netInput.twineDiameter.max);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    List<Net> nets = new ArrayList<Net>();
                    for (ParseObject parseObject : objects) {
                        nets.add(new Net(
                                netInput,
                                parseObject.getString(NET_CODE),
                                parseObject.getString(ORIGIN)));
                    }
                    searchCallback.done(nets);
                } else {
                    Log.e(TAG, "Error querying Parse", e);
                }
            }
        });
    }
}

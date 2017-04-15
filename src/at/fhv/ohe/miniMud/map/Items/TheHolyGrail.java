package at.fhv.ohe.miniMud.map.Items;

import java.io.Serializable;

/**
 * Created by Oliver H on 15.04.2017.
 */
public class TheHolyGrail extends Items {
    private static final String _ONNOSHORTDESCR = "A Grail";
    private static final String _ONNOLONGDESCR  = "A beautiful very old looking Grail. Maybe it is Holy";
    private static final int _WEIGHT = 5;

    public TheHolyGrail(int itemID) {
        this(itemID, _ONNOSHORTDESCR, _ONNOLONGDESCR);
    }

    public TheHolyGrail(int itemID, String shortDescription, String longDescription) {
        super(itemID, _WEIGHT, shortDescription, longDescription);
    }
}

package at.fhv.ohe.miniMud.map.Fields;

import at.fhv.ohe.miniMud.map.Player;

/**
 * Created by Oliver H on 15.04.2017.
 */
public class Carpet extends Field {
    private static final String _ONNOSHORTDESCR = "A really beautifull Carpet";
    private static final String _ONNOLONGDESCR  = "A really beautifull Carpet. It is very fluffy. <3";

    public Carpet(int fieldID) {
        super(fieldID,_ONNOSHORTDESCR, _ONNOLONGDESCR);
    }

    public Carpet(int fieldID, String shortDescription, String longDescription) {
        super(fieldID,shortDescription, longDescription);
    }

    @Override
    public Field enter(Player player) {
        return this;    // Nothing special here
    }
}

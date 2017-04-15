package at.fhv.ohe.miniMud.map.Items;

/**
 * Created by Oliver H on 15.04.2017.
 */
public class Key extends Items {
    private static final String _ONNOSHORTDESCR = "A old looking Key";
    private static final String _ONNOLONGDESCR  = "A key with some magical looking engravings";
    private static final int _KEYWIGHT  = 10;

    private int _keyID;

    public Key(int itemID, int KeyID) {
        this(itemID,KeyID,_ONNOSHORTDESCR, _ONNOLONGDESCR);
    }

    public Key(int itemID, int KeyID, String shortDescription, String longDescription) {
        super(itemID, _KEYWIGHT, shortDescription, longDescription);
        _keyID = KeyID;
    }

    public int getKeyID() {
        return _keyID;
    }
}

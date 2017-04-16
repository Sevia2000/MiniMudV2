package at.fhv.ohe.miniMud.map.Fields;

import at.fhv.ohe.miniMud.map.Items.Items;
import at.fhv.ohe.miniMud.map.Items.Key;
import at.fhv.ohe.miniMud.map.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Describes a Door
 * <p>
 * Created by Oliver H on 15.04.2017.
 */
public class Door extends Field {
    private static final String _ONNOSHORTDESCR = "A Door";
    private static final String _ONNOLONGDESCR = "A old Door with a keyhole";
    private static final String _ONNOKEYCLOSED = "You need a Key to Open this Door";

    private int _keyID;
    private List<Integer> _playerEx;

    public Door(int fieldID, int keyID) {
        this(fieldID, keyID, _ONNOSHORTDESCR, _ONNOLONGDESCR);
    }

    public Door(int fieldID, int keyID, String shortDescription, String longDescription) {
        super(fieldID, shortDescription, longDescription);
        _keyID = keyID;
        _playerEx = new LinkedList<>();
    }

    @Override
    public Field enter(Player player) throws IllegalFieldEnterException {
        if (!isTheDoorOpenfor(player)) {
            for (Items item : player.getAllItemsfromType(Key.class)) {
                if (_keyID == ((Key) item).getKeyID()) {
                    openDoorFor(player);
                    player.deleteFromInventory(item);
                    return this;
                }
            }
            player.playerOutputStream(_ONNOKEYCLOSED);
        }
        return this;
    }

    private void openDoorFor(Player player) {
        int id = player.getID();
        _playerEx.add(id);  // Boxing removed -> new Integer(id)
    }

    private boolean isTheDoorOpenfor(Player player) {
        int id = player.getID();
        for (Integer exId : _playerEx) {
            if (exId == id) { // Boxing removed -> exId.intValue()
                return true;
            }
        }
        return false;
    }
}

package at.fhv.ohe.miniMud.map.FieldFunctions;

import at.fhv.ohe.miniMud.map.Items.Items;
import at.fhv.ohe.miniMud.map.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oliver H on 15.04.2017.
 */
public class SingleSupplyFunction extends FieldFunction implements IFieldFunction {
    private static final String _DESCRIPTION = "Oo here is a ";
    private static final String _SUCESSDESCR = "You got ";
    private static final String _ALREADYGOT = "There is nothing left for you..";
    private static final String _FAILDESCR = "You cant do this here...";

    private Items _supplyOf;
    private List<Integer> _playerEx;

    public SingleSupplyFunction(Items aItem) {
        this(aItem, _DESCRIPTION + aItem.getShortDescription());
    }

    public SingleSupplyFunction(Items aItem, String description) {
        super(description);
        _supplyOf = aItem;
        _playerEx = new LinkedList<>();
    }

    @Override
    public void execude(Player player, ActionFunctions actionFunctions) {
        if (actionFunctions == ActionFunctions.PICKUP) {
            if (!gotAlreadyItem(player)) {
                giveItemTo(player);
                player.playerOutputStream(_SUCESSDESCR + _supplyOf.getShortDescription());
            } else {
                player.playerOutputStream(_ALREADYGOT);
            }
        } else {
            player.playerOutputStream(_FAILDESCR);
        }
    }

    private void giveItemTo(Player player) {
        addToInventorry(player);
        int id = player.getID();
        _playerEx.add(id);  // Boxing removed -> new Integer(id)
    }

    protected void addToInventorry(Player player) {
        player.addToInventorry(Items.clone(_supplyOf));
    }

    private boolean gotAlreadyItem(Player player) {
        int id = player.getID();
        for (Integer exId : _playerEx) {
            if (exId == id) { // Boxing removed -> exId.intValue()
                return true;
            }
        }
        return false;
    }
}

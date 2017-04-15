package at.fhv.ohe.miniMud.map.FieldFunctions;

import at.fhv.ohe.miniMud.map.Items.Items;
import at.fhv.ohe.miniMud.map.Player;

/**
 *
 *
 * Created by Oliver H on 15.04.2017.
 */
public class MultiSupplyFunction extends SingleSupplyFunction implements IFieldFunction{
    private static final String _SUCESSDESCR = "You got something from ";

    public MultiSupplyFunction(Items aItem, String description) {
        super(aItem, description);
    }

    @Override
    public void execude(Player player, ActionFunctions actionFunctions){
        if (actionFunctions == ActionFunctions.PICKUP) {
            addToInventorry(player);
        }
        player.playerOutputStream(_SUCESSDESCR + getDescription());
    }
}

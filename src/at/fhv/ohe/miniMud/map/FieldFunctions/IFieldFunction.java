package at.fhv.ohe.miniMud.map.FieldFunctions;

import at.fhv.ohe.miniMud.map.Player;

/**
 * Created by Oliver H on 15.04.2017.
 */
public interface IFieldFunction {
    void execude(Player player, ActionFunctions actionFunctions);

    String getDescription();
}

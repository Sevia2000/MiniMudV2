package at.fhv.ohe.miniMud.map.Items;

import at.fhv.ohe.miniMud.map.Player;

/**
 * Created by Oliver H on 15.04.2017.
 */
public class Apple extends Items implements IConsumable{
    private static final String _ONNOSHORTDESCR = "A Apple";
    private static final String _ONNOLONGDESCR  = "A green Apple. It regenerates some Health";
    private static final String _ONSUCESS  = "Hmm its so tasty..xD";
    private static final int _WEIGHT = 5;
    private static final int _HEALTHREGENERATION = 20;

    public Apple(int itemID) {
        this(itemID, _ONNOSHORTDESCR, _ONNOLONGDESCR);
    }

    public Apple(int itemID, String shortDescription, String longDescription) {
        super(itemID, _WEIGHT, shortDescription, longDescription);
    }

    @Override
    public void consumeBy(Player player) {
        player.healthIncrease(_HEALTHREGENERATION);
        player.deleteFromInventory(this);
        player.playerOutputStream(_ONSUCESS);
    }
}

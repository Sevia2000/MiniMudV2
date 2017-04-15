package at.fhv.ohe.miniMud;

import at.fhv.ohe.miniMud.map.Directions;
import at.fhv.ohe.miniMud.map.MapController;
import at.fhv.ohe.miniMud.map.Player;

/**
 * Created by Oliver H on 15.04.2017.
 */
public class TestMapHere {

    public static void main(String[] args) throws Exception{
        MapController testController = MapController.getNewMapInstance("map0.map");
        Player test = testController.addPlayer("TestPalyer");
        test.lookHere();
        testController.saveMap("map0.map");
    }
}

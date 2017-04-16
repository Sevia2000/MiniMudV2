package at.fhv.ohe.miniMud;


import at.fhv.ohe.miniMud.map.Directions;
import at.fhv.ohe.miniMud.map.Fields.Carpet;
import at.fhv.ohe.miniMud.map.Fields.Door;
import at.fhv.ohe.miniMud.map.Fields.Field;
import at.fhv.ohe.miniMud.map.MapController;

/**
 * Created by Oliver H on 15.04.2017.
 */
public class TestNewMapCreaton {

    public static void main(String[] args) {

        Field[] fields = new Field[4];
        fields[0] = new Carpet(0);
        fields[1] = new Door(1, 6542);
        fields[2] = new Carpet(2);
        fields[3] = new Carpet(3);

        fields[0].bindFields(fields[2], Directions.EAST);

        MapController testController = new MapController(fields[0], "TestMap");
        testController.saveMap("map0.map");
    }
}

package at.fhv.ohe.miniMud.game;

import at.fhv.ohe.miniMud.map.MapController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oliver H on 16.04.2017.
 */
public class GameHub {
    private static GameHub ourInstance = new GameHub();
    private List<MapController> _mapControllers;

    private GameHub() {
        _mapControllers = new ArrayList<>();
        MapController temp = MapController.getNewMapInstance("map0.map");
        _mapControllers.add(temp);
    }

    public static GameHub getInstance() {
        return ourInstance;
    }

    public List<String> getMapControllerNames() {
        List<String> temp = new LinkedList<>();
        int i = 0;
        for (MapController item : _mapControllers) {
            temp.add("<" + i + "> " + item.getName());
        }
        return Collections.unmodifiableList(temp);
    }

    public MapController getMapController(int i) {
        return (_mapControllers.size() > i) ? _mapControllers.get(i) : null;
    }
}

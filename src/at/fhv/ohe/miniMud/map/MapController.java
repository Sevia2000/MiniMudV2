package at.fhv.ohe.miniMud.map;


import at.fhv.ohe.miniMud.map.Fields.Field;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Handels the Map
 * <p>
 * Created by Oliver H on 15.04.2017.
 */
public class MapController implements Serializable {
    private String _mapName;
    private Field _PlayerSporneField;
    private List<Player> _playerPlayOnMap;
    private List<Player> _playerSavedOnMap;
    private int _playerIDs;

    public MapController(Field _startField, String mapName) {
        _PlayerSporneField = _startField;
        _playerPlayOnMap = new LinkedList<>();
        _playerSavedOnMap = new LinkedList<>();
        _playerIDs = 0;
        _mapName = mapName;
    }

    public static MapController getNewMapInstance(String mapPath) {
        return loadMap(mapPath);
    }

    private static MapController loadMap(String s) {
        MapController temp = null;
        try {
            FileInputStream fileIn = new FileInputStream(s);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            temp = (MapController) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Successful loaded Map");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("MapLoader class not found");
            c.printStackTrace();
        }
        temp._playerSavedOnMap.addAll(temp._playerPlayOnMap);
        temp._playerPlayOnMap.clear();
        return temp;
    }

    public void saveMap(String mapPath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(mapPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + mapPath);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public boolean isNameFree(String name) {
        for (Player player : _playerSavedOnMap) {
            if (player.getName() == name) {
                return false;
            }
        }
        for (Player player : _playerPlayOnMap) {
            if (player.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    private Player addNewPlayer(String name) throws IllegalArgumentException {
        if (!isNameFree(name)) {
            throw new IllegalArgumentException("Name is already used :(");
        }
        Player newPlayer = new Player(name, _playerIDs++, _PlayerSporneField, this);
        _playerPlayOnMap.add(newPlayer);
        return newPlayer;
    }

    public Player addPlayer(String name) throws IllegalArgumentException {
        for (Player player : _playerSavedOnMap) {
            if (player.getName().equals(name)) {
                player.setMapController(this);
                return player;
            }
        }
        return addNewPlayer(name);
    }

    public void logout(Player player) {
        if (_playerPlayOnMap.contains(player)) {
            _playerPlayOnMap.remove(player);
            _playerSavedOnMap.add(player);
        }
    }

    public String lookForAnotherPlayer(Player player) {
        StringBuilder buf = new StringBuilder();
        for (Player player2 : _playerPlayOnMap) {
            if (player2.getPositionID() == player.getPositionID()) {
                if (player2 != player) {
                    buf.append((buf.length() == 0) ? "Here are: " : ", ");
                    buf.append(player2.getName());
                }
            }
        }
        return buf.toString();
    }

    public String lookForAllPlayerOnMap() {
        StringBuilder buf = new StringBuilder();
        for (Player player : _playerPlayOnMap) {
            buf.append((buf.length() == 0) ? "Connected are: " : ", ");
            buf.append(player.getName());
        }
        return buf.toString();
    }


    public String getName() {
        return _mapName;
    }
}

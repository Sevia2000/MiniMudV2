package at.fhv.ohe.miniMud.map;

import at.fhv.ohe.miniMud.game.ConnectionHandler;
import at.fhv.ohe.miniMud.map.FieldFunctions.ActionFunctions;
import at.fhv.ohe.miniMud.map.Fields.Field;
import at.fhv.ohe.miniMud.map.Fields.IllegalFieldEnterException;
import at.fhv.ohe.miniMud.map.Items.IConsumable;
import at.fhv.ohe.miniMud.map.Items.Items;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oliver H on 15.04.2017.
 */
public class Player implements Serializable {
    private static final String _NOWAYDESCRIP = "There is no Way...";

    private static final int _maxWeight = 200;
    private static final int _maxHealth = 100;

    private String _name;
    private int _playerID;
    private int _health;
    private Field _position;
    private boolean _death;
    private transient MapController _mapController;
    private transient ConnectionHandler _outStream;

    private List<Items> _carry;

    public Player(String name, int playerID, Field position, MapController mapController) {
        _name = name;
        _playerID = playerID;
        _health = _maxHealth;
        _position = position;
        _mapController = mapController;
        _carry = new LinkedList<>();
    }

    //Getter /Setter
    public String getName() {
        return _name;
    }

    public int getID() {
        return _playerID;
    }

    public int getPositionID() {
        return _position.getFieldID();
    }

    public void setOutStream(ConnectionHandler outStream) {
        _outStream = outStream;
    }

    public MapController getMapController() {
        return _mapController;
    }

    public void setMapController(MapController mapController) {
        _mapController = mapController;
    }

    // Inventory Action
    public List<Items> getAllItemsfromType(Class Item) {
        List<Items> returnList = new LinkedList<>();
        for (Items item : _carry) {
            if (Item.isInstance(item)) {
                returnList.add(item);
            }
        }
        return Collections.unmodifiableList(returnList);
    }

    public void deleteFromInventory(Items item) {
        _carry.remove(item);
    }

    public void addToInventorry(Items supplyOf) {
        int weight = supplyOf.getWeight();
        for (Items item : _carry) {
            weight += item.getWeight();
        }
        if (weight <= _maxWeight) {
            _carry.add(supplyOf);
        }
    }

    public void consume(IConsumable item) {
        item.consumeBy(this);
    }

    // Player Stats
    public void healthIncrease(int of) {
        _health += of;
        if (_health > _maxHealth) {
            _health = _maxHealth;
        }
    }

    public void healthReduction(int of) {
        _health -= of;
        if (_health < 0) {
            _death = true;
        }
    }

    // Player Possibility's
    public void move(Directions direction) {
        try {
            _position = _position.getFieldBindings(direction).enter(this);
        } catch (IllegalFieldEnterException e) {
            playerOutputStream(e.getMessage());
        } catch (NullPointerException e) {
            playerOutputStream(_NOWAYDESCRIP);
        }
    }

    public void action(ActionFunctions actionFunctions) {
        _position.action(this, actionFunctions);
    }

    public void lookInventorry(List<Items> listOfItems) {
        StringBuilder buf = new StringBuilder();
        int i = 0;
        for (Items item : listOfItems) {
            buf.append("\r\n <");
            buf.append(i);
            buf.append("> ");
            buf.append(item.getShortDescription());
        }
        playerOutputStream(buf.toString());
    }

    public void lookAround() {
        StringBuilder buf = new StringBuilder();
        for (Directions dir : Directions.values()) {
            buf.append(dir);
            buf.append(" - ");
            Field temp = _position.getFieldBindings(dir);
            buf.append((temp == null) ? _NOWAYDESCRIP : temp.getShortDescription());
            buf.append("\r\n");
        }
        playerOutputStream(buf.toString());
    }

    public void lookHere() {
        StringBuilder buf = new StringBuilder();
        buf.append(_position.getShortDescription());
        buf.append("\r\n-> ");
        buf.append(_position.getLongDescription());
        buf.append("\r\n- ");
        buf.append(_position.getFunctionDescription());
        buf.append("\r\n");
        buf.append(_mapController.lookForAnotherPlayer(this));
        playerOutputStream(buf.toString());
    }

    public void logOutFromGame() {
        _mapController.logout(this);
    }

    public synchronized void playerOutputStream(String s) {
        if (_outStream == null) {
            System.out.println(s);
        } else {
            _outStream.sendToClient("\r\n" + s);
        }
    }

}

package at.fhv.ohe.miniMud.map.Fields;

import at.fhv.ohe.miniMud.map.Directions;
import at.fhv.ohe.miniMud.map.FieldFunctions.ActionFunctions;
import at.fhv.ohe.miniMud.map.FieldFunctions.FieldFunction;
import at.fhv.ohe.miniMud.map.FieldFunctions.IFieldFunction;
import at.fhv.ohe.miniMud.map.Player;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Describes a basic Field in the Mud
 * <p>
 * Created by Oliver H on 15.04.2017.
 */
public abstract class Field implements Serializable {
    // Default Strings
    private static final String _ONFUNCTIONNULL = "There is nothing to do..";
    private static final String _ONNOSHORTDESCR = "A Field.";
    private static final String _ONNOLONGDESCR  = "The Map Designer was to lazy to give this Field a proper description :(";

    private Map<Directions, Field> _fieldBindings;

    private int _fieldID;
    private IFieldFunction _fieldFunction;
    private String _shortDescription;
    private String _longDescription;

    public Field(int fieldID) {
        this(fieldID, _ONNOSHORTDESCR, _ONNOLONGDESCR);
    }

    public Field(int fieldID, String shortDescription, String longDescription) {
        this(fieldID,shortDescription,longDescription, null);
    }

    public Field(int fieldID, String shortDescription, String longDescription, IFieldFunction fieldFunction) {
        _fieldID = fieldID;
        _fieldFunction = fieldFunction;
        _shortDescription = shortDescription;
        _longDescription = longDescription;
        _fieldBindings = new EnumMap<>(Directions.class);
    }

    public abstract Field enter(Player player) throws IllegalFieldEnterException;

    public void action(Player player, ActionFunctions actionFunctions) {
        if (_fieldFunction != null) {
            player.playerOutputStream(_ONFUNCTIONNULL);
        } else {
            _fieldFunction.execude(player,actionFunctions);
        }
    }

    public void bindFields(Field field, Directions direction) {
        _fieldBindings.put(direction, field);
        field._fieldBindings.put(Directions.getReverse(direction), this);
    }

    public void addFunktion(IFieldFunction fieldFunction) {
        _fieldFunction = fieldFunction;
    }

    public Field getFieldBindings(Directions direction) {
        return _fieldBindings.get(direction);
    }

    public String getFunctionDescription() {
        return (_fieldFunction == null) ? _ONFUNCTIONNULL : _fieldFunction.getDescription();
    }

    public String getShortDescription() {
        return _shortDescription;
    }

    public String getLongDescription() {
        return _longDescription;
    }

    public int getFieldID() {
        return _fieldID;
    }
}

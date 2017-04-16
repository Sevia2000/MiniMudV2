package at.fhv.ohe.miniMud.map.FieldFunctions;

import java.io.Serializable;

/**
 * Created by Oliver H on 15.04.2017.
 */
public abstract class FieldFunction implements Serializable {
    private String _description;

    public FieldFunction(String description) {
        _description = description;
    }

    public String getDescription() {
        return _description;
    }
}

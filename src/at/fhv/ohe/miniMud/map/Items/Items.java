package at.fhv.ohe.miniMud.map.Items;

import java.io.*;


/**
 * Describes an item
 * <p>
 * Created by Oliver H on 15.04.2017.
 */
public abstract class Items implements Serializable {
    private static final String _ONNOSHORTDESCR = "A Item";
    private static final String _ONNOLONGDESCR = "The Map Designer was to lazy to give this Item a proper description :(";

    private int _ItemID;
    private int _weight;
    private String _shortDescription;
    private String _longDescription;

    public Items(int itemID, int weight) {
        this(itemID, weight, _ONNOSHORTDESCR, _ONNOLONGDESCR);
    }

    public Items(int itemID, int weight, String shortDescription, String longDescription) {
        _ItemID = itemID;
        _weight = weight;
        _shortDescription = shortDescription;
        _longDescription = longDescription;
    }

    public static Items clone(Items item) {
        try {
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(arrayOutputStream);
            outputStream.writeObject(item);

            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
            ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
            return (Items) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getItemID() {
        return _ItemID;
    }

    public int getWeight() {
        return _weight;
    }

    public String getShortDescription() {
        return _shortDescription;
    }

    public String getLongDescription() {
        return _longDescription;
    }
}

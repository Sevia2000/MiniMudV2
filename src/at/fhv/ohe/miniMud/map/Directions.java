package at.fhv.ohe.miniMud.map;

/**
 * Created by Oliver H on 15.04.2017.
 */
public enum Directions {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    private final int Value;

    Directions(int i) {
        Value = i;
    }

    public static Directions getReverse(Directions direction) {
        switch ((direction.getValue() + 2) % 4) {
            case 0:
                return NORTH;
            case 1:
                return EAST;
            case 2:
                return SOUTH;
            case 3:
                return WEST;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int getValue() {
        return Value;
    }
}

package at.fhv.ohe.miniMud;


import at.fhv.ohe.miniMud.map.Directions;
import at.fhv.ohe.miniMud.map.FieldFunctions.MultiSupplyFunction;
import at.fhv.ohe.miniMud.map.FieldFunctions.SingleSupplyFunction;
import at.fhv.ohe.miniMud.map.Fields.Carpet;
import at.fhv.ohe.miniMud.map.Fields.Door;
import at.fhv.ohe.miniMud.map.Fields.Field;
import at.fhv.ohe.miniMud.map.Items.Beer;
import at.fhv.ohe.miniMud.map.Items.Key;
import at.fhv.ohe.miniMud.map.MapController;

/**
 * Created by Oliver H on 15.04.2017.
 */
public class TestNewMapCreaton {


    public static void main(String[] args) {

        Field[] fields = new Field[37];

        fields[0] = new Carpet(0, "There is the center of the city of Keldiskor", "You stand in the center of the City of Keldiskor. Capitol of the Keldis Empire and home to the great nobles. Here in the center square stands a large bronze statue of Kael the Great, first ruler of the Elemental lands, gazing endlessly to the distant North. A small metal bench has been placed here for travellers to rest on.");
        fields[1] = new Carpet(1, "There is the Jewel street, to the east is Ragge's general shop, and west is Mab's armory", "Crowds of people swarm the streets here yelling and shouting as they make their way on errands that are far more imporant than everyone else's, while several guards are trying to maintain some measure of peace and prevent any real brawls. The crowds stream north and south along Jewel street, and East into the general store. West is the armory into which flows a slightly hardier stock of people.");
        fields[2] = new Carpet(2, "There is the South of the main square.", "You are South of the main sqaure. The corner of the large castle of the Sha'tals rises here marking the southern end of the square.");
        fields[3] = new Carpet(3, "There is a small flower garden in the Northwest corner of Kalstor.", "A small flower garden has been planted in the northwestern corner of the square making it quite pleasent here for anyone who enjoys flowers. Crowds of people come here to rest and relax, away from the crowds that fill the rest of the square. A small sign has been placed by the garden.");
        fields[4] = new Carpet(4, "There is a large fountain here.", "The large fountain of peace rises up towering nearly fifty feet into the air sending a large spray of water cascading down to a pool at the bottom. It is said that anyone who drinks near the fountain will have good luck for a long time.");
        fields[5] = new Carpet(5, "The traders guildhall is here and a large stone wall rises to the south.", "Long ago before the city of Kalstor existed, a great king built a fortress just south of here. The tall walls rise blocking your path even now centuries later. Many traders came to sell goods to the king withing the fortress building a large guildhall next to the fortress. This guildhall has decayed some over the years but still remains here.");
        fields[6] = new Carpet(6, "The Southeastern corner of the central square is relatively empty.", "The Southeastern corner of the central square is relatively empty. Ocean lane connects to the central square here leading between the docks and the center of the city.");
        fields[7] = new Carpet(7, "There is a fruit seller here.", "On the Eastern end of the great square a trader has set up shop selling fruits from a small wooden cart.");
        fields[8] = new Carpet(8, "The Northeastern end of the square.", "The Northeastern end of the square is dominated by the great library. Floating high above the city on five pillars, one of each type of magic.");
        fields[9] = new Carpet(9, "The Trader's Guild main hall.", "The Trader's Guild Main hall is clearly not what it used to be, worn down by the ages and lack of repair. The high stone walls are crumbling all around and you think the whole thing may cave in at anytime");
        fields[10] = new Door(10, 1,"The front Door of the Trader's Guild main hall.", "The front Door of the Trader's Guild main hall. The Door looks worn down by the ages and lack of repair. ");
        fields[11] = new Carpet(11, "The Ocean Lane, just southwest of the main square.", "Ocean Lane runs between the town square just to the North-West and the docks to the east. Many travelers walk the roads, on their way into the city from some recently docked ship or into the store to see what new imports have arrived.");
        fields[12] = new Carpet(12, "The Ocean Lane, just southwest of the main square.", "Ocean Lane runs between the town square just to the North-West and the docks to the east. Many travelers walk the roads, on their way into the city from some recently docked ship or into the store to see what new imports have arrived.");
        fields[13] = new Carpet(13, "A interscetion of Ocean lane and Tiger's way", "A large streetlamp marks the interscetion of Ocean lane and Tiger's way");
        fields[14] = new Carpet(14, "Tiger's way bends south and east of here. ", "Tiger's way bends south and east of here. A large metal wall rises to the north.");
        fields[15] = new Carpet(15, "Large warehouses tower both north ands south of here.", "Large warehouses tower both north ands south of here. There is a larger metal door with the sigal of a phoenix rising from it's ashes in the middle");
        fields[16] = new Carpet(16, "The end of the Tiger´s way.", "A large sign marks the end of Tiger's way to the southwest and Gorn's trot to the south. The sign has been painted over with the words, \" Death to the orkish slimes \"");
        fields[17] = new Door(17,2, "A larger metal door with the sigal of a phoenix rising from it's ashes", "A very large door with the sigal of a phoenix rising from it's ashes"); // KEY
        fields[18] = new Door(18,0 );
        fields[19] = new Door(19,0 );
        fields[20] = new Door(20,0 );
        fields[21] = new Door(21,0 );
        fields[22] = new Door(22,0 );
        fields[4].addFunktion(new MultiSupplyFunction(new Beer(1),"A basket full of beer"));
        fields[8].addFunktion(new SingleSupplyFunction(new Key(1,1)));
        fields[9].addFunktion(new SingleSupplyFunction(new Key(1,2)));

        fields[0].bindFields(fields[4], Directions.SOUTH);
        fields[0].bindFields(fields[2], Directions.NORTH);
        fields[0].bindFields(fields[7], Directions.EAST);
        fields[0].bindFields(fields[3], Directions.WEST);
        fields[1].bindFields(fields[2], Directions.EAST);
        fields[1].bindFields(fields[3], Directions.SOUTH);
        fields[1].bindFields(fields[20], Directions.WEST);
        fields[2].bindFields(fields[0], Directions.SOUTH);
        fields[2].bindFields(fields[13], Directions.NORTH);
        fields[2].bindFields(fields[8], Directions.EAST);
        fields[2].bindFields(fields[1], Directions.WEST);
        fields[3].bindFields(fields[0], Directions.EAST);
        fields[3].bindFields(fields[11], Directions.SOUTH);
        fields[3].bindFields(fields[1], Directions.NORTH);
        fields[3].bindFields(fields[21], Directions.WEST);
        fields[4].bindFields(fields[5], Directions.SOUTH);
        fields[4].bindFields(fields[0], Directions.NORTH);
        fields[4].bindFields(fields[6], Directions.EAST);
        fields[4].bindFields(fields[11], Directions.WEST);
        fields[4].bindFields(fields[5], Directions.SOUTH);
        fields[4].bindFields(fields[0], Directions.NORTH);
        fields[4].bindFields(fields[6], Directions.EAST);
        fields[4].bindFields(fields[11], Directions.WEST);
        fields[1].bindFields(fields[2], Directions.EAST);
        fields[1].bindFields(fields[3], Directions.SOUTH);
        fields[1].bindFields(fields[20], Directions.WEST);
        fields[3].bindFields(fields[0], Directions.EAST);
        fields[3].bindFields(fields[11], Directions.SOUTH);
        fields[3].bindFields(fields[1], Directions.NORTH);
        fields[3].bindFields(fields[21], Directions.WEST);
        fields[5].bindFields(fields[10], Directions.EAST);
        fields[5].bindFields(fields[4], Directions.NORTH);
        fields[5].bindFields(fields[10], Directions.EAST);
        fields[5].bindFields(fields[4], Directions.NORTH);
        fields[6].bindFields(fields[4], Directions.WEST);
        fields[6].bindFields(fields[7], Directions.NORTH);
        fields[1].bindFields(fields[2], Directions.EAST);
        fields[1].bindFields(fields[3], Directions.SOUTH);
        fields[1].bindFields(fields[20], Directions.WEST);
        fields[7].bindFields(fields[0], Directions.WEST);
        fields[7].bindFields(fields[6], Directions.SOUTH);
        fields[7].bindFields(fields[8], Directions.NORTH);
        fields[7].bindFields(fields[19], Directions.EAST);
        fields[8].bindFields(fields[2], Directions.WEST);
        fields[8].bindFields(fields[7], Directions.SOUTH);
        fields[8].bindFields(fields[18], Directions.EAST);
        fields[8].bindFields(fields[22], Directions.NORTH);
        fields[1].bindFields(fields[2], Directions.EAST);
        fields[1].bindFields(fields[3], Directions.SOUTH);
        fields[1].bindFields(fields[20], Directions.WEST);
        fields[8].bindFields(fields[2], Directions.WEST);
        fields[8].bindFields(fields[7], Directions.SOUTH);
        fields[8].bindFields(fields[18], Directions.EAST);
        fields[8].bindFields(fields[22], Directions.NORTH);
        fields[9].bindFields(fields[10], Directions.WEST);
        fields[10].bindFields(fields[9], Directions.EAST);
        fields[10].bindFields(fields[5], Directions.WEST);
        fields[11].bindFields(fields[4], Directions.EAST);
        fields[11].bindFields(fields[3], Directions.NORTH);
        fields[4].bindFields(fields[5], Directions.SOUTH);
        fields[4].bindFields(fields[0], Directions.NORTH);
        fields[4].bindFields(fields[6], Directions.EAST);
        fields[4].bindFields(fields[11], Directions.WEST);
        fields[11].bindFields(fields[4], Directions.EAST);
        fields[11].bindFields(fields[3], Directions.NORTH);
        fields[4].bindFields(fields[5], Directions.SOUTH);
        fields[4].bindFields(fields[0], Directions.NORTH);
        fields[4].bindFields(fields[6], Directions.EAST);
        fields[4].bindFields(fields[11], Directions.WEST);
        fields[11].bindFields(fields[4], Directions.EAST);
        fields[11].bindFields(fields[3], Directions.NORTH);
        fields[13].bindFields(fields[2], Directions.SOUTH);
        fields[13].bindFields(fields[14], Directions.NORTH);
        fields[13].bindFields(fields[22], Directions.EAST);
        fields[13].bindFields(fields[2], Directions.SOUTH);
        fields[13].bindFields(fields[14], Directions.NORTH);
        fields[13].bindFields(fields[22], Directions.EAST);
        fields[14].bindFields(fields[13], Directions.SOUTH);
        fields[14].bindFields(fields[15], Directions.WEST);
        fields[15].bindFields(fields[14], Directions.EAST);
        fields[15].bindFields(fields[17], Directions.WEST);
        fields[16].bindFields(fields[17], Directions.EAST);
        fields[17].bindFields(fields[15], Directions.EAST);
        fields[17].bindFields(fields[16], Directions.WEST);
        fields[18].bindFields(fields[8], Directions.WEST);
        fields[19].bindFields(fields[7], Directions.WEST);
        fields[20].bindFields(fields[1], Directions.EAST);
        fields[21].bindFields(fields[3], Directions.EAST);
        fields[22].bindFields(fields[8], Directions.SOUTH);
        fields[22].bindFields(fields[13], Directions.WEST);

        MapController testController = new MapController(fields[0], "The rise Of Keldiskor");
        testController.saveMap("map1.map");
    }
}

package agh.ics.oop;
import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    @Test
    public void testSimulation1() {
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r"});
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        RectangularMap map = new RectangularMap(5, 5);

        Simulation simulation = new Simulation(positions, directions,map);
        simulation.run();

        // Verify orientation and positions of animals
        assertEquals(MapDirection.NORTH, ((Animal)map.objectAt(new Vector2d(3,3))).getOrientation());
        assertEquals(MapDirection.SOUTH, ((Animal)map.objectAt(new Vector2d(2,3))).getOrientation());
    }

    @Test
    public void testSimulation2() {
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"b", "r", "f", "f", "l", "l", "r", "f", "l", "f", "b"});
        List<Vector2d> positions = List.of(new Vector2d(0, 0), new Vector2d(3, 4), new Vector2d(7,2));
        RectangularMap map = new RectangularMap(9, 5);

        Simulation simulation = new Simulation(positions, directions,map);
        simulation.run();

        // Verify orientation and positions of animals
        assertEquals(MapDirection.EAST, ((Animal)map.objectAt(new Vector2d(1,1))).getOrientation());
        assertEquals(MapDirection.SOUTH, ((Animal)map.objectAt(new Vector2d(7,3))).getOrientation());
        assertEquals(MapDirection.NORTH, ((Animal)map.objectAt(new Vector2d(3,3))).getOrientation());
    }

    @Test
    public void testInvalidInput1() {
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "r", "b", "l", "f", "f", "r", "r"});
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(-1,-1), new Vector2d(5,5));
        RectangularMap map = new RectangularMap(5, 5);

        Simulation simulation = new Simulation(positions,directions,map);
        simulation.run();
        // Verify orientation and positions of animals
        assertEquals(MapDirection.EAST, ((Animal)map.objectAt(new Vector2d(3,4))).getOrientation());
        assertEquals(MapDirection.EAST, ((Animal)map.objectAt(new Vector2d(2,3))).getOrientation());
    }

//    @Test
//    public void testInvalidInput2() {
//        List<MoveDirection> directions = OptionsParser.parse(new String[]{"l", "l", "f", "r", "BUG", "f", "f", "r", "r", "BUG","f"});
//        assertThrows(IllegalAccessException.class, () -> {
//            OptionsParser.parse(args);
//        });
//        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(100,-1), new Vector2d(5,5), new Vector2d(18,3));
//        RectangularMap map = new RectangularMap(101, 5);
//
//        Simulation simulation = new Simulation(positions,directions,map);
//        simulation.run();
//        // Verify orientation and positions of animals
//        assertEquals(MapDirection.EAST, ((Animal)map.objectAt(new Vector2d(2,2))).getOrientation());
//        assertEquals(MapDirection.NORTH, ((Animal)map.objectAt(new Vector2d(2,4))).getOrientation());
//        assertEquals(MapDirection.NORTH, ((Animal)map.objectAt(new Vector2d(18,4))).getOrientation());
//    }
}

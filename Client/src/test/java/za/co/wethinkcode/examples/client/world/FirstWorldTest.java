package za.co.wethinkcode.examples.client.world;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstWorldTest {


    @Test
    void checkVariablesInitialised(){
        FirstWorld firstWorld = new FirstWorld();


        assertEquals((Integer.parseInt((firstWorld.properties.getProperty("number_of_shots_type_shortrange")))),  firstWorld.default_shots_amount_short_range );
        assertEquals((Integer.parseInt((firstWorld.properties.getProperty("number_of_shots_type_longrange")))),  firstWorld.default_shots_amount_long_range  );
        assertEquals((Integer.parseInt((firstWorld.properties.getProperty("shot_distance_type_shortrange")))),  firstWorld. default_shots_distance_short_range );
        assertEquals((Integer.parseInt((firstWorld.properties.getProperty("shot_distance_type_longrange")))),  firstWorld.   default_shots_distance_long_range );

    }
}
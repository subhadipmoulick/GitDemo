package stepDefinition;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@deletePlace")
    public void beforeScenerio() throws IOException {
        stepDefinition  m =new stepDefinition();
        if(stepDefinition.place_id == null) {
            m.add_Place_Payload_with("Moulick", "Bengali", "Kolkata");
            m.user_calls_with_http_request("addPlaceAPI", "POST");
            m.verify_place_id_created_maps_to_using("Moulick", "getPlaceAPI");
        }


    }
}

package com.dikshant.userlocation;

import com.dikshant.userlocation.controllers.UserLocationController;
import com.dikshant.userlocation.models.UserLocation;
import com.dikshant.userlocation.services.IUserLocationService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class UserLocationApplicationTests {

    private final IUserLocationService userLocationService = mock(IUserLocationService.class);

    private final UserLocationController userLocationController = new UserLocationController(userLocationService);

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userLocationController).build();

    @Test
    public void testCreateUserLocationTable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user_location/create_data"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("user_location table created"));
    }

    @Test
    public void testUpdateUserLocation() throws Exception {
        UserLocation userLocation = new UserLocation();
        userLocation.setName("Balaji R");
        userLocation.setLatitude(37.7749);
        userLocation.setLongitude(-122.4194);

        when(userLocationService.save(any(UserLocation.class))).thenReturn(userLocation);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user_location/update_data")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Balaji R\",\n" +
                                "    \"latitude\": 37.7749,\n" +
                                "    \"longitude\": -122.4194\n" +
                                "}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User location updated"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userLocation.name").value("Balaji R"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userLocation.latitude").value(37.7749))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userLocation.longitude").value(-122.4194));
    }

    @Test
    public void testGetNearestUsers() throws Exception {
        UserLocation user1 = new UserLocation();
        user1.setName("Balaji R");
        user1.setLatitude(37.7749);
        user1.setLongitude(-122.4194);

        UserLocation user2 = new UserLocation();
        user2.setName("Jadeja");
        user2.setLatitude(23.7749);
        user2.setLongitude(-22.4194);

        List<UserLocation> users = Arrays.asList(user1, user2);

        when(userLocationService.findNearest(2, 0.0, 0.0)).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user_location/get_users/2")
                        .param("latitude", "0.0")
                        .param("longitude", "0.0"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.users[0].name").value("Balaji R"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.users[1].name").value("Jadeja"));
    }

}

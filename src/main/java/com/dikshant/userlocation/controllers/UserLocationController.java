package com.dikshant.userlocation.controllers;

import com.dikshant.userlocation.models.UserLocation;
import com.dikshant.userlocation.services.IUserLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user_location")
public class UserLocationController {
    @Autowired
    IUserLocationService userLocationService;

    public UserLocationController(IUserLocationService userLocationService) {
        this.userLocationService = userLocationService;
    }

    /**
     * Endpoint for creating the user_location table in the database.
     *
     * @return A message indicating that the table was created successfully.
     */
    @PostMapping("/create_data")
    @ResponseStatus(HttpStatus.OK)
    public String createUserLocationTable() {
        userLocationService.createTable();
        return "user_location table created";
    }

    /**
     * Endpoint for updating a user's location in the database.
     *
     * @param userLocation The UserLocation object representing the updated user location.
     * @return The updated UserLocation object with generated id and the message indicating that user's location was updated.
     */
    @PostMapping("/update_data")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> updateUserLocation(
            @RequestBody UserLocation userLocation
    ) {
        var res = new HashMap<String, Object>();
        res.put("message", "User location updated");
        res.put("userLocation", userLocationService.save(userLocation));
        return res;
    }

    /**
     * Endpoint for retrieving a list of the nearest users to a given location.
     *
     * @param limit     The maximum number of users to return.
     * @param latitude  The latitude of the target location.
     * @param longitude The longitude of the target location.
     * @return A list of UserLocation objects representing the nearest users.
     */
    @GetMapping("/get_users/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<UserLocation>> getNearestUsers(
            @PathVariable Integer limit, @RequestParam Double latitude, @RequestParam Double longitude
    ) {
        var res = new HashMap<String, List<UserLocation>>();
        res.put("users", userLocationService.findNearest(limit, latitude, longitude));
        return res;
    }

}

package com.dikshant.userlocation.repositories;

import com.dikshant.userlocation.models.UserLocation;

import java.util.List;

public interface IUserLocationRepo {
    void createTable();
    UserLocation save(UserLocation userLocation);
    List<UserLocation> findNearest(Integer limit, Double latitude, Double longitude);
}

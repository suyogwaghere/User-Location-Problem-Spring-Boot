package com.dikshant.userlocation.services;

import com.dikshant.userlocation.models.UserLocation;
import com.dikshant.userlocation.repositories.IUserLocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserLocationService implements IUserLocationService {

    @Autowired
    IUserLocationRepo userLocationRepo;

    @Override
    public void createTable() {
        userLocationRepo.createTable();
    }

    @Override
    public UserLocation save(UserLocation userLocation) {
        return userLocationRepo.save(userLocation);
    }

    @Override
    public List<UserLocation> findNearest(Integer limit, Double latitude, Double longtitude) {
        return userLocationRepo.findNearest(limit, latitude, latitude);
    }
}

package com.example.library.service.Implem;

import com.example.library.entities.City;
import com.example.library.repositories.CityRepository;
import com.example.library.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;
    @Override
    public List<City> getAll(){
        return null;
    }
}

package ru.ifmo.pashaac.treii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.pashaac.treii.domain.Venue;
import ru.ifmo.pashaac.treii.domain.foursquare.PlaceType;
import ru.ifmo.pashaac.treii.service.CityService;
import ru.ifmo.pashaac.treii.service.VenueService;
import ru.ifmo.pashaac.treii.service.data.MachineLearningService;
import ru.ifmo.pashaac.treii.service.miner.QuadTreeMinerService;

import java.util.List;

/**
 * Created by Pavel Asadchiy
 * on 0:06 13.10.17.
 */
@RestController
@RequestMapping("/nightLifeSpots")
@Deprecated // TODO: temporary deprecated due to development / investigation on attractions side
@CrossOrigin
public class NightLifeSpotsController extends DataController {

    @Autowired
    public NightLifeSpotsController(CityService cityService, VenueService venueService, QuadTreeMinerService quadTreeMinerService, MachineLearningService machineLearningService) {
        super(cityService, venueService, quadTreeMinerService, machineLearningService);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Venue> nightLifeSpots(@RequestParam double lat, @RequestParam double lng) {
        return data(lat, lng, PlaceType.getNightLifeSpots());
    }

    @RequestMapping(value = "/force", method = RequestMethod.GET)
    public List<Venue> nightLifeSpotsForce(@RequestParam double lat, @RequestParam double lng) {
        nightLifeSpotsRemove(lat, lng);
        return nightLifeSpots(lat, lng);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public void nightLifeSpotsRemove(@RequestParam double lat, @RequestParam double lng) {
        data_remove(lat, lng, PlaceType.getNightLifeSpots());
    }

}

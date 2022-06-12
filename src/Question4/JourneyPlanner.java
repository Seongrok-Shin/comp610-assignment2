/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question4;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ssr7324
 */
public class JourneyPlanner {

    private Map<String, Set<BusTrip>> locationMap;

    public JourneyPlanner() {
        locationMap = new HashMap<>();
    }

    public LinkedList<BusJourney> getPossibleJourneys(String startLocation, LocalTime startTime, String endLocation, LocalTime endTime) throws NullPointerException {
        LinkedList<BusJourney> busJourneys = new LinkedList<>();
        BusJourney currentBusJourney = new BusJourney();

        return busJourneys;
    }

    public boolean add(BusTrip bus) {
        if (locationMap.containsKey(bus.getDepartLocation())) {

            HashSet<BusTrip> hashSet = (HashSet) locationMap.get(bus.getDepartLocation());
            hashSet.add(bus);

            return true;
        } else {
            return false;
        }
    }

    private void findPaths(String startLocation, LocalTime startTime, String endLocation, LocalTime endTime, BusJourney currentBusJourney, LinkedList<BusJourney> busJourneys) {

    }

}

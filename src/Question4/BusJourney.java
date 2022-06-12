/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question4;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author ssr7324
 */
public class BusJourney {

    private LinkedList<BusTrip> busList;

    public BusJourney() {
        this.busList = new LinkedList<>();
    }

    public BusJourney(LinkedList<BusTrip> list) {
        busList = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            this.addBus(list.get(i));
        }
    }

    boolean addBus(BusTrip bus) {
        if (!busList.isEmpty()) {

            if (Objects.isNull(getDestination())) {
                return true;
            } else if (getDestination() == null ? bus.getDepartLocation() == null
                    : getDestination().equals(bus.getDepartLocation())) {

                busList.add(bus);
                return true;
            }
        }

        return false;
    }

    public boolean removeLastTrip() {
        if (!busList.isEmpty()) {

            busList.remove(busList.getLast());
            return true;
        }

        return false;
    }

    public boolean containsLocation(String location) {
        for (int i = 0; i < busList.size(); i++) {
            return ((location == null ? busList.get(i).getArrivalLocation() == null : location.equals(busList.get(i).getArrivalLocation()))
                    || (location == null ? busList.get(i).getDepartLocation() == null : location.equals(busList.get(i).getDepartLocation())));
        }

        return false;
    }

    public String getOrigin() {
        return busList.getFirst().getDepartLocation();
    }

    public String getDestination() {
        if (!busList.isEmpty()) {
            return busList.get(busList.size() - 1).getArrivalLocation();
        }

        return null;
    }

    public LocalTime getOriginTime() {
        return busList.get(0).getDepartTime();
    }

    public LocalTime getDestinationTime() {
        return busList.getLast().getArrivalTime();
    }

    public BusJourney cloneJourney() {
        return new BusJourney(busList);
    }

    public int getNumberOfBusTrips() {
        return busList.size();
    }

    public double getTotalCost() {
        double totalCost = 0.0;

        for (int i = 0; i < getNumberOfBusTrips(); i++) {
            totalCost += busList.get(i).getCost();
        }

        return totalCost;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < getNumberOfBusTrips(); i++) {
            output += "\nBus: " + busList.get(i).toString() + "\n";
        }
        output += "\nTotal Cost: " + getTotalCost();
        return output;
    }
}

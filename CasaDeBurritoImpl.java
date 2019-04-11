package OOP.Solution;

import OOP.Provided.CasaDeBurrito;
import java.util.*;
public class CasaDeBurritoImpl implements CasaDeBurrito {
    public int burritoId;
    public String burritoName;
    public int burritoDist;
    public Set<String> burritoMenu;
    public Map<Profesor, Integer> burritoRankByProfs;

    public CasaDeBurritoImpl(int id, String name, int dist, Set<String> menu) {
        burritoId = id;
        burritoName = name;
        burritoDist = dist;
        burritoMenu = menu.clone(); //TODO: check!
        burritoRankByProfs = new HashMap<Profesor, Integer>();
    }

    //Getters:
    public int getId() {
        return burritoId;
    }

    public String getName() {
        return burritoName;
    }

    public int distance() {
        return burritoDist;
    }

    //Other Methods:
    public boolean isRatedBy(Profesor p) {
        return burritoRankByProfs.containsKey(p);
    }

    public CasaDeBurrito rate(Profesor p, int r) throws RateRangeException {
        if (r < 0 || r > 5) {
            throw new RateRangeException();
        }
        burritoRankByProfs.put(p, r);
        return this;
    }

    public int numberOfRates() {
        return burritoRankByProfs.size();
    }

    public double averageRating() {
        double avg = 0;
        for (Integer rate : burritoRankByProfs.valueSet()) {
            avg += rate;
        }
        avg = avg / burritoRankByProfs.size();
        return avg;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof CasaDeBurritoImpl)) return false;
        CasaDeBurritoImpl other = (CasaDeBurritoImpl)o;
        return burritoId.equals(other.burritoId);
    }


}
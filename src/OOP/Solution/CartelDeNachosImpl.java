package OOP.Solution;

import OOP.Provided.CartelDeNachos;
import OOP.Provided.CasaDeBurrito;
import OOP.Provided.Profesor;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CartelDeNachosImpl implements CartelDeNachos {

    private Map<Integer, Profesor> profesorsMap;
    private Map<Integer, CasaDeBurrito> burritoMap;

    public CartelDeNachosImpl() {
        profesorsMap = new HashMap<>();
        burritoMap = new HashMap<>();
    }

    public Profesor joinCartel(int id, String name)
            throws Profesor.ProfesorAlreadyInSystemException {
        if (profesorsMap.containsKey(id)) throw new Profesor.ProfesorAlreadyInSystemException();
        Profesor newProf = new ProfesorImpl(id, name);
        profesorsMap.put(id, newProf);
        return newProf;
    }

    public CasaDeBurrito addCasaDeBurrito(int id, String name, int dist, Set<String> menu)
            throws CasaDeBurrito.CasaDeBurritoAlreadyInSystemException {
        if (burritoMap.containsKey(id)) throw new CasaDeBurrito.CasaDeBurritoAlreadyInSystemException();
        CasaDeBurrito newBuri = new CasaDeBurritoImpl(id, name, dist, menu);
        burritoMap.put(id, newBuri);
        return newBuri;
    }

    public Collection<Profesor> registeredProfesores() {
        return new HashSet<>(profesorsMap.values());
    }

    public Collection<CasaDeBurrito> registeredCasasDeBurrito() {
        return new HashSet<>(burritoMap.values());
    }

    public Profesor getProfesor(int id)
            throws Profesor.ProfesorNotInSystemException {
        if (!profesorsMap.containsKey(id)) throw new Profesor.ProfesorNotInSystemException();
        return profesorsMap.get(id);
    }

    public CasaDeBurrito getCasaDeBurrito(int id)
            throws CasaDeBurrito.CasaDeBurritoNotInSystemException {
        if (!burritoMap.containsKey(id)) throw new CasaDeBurrito.CasaDeBurritoNotInSystemException();
        return burritoMap.get(id);
    }

    public CartelDeNachos addConnection(Profesor p1, Profesor p2)
            throws Profesor.ProfesorNotInSystemException, Profesor.ConnectionAlreadyExistsException, Profesor.SameProfesorException {
        if (!profesorsMap.containsKey(p1) || !profesorsMap.containsKey(p2))
            throw new Profesor.ProfesorNotInSystemException();
        if (p1 == p2) throw new Profesor.SameProfesorException();
        Profesor prof_A = profesorsMap.get(p1);
        Profesor prof_B = profesorsMap.get(p2);
        if (prof_A.getFriends().contains(prof_B) || prof_B.getFriends().contains(prof_A))
            throw new Profesor.ConnectionAlreadyExistsException();
        prof_A.addFriend(prof_B);
        prof_B.addFriend(prof_A);
        return this;
    }

    public Collection<CasaDeBurrito> favoritesByRating(Profesor p)
            throws Profesor.ProfesorNotInSystemException {
        if (!profesorsMap.containsValue(p)) throw new Profesor.ProfesorNotInSystemException();
        return p.getFriends().stream()
                .sorted()
                .map(proFriend -> proFriend.favoritesByRating(0))
                .flatMap(casa -> casa.stream())
                .distinct()
                .collect(Collectors.toList());

    }

    public Collection<CasaDeBurrito> favoritesByDist(Profesor p)
            throws Profesor.ProfesorNotInSystemException {
        if (!profesorsMap.containsValue(p)) throw new Profesor.ProfesorNotInSystemException();
        int maxDist = p.getFriends().stream()
                .map(proFriend -> proFriend.favorites())
                .flatMap(casa -> casa.stream())
                .mapToInt(casa -> casa.distance()).max().orElse(0);
        return p.getFriends().stream()
                .sorted()
                .map(proFriend -> proFriend.favoritesByDist(maxDist))
                .flatMap(casa -> casa.stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public boolean getRecommendation(Profesor p, CasaDeBurrito c, int t)
            throws Profesor.ProfesorNotInSystemException, CasaDeBurrito.CasaDeBurritoNotInSystemException, ImpossibleConnectionException {
        if (!profesorsMap.containsValue(p)) throw new Profesor.ProfesorNotInSystemException();
        if (!burritoMap.containsValue(c)) throw new CasaDeBurrito.CasaDeBurritoNotInSystemException();
        if (t < 0) throw new ImpossibleConnectionException();
        Set<Profesor> profesorsTLength = new HashSet<>();
        Set<Profesor> profesorTempGroup = new HashSet<>();
        profesorTempGroup.add(p); //add
        for (int i = 0; i < t; i++) {
            for (Profesor pIterator : profesorsTLength) {
                profesorTempGroup.addAll(pIterator.getFriends());
            }
            profesorsTLength.addAll(profesorTempGroup);
        }
        return true;//TODO: להכניס את כל הפרופסורים שבמחרק טי מהנוכחי, ואז לעשות סינון למסעדות מועדפות
    }

    public List<Integer> getMostPopularRestaurantsIds() {
        return new ArrayList<>();//TODO:
    }

}

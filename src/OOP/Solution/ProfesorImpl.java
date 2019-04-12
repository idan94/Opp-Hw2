package OOP.Solution;

import OOP.Provided.CasaDeBurrito;
import OOP.Provided.Profesor;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProfesorImpl implements Profesor {
    private int profesorId;
    private String profesorName;
    private Set<CasaDeBurrito> favoriteBurrito;
    private Set<Profesor> profesorFriends;

    public ProfesorImpl(int id, String name) {
        profesorId = id;
        profesorName = name;
        favoriteBurrito = new HashSet<CasaDeBurrito>();
        profesorFriends = new HashSet<Profesor>();
    }

    public int getId() {
        return profesorId;
    }

    public Profesor favorite(CasaDeBurrito c)
            throws UnratedFavoriteCasaDeBurritoException {
        if (!c.isRatedBy(this)) throw new UnratedFavoriteCasaDeBurritoException();
        else {
            favoriteBurrito.add(c);
            return this;
        }
    }

    public Collection<CasaDeBurrito> favorites() {
        return new HashSet<CasaDeBurrito>(favoriteBurrito);
    }

    public Profesor addFriend(Profesor p)
            throws SameProfesorException, ConnectionAlreadyExistsException {
        if (this.equals(p)) throw new SameProfesorException();
        if (profesorFriends.contains(p)) throw new ConnectionAlreadyExistsException();
        profesorFriends.add(p);
        return this;
    }

    public Set<Profesor> getFriends() {
        return new HashSet<>(profesorFriends);
    }

    public Set<Profesor> filteredFriends(Predicate<Profesor> p) {
        return getFriends().stream().filter(p).collect(Collectors.toSet());
    }

    public Collection<CasaDeBurrito> filterAndSortFavorites(Comparator<CasaDeBurrito> comp, Predicate<CasaDeBurrito> p) {
        return favorites().stream().filter(p).sorted(comp).collect(Collectors.toList());
    }

    public Collection<CasaDeBurrito> favoritesByRating(int rLimit) {
        Comparator<CasaDeBurrito> comparator = Comparator.comparing(b -> b.averageRating());
        //TODO: reverse it + check if the want rate, its mean the avg rate
        comparator = comparator.thenComparing(Comparator.comparing(b -> b.distance()));
        comparator = comparator.thenComparing(Comparator.comparing(b -> b.getId()));
        return favorites().stream().filter((b) -> b.averageRating() >= rLimit)
                .sorted(comparator).collect(Collectors.toList());
    }

}

package OOP.Solution;

public class CasaDeBurritoImpl implements CasaDeBurrito {
    public int burritoId;
    public String burritoName;
    public int burritoDist;
    public Set<String> burritoMenu;


    public CasaDeBurritoImpl(int id, String name, int dist, Set<String> menu) {
        burritoId = id;
        burritoName = name;
        burritoDist = dist;
        burritoMenu = menu.clone(); //TODO: check!
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int distance() {

    }
    public isRatedBy(Profesor p){

    }
}
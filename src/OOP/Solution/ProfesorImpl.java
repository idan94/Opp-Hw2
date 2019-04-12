package OOP.Solution;

import OOP.Provided.CasaDeBurrito;
import OOP.Provided.Profesor;

import java.util.Collection;

public class ProfesorImpl implements Profesor {
    public int profesorId;
    public String profesorName;
    public CasaDeBurrito favoriteBurrito; //TODO: needs to be a SET!!!!!

    public ProfesorImpl(int id, String name) {
        profesorId = id;
        profesorName = name;
    }

    public int getId() {
        return profesorId;
    }

    public Profesor favorite(CasaDeBurrito c)
            throws UnratedFavoriteCasaDeBurritoException{
        if(!c.isRatedBy(this)) throw new UnratedFavoriteCasaDeBurritoException();
        else{
            favoriteBurrito = c;
            return this;
        }
    }

    public Collection<CasaDeBurrito> favorites(); //returns the SET

    public Profesor addFriend(Profesor p)
            throws SameProfesorException, ConnectionAlreadyExistsException;


}

package daos;

import java.util.List;

public interface CRUD<T> {

    T create(T t);

    List<T> readAll();

    void remove(int id);

    void update(T t);
}

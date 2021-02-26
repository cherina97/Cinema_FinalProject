package daos;

import java.util.List;

/**
 * The interface Crud.
 *
 * @param <T> the type parameter
 */
public interface CRUD<T> {

    /**
     * Create t.
     *
     * @param t the t
     * @return the t
     */
    T create(T t);

    /**
     * Read all list.
     *
     * @return the list
     */
    List<T> readAll();

    /**
     * Remove.
     *
     * @param id the id
     */
    void remove(int id);

    /**
     * Update t.
     *
     * @param t the t
     * @return the t
     */
    T update(T t);
}

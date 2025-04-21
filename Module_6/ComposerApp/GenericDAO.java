import java.util.List;

public interface GenericDAO<E, K> {
    List<E> findAll();
    E findBy(K key);
    void insert(E entity);
}

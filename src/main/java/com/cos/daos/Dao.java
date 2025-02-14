package com.cos.daos;

import java.util.List;
import java.util.Optional;

// Interface for the Data Access Object using generic type <T> (Type)
public interface Dao<T> {

    Optional<T> get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t, Object[] params);

    void delete(T t);
}
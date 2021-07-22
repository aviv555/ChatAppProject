package dao;

import java.util.List;

public interface IDao<T> {
    String file_path = null;

    void write();

    void write_lst(List<T> data);

    List<T> read();

    void remove(long id);

    void add(T new_model);
}

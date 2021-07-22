package dao;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hit.algorithm.AES;
import dm.User_In_Server;
import service.ChattingService;

public class UserDaoImpl implements IDao<User_In_Server> {
    private final String file_path;
    private List<User_In_Server> datasource;

    public UserDaoImpl(String file_path) {
        this.file_path = file_path;
        datasource = new ArrayList<>();
    }

    public String getFile_path() {
        return file_path;
    }

    public List<User_In_Server> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<User_In_Server> datasource) {
        this.datasource = datasource;
    }

    @Override
    public void write() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // create a writer
            Writer writer = Files.newBufferedWriter(Paths.get(this.file_path));

            // convert data object to JSON file
            gson.toJson(datasource, writer);

            // close writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void write_lst(List<User_In_Server> data) {
        try {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // create a writer
            Writer writer = Files.newBufferedWriter(Paths.get(this.file_path));

            // convert data object to JSON file
            gson.toJson(data, writer);

            // close writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User_In_Server> read() {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(this.file_path));

            // convert JSON array to list of users
            this.datasource = new Gson().fromJson(reader, new TypeToken<List<User_In_Server>>() {
            }.getType());

            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return this.datasource;
    }

    @Override
    public void remove(long phone_num) {
        this.datasource.removeIf(user -> user.getPhone_number() == phone_num);
        this.write();
    }

    @Override
    public void add(User_In_Server new_user) {
        for (User_In_Server user : datasource) {
            if (new_user.getPhone_number() == (user.getPhone_number()))
                return;
        }
        datasource.add(new_user);
        this.write();
    }
}


package service;
import com.hit.algorithm.IEncryptionAlgoFamily;
import dao.IDao;
import dao.UserDaoImpl;
import dm.User_In_Server;

public class ChattingService {
    private IEncryptionAlgoFamily<String> alg;
    private IDao<User_In_Server> user_db_handler;

    public ChattingService(IEncryptionAlgoFamily<String> algoFamily, String user_file_path) {
        this.alg = algoFamily;
        this.user_db_handler = new UserDaoImpl(user_file_path);
    }

    public IEncryptionAlgoFamily<String> getAlg() {
        return alg;
    }

    public void setAlg(IEncryptionAlgoFamily<String> alg) {
        this.alg = alg;
    }

    public IDao<User_In_Server> getUser_db_handler() {
        return user_db_handler;
    }

    public void setUser_db_handler(IDao<User_In_Server> user_db_handler) {
        this.user_db_handler = user_db_handler;
    }
}

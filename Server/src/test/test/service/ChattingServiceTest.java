package service;
import com.hit.algorithm.AES;
import com.hit.algorithm.IEncryptionAlgoFamily;
import dm.User_In_Server;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class ChattingServiceTest {

    ChattingService chattingService;
    List<User_In_Server> lst;

    @Before
    public void setUp() {

        IEncryptionAlgoFamily<String> algo = new AES();

        //test files path
        String user_path = "C:\\Users\\avivr\\IdeaProjects\\Chat_App\\src\\resources\\Test\\UserTestFile.json";
        String conversation_path = "C:\\Users\\avivr\\IdeaProjects\\Chat_App\\src\\resources\\Test\\ConversationTestFile.json";

        this.chattingService = new ChattingService(algo, user_path);
        this.lst = new ArrayList<>();

        Assert.assertNotNull("algo could not created", algo);
        Assert.assertNotNull("Chatting service not created", algo);
    }

    @Test
    public void write_and_read_test() {

        //adding user to a list
        User_In_Server user = new User_In_Server("user1", 1);
        this.lst.add(user);
        System.out.println(lst);

        //writing the user to the file
        this.chattingService.getUser_db_handler().add(user);

        //reading the file to a new list
        List<User_In_Server> new_lst = this.chattingService.getUser_db_handler().read();
        System.out.println(lst);

        for(User_In_Server user_in_list : new_lst)
        {
            Assert.assertEquals(user.getName(), user_in_list.getName());
            Assert.assertEquals(user.getPhone_number(), user_in_list.getPhone_number());
        }
    }

    @Test
    public void duplicates_test() {

        //adding user and replica to list
        User_In_Server user = new User_In_Server("user1", 1);
        //complete replica
        User_In_Server user_replica1 = new User_In_Server("user1", 1);

        //our DB should ignore phone number we already have in our DB even if the name is the same
        User_In_Server user_replica2 = new User_In_Server("user2", 1);

        this.lst.add(user);
        this.lst.add(user_replica1);
        this.lst.add(user_replica2);

        //writing users to file
        this.chattingService.getUser_db_handler().add(user);

        //reading file to a new list
        List<User_In_Server> new_lst = this.chattingService.getUser_db_handler().read();
        System.out.println(new_lst);

        //we expect the new list to hold only one replica
        Assert.assertEquals(1, new_lst.size());
    }
}
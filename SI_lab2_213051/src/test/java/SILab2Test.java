import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    User user;
    List<User> userList = new ArrayList<>();

    @Test
    void testEveryBranch(){
        //TEST1
        RuntimeException ex;
        //user = new User("username",null,"email");
        user=null;
        ex = assertThrows(RuntimeException.class, ()-> SILab2.function(user, userList));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        //TEST2 - creating new user, adding the same user to userList
        user = new User("user","userUSER$$","@@..");
        userList.add(user);
        assertFalse(SILab2.function(user,userList));

        //TEST3 - adding a different user to userList
        userList.remove(user);
        user = new User("user","userUSER", "@@..");
        User user1 = new User("name","pass","okayyy");
        userList.add(user1);
        assertFalse(SILab2.function(user,userList));

        //TEST4 - username==null, short password and email without @ and .
        user = new User(null,"lol","lol_email");
        assertFalse(SILab2.function(user,userList));

        //TEST5 - password with blank space
        user = new User("user","@@..","user USER$$");
        assertFalse(SILab2.function(user,userList));
    }

    //(user==null || user.getPassword()==null || user.getEmail()==null)
    @Test
    void multipleConditionTest(){
        //TEST1 - all conditions are false
      user = new User("username","pass","email");
      userList.add(user);
      assertFalse(SILab2.function(user,userList));

      RuntimeException ex;

      //TEST2 - FTX
      user = new User("username", null, null);
        ex = assertThrows(RuntimeException.class, ()-> SILab2.function(user,userList));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        //TEST3 - FFX
       user = new User("username","password",null);
        ex = assertThrows(RuntimeException.class, ()-> SILab2.function(user,userList));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        //TEST4 - TXX
        user = null;
        ex = assertThrows(RuntimeException.class, ()-> SILab2.function(user,userList));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));
    }

}
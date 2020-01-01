import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: wangwei
 * @Date: 2019/11/10 0010 19:54
 */
public class TestA1 {

    @Test
    public  void Test1() {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setIdNumber("123");
        user.setName("123");
        list.add(user);
        User user1= new User();
        user1.setIdNumber("123");
        user1.setName("new");
        list.add(user1);

        Map<String, String> collect = list.stream().collect(Collectors.toMap(User::getIdNumber, User::getName, (a, b) -> a+b));
        System.out.println(collect);
    }

    @Data
    static class User{
        private String IdNumber;
        private String Name;
    }
}

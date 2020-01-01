package regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: wangwei
 * @Date: 2019-12-01 20:29
 */
public class RegexTest {

    public static void main(String[] args) {
        /**
         *  (?<!patten)
         * 非获取匹配，反向否定预查，与正向否定预查类似，只是方向相反。例如“(?<!95|98|NT|2000)Windows”能匹配“3.1Windows”中的“Windows”，但不能匹配“2000Windows”中的“Windows”。
         */
        Pattern pattern=Pattern.compile("(?<!\\{)\\?");
        Matcher matcher=pattern.matcher("http://dd?dd=dd");
        boolean a=matcher.find();

    }

    @Test
    public void test(){
        Pattern NAMES_PATTERN = Pattern.compile("\\{([^/]+?)\\}");
        Matcher matcher=NAMES_PATTERN.matcher("http://{dd}?dd=dd");
        boolean a=matcher.find();
    }
}

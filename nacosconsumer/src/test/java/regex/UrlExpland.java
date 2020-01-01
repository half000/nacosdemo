package regex;

import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * @Author: wangwei
 * @Date: 2019-12-02 22:39
 */
public class UrlExpland {

    public static void main(String[] args) {
        DefaultUriBuilderFactory uriFactory = new DefaultUriBuilderFactory();
        uriFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);
        String a=uriFactory.expand("http://{a}{b}/111","sss","ddd","ffff").toString();
    }

}


import com.half.base.feign.IUserController;
import com.half.nacos.hystrix.QueryOrderIdCommand;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;

/**
 * @Author: wangwei
 * @Date: 2019-12-03 18:15
 */
@Slf4j
public class FeignClientTest {
    private QueryOrderIdCommand.OrderServiceProvider orderServiceProvider;

    public FeignClientTest() {
        orderServiceProvider = new QueryOrderIdCommand.OrderServiceProvider();
    }


    @Test
    public void testQueryByOrderIdCommand() {
        Integer r = new QueryOrderIdCommand(orderServiceProvider).execute();
        log.info("result:{}", r);

    }

    @Test
    public void test(){
        AnnotationMetadata annotationMetadata= new StandardAnnotationMetadata(IUserController.class);
        String className = annotationMetadata.getClassName();
        /*BeanDefinitionBuilder definition = BeanDefinitionBuilder
                .genericBeanDefinition(FeignClientFactoryBean.class);
        validate(attributes);
        definition.addPropertyValue("url", getUrl(attributes));
        definition.addPropertyValue("path", getPath(attributes));
        String name = getName(attributes);
        definition.addPropertyValue("name", name);
        String contextId = getContextId(attributes);
        definition.addPropertyValue("contextId", contextId);
        definition.addPropertyValue("type", className);
        definition.addPropertyValue("decode404", attributes.get("decode404"));
        definition.addPropertyValue("fallback", attributes.get("fallback"));
        definition.addPropertyValue("fallbackFactory", attributes.get("fallbackFactory"));
        definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

        String alias = contextId + "FeignClient";
        AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();

        boolean primary = (Boolean) attributes.get("primary"); // has a default, won't be
        // null

        beanDefinition.setPrimary(primary);

        String qualifier = getQualifier(attributes);
        if (StringUtils.hasText(qualifier)) {
            alias = qualifier;
        }

        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, className,
                new String[] { alias });
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);*/
    }
}

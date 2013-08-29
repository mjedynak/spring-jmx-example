package pl.mjedynak;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppJmxIntegrationTestConfig.class})
public class AppJmxIntegrationTest {

    private static final String CHECK_GLOBAL_DISCOUNT_METHOD_NAME = "checkGlobalDiscount";
    private static final String MODIFY_GLOBAL_DISCOUNT_METHOD_NAME = "modifyGlobalDiscount";

    @Autowired
    private MBeanServerConnectionFactoryBean clientConnector;

    @Test
    public void shouldInvokeOperations() throws Exception {
        // given
        MBeanServerConnection connection = clientConnector.getObject();
        ObjectName objectName = new ObjectName("pl.mjedynak:name=discountService,type=DiscountService");

        // when
        Integer oldDiscount = (Integer) connection.invoke(objectName, CHECK_GLOBAL_DISCOUNT_METHOD_NAME, null, null);
        Integer newDiscount = ++oldDiscount;
        connection.invoke(objectName, MODIFY_GLOBAL_DISCOUNT_METHOD_NAME, new Object[]{newDiscount}, new String[]{int.class.getName()});
        Integer currentDiscount = (Integer) connection.invoke(objectName, CHECK_GLOBAL_DISCOUNT_METHOD_NAME, null, null);

        // then
        assertThat(currentDiscount, is(newDiscount));
    }
}

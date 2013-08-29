package pl.mjedynak;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import java.net.MalformedURLException;

@Configuration
public class AppJmxIntegrationTestConfig {

    @Bean
    public MBeanServerConnectionFactoryBean clientConnector() throws MalformedURLException {
        MBeanServerConnectionFactoryBean mBeanServerConnectionFactoryBean = new MBeanServerConnectionFactoryBean();
        mBeanServerConnectionFactoryBean.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/connector");
        return mBeanServerConnectionFactoryBean;
    }
}

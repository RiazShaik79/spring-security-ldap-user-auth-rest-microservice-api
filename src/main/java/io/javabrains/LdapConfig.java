package io.javabrains;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathBeanPostProcessor;

@Configuration
@RefreshScope
public class LdapConfig {

    @Value("${spring.ldap.embedded.base-dn}")
    private String baseDn;

    @Bean
    public BaseLdapPathBeanPostProcessor ldapPathBeanPostProcessor(){
        BaseLdapPathBeanPostProcessor baseLdapPathBeanPostProcessor = new BaseLdapPathBeanPostProcessor();
        baseLdapPathBeanPostProcessor.setBasePath(baseDn);
        return baseLdapPathBeanPostProcessor;
    }
}
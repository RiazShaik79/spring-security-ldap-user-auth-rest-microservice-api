package io.javabrains;


import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class SSLConfigServiceBootstrapConfiguration {
	 @Autowired
	    ConfigClientProperties properties;
	 
	  /*  @Bean
	    public ConfigServicePropertySourceLocator configServicePropertySourceLocator() throws Exception {
	        final char[] password = "India330$$".toCharArray();
	        final ClassPathResource resource = new ClassPathResource("config-service-cert.jks");
	 
	        SSLContext sslContext = SSLContexts.custom()
	            .loadKeyMaterial(resource.getFile(), password, password)
	            .loadTrustMaterial(resource.getFile(), password, new TrustSelfSignedStrategy()).build();
	        CloseableHttpClient httpClient = HttpClients.custom()
	            .setSSLContext(sslContext)
	            .setSSLHostnameVerifier((s, sslSession) -> true)
	            .build();
	        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	        ConfigServicePropertySourceLocator configServicePropertySourceLocator = new ConfigServicePropertySourceLocator(properties);
	        configServicePropertySourceLocator.setRestTemplate(new RestTemplate(requestFactory));
	        return configServicePropertySourceLocator;
	    }  */

}

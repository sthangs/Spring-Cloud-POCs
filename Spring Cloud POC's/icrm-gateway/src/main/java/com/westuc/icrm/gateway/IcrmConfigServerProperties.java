package com.westuc.icrm.gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("vcap.services.icrm-cups-service.credentials")
public class IcrmConfigServerProperties {
	public String icrmSubEnv;
	public String icrmEnv;

	public String getIcrmSubEnv() {
		return icrmSubEnv;
	}

	public void setIcrmSubEnv(String icrmSubEnv) {
		this.icrmSubEnv = icrmSubEnv;
	}

	public String getIcrmEnv() {
		return icrmEnv;
	}

	public void setIcrmEnv(String icrmEnv) {
		this.icrmEnv = icrmEnv;
	}

}


package org.springframework.security.samples.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author chaitanyam
 * 
 */
public class SecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {

	public SecurityWebApplicationInitializer() {
		super(SecurityConfig.class);
	}
}

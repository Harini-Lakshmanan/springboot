package cam.walmart.payment.configuration;

import com.azure.spring.aad.webapi.AADJwtBearerTokenAuthenticationConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests
                .antMatchers("/actuator/**", "/swagger*/**", "/v2/api-docs", "/webjars/**",
                        "/configuration/**","/authenticate/menus/getMenusByRoleDescription/**").permitAll()
                .anyRequest().authenticated())
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(new AADJwtBearerTokenAuthenticationConverter("roles","ROLE_"));
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v3/api-docs/**",
                "/swagger-ui.html",
                "/swagger-ui/**");
    }
}

package cam.walmart.payment.utility;


import cam.walmart.payment.repositorys.MenuRepository;
import cam.walmart.payment.repositorys.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class MySecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {


    private final MenuRepository menuProfilesDao;

    private final HttpServletRequest httpServletRequest;

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        MyMethodSecurityExpressionRoot root = new MyMethodSecurityExpressionRoot(authentication,menuProfilesDao,httpServletRequest);
       // root.setAuthentication(authentication);
        return root;
    }
}


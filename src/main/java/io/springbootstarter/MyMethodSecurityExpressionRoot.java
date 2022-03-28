package cam.walmart.payment.utility;

import cam.walmart.payment.exception.CamException;
import cam.walmart.payment.model.entity.MenuRoles;
import cam.walmart.payment.model.response.MenuRolesResponse;
import cam.walmart.payment.repositorys.MenuRepository;
import cam.walmart.payment.repositorys.RolesRepository;
import com.azure.spring.aad.webapi.AADOAuth2AuthenticatedPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
class MyMethodSecurityExpressionRoot extends SecurityExpressionRoot
        implements MethodSecurityExpressionOperations {
    private Object filterObject;
    private Object returnObject;
    private Object target;
    private final Authentication authentication;
    private final MenuRepository menuProfilesDao;
    private final HttpServletRequest httpServletRequest;


    public MyMethodSecurityExpressionRoot(Authentication authentication, MenuRepository menuProfilesDao, HttpServletRequest httpServletRequest) {
        super(authentication);
        this.authentication = authentication;
        this.menuProfilesDao = menuProfilesDao;
        this.httpServletRequest = httpServletRequest;
    }

    public boolean hasPermission() {
        AADOAuth2AuthenticatedPrincipal obj = (AADOAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        final String roleFromHeader = httpServletRequest.getHeader("mappedrole");
        final Integer menuFromHeader = httpServletRequest.getIntHeader("menuId");
        final String requestType = httpServletRequest.getMethod();
       // @Todo ll remove this logger once Authorization is stable.
        log.info("role from Header" + roleFromHeader);
        log.info("menu from Header" + menuFromHeader);
        try {
            log.info("Fetching roles from  Token");
            List<String> rolesFromToken = obj.getJwtClaimsSet().getStringListClaim("roles");

            if (rolesFromToken.contains(roleFromHeader.trim())) {
                log.info("Fetching profileId from  HeaderRole");
                MenuRolesResponse menu = menuProfilesDao.getMenuRoleByRoleDescriptionAndMenuId(roleFromHeader, menuFromHeader);
                if (menu != null) {
                    switch (requestType) {
                        case "GET":
                            if ((menu.getGet() != null) && (menu.getGet().equals(true)))
                                return true;
                            break;
                        case "POST":
                            if ((menu.getPost() != null) && (menu.getPost().equals(true)))
                                return true;
                            break;
                        case "PUT":
                            if ((menu.getPut() != null) && (menu.getPut().equals(true)))
                                return true;
                            break;
                        case "DELETE":
                            if ((menu.getDel() != null) && (menu.getDel().equals(true)))
                                return true;
                            break;
                        default:
                            throw new CamException(CamException.Code.PERMISSION_ERROR.getExcCode(),requestType + "_NOT_ALLOWED");
                    }
                }
                throw new CamException(CamException.Code.PERMISSION_ERROR.getExcCode(),requestType + "_NOT_ALLOWED");
            }

        } catch (ParseException e) {
            log.error("ParseException ", e);
            throw new CamException(CamException.Code.INVALID_UPN);
        }
        return false;
    }


    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    public void setThis(Object target) {
        this.target = target;
    }

    @Override
    public Object getThis() {
        return target;
    }
}

package cam.walmart.payment.service;

import cam.walmart.payment.exception.CamException;
import cam.walmart.payment.model.entity.Favourites;
import cam.walmart.payment.model.entity.Menu;
import cam.walmart.payment.model.entity.Role;
import cam.walmart.payment.model.mapper.MenuMapper;
import cam.walmart.payment.model.request.FavouriteRequest;
import cam.walmart.payment.model.response.MenusResponse;
import cam.walmart.payment.model.response.RoleMenuResponse;
import cam.walmart.payment.repositorys.FavouritesRepository;
import cam.walmart.payment.repositorys.MenuRepository;
import cam.walmart.payment.repositorys.RolesRepository;
import cam.walmart.payment.utility.CommonMethods;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    final MenuRepository menuRepository;
    final RolesRepository rolesRepository;
    final FavouritesRepository favouritesRepository;
    final MenuMapper menuMapper;

    @Override
    public RoleMenuResponse getMenusByRoleDescription() throws CamException {
        RoleMenuResponse roleMenuResponse;

        List<String> roles = CommonMethods.getRolesFromToken();
        if (CollectionUtils.isEmpty(roles)) {
            log.error("No Roles found in token");
            throw new CamException(CamException.Code.ROLE_OR_ROLE_NAME_NOT_FOUND);
        } else {
            String mappedRole = CommonMethods.getRoleNameFromToken(roles);
            List<Menu> menusByRoleDescription = menuRepository.getMenusByRoleDescription(mappedRole);
            if (CollectionUtils.isEmpty(menusByRoleDescription)) {
                log.error("No menus are configured");
                throw new CamException(CamException.Code.NO_MENUS_CONFIGURED);
            } else {
                final List<Favourites> favouritesList = favouritesRepository.findAllByUserId(CommonMethods.getUserName());
                final List<Integer> favMenuIds = favouritesList.parallelStream().map(f -> f.getMenuId().getMenuId()).collect(Collectors.toList());
                List<MenusResponse> menusResponses = menuMapper.getMenuResponse(menusByRoleDescription, 0, favMenuIds);
                Role role = rolesRepository.findByMappedRole(mappedRole);
                roleMenuResponse = new RoleMenuResponse();
                roleMenuResponse.setRoleId(role.getRoleId());
                roleMenuResponse.setDescription(role.getRoleDesc());
                roleMenuResponse.setMappedRole(role.getMappedRole());
                roleMenuResponse.setMenusResponse(menusResponses);
                return roleMenuResponse;
            }
        }
    }

    @Override
    public void addOrRemoveFavouriteMenu(FavouriteRequest favouriteRequest) {
        Integer menuId = favouriteRequest.getMenuId();
        String userId = CommonMethods.getUserName();
        Optional<Favourites> favouritesDb = favouritesRepository.findByMenuIdMenuIdAndUserId(menuId, userId);
        Optional<Menu> menuDb = menuRepository.findByMenuId(menuId);

        if (favouriteRequest.getIsActive()) {
            Favourites favourites = new Favourites();
            if (favouritesDb.isPresent()) {
                if (menuDb.isPresent()) {
                    favouritesDb.get().setMenuId(menuDb.get());
                    favouritesDb.get().setUserId(userId);

                    favouritesRepository.save(favouritesDb.get());
                }
            } else {
                if (menuDb.isPresent()) {
                    favourites.setMenuId(menuDb.get());
                    favourites.setUserId(userId);
                    favouritesRepository.save(favourites);
                }
            }
        } else {
            if (favouritesDb.isEmpty()) {
                log.error("Favourite record was not found in db");
                throw new CamException(CamException.Code.FAVOURITE_RECORD_NOT_FOUND);
            }
            favouritesRepository.deleteById(favouritesDb.get().getId());
        }
    }
}
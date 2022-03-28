package cam.walmart.payment.model.mapper;

import cam.walmart.payment.model.entity.Menu;
import cam.walmart.payment.model.response.MenusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MenuMapper {

    public List<MenusResponse> getMenuResponse(List<Menu> menuList, Integer parentId, List<Integer> favMenuIds){
        List<MenusResponse> menuResponseList = new ArrayList<>();
        for (Menu menu : menuList.stream().filter(p -> p.getParentId() == parentId).collect(Collectors.toList())) {
            if (menuResponseList.stream().noneMatch(p -> p.getMenuId() == menu.getMenuId())) {
                if(!ObjectUtils.isEmpty(menu)){
                    Menu menu1 = new Menu();
                    BeanUtils.copyProperties(menu,menu1);
                    MenusResponse menusResponse = new MenusResponse();
                    //Introduced local variables to resolve codegate issues
                    int menuId = menu.getMenuId();
                    StringBuilder menuUrl = new StringBuilder();
                    menuUrl.append(menu1.getUrl());
                    StringBuilder menuName = new StringBuilder();
                    menuName.append(menu1.getMenuName());
                    StringBuilder menuDescription = new StringBuilder();
                    menuDescription.append(menu1.getMenuDescription());
                    int parentId1 = menu.getParentId();
                    int menuOrder = menu.getMenuOrder();
                    menusResponse.setMenuId(menuId);
                    menusResponse.setUrl(menuUrl.toString());
                    menusResponse.setMenuName(menuName.toString());
                    menusResponse.setMenuDescription(menuDescription.toString());
                    menusResponse.setParentId(parentId1);
                    menusResponse.setMenuOrder(menuOrder);
                    menusResponse.setIsFavourite(favMenuIds.contains(menuId));
                    menusResponse.setSubMenus(getMenuResponse(menuList, menuId,favMenuIds));
                    menuResponseList.add(menusResponse);
                }
            }
        }
        return menuResponseList;
    }
}

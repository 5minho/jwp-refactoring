package kitchenpos.application;

import kitchenpos.MenuGroupService;
import dto.menugroup.MenuGroupRequest;
import dto.menugroup.MenuGroupResponse;
import kitchenpos.utils.FixtureUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class MenuGroupServiceTest extends FixtureUtils {
    @Autowired
    private MenuGroupService menuGroupService;

    @DisplayName("메뉴 그룹을 등록할 수 있다.")
    @Test
    void createMenuGroupTest() {
        // given
        MenuGroupRequest menuGroupRequest = new MenuGroupRequest("testMenuGroup");

        // when
        MenuGroupResponse response = menuGroupService.create(menuGroupRequest);

        // then
        assertThat(response.getId()).isNotNull();
    }

    @DisplayName("메뉴 그룹 목록을 조회할 수 있다.")
    @Test
    void getMenuGroupsTest() {
        // given
        MenuGroupRequest menuGroupRequest = new MenuGroupRequest("testMenuGroup");
        menuGroupService.create(menuGroupRequest);

        // when
        List<MenuGroupResponse> foundMenuGroups = menuGroupService.list();
        List<String> names = foundMenuGroups.stream()
                .map(MenuGroupResponse::getName)
                .collect(Collectors.toList());

        // then
        assertThat(names).contains(menuGroupRequest.getName());
    }
}
package dto.menugroup;

import kitchenpos.domain.menugroup.MenuGroup;

import java.util.Objects;

public class MenuGroupResponse {
    private Long id;
    private String name;

    MenuGroupResponse() {
    }

    public MenuGroupResponse(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static MenuGroupResponse of(final MenuGroup menuGroup) {
        return new MenuGroupResponse(menuGroup.getId(), menuGroup.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MenuGroupResponse that = (MenuGroupResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MenuGroupResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
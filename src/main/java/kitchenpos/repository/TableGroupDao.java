package kitchenpos.repository;

import java.util.List;
import java.util.Optional;
import kitchenpos.domain.TableGroup;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(idClass = Long.class, domainClass = TableGroup.class)
public interface TableGroupDao {
    TableGroup save(TableGroup entity);

    Optional<TableGroup> findById(Long id);

    List<TableGroup> findAll();
}
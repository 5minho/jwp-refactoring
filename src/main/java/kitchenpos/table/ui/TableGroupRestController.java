package kitchenpos.table.ui;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kitchenpos.table.application.TableGroupService;
import kitchenpos.table.dto.TableGroupReponse;
import kitchenpos.table.dto.TableGroupRequest;

@RestController
public class TableGroupRestController {
	private final TableGroupService tableGroupService;

	public TableGroupRestController(final TableGroupService tableGroupService) {
		this.tableGroupService = tableGroupService;
	}

	@PostMapping("/api/table-groups")
	public ResponseEntity<TableGroupReponse> create(@RequestBody final TableGroupRequest request) {
		final TableGroupReponse created = tableGroupService.create(request);
		final URI uri = URI.create("/api/table-groups/" + created.getId());
		return ResponseEntity.created(uri)
			.body(created)
			;
	}

	@DeleteMapping("/api/table-groups/{tableGroupId}")
	public ResponseEntity<Void> ungroup(@PathVariable final Long tableGroupId) {
		tableGroupService.ungroup(tableGroupId);
		return ResponseEntity.noContent()
			.build()
			;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgsException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
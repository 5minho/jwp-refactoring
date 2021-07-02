package kitchenpos.dto;

import static java.util.Objects.*;

import java.util.List;
import java.util.stream.Collectors;

import kitchenpos.domain.NumberOfGuests;
import kitchenpos.domain.OrderTable;
import kitchenpos.domain.TableGroup;

public class OrderTableResponse {

	private Long id;

	private Long tableGroupId;

	private int numberOfGuests;

	private boolean empty;

	public OrderTableResponse(Long id, int numberOfGuests, boolean empty) {
		this(id, null, numberOfGuests, empty);
	}

	public OrderTableResponse(Long id, Long tableGroupId, int numberOfGuests, boolean empty) {
		this.id = id;
		this.tableGroupId = tableGroupId;
		this.numberOfGuests = numberOfGuests;
		this.empty = empty;
	}

	public static OrderTableResponse of(OrderTable orderTable) {
		TableGroup tableGroup = orderTable.getTableGroup();
		NumberOfGuests numberOfGuests = orderTable.getNumberOfGuests();
		if (isNull(tableGroup)) {
			return new OrderTableResponse(orderTable.getId(), numberOfGuests.getNumberOfGuests(), orderTable.isEmpty());
		}
		return new OrderTableResponse(orderTable.getId(), numberOfGuests.getNumberOfGuests(), orderTable.isEmpty());
	}

	public static List<OrderTableResponse> listOf(List<OrderTable> orderTables) {
		return orderTables.stream()
			.map(OrderTableResponse::of)
			.collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public Long getTableGroupId() {
		return tableGroupId;
	}

	public int getNumberOfGuests() { return numberOfGuests; }

	public boolean isEmpty() {
		return empty;
	}
}

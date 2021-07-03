package kitchenpos.domain;

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTableTest {

	@DisplayName("주문테이블을 생성시 그룹은 설정 안되어 있다.")
	@Test
	void createTest() {
		OrderTable orderTable = new OrderTable(1, true);

		assertThat(orderTable.isGrouped()).isFalse();
	}

	@DisplayName("그룹 설정이 되어 있는 주문테이블은 비우기 설정을 할 수 없다.")
	@Test
	void emptyGroupedTableTest() {
		OrderTable orderTable1 = new OrderTable(1, true);
		OrderTable orderTable2 = new OrderTable(2, true);
		TableGroup.create(asList(orderTable1, orderTable2), LocalDateTime.now());

		assertThatThrownBy(() -> orderTable1.emptyOn())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("그룹 설정이 되어 있는 테이블은 주문 등록 불가 상태로 바꿀 수 없습니다.");

		assertThatThrownBy(() -> orderTable1.emptyOff())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("그룹 설정이 되어 있는 테이블은 주문 등록 불가 상태로 바꿀 수 없습니다.");
	}

	@DisplayName("주문테이블의 주문이 조리 상태이거나 식사 상태이면 주문테이블 상태를 바꿀 수 없다.")
	@Test
	void changeEmptyWithCookingOrderTest() {
		Order order = mock(Order.class);
		when(order.isComplete()).thenReturn(false);
		OrderTable orderTable = new OrderTable(1, true, asList(order));

		assertThatThrownBy(() -> orderTable.emptyOn())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("조리상태이거나 식사상태주문의 주문테이블은 상태를 변경할 수 없습니다.");

		assertThatThrownBy(() -> orderTable.emptyOff())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("조리상태이거나 식사상태주문의 주문테이블은 상태를 변경할 수 없습니다.");
	}

	@DisplayName("주문테이블은 비우기 설정을 할 수 있다.")
	@Test
	void emptyTest() {
		OrderTable orderTable = new OrderTable(1, false);

		orderTable.emptyOn();

		assertThat(orderTable.isEmpty()).isTrue();

		orderTable.emptyOff();

		assertThat(orderTable.isEmpty()).isFalse();
	}

	@DisplayName("방문 손님 수를 음수로 수정할 수 없다.")
	@Test
	void changeNumberOfGuestsNegativeNumberTest() {
		OrderTable orderTable = new OrderTable(1, false);

		assertThatThrownBy(() -> orderTable.changeNumberOfGuests(-1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("방문 손님 수는 음수일 수 없습니다.");
	}

	@DisplayName("빈 테이블의 방문 손님 수는 수정할 수 없다.")
	@Test
	void changeNumberOfGuestsEmptyOrderTableTest() {
		OrderTable orderTable = new OrderTable(1, true);

		assertThatThrownBy(() -> orderTable.changeNumberOfGuests(2))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("빈 테이블은 방문 손님 수를 수정할 수 없습니다.");
	}

	@DisplayName("주문테이블의 방문 손님 수를 수정할 수 있다.")
	@Test
	void changeNumberOfGuests() {
		OrderTable orderTable = new OrderTable(1, false);

		orderTable.changeNumberOfGuests(2);

		assertThat(orderTable.getNumberOfGuests()).isEqualTo(2);
	}

}
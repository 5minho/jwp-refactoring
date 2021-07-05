package kitchenpos.domain;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

	public static Product 후라이드치킨 = new Product(Name.valueOf("후라이드치킨"), Price.wonOf(1000));
	public static Product 피자 = new Product(Name.valueOf("피자"), Price.wonOf(2000));

	@DisplayName("가격이 음수인 상품은 생성 될 수 없다.")
	@Test
	void negativePriceTest() {
		// given
		// when
		// than
		assertThatThrownBy(() -> new Product(Name.valueOf("상품"), Price.wonOf(BigDecimal.valueOf(-1))))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("가격은 음수가 될 수 없습니다.");
	}

	@DisplayName("상품의 이름과 가격은 필수 정보이다.")
	@Test
	void createProductionTest() {
		assertThatThrownBy(() -> new Product(Name.valueOf("상품"), null))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("상품의 이름과 가격은 필수 정보입니다.");
	}
}
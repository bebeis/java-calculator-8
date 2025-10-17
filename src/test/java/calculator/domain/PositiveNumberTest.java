package calculator.domain;

import calculator.domain.number.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PositiveNumberTest {

    @DisplayName("양수 값을 입력하면 정상적으로 객체가 생성된다")
    @Test
    void createPositiveNumber() {
        assertThatNoException().isThrownBy(() -> new PositiveNumber(3));
        assertThatNoException().isThrownBy(() -> new PositiveNumber(3.3));
    }

    @DisplayName("0 이하의 숫자를 입력하면 예외가 발생한다.")
    @Test
    void throwsException_whenNumberIsNotPositive() {
        assertThatThrownBy(() -> new PositiveNumber(0.0));
        assertThatThrownBy(() -> new PositiveNumber(0));
        assertThatThrownBy(() -> new PositiveNumber(-2.1));
        assertThatThrownBy(() -> new PositiveNumber(-3));
    }

    @DisplayName("두 양수를 더하면 새로운 양수 객체를 얻는다.")
    @Test
    void addTwoPositiveNumbers() {
        // given
        PositiveNumber num1 = new PositiveNumber(1.1);
        PositiveNumber num2 = new PositiveNumber(1.3);

        // when
        PositiveNumber result = num1.addTo(num2);

        // then
        assertThat(result).isEqualTo(new PositiveNumber(2.4));
    }

    @DisplayName("정수 덧셈의 결과는 소수점 없이 표현된다.")
    @Test
    void addTwoIntegerToString_withoutPoint() {
        // given
        PositiveNumber num1 = new PositiveNumber(1);
        PositiveNumber num2 = new PositiveNumber(2);

        // when
        String result = num1.addTo(num2).toString();

        // then
        assertThat(result).isEqualTo("3");
    }

    @DisplayName("소수 덧셈의 결과는 소수점을 포함하여 표현된다.")
    @Test
    void addTwoDoubleToString_withPoint() {
        // given
        PositiveNumber num1 = new PositiveNumber(1.3);
        PositiveNumber num2 = new PositiveNumber(2.4);

        // when
        String result = num1.addTo(num2).toString();

        // then
        assertThat(result).isEqualTo("3.7");
    }
}
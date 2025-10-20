package calculator.domain.operand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OperandTest {

    @DisplayName("양수 값을 입력하면 정상적으로 객체가 생성된다")
    @Test
    void createPositiveNumber() {
        assertThatNoException().isThrownBy(() -> new Operand(3));
        assertThatNoException().isThrownBy(() -> new Operand(3.3));
    }

    @DisplayName("0 이하의 숫자를 입력하면 예외가 발생한다.")
    @Test
    void throwsException_whenNumberIsNotPositive() {
        assertThatThrownBy(() -> new Operand(0.0));
        assertThatThrownBy(() -> new Operand(0));
        assertThatThrownBy(() -> new Operand(-2.1));
        assertThatThrownBy(() -> new Operand(-3));
    }

    @DisplayName("두 양수를 더하면 새로운 양수 객체를 얻는다.")
    @Test
    void addTwoPositiveNumbers() {
        // given
        Operand num1 = new Operand(1.1);
        Operand num2 = new Operand(1.3);

        // when
        Operand result = num1.addTo(num2);

        // then
        assertThat(result).isEqualTo(new Operand(2.4));
    }

    @DisplayName("정수 덧셈의 결과는 소수점 없이 표현된다.")
    @Test
    void addTwoIntegerToString_withoutPoint() {
        // given
        Operand num1 = new Operand(1);
        Operand num2 = new Operand(2);

        // when
        String result = num1.addTo(num2).toString();

        // then
        assertThat(result).isEqualTo("3");
    }

    @DisplayName("소수 덧셈의 결과는 소수점을 포함하여 표현된다.")
    @Test
    void addTwoDoubleToString_withPoint() {
        // given
        Operand num1 = new Operand(1.3);
        Operand num2 = new Operand(2.4);

        // when
        String result = num1.addTo(num2).toString();

        // then
        assertThat(result).isEqualTo("3.7");
    }
}
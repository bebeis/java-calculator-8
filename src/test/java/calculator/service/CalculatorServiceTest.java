package calculator.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorServiceTest {

    @DisplayName("기본 구분자로 구분된 숫자들을 더할 수 있다.")
    @Test
    void calculateWithCommaDelimiter() {
        // given
        CalculatorService service = new CalculatorService();
        String request = "1,2,3";

        // when
        String result = service.calculate(request);

        // then
        assertThat(result).isEqualTo("6");
    }

    @DisplayName("기본 구분자(쉼표, 콜론)를 모두 사용하여 숫자들을 더할 수 있다.")
    @Test
    void calculateWithMixedDefaultDelimiters() {
        // given
        CalculatorService service = new CalculatorService();
        String request = "1,2:3";

        // when
        String result = service.calculate(request);

        // then
        assertThat(result).isEqualTo("6");
    }

    @DisplayName("커스텀 구분자로 구분된 숫자들을 더할 수 있다.")
    @Test
    void calculateWithCustomDelimiter() {
        // given
        CalculatorService service = new CalculatorService();
        String request = "//;\\n1;2;3";

        // when
        String result = service.calculate(request);

        // then
        assertThat(result).isEqualTo("6");
    }

    @DisplayName("커스텀 구분자와 기본 구분자를 혼합하여 숫자들을 더할 수 있다.")
    @Test
    void calculateWithCustomAndDefaultDelimiters() {
        // given
        CalculatorService service = new CalculatorService();
        String request = "//;\\n1;2,3:4";

        // when
        String result = service.calculate(request);

        // then
        assertThat(result).isEqualTo("10");
    }

    @DisplayName("빈 문자열 또는 null 입력 시 0을 반환한다.")
    @Test
    void calculateEmptyStringReturnsZero() {
        // given
        CalculatorService service = new CalculatorService();
        String request = "";

        // when
        String result = service.calculate(request);

        // then
        assertThat(result).isEqualTo("0");
    }

    @DisplayName("소수를 포함한 계산을 할 수 있다.")
    @Test
    void calculateWithDoubleNumbers() {
        // given
        CalculatorService service = new CalculatorService();
        String request = "1.5,2.3,3.7";

        // when
        String result = service.calculate(request);

        // then
        assertThat(result).isEqualTo("7.5");
    }

    @DisplayName("정수와 소수가 모두 포함된 식에서 덧셈에 성공한다.")
    @Test
    void calculateWithMixedIntegerAndDouble() {
        // given
        CalculatorService service = new CalculatorService();
        String request = "1,2.5,3";

        // when
        String result = service.calculate(request);

        // then
        assertThat(result).isEqualTo("6.5");
    }

    @DisplayName("음수가 포함되면 예외가 발생한다.")
    @Test
    void throwExceptionWithNegativeNumber() {
        // given
        CalculatorService service = new CalculatorService();
        String request = "1,-2,3";

        // when & then
        assertThatThrownBy(() -> service.calculate(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("0이 포함되면 예외가 발생한다.")
    @Test
    void throwExceptionWithZero() {
        // given
        CalculatorService service = new CalculatorService();
        String request = "1,0,3";

        // when & then
        assertThatThrownBy(() -> service.calculate(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("여러 문자로 구성된 커스텀 구분자로 계산할 수 있다.")
    @Test
    void calculateWithMultiCharacterCustomDelimiter() {
        // given
        CalculatorService service = new CalculatorService();
        String request = "//***\\n1***2***3";

        // when
        String result = service.calculate(request);

        // then
        assertThat(result).isEqualTo("6");
    }

    @DisplayName("숫자 하나만 입력 시 해당 숫자를 반환한다.")
    @Test
    void calculateWithSingleNumber() {
        // given
        CalculatorService service = new CalculatorService();
        String request = "5";

        // when
        String result = service.calculate(request);

        // then
        assertThat(result).isEqualTo("5");
    }
}


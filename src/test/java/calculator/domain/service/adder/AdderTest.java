package calculator.domain.service.adder;

import calculator.domain.number.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AdderTest {

    @DisplayName("여러 개의 정수를 더할 수 있다.")
    @Test
    void addMultipleIntegerNumbers() {
        // given
        Adder adder = new Adder();
        List<PositiveNumber> positiveNumbers = List.of(new PositiveNumber(1), new PositiveNumber(2), new PositiveNumber(3));

        // when
        Optional<PositiveNumber> sumOpt = adder.sum(positiveNumbers);

        // then
        assertThat(sumOpt.get()).isEqualTo(new PositiveNumber(6));
    }

    @DisplayName("여러 개의 소수를 더할 수 있다.")
    @Test
    void addMultipleDoubleNumbers() {
        // given
        Adder adder = new Adder();
        List<PositiveNumber> positiveNumbers = List.of(new PositiveNumber(1.1), new PositiveNumber(2.2), new PositiveNumber(3.3));

        // when
        Optional<PositiveNumber> sumOpt = adder.sum(positiveNumbers);

        // then
        assertThat(sumOpt.get()).isEqualTo(new PositiveNumber(6.6));
    }

    @DisplayName("여러 개의 정수, 소수의 복합 연산을 수행할 수 있다.")
    @Test
    void addMultipleDoubleAndIntegerNumbers() {
        // given
        Adder adder = new Adder();
        List<PositiveNumber> positiveNumbers = List.of(new PositiveNumber(1), new PositiveNumber(1.2), new PositiveNumber(2));

        // when
        Optional<PositiveNumber> sumOpt = adder.sum(positiveNumbers);

        // then
        assertThat(sumOpt.get()).isEqualTo(new PositiveNumber(4.2));
    }

}
package calculator.domain.service.adder;

import calculator.domain.operand.Operand;
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
        List<Operand> operands = List.of(new Operand(1), new Operand(2), new Operand(3));

        // when
        Optional<Operand> sumOpt = adder.sum(operands);

        // then
        assertThat(sumOpt.get()).isEqualTo(new Operand(6));
    }

    @DisplayName("여러 개의 소수를 더할 수 있다.")
    @Test
    void addMultipleDoubleNumbers() {
        // given
        Adder adder = new Adder();
        List<Operand> operands = List.of(new Operand(1.1), new Operand(2.2), new Operand(3.3));

        // when
        Optional<Operand> sumOpt = adder.sum(operands);

        // then
        assertThat(sumOpt.get()).isEqualTo(new Operand(6.6));
    }

    @DisplayName("여러 개의 정수, 소수의 복합 연산을 수행할 수 있다.")
    @Test
    void addMultipleDoubleAndIntegerNumbers() {
        // given
        Adder adder = new Adder();
        List<Operand> operands = List.of(new Operand(1), new Operand(1.2), new Operand(2));

        // when
        Optional<Operand> sumOpt = adder.sum(operands);

        // then
        assertThat(sumOpt.get()).isEqualTo(new Operand(4.2));
    }

}
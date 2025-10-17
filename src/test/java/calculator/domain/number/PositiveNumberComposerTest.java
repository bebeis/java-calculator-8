package calculator.domain.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PositiveNumberComposerTest {

    @DisplayName("여러 개의 정수를 더할 수 있다.")
    @Test
    void addMultipleIntegerNumbers() {
        // given
        PositiveNumberComposer composer = new PositiveNumberComposer();
        composer.addPositiveNumber(new PositiveNumber(3));
        composer.addPositiveNumber(new PositiveNumber(4));
        composer.addPositiveNumber(new PositiveNumber(5));
        composer.addPositiveNumber(new PositiveNumber(6));

        // when
        Optional<PositiveNumber> positiveNumber = composer.sum();

        // then
        assertThat(positiveNumber.get()).isEqualTo(new PositiveNumber(18));
    }

    @DisplayName("여러 개의 소수를 더할 수 있다.")
    @Test
    void addMultipleDoubleNumbers() {
        // given
        PositiveNumberComposer composer = new PositiveNumberComposer();
        composer.addPositiveNumber(new PositiveNumber(1.2));
        composer.addPositiveNumber(new PositiveNumber(2.3));
        composer.addPositiveNumber(new PositiveNumber(3.1));
        composer.addPositiveNumber(new PositiveNumber(4.2));

        // when
        Optional<PositiveNumber> positiveNumber = composer.sum();

        // then
        assertThat(positiveNumber.get()).isEqualTo(new PositiveNumber(10.8));
    }

    @DisplayName("여러 개의 정수, 소수의 복합 연산을 수행할 수 있다.")
    @Test
    void addMultipleDoubleAndIntegerNumbers() {
        // given
        PositiveNumberComposer composer = new PositiveNumberComposer();
        composer.addPositiveNumber(new PositiveNumber(1.2));
        composer.addPositiveNumber(new PositiveNumber(2.3));
        composer.addPositiveNumber(new PositiveNumber(3));
        composer.addPositiveNumber(new PositiveNumber(4));

        // when
        Optional<PositiveNumber> positiveNumber = composer.sum();

        // then
        assertThat(positiveNumber.get()).isEqualTo(new PositiveNumber(10.5));
    }


}
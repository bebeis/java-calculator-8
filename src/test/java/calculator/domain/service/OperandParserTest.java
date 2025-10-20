package calculator.domain.service;

import calculator.domain.operand.Operand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OperandParserTest {

    @DisplayName("기본 구분자인 ,로 피연산자를 추출할 수 있다.")
    @Test
    void extractOperandsWithCommaDelimiter() {
        // given
        OperandParser parser = new OperandParser();
        String expression = "1,2,3";

        // when
        List<Operand> operands = parser.extractOperandsFrom(expression);

        // then
        assertThat(operands).containsExactly(
                new Operand(1),
                new Operand(2),
                new Operand(3)
        );
    }

    @DisplayName("기본 구분자인 ;으로 피연산자를 추출할 수 있다.")
    @Test
    void extractOperandsWithColonDelimiter() {
        // given
        OperandParser parser = new OperandParser();
        String expression = "1:2:3";

        // when
        List<Operand> operands = parser.extractOperandsFrom(expression);

        // then
        assertThat(operands).containsExactly(
                new Operand(1),
                new Operand(2),
                new Operand(3)
        );
    }

    @DisplayName("기본 구분자(쉼표, 콜론)를 모두 사용하여 피연산자를 추출할 수 있다.")
    @Test
    void extractOperandsWithMixedDefaultDelimiters() {
        // given
        OperandParser parser = new OperandParser();
        String expression = "1,2:3";

        // when
        List<Operand> operands = parser.extractOperandsFrom(expression);

        // then
        assertThat(operands).containsExactly(
                new Operand(1),
                new Operand(2),
                new Operand(3)
        );
    }

    @DisplayName("커스텀 구분자로 피연산자를 추출할 수 있다.")
    @Test
    void extractOperandsWithCustomDelimiter() {
        // given
        OperandParser parser = new OperandParser();
        String expression = "//;\\n1;2;3";

        // when
        List<Operand> operands = parser.extractOperandsFrom(expression);

        // then
        assertThat(operands).containsExactly(
                new Operand(1),
                new Operand(2),
                new Operand(3)
        );
    }

    @DisplayName("커스텀 구분자와 기본 구분자를 모두 사용하여 피연산자를 추출할 수 있다.")
    @Test
    void extractOperandsWithCustomAndDefaultDelimiters() {
        // given
        OperandParser parser = new OperandParser();
        String expression = "//;\\n1;2,3:4";

        // when
        List<Operand> operands = parser.extractOperandsFrom(expression);

        // then
        assertThat(operands).containsExactly(
                new Operand(1),
                new Operand(2),
                new Operand(3),
                new Operand(4)
        );
    }

    @DisplayName("소수를 포함한 피연산자를 추출할 수 있다.")
    @Test
    void extractOperandsWithDoubleNumbers() {
        // given
        OperandParser parser = new OperandParser();
        String expression = "1.5,2.3,3.7";

        // when
        List<Operand> operands = parser.extractOperandsFrom(expression);

        // then
        assertThat(operands).containsExactly(
                new Operand(1.5),
                new Operand(2.3),
                new Operand(3.7)
        );
    }

    @DisplayName("정수와 소수를 모두 존재하는 케이스에서 피연산자를 추출할 수 있다.")
    @Test
    void extractOperandsWithMixedIntegerAndDouble() {
        // given
        OperandParser parser = new OperandParser();
        String expression = "1,2.5,3";

        // when
        List<Operand> operands = parser.extractOperandsFrom(expression);

        // then
        assertThat(operands).containsExactly(
                new Operand(1),
                new Operand(2.5),
                new Operand(3)
        );
    }

    @DisplayName("음수가 포함되면 예외가 발생한다.")
    @Test
    void throwExceptionWithNegativeNumber() {
        // given
        OperandParser parser = new OperandParser();
        String expression = "1,-2,3";

        // when & then
        assertThatThrownBy(() -> parser.extractOperandsFrom(expression))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("0이 포함되면 예외가 발생한다.")
    @Test
    void throwExceptionWithZero() {
        // given
        OperandParser parser = new OperandParser();
        String expression = "1,0,3";

        // when & then
        assertThatThrownBy(() -> parser.extractOperandsFrom(expression))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("여러 문자로 구성된 커스텀 구분자로 피연산자를 추출할 수 있다.")
    @Test
    void extractOperandsWithMultiCharacterCustomDelimiter() {
        // given
        OperandParser parser = new OperandParser();
        String expression = "//***\\n1***2***3";

        // when
        List<Operand> operands = parser.extractOperandsFrom(expression);

        // then
        assertThat(operands).containsExactly(
                new Operand(1),
                new Operand(2),
                new Operand(3)
        );
    }
}


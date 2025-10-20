package calculator.domain.expression;

import calculator.domain.operand.Operand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OperandSectionTest {

    @DisplayName("단일 구분자로 숫자를 추출할 수 있다.")
    @Test
    void extractWith_singleDelimiter() {
        // given
        OperandSection section = new OperandSection("1,2,3");
        String regex = ",";

        // when
        List<Operand> operands = section.extractWith(regex);

        // then
        assertThat(operands).containsExactly(
                new Operand(1),
                new Operand(2),
                new Operand(3)
        );
    }

    @DisplayName("여러 구분자로 숫자를 추출할 수 있다.")
    @Test
    void extractWith_multipleDelimiters() {
        // given
        OperandSection section = new OperandSection("1,2:3");
        String regex = ",|:";

        // when
        List<Operand> operands = section.extractWith(regex);

        // then
        assertThat(operands).containsExactly(
                new Operand(1),
                new Operand(2),
                new Operand(3)
        );
    }

    @DisplayName("소수를 포함한 숫자를 추출할 수 있다.")
    @Test
    void extractWith_decimalNumbers() {
        // given
        OperandSection section = new OperandSection("1.5,2.3,3.7");
        String regex = ",";

        // when
        List<Operand> operands = section.extractWith(regex);

        // then
        assertThat(operands).containsExactly(
                new Operand(1.5),
                new Operand(2.3),
                new Operand(3.7)
        );
    }

    @DisplayName("공백이 포함된 경우 trim 처리된다.")
    @Test
    void extractWith_withWhitespace() {
        // given
        OperandSection section = new OperandSection(" 1 , 2 , 3 ");
        String regex = ",";

        // when
        List<Operand> operands = section.extractWith(regex);

        // then
        assertThat(operands).containsExactly(
                new Operand(1),
                new Operand(2),
                new Operand(3)
        );
    }

    @DisplayName("연속된 구분자가 있으면 예외를 발생시킨다.")
    @Test
    void extractWith_consecutiveDelimiters_throwsException() {
        // given
        OperandSection section = new OperandSection("1,,2,3");
        String regex = ",";

        // when & then
        assertThatThrownBy(() -> section.extractWith(regex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("연속된 구분자는 허용되지 않습니다");
    }

    @DisplayName("시작 부분에 구분자가 있으면 예외를 발생시킨다.")
    @Test
    void extractWith_delimiterAtStart_throwsException() {
        // given
        OperandSection section = new OperandSection(",1,2,3");
        String regex = ",";

        // when & then
        assertThatThrownBy(() -> section.extractWith(regex))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("끝 부분에 구분자가 있으면 예외를 발생시킨다.")
    @Test
    void extractWith_delimiterAtEnd_throwsException() {
        // given
        OperandSection section = new OperandSection("1,2,3,");
        String regex = ",";

        // when & then
        assertThatThrownBy(() -> section.extractWith(regex))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숫자 대신 공백이 존재하면 예외를 발생시킨다.")
    @Test
    void extractWith_whitespaceOnlyToken_throwsException() {
        // given
        OperandSection section = new OperandSection("1, ,3");
        String regex = ",";

        // when & then
        assertThatThrownBy(() -> section.extractWith(regex))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효하지 않은 숫자 형식이 포함된 경우 예외를 발생시킨다.")
    @Test
    void extractWith_invalidNumber_throwsException() {
        // given
        OperandSection section = new OperandSection("1,a,3");
        String regex = ",";

        // when & then
        assertThatThrownBy(() -> section.extractWith(regex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 숫자 형식입니다");
    }

    @DisplayName("음수가 포함된 경우 예외를 발생시킨다.")
    @Test
    void extractWith_negativeNumber_throwsException() {
        // given
        OperandSection section = new OperandSection("1,-2,3");
        String regex = ",";

        // when & then
        assertThatThrownBy(() -> section.extractWith(regex))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("공백을 구분자로 사용할 수 있다.")
    @Test
    void extractWith_spaceAsDelimiter() {
        // given
        OperandSection section = new OperandSection("1 2 3");
        String regex = " ";

        // when
        List<Operand> operands = section.extractWith(regex);

        // then
        assertThat(operands).containsExactly(
                new Operand(1),
                new Operand(2),
                new Operand(3)
        );
    }

    @DisplayName("공백을 구분자로 사용할 때 소수도 처리할 수 있다.")
    @Test
    void extractWith_spaceAsDelimiterWithDecimals() {
        // given
        OperandSection section = new OperandSection("1.5 2.3 3.7");
        String regex = " ";

        // when
        List<Operand> operands = section.extractWith(regex);

        // then
        assertThat(operands).containsExactly(
                new Operand(1.5),
                new Operand(2.3),
                new Operand(3.7)
        );
    }

    @DisplayName("공백 구분자 사용 시 연속된 공백이 있으면 예외를 발생시킨다.")
    @Test
    void extractWith_consecutiveSpaces_throwsException() {
        // given
        OperandSection section = new OperandSection("1  2");
        String regex = " ";

        // when & then
        assertThatThrownBy(() -> section.extractWith(regex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("연속된 구분자는 허용되지 않습니다");
    }

    @DisplayName("공백 구분자 사용 시 시작 부분에 공백이 있으면 예외를 발생시킨다.")
    @Test
    void extractWith_spaceAtStart_throwsException() {
        // given
        OperandSection section = new OperandSection(" 1 2");
        String regex = " ";

        // when & then
        assertThatThrownBy(() -> section.extractWith(regex))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("공백 구분자 사용 시 끝 부분에 공백이 있으면 예외를 발생시킨다.")
    @Test
    void extractWith_spaceAtEnd_throwsException() {
        // given
        OperandSection section = new OperandSection("1 2 ");
        String regex = " ";

        // when & then
        assertThatThrownBy(() -> section.extractWith(regex))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("커스텀 문자 구분자를 사용할 수 있다.")
    @Test
    void extractWith_customCharacterDelimiter() {
        // given
        OperandSection section = new OperandSection("3a1");
        String regex = "a";

        // when
        List<Operand> operands = section.extractWith(regex);

        // then
        assertThat(operands).containsExactly(
                new Operand(3),
                new Operand(1)
        );
    }

    @DisplayName("커스텀 문자 구분자 사용 시 공백이 포함되어도 처리할 수 있다.")
    @Test
    void extractWith_customDelimiterWithSpaces() {
        // given
        OperandSection section = new OperandSection("3 a 1");
        String regex = "a";

        // when
        List<Operand> operands = section.extractWith(regex);

        // then
        assertThat(operands).containsExactly(
                new Operand(3),
                new Operand(1)
        );
    }

    @DisplayName("여러 문자로 구성된 구분자를 사용할 수 있다.")
    @Test
    void extractWith_multiCharacterDelimiter() {
        // given
        OperandSection section = new OperandSection("1abc2abc3");
        String regex = "abc";

        // when
        List<Operand> operands = section.extractWith(regex);

        // then
        assertThat(operands).containsExactly(
                new Operand(1),
                new Operand(2),
                new Operand(3)
        );
    }

    @DisplayName("공백을 포함한 여러 문자로 구성된 구분자를 사용할 수 있다.")
    @Test
    void extractWith_multiCharacterDelimiterWithSpaces() {
        // given
        OperandSection section = new OperandSection("1 a 3 b 2 a 3 b 5");
        String regex = " a 3 b ";

        // when
        List<Operand> operands = section.extractWith(regex);

        // then
        assertThat(operands).containsExactly(
                new Operand(1),
                new Operand(2),
                new Operand(5)
        );
    }

    @DisplayName("여러 문자 구분자 사용 시 소수도 처리할 수 있다.")
    @Test
    void extractWith_multiCharacterDelimiterWithDecimals() {
        // given
        OperandSection section = new OperandSection("1.5abc2.3abc3.7");
        String regex = "abc";

        // when
        List<Operand> operands = section.extractWith(regex);

        // then
        assertThat(operands).containsExactly(
                new Operand(1.5),
                new Operand(2.3),
                new Operand(3.7)
        );
    }

    @DisplayName("여러 문자 구분자 사용 시 연속된 구분자가 있으면 예외를 발생시킨다.")
    @Test
    void extractWith_consecutiveMultiCharacterDelimiters_throwsException() {
        // given
        OperandSection section = new OperandSection("1abcabc2");
        String regex = "abc";

        // when & then
        assertThatThrownBy(() -> section.extractWith(regex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("연속된 구분자는 허용되지 않습니다");
    }

    @DisplayName("여러 문자 구분자 사용 시 시작 부분에 구분자가 있으면 예외를 발생시킨다.")
    @Test
    void extractWith_multiCharacterDelimiterAtStart_throwsException() {
        // given
        OperandSection section = new OperandSection("abc1abc2");
        String regex = "abc";

        // when & then
        assertThatThrownBy(() -> section.extractWith(regex))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("여러 문자 구분자 사용 시 끝 부분에 구분자가 있으면 예외를 발생시킨다.")
    @Test
    void extractWith_multiCharacterDelimiterAtEnd_throwsException() {
        // given
        OperandSection section = new OperandSection("1abc2abc");
        String regex = "abc";

        // when & then
        assertThatThrownBy(() -> section.extractWith(regex))
                .isInstanceOf(IllegalArgumentException.class);
    }


}


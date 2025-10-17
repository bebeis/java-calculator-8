package calculator.domain.delimiter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DelimiterTest {

    @DisplayName("구분자는 정상적으로 생성된다.")
    @Test
    void createValidDelimiter_success() {
        assertThatNoException().isThrownBy(() -> new Delimiter("1ab2"));
        assertThatNoException().isThrownBy(() -> new Delimiter(":"));
        assertThatNoException().isThrownBy(() -> new Delimiter("\\"));
        assertThatNoException().isThrownBy(() -> new Delimiter("."));
        assertThatNoException().isThrownBy(() -> new Delimiter("."));
    }

    @DisplayName("구분자는 숫자만으로 구성될 수 없다.")
    @Test
    void numericOnlyDelimiter_throwsException() {
        assertThatThrownBy(() -> new Delimiter("12"));
        assertThatThrownBy(() -> new Delimiter("11"));
        assertThatThrownBy(() -> new Delimiter("1"));
    }

    @DisplayName("구분자는 공백문자를 포함할 수 있다.")
    @Test
    void containsWhiteSpace_success() {
        assertThatNoException().isThrownBy(() -> new Delimiter(" "));
        assertThatNoException().isThrownBy(() -> new Delimiter(" a"));
        assertThatNoException().isThrownBy(() -> new Delimiter("1 a"));
        assertThatNoException().isThrownBy(() -> new Delimiter("b "));
        assertThatNoException().isThrownBy(() -> new Delimiter("b c e"));
    }

    @DisplayName("구분자는 빈 문자열일 수 없다.")
    @Test
    void emptyString_throwsException() {
        assertThatThrownBy(() -> new Delimiter(""));
    }

    @DisplayName("구분자는 tab을 포함할 수 있다.")
    @Test
    void containsTab_success() {
        assertThatNoException().isThrownBy(() -> new Delimiter("\t"));
        assertThatNoException().isThrownBy(() -> new Delimiter("a\t"));
        assertThatNoException().isThrownBy(() -> new Delimiter("a\t8"));
        assertThatNoException().isThrownBy(() -> new Delimiter("\tb"));
    }

    @DisplayName("구분자는 탭을 제외한 제어문자를 포함할 수 없다.")
    @Test
    void containsControlCharacter_throwsException() {
        assertThatThrownBy(() -> new Delimiter("\u0000a"));
        assertThatThrownBy(() -> new Delimiter("c\u0004b"));
        assertThatThrownBy(() -> new Delimiter("a\nd"));
        assertThatThrownBy(() -> new Delimiter("z\n"));
    }
}
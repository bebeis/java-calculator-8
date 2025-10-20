package calculator.domain.service.extractor;

import calculator.domain.delimiter.Delimiter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CustomDelimiterExtractorTest {

    @DisplayName("커스텀 구분자가 존재하면 Delimiter 객체를 반환한다.")
    @Test
    void extractCustomDelimiter_whenExist() {
        // given
        CustomDelimiterExtractor extractor = new CustomDelimiterExtractor();
        String expr = "//;\\n1;2:3";

        // when
        Optional<Delimiter> delimiterOpt = extractor.extractDelimitersFrom(expr);

        // then
        assertThat(delimiterOpt.get()).isEqualTo(new Delimiter(";"));
    }

    @DisplayName("정규식 특수문자의 경우에도 정상적으로 Delimiter 객체를 반환한다.")
    @Test
    void extractCustomDelimiter_whenSpecialRegexCharacter() {
        // given
        CustomDelimiterExtractor extractor = new CustomDelimiterExtractor();
        String expr = "//.\\n1.2:3";

        // when
        Optional<Delimiter> delimiterOpt = extractor.extractDelimitersFrom(expr);

        // then
        assertThat(delimiterOpt.get()).isEqualTo(new Delimiter("."));
    }

    @DisplayName("커스텀 구분자가 존재하지 않으면 null 래핑 객체를 반환한다.")
    @Test
    void returnNullWrapperObject_whenNoCustomDelimiter() {
        CustomDelimiterExtractor extractor = new CustomDelimiterExtractor();
        String expr = "1,2:3";

        // when
        Optional<Delimiter> delimiterOpt = extractor.extractDelimitersFrom(expr);

        // then
        assertThat(delimiterOpt.isPresent()).isFalse();
    }

    @DisplayName("suffix가 존재하지 않는 경우 예외를 발생시킨다.")
    @Test
    void noSuffix_throwsException() {
        CustomDelimiterExtractor extractor = new CustomDelimiterExtractor();
        String expr = "//a1,2,3";

        assertThatThrownBy(() -> extractor.extractDelimitersFrom(expr))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("suffix가 잘못된 경우 예외를 발생시킨다.")
    @Test
    void illegalSuffix_throwsException() {
        CustomDelimiterExtractor extractor = new CustomDelimiterExtractor();
        String expr = "//a\1,2,3";

        assertThatThrownBy(() -> extractor.extractDelimitersFrom(expr))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
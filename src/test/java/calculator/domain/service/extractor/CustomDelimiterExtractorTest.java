package calculator.domain.service.extractor;

import calculator.domain.delimiter.Delimiter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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

}
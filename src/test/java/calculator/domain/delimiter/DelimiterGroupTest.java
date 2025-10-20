package calculator.domain.delimiter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DelimiterGroupTest {

    @DisplayName("기본 구분자에 커스텀 구분자를 추가하여 정규표현식을 얻을 수 있다.")
    @Test
    void getEmptyRegexStringByEmptyDelimiters() {
        // given
        DelimiterGroup delimiterGroup = DelimiterGroup.defaultDelimiterGroup();
        DelimiterGroup addedGroup = delimiterGroup.addCustomDelimiter(new Delimiter("a"));

        // when
        String regex = addedGroup.toRegexForSplit();

        // then
        assertThat(regex).isEqualTo("\\Q:\\E|\\Q,\\E|\\Qa\\E");
    }

    @DisplayName("기본 구분자에 커스텀 구분자를 추가하여 정규표현식을 얻을 수 있다.")
    @Test
    void getEmptyRegexStringByEmptyDelimiters2() {
        // given
        DelimiterGroup delimiterGroup = DelimiterGroup.defaultDelimiterGroup();
        DelimiterGroup addedGroup = delimiterGroup.addCustomDelimiter(new Delimiter("."));

        // when
        String regex = addedGroup.toRegexForSplit();

        // then
        assertThat(regex).isEqualTo("\\Q:\\E|\\Q,\\E|\\Q.\\E");
    }

    @DisplayName("기본 구분자 그룹을 생성한다.")
    @Test
    void createDefaultDelimiterGroup() {
        // given
        DelimiterGroup delimiterGroup = DelimiterGroup.defaultDelimiterGroup();

        // when
        String regex = delimiterGroup.toRegexForSplit();

        // then
        assertThat(regex).isEqualTo("\\Q:\\E|\\Q,\\E");
    }

    @DisplayName("이미 존재하는 구분자를 추가하면 기존 그룹을 그대로 반환한다")
    @Test
    void addDuplicatedDelimiter() {
        // given
        DelimiterGroup delimiterGroup = DelimiterGroup.defaultDelimiterGroup();

        // when
        DelimiterGroup sameGroup = delimiterGroup.addCustomDelimiter(new Delimiter(":"));

        assertThat(delimiterGroup).isEqualTo(sameGroup);
    }
}
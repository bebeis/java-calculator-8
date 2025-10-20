package calculator.domain.service.extractor;

import calculator.domain.delimiter.Delimiter;

import java.util.Optional;
import java.util.regex.Matcher;

import static calculator.domain.delimiter.CustomDelimiterPattern.CUSTOM_DELIMITER;

public class CustomDelimiterExtractor {

    public Optional<Delimiter> extractDelimitersFrom(final String delimitedNumberExpr) {

        if (hasNotCustomDelimiter(delimitedNumberExpr)) {
            return Optional.empty();
        }

        Matcher matcher = CUSTOM_DELIMITER.matcher(delimitedNumberExpr);
        if (!matcher.find()) {
            throw new IllegalArgumentException("커스텀 구분자 문법이 올바르지 않습니다.");
        }

        return Optional.of(new Delimiter(matcher.group(1)));
    }

    private boolean hasNotCustomDelimiter(final String delimitedNumberExpr) {
        return !delimitedNumberExpr.startsWith("//");
    }
}

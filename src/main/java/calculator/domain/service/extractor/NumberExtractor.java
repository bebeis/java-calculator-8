package calculator.domain.service.extractor;

import calculator.domain.number.PositiveNumber;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static calculator.domain.delimiter.CustomDelimiterPattern.CUSTOM_DELIMITER;

public class NumberExtractor {

    public List<PositiveNumber> extractNumbers(String delimitedNumberExpr, String regex) {

        String numberSection = stripCustomDelimiterHeader(delimitedNumberExpr);
        String[] tokens = numberSection.split(regex);

        return Arrays.stream(tokens)
                .map(String::trim)
                .filter(token -> !token.isEmpty())
                .map(this::toPositiveNumber)
                .toList();
    }

    private String stripCustomDelimiterHeader(final String delimitedNumberExpr) {
        Matcher matcher = CUSTOM_DELIMITER.matcher(delimitedNumberExpr);
        if (matcher.find()) {
            return delimitedNumberExpr.substring(matcher.end());
        }
        return delimitedNumberExpr;
    }

    private PositiveNumber toPositiveNumber(String token) {
        if (!Pattern.matches("\\d+(\\.\\d+)?", token)) {
            throw new IllegalArgumentException("유효하지 않은 숫자 형식입니다: " + token);
        }

        return new PositiveNumber(Double.parseDouble(token));
    }
}

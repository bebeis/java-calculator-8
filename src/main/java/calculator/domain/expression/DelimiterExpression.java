package calculator.domain.expression;

import java.util.regex.Matcher;

import static calculator.domain.delimiter.CustomDelimiterPattern.CUSTOM_DELIMITER;

public class DelimiterExpression {

    private final String header;
    private final String body;

    private DelimiterExpression(final String header, final String body) {
        this.header = header;
        this.body = body;
    }

    public static DelimiterExpression from(final String fullExpr) {
        if (!hasCustomDelimiter(fullExpr)) {
            return new DelimiterExpression("", fullExpr);
        }

        Matcher matcher = CUSTOM_DELIMITER.matcher(fullExpr);
        if (!matcher.find()) {
            throw new IllegalArgumentException("커스텀 구분자 문법이 올바르지 않습니다.");
        }

        return new DelimiterExpression(matcher.group(1), fullExpr.substring(matcher.end()));
    }

    private static boolean hasCustomDelimiter(final String fullExpr) {
        return fullExpr.startsWith("//");
    }

    public CustomDelimiterHeader toHeader() {
        return new CustomDelimiterHeader(header);
    }

    public OperandSection toOperandSection() {
        return new OperandSection(body);
    }
}



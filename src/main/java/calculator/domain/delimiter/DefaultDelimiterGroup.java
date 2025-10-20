package calculator.domain.delimiter;

import java.util.List;

public final class DefaultDelimiterGroup {
    private static final List<Delimiter> DEFAULT_DELIMITERS =
            List.of(new Delimiter(":"), new Delimiter(","));

    private DefaultDelimiterGroup() {
    }

    public static List<Delimiter> getDelimiterAll() {
        return DEFAULT_DELIMITERS;
    }
}

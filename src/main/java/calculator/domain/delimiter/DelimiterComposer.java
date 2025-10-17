package calculator.domain.delimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DelimiterComposer {
    private final List<Delimiter> delimiters = new ArrayList<>();

    public void addDelimiter(final Delimiter delimiter) {
        delimiters.add(delimiter);
    }

    public String toRegexForSplit() {
        return delimiters.stream()
                .map(Delimiter::toRegexToken)
                .collect(Collectors.joining("|"));
    }
}

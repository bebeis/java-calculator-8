package calculator.domain.delimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DelimiterGroup {
    private final List<Delimiter> delimiters;

    private DelimiterGroup(final List<Delimiter> delimiters) {
        this.delimiters = delimiters;
    }

    public static DelimiterGroup defaultDelimiterGroup() {
        return new DelimiterGroup(DefaultDelimiterGroup.getDelimiterAll());
    }

    public DelimiterGroup addCustomDelimiter(final Delimiter customDelimiter) {
        if (contains(customDelimiter)) {
            return this;
        }

        return new DelimiterGroup(mergeWith(customDelimiter));
    }

    private boolean contains(final Delimiter delimiter) {
        return delimiters.contains(delimiter);
    }

    private List<Delimiter> mergeWith(final Delimiter customDelimiter) {
        List<Delimiter> merged = new ArrayList<>(delimiters);
        merged.add(customDelimiter);
        return merged;
    }

    public String toRegexForSplit() {
        return delimiters.stream()
                .map(Delimiter::toRegexToken)
                .collect(Collectors.joining("|"));
    }
}

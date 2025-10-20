package calculator.domain.service;

import calculator.domain.delimiter.Delimiter;
import calculator.domain.delimiter.DelimiterGroup;
import calculator.domain.operand.Operand;
import calculator.domain.service.extractor.CustomDelimiterExtractor;
import calculator.domain.service.extractor.NumberExtractor;

import java.util.List;
import java.util.Optional;

public class DelimitedNumberService {

    private final CustomDelimiterExtractor delimiterExtractor = new CustomDelimiterExtractor();
    private final NumberExtractor numberExtractor = new NumberExtractor();

    public List<Operand> extractNumbersFrom(String delimitedNumberExpr) {
        Optional<Delimiter> customDelimiter = delimiterExtractor.extractDelimitersFrom(delimitedNumberExpr);

        DelimiterGroup delimiterGroup = customDelimiter
                .map(DelimiterGroup.defaultDelimiterGroup()::addCustomDelimiter)
                .orElse(DelimiterGroup.defaultDelimiterGroup());

        String regex = delimiterGroup.toRegexForSplit();
        return numberExtractor.extractNumbers(delimitedNumberExpr, regex);
    }
}

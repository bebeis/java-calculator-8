package calculator.domain.service;

import calculator.domain.delimiter.Delimiter;
import calculator.domain.delimiter.DelimiterGroup;
import calculator.domain.operand.Operand;
import calculator.domain.service.extractor.CustomDelimiterExtractor;
import calculator.domain.service.extractor.OperandExtractor;

import java.util.List;
import java.util.Optional;

public class DelimitedOperandService {

    private final CustomDelimiterExtractor delimiterExtractor = new CustomDelimiterExtractor();
    private final OperandExtractor operandExtractor = new OperandExtractor();

    public List<Operand> extractNumbersFrom(String delimitedNumberExpr) {
        Optional<Delimiter> customDelimiter = delimiterExtractor.extractDelimitersFrom(delimitedNumberExpr);

        DelimiterGroup delimiterGroup = customDelimiter
                .map(DelimiterGroup.defaultDelimiterGroup()::addCustomDelimiter)
                .orElse(DelimiterGroup.defaultDelimiterGroup());

        String regex = delimiterGroup.toRegexForSplit();
        return operandExtractor.extractOperands(delimitedNumberExpr, regex);
    }
}

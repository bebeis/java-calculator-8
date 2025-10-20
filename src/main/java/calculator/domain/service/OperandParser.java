package calculator.domain.service;

import calculator.domain.delimiter.Delimiter;
import calculator.domain.delimiter.DelimiterGroup;
import calculator.domain.expression.CustomDelimiterHeader;
import calculator.domain.expression.DelimiterExpression;
import calculator.domain.expression.OperandSection;
import calculator.domain.operand.Operand;

import java.util.List;
import java.util.Optional;

public class OperandParser {

    public List<Operand> extractOperandsFrom(final String fullExpr) {
        DelimiterExpression expression = DelimiterExpression.from(fullExpr);

        CustomDelimiterHeader header = expression.toHeader();
        OperandSection operandSection = expression.toOperandSection();

        Optional<Delimiter> customDelimiter = header.extractDelimiter();

        DelimiterGroup delimiterGroup = customDelimiter
                .map(DelimiterGroup.defaultDelimiterGroup()::addCustomDelimiter)
                .orElse(DelimiterGroup.defaultDelimiterGroup());

        String regex = delimiterGroup.toRegexForSplit();

        return operandSection.extractWith(regex);
    }
}

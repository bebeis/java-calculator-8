package calculator.service;

import calculator.domain.operand.Operand;
import calculator.domain.service.Adder;
import calculator.domain.service.OperandParser;

import java.util.List;
import java.util.Optional;

public class CalculatorService {

    private final OperandParser parser = new OperandParser();
    private final Adder adder = new Adder();

    public String calculate(final String calculationRequest) {
        // "숫자를 추출"하는 것과 "더하는" 것은 별도의 유즈케이스로 해석함.
        // 추후, 뺄샘이 생기면 subtracter 이런 것만 추가해줘도 됨
        List<Operand> operands = parser.extractOperandsFrom(calculationRequest);
        Optional<Operand> sumOpt = adder.sum(operands);

        // 더할 숫자가 없는 경우는 0을 반환한다는 Application 규칙으로 판단함. 도메인 규칙이 아님!
        return sumOpt.map(Operand::toString).orElse("0");
    }
}

package calculator.domain.service.adder;

import calculator.domain.operand.Operand;

import java.util.List;
import java.util.Optional;

public class Adder {

    public Optional<Operand> sum(List<Operand> operands) {
        return operands.stream()
                .reduce(Operand::addTo);
    }
}

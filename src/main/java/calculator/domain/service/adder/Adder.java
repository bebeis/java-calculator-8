package calculator.domain.service.adder;

import calculator.domain.number.PositiveNumber;

import java.util.List;
import java.util.Optional;

public class Adder {

    public Optional<PositiveNumber> sum(List<PositiveNumber> positiveNumbers) {
        return positiveNumbers.stream()
                .reduce(PositiveNumber::addTo);
    }
}

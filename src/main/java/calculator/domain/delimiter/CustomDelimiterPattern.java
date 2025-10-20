package calculator.domain.delimiter;

import java.util.regex.Pattern;

public final class DelimiterPattern {

    private DelimiterPattern() {
    }

    // Pattern 클래스는 Immutable 하고 Thread-safe 하므로, `static final`로 미리 컴파일해 재사용할 수 있도록 구현했습니다.
    public static final Pattern CUSTOM_DELIMITER = Pattern.compile("^//(.+?)\\\\n");
}

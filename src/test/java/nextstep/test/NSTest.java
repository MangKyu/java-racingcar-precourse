package nextstep.test;

import nextstep.utils.Randoms;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockedStatic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

public abstract class NSTest {
    private static final Duration TEST_TIMEOUT = Duration.ofSeconds(1L);

    private PrintStream standardOut;
    private OutputStream captor;

    protected void setUp() {
        standardOut = System.out;
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    protected void assertSimpleTest(final Executable executable) {
        assertTimeoutPreemptively(TEST_TIMEOUT, executable);
    }

    protected void assertRandomTest(final Executable executable, final int value, final int... values) {
        assertTimeoutPreemptively(TEST_TIMEOUT, () -> {
            try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
                mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                        .thenReturn(value, Arrays.stream(values).boxed().toArray());
                executable.execute();
            }
        });
    }

    /**
     * 사용자의 입력을 기다리는 상황에서 테스트 종료
     * @param args
     */
    protected void runNoLineFound(final String... args) {
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(
                () -> subject(args)
        );
    }

    /**
     * 프로그램이 정상적으로 종료
     * @param args
     */
    protected void run(final String... args) {
        subject(args);
    }

    protected void verify(final String... args) {
        assertThat(output()).contains(args);
    }

    private void subject(final String... args) {
        command(args);
        runMain();
    }

    protected abstract void runMain();

    private void command(final String... args) {
        final byte[] buf = Strings.join(args).with("\n").getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }

    protected String output() {
        return captor.toString().trim();
    }

    protected void outputStandard() {
        System.setOut(standardOut);
        System.out.println(output());
    }
}

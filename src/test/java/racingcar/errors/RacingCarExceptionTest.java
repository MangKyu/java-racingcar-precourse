package racingcar.errors;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RacingCarExceptionTest {

    @Test
    void invalidInput의타입과메세지검사() {
        // given

        // when
        final RuntimeException result = RacingCarException.invalidInput("메세지");

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class).hasMessageContaining(RacingCarException.PREFIX);
    }

    @Test
    void invalidState의타입과메세지검사() {
        // given

        // when
        final RuntimeException result = RacingCarException.invalidState("메세지");

        // then
        assertThat(result).isInstanceOf(IllegalStateException.class).hasMessageContaining(RacingCarException.PREFIX);
    }

}
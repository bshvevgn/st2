package com.bshvevgn;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.stream.Stream;

import com.bshvevgn.func.FunctionsSystem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FunctionsSystemTest {

  private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.00000001");
  private static final int DEFAULT_SCALE = 8;

  @Test
  void shouldNotAcceptNullArgument() {
    final FunctionsSystem system = new FunctionsSystem();
    assertThrows(NullPointerException.class, () -> system.calculate(null, DEFAULT_PRECISION));
  }

  @Test
  void shouldNotAcceptNullPrecision() {
    final FunctionsSystem system = new FunctionsSystem();
    assertThrows(NullPointerException.class, () -> system.calculate(new BigDecimal(-2), null));
  }

  @ParameterizedTest
  @MethodSource("illegalPrecisions")
  void shouldNotAcceptIncorrectPrecisions(final BigDecimal precision) {
    final FunctionsSystem system = new FunctionsSystem();
    assertThrows(ArithmeticException.class, () -> system.calculate(new BigDecimal(-2), precision));
  }

  @Test
  void shouldThrowExceptionForZeroArgument() {
    final FunctionsSystem system = new FunctionsSystem();
    assertThrows(
            ArithmeticException.class,
            () -> system.calculate(ZERO, DEFAULT_PRECISION)
    );
  }

  @Test
  void shouldNotAcceptOneArgument() {
    final FunctionsSystem system = new FunctionsSystem();
    assertNull((system.calculate(ONE, DEFAULT_PRECISION)));
  }

  @Test
  void shouldCalculateForPositiveValue() {
    final FunctionsSystem system = new FunctionsSystem();
    final BigDecimal expected = new BigDecimal("-1.8731678428829290");
    assertEquals(expected, system.calculate(new BigDecimal(5), DEFAULT_PRECISION));
  }

  private static Stream<Arguments> illegalPrecisions() {
    return Stream.of(
        Arguments.of(BigDecimal.valueOf(1)),
        Arguments.of(BigDecimal.valueOf(0)),
        Arguments.of(BigDecimal.valueOf(1.01)),
        Arguments.of(BigDecimal.valueOf(-0.01)));
  }
}

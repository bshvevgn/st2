package com.bshvevgn;

import static java.math.BigDecimal.ONE;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.stream.Stream;

import com.bshvevgn.func.SeriesFunction;
import com.bshvevgn.log.Ln;
import com.bshvevgn.log.Log;
import com.bshvevgn.trg.Cos;
import com.bshvevgn.trg.Sin;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SeriesFunctionTest {

  private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.000001");

  @ParameterizedTest
  @MethodSource("functions")
  void shouldNotAcceptNullArgument(final SeriesFunction function) {
    assertThrows(NullPointerException.class, () -> function.calculate(null, DEFAULT_PRECISION));
  }

  @ParameterizedTest
  @MethodSource("functions")
  void shouldNotAcceptNullPrecision(final SeriesFunction function) {
    assertThrows(NullPointerException.class, () -> function.calculate(ONE, null));
  }

  private static Stream<Arguments> functions() {
    return Stream.of(
        Arguments.of(new Sin()),
        Arguments.of(new Cos()),
        Arguments.of(new Ln()),
        Arguments.of(new Log(3)),
        Arguments.of(new Log(5)),
        Arguments.of(new Log(10)));
  }
}

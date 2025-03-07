package com.bshvevgn.func;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class IterFunction implements SeriesFunction {

  private static final int MAX_ITERATIONS = 1000;

  protected final int maxIterations;

  protected IterFunction() {
    this.maxIterations = MAX_ITERATIONS;
  }

  protected void checkValidity(final BigDecimal x, final BigDecimal precision) {
    Objects.requireNonNull(x, "Аргумент функции не может быть null");
    Objects.requireNonNull(precision, "Точность не может быть null");
    if (precision.compareTo(ZERO) <= 0 || precision.compareTo(ONE) >= 0) {
      throw new ArithmeticException("Точность должна быть меньше 1 и больше 0");
    }
  }
}

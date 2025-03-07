package com.bshvevgn.log;

import com.bshvevgn.func.IterFunction;

import static java.lang.String.format;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.util.Objects;

public class Ln extends IterFunction {

  public Ln() {
    super();
  }

  @Override
  public BigDecimal calculate(final BigDecimal x, final BigDecimal precision)
      throws ArithmeticException {
    Objects.requireNonNull(x, "Аргумент функции не может быть null");
    Objects.requireNonNull(precision, "Точность не может быть null");
    if (precision.compareTo(ZERO) <= 0 || precision.compareTo(ONE) >= 0) {
      throw new ArithmeticException("Точность должна быть меньше 1 и больше 0");
    }
    if (x.compareTo(ZERO) <= 0) {
      throw new ArithmeticException("Значения функции для аргумента " + x + " не существует");
    }
    if (x.compareTo(ONE) == 0) {
      return ZERO;
    }

    double X = x.doubleValue();

    BigDecimal curValue = BigDecimal.ZERO, prevValue;
    int i = 1;

    if (Math.abs(X - 1) <= 1){
      do {
        prevValue = curValue;
        curValue =  curValue.add(
                (
                        (BigDecimal.valueOf(-1).pow(i - 1))
                                .multiply(BigDecimal.valueOf(X - 1).pow(i))
                )
                        .divide(BigDecimal.valueOf(i), precision.scale(), HALF_UP)
        );
        i++;
      } while (new BigDecimal("0.1").pow(precision.scale()).compareTo((prevValue.subtract(curValue)).abs()) < 0 && i < maxIterations);
      return curValue.add(prevValue).divide(BigDecimal.valueOf(2), HALF_EVEN);
    } else {
      do {
        prevValue = curValue;
        curValue =  curValue.add(
                (
                        BigDecimal.valueOf(-1).pow(i - 1)
                                .divide(BigDecimal.valueOf(X - 1).pow(i), precision.scale(), HALF_UP)
                )
                        .divide(BigDecimal.valueOf(i), precision.scale(), HALF_UP)
        );
        i++;
      } while (new BigDecimal("0.1").pow(precision.scale()).compareTo((prevValue.subtract(curValue)).abs()) < 0 && i < maxIterations);

      curValue = curValue.add(calculate(BigDecimal.valueOf(X - 1), precision));
    }

    return curValue.setScale(precision.scale(), HALF_EVEN);
  }
}

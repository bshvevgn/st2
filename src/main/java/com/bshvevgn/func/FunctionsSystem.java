package com.bshvevgn.func;

import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.bshvevgn.log.Ln;
import com.bshvevgn.log.Log;
import com.bshvevgn.trg.Cos;
import com.bshvevgn.trg.Csc;

import java.math.BigDecimal;
import java.math.MathContext;

public class FunctionsSystem implements SeriesFunction {

  private final Cos cos;
  private final Csc csc;
  private final Ln ln;
  private final Log log3;
  private final Log log10;

  public FunctionsSystem() {
    this.cos = new Cos();
    this.csc = new Csc();
    this.ln = new Ln();
    this.log3 = new Log(3);
    this.log10 = new Log(10);
  }

  public BigDecimal calculate(final BigDecimal x, final BigDecimal precision) {
    final MathContext mc = new MathContext(DECIMAL128.getPrecision(), HALF_EVEN);
    final BigDecimal cX = x.remainder(BigDecimalMath.pi(mc).multiply(new BigDecimal(2)));
    if (x.compareTo(ZERO) <= 0) {
      return cos.calculate(cX, precision)
              .add(csc.calculate(cX, precision))
              .setScale(precision.scale(), HALF_EVEN);
    } else {
      if (ln.calculate(cX, precision).equals(ZERO)) return null;
      return (((ln.calculate(cX, precision)
              .multiply(log10.calculate(cX, precision)))
              .divide(log3.calculate(cX, precision), HALF_UP)
              .subtract(log10.calculate(cX, precision)
                      .divide(log3.calculate(cX, precision), HALF_UP)))
              .subtract(log10.calculate(cX, precision)
                      .add(log3.calculate(cX, precision))));
    }
  }
}

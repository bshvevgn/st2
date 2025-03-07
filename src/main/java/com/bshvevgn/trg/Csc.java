package com.bshvevgn.trg;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.bshvevgn.func.IterFunction;

import java.math.BigDecimal;
import java.math.MathContext;

public class Csc extends IterFunction {

    private final Sin sin;

    public Csc() {
        super();
        this.sin = new Sin();
    }

    public Csc(final Sin sin) {
        super();
        this.sin = sin;
    }

    @Override
    public BigDecimal calculate(final BigDecimal x, final BigDecimal precision)
            throws ArithmeticException {
        checkValidity(x, precision);

        final MathContext mc = new MathContext(DECIMAL128.getPrecision(), HALF_EVEN);
        final BigDecimal correctedX = x.remainder(BigDecimalMath.pi(mc).multiply(new BigDecimal(2), mc));

        final BigDecimal sinResult = sin.calculate(correctedX, precision);

        if (sinResult.compareTo(ZERO) == 0) {
            throw new ArithmeticException("csc не определён для x, в котором sin равен 0");
        }

        final BigDecimal result = ONE.divide(sinResult, DECIMAL128.getPrecision(), HALF_EVEN);
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
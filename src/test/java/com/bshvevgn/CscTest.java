package com.bshvevgn;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import ch.obermuhlner.math.big.BigDecimalMath;
import java.math.BigDecimal;
import java.math.MathContext;

import com.bshvevgn.trg.Csc;
import com.bshvevgn.trg.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CscTest {

  private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.000001");
  private static final MathContext MC = new MathContext(DECIMAL128.getPrecision());

  @Mock private Sin mockSin;
  @Spy private Sin spySin;

  @Test
  void shouldCallSinFunction() {
    final Csc csc = new Csc(spySin);
    csc.calculate(new BigDecimal(1), new BigDecimal("0.001"));
    
    verify(spySin, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
  }

  @Test
  void shouldCalculateWithMockSin() {
    final BigDecimal arg = new BigDecimal("1.5708"); // ~π/2
    final BigDecimal sinResult = new BigDecimal("1.0");
    
    when(mockSin.calculate(eq(arg), any(BigDecimal.class))).thenReturn(sinResult);
    
    final Csc csc = new Csc(mockSin);
    assertEquals(ONE.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), 
                csc.calculate(arg, new BigDecimal("0.000001")));
  }

  @Test
  void shouldCalculateForPiDividedByTwo() {
    final Csc csc = new Csc();
    final BigDecimal arg = BigDecimalMath.pi(MC)
                          .divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN);
    
    assertEquals(ONE.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), 
                csc.calculate(arg, DEFAULT_PRECISION));
  }

  @Test
  void shouldThrowForZero() {
    final Csc csc = new Csc();
    final BigDecimal arg = ZERO;
    
    assertThrows(ArithmeticException.class, 
                () -> csc.calculate(arg, DEFAULT_PRECISION));
  }

  @Test
  void shouldCalculateForThreePiDividedByTwo() {
    final Csc csc = new Csc();
    final BigDecimal arg = BigDecimalMath.pi(MC)
                          .multiply(new BigDecimal("1.5"))
                          .setScale(MC.getPrecision(), HALF_EVEN);
    
    assertEquals(new BigDecimal("-1.000000"),
                csc.calculate(arg, DEFAULT_PRECISION));
  }

  @Test
  void shouldHandlePeriodicity() {
    final Csc csc = new Csc();
    final BigDecimal baseArg = BigDecimalMath.pi(MC).divide(new BigDecimal(2), MC);
    final BigDecimal periodicArg = baseArg.add(BigDecimalMath.pi(MC).multiply(new BigDecimal(10)));
    
    assertEquals(ONE.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), 
                csc.calculate(periodicArg, DEFAULT_PRECISION));
  }
}
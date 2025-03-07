package com.bshvevgn;

import com.bshvevgn.func.FunctionsSystem;
import com.bshvevgn.log.Ln;
import com.bshvevgn.log.Log;
import com.bshvevgn.trg.Cos;
import com.bshvevgn.trg.Csc;
import com.bshvevgn.trg.Sin;

import java.io.IOException;
import java.math.BigDecimal;

public class App {

    public static void main(String[] args) throws IOException {
        final Csc csc = new Csc();
        CSVWriter.write(
                "csv/csc.csv",
                csc,
                new BigDecimal(-2),
                new BigDecimal(-1),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));

        final Cos cos = new Cos();
        CSVWriter.write(
                "csv/cos.csv",
                cos,
                new BigDecimal(-1),
                new BigDecimal(1),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));

        final Sin sin = new Sin();
        CSVWriter.write(
                "csv/sin.csv",
                sin,
                new BigDecimal(-1),
                new BigDecimal(1),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));

        final Ln ln = new Ln();
        CSVWriter.write(
                "csv/ln.csv",
                ln,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));

        final Log log3 = new Log(3);
        CSVWriter.write(
                "csv/log3.csv",
                log3,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.00000000001"));

        final Log log5 = new Log(5);
        CSVWriter.write(
                "csv/log5.csv",
                log5,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.00000000001"));

        final Log log10 = new Log(10);
        CSVWriter.write(
                "csv/log10.csv",
                log10,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.00000000001"));

        final FunctionsSystem func = new FunctionsSystem();
        CSVWriter.write(
                "csv/func.csv",
                func,
                new BigDecimal(-2),
                new BigDecimal(-1),
                new BigDecimal("0.1"),
                new BigDecimal("0.00000000001"));
    }
}

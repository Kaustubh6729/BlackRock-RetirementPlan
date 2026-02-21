package com.blackrock.hackerrank.util;

import com.blackrock.hackerrank.model.Period;

import java.time.LocalDateTime;
import java.util.List;

public final class PeriodUtil {

    private PeriodUtil() {
    }

    public static boolean isWithinPeriod(
            LocalDateTime timestamp,
            Period period) {

        if (timestamp == null || period == null) {
            return false;
        }

        return !timestamp.isBefore(period.getStart())
                && !timestamp.isAfter(period.getEnd());
    }

    public static Period findApplicableFixedPeriod(
            LocalDateTime timestamp,
            List<Period> periods) {

        if (periods == null || periods.isEmpty()) {
            return null;
        }

        Period selected = null;

        for (Period period : periods) {

            if (period.getFixed() == null) {
                continue;
            }

            if (isWithinPeriod(timestamp, period)) {

                if (selected == null
                        || period.getStart().isAfter(selected.getStart())) {

                    selected = period;
                }
            }
        }

        return selected;
    }

    public static double calculateTotalExtras(
            LocalDateTime timestamp,
            List<Period> periods) {

        if (periods == null || periods.isEmpty()) {
            return 0;
        }

        double extraSum = 0;

        for (Period period : periods) {

            if (period.getExtra() == null) {
                continue;
            }

            if (isWithinPeriod(timestamp, period)) {

                extraSum = MoneyUtil.safeAdd(
                        extraSum,
                        period.getExtra()
                );
            }
        }

        return extraSum;
    }
}
package com.blackrock.hackerrank.model;

import java.time.LocalDateTime;

public class Period {

    private LocalDateTime start;
    private LocalDateTime end;

    private Double fixed;
    private Double extra;

    public Period() {
    }

    public Period(LocalDateTime start,
                  LocalDateTime end,
                  Double fixed,
                  Double extra) {

        this.start = start;
        this.end = end;
        this.fixed = fixed;
        this.extra = extra;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Double getFixed() {
        return fixed;
    }

    public void setFixed(Double fixed) {
        this.fixed = fixed;
    }

    public Double getExtra() {
        return extra;
    }

    public void setExtra(Double extra) {
        this.extra = extra;
    }
}
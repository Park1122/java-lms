package nextstep.courses.domain.session;

import java.time.LocalDate;

public class Period {
    private LocalDate startDate;

    private LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        validate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void validate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작일이 종료일보다 늦을 수 없습니다.");
        }
    }
}

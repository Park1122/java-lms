package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserPaymentInfoTest {

    @Test
    void hasEqualUser() {
        UserPaymentInfo userPaymentInfo = new UserPaymentInfo(NsUserTest.SANJIGI, new Payment());
        assertThat(userPaymentInfo.hasEqualUser(NsUserTest.SANJIGI)).isTrue();
    }
}

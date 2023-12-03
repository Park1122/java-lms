package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class UserPaymentInfo {

    private final NsUser user;
    private final Payment payment;

    public UserPaymentInfo(NsUser user, Payment payment) {
        this.user = user;
        this.payment = payment;
    }

    public boolean hasEqualUser(NsUser user) {
        return user.equals(this.user);
    }
}

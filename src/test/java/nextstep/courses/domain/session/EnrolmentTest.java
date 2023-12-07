package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrolmentTest {

    @DisplayName("강의 유료 결제 한다.")
    @Test
    void 강의유료결제한다() {
        // given
        boolean isFree = false;
        int money = 10000;
        ParticipantManager participantManager = new ParticipantManager(10);
        Price price = new Price(isFree, money);
        Enrolment enrolment = new Enrolment(participantManager, price);
        // when
        enrolment.addParticipant(money, NsUserTest.JAVAJIGI);
        // then
        assertThat(enrolment.nowParticipants()).isEqualTo(1);
    }

    @DisplayName("강의 참여가 최대를 넘기면 예외가 발생한다.")
    @Test
    void 강의_참여가_최대를_넘기면_예외가_발생한다() {
        // given
        boolean isFree = false;
        int money = 10000;
        List<NsUser> users = new ArrayList<>() {{
            add(NsUserTest.JAVAJIGI);
        }};
        ParticipantManager participantManager = new ParticipantManager(1, new SessionParticipants(users));
        Price price = new Price(isFree, money);
        Enrolment enrolment = new Enrolment(participantManager, price);
        // when
        // then
        assertThatThrownBy(() -> enrolment.addParticipant(money, NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("결제할 금액이 강의가격과 다르면 예외가 발생한다.")
    @Test
    void 결제할_금액이_강의가격과_다르면_예외가_발생한다() {
        // given
        boolean isFree = false;
        int money = 10000;
        ParticipantManager participantManager = new ParticipantManager(10);
        Price price = new Price(isFree, money);
        Enrolment enrolment = new Enrolment(participantManager, price);
        // when
        // then
        assertThatThrownBy(() -> enrolment.addParticipant(1000, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

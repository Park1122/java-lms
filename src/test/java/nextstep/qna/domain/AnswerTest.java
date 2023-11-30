package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 생성 시 삭제 여부 메서드에 false가 반환된다.")
    void answer_초기() {
        assertThat(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3").isDeleted()).isFalse();
    }

    @Test
    @DisplayName("답변을 삭제하면 삭제 여부 메서드에 true가 반환된다.")
    void answer_삭제() {
        A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변을 삭제하면 히스토리가 반환된다.")
    void answer_삭제_히스토리() {
        DeleteHistory fakeHistory = new DeleteHistory(ContentType.ANSWER, A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now());

        DeleteHistory deleteHistory = A1.delete(NsUserTest.JAVAJIGI);

        assertThat(deleteHistory).isEqualTo(fakeHistory);
    }

    @Test
    @DisplayName("질문자와 삭제하려는 답변자가 다르면 예외를 던진다.")
    void answer_삭제_검증() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다");
    }
}

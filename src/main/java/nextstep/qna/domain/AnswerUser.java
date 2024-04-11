package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class AnswerUser {

    private List<Answer> users;

    public AnswerUser(List<Answer> users) {
        this.users = users;
    }

    public void add(Answer answer) {
        users.add(answer);
    }

    public void isOwner(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : users) {
            if (!answer.getWriter().equals(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }
    }

    public List<Answer> getUsers() {
        return users;
    }
}

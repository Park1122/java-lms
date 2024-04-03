package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public void addQuestionDeleteHistory(Question question) {
        deleteHistories.add(question.makeDeleteHistory());
        for (Answer answer : question.getAnswers()) {
            deleteHistories.add(answer.makeDeleteHistory());
        }

    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }


}

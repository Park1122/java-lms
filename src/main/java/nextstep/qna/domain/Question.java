package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question{
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private List<Answer> answers = new ArrayList<>();

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(NsUser writer, String title, String contents, boolean deleted, List<Answer> answers) {
        this(0L, title, contents, writer, answers, deleted, LocalDateTime.now(), LocalDateTime.now());
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this(id, title, contents, writer, new ArrayList<>(), false, LocalDateTime.now(), LocalDateTime.now());
    }

    public Question(Long id, String title, String contents, NsUser writer, List<Answer> answers, boolean deleted, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.answers = answers;
        this.deleted = deleted;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Question setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public void delete(NsUser nsUser) throws CannotDeleteException {
        checkIfOwner(nsUser);
        checkAnswer(nsUser);
        this.deleted = true;
    }

    public void checkIfOwner(NsUser nsUser) throws CannotDeleteException {
        if(!isOwner(nsUser)){
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public void checkAnswer(NsUser nsUser) throws CannotDeleteException {
        boolean ifNotUser = this.answers
                .stream()
                .anyMatch(iter -> !iter.isOwner(nsUser));

        if(ifNotUser){
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return deleted == question.deleted && Objects.equals(id, question.id) && Objects.equals(title, question.title) && Objects.equals(contents, question.contents) && Objects.equals(writer, question.writer) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, contents, writer, answers, deleted);
    }
}

package parsecsei;

public class Question {
    
    private boolean status;
    private String question;
    
    public Question(boolean status, String question) {
        this.status = status;
        this.question = question;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public boolean isEnabled() {
        return status;
    }
    
    @Override
    public String toString() {
        return question;
    }
    
}

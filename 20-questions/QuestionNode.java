/**
*
* Matt Jensen
* CS145 - Lab 6
* 5/30/19
*
*/

public class QuestionNode {
    private QuestionNode yes;
    private QuestionNode no;
    private String data;
    private boolean isQuestion;
    

    public QuestionNode(String input) {
        this.data = unwrap(input);
    }
    public QuestionNode(String input, boolean isQuestion) {
        this.data = unwrap(input);
        if( isQuestion ) {
            this.markAsQuestion();
        } else {
            this.markAsAnswer();
        }
    }
    public boolean hasQuestion() {
        return ! this.isAnswer() && (this.hasYes() || this.hasNo() );
    }
    public boolean needsAnswer() {
        return ! this.isAnswer() && (! this.hasYes() || ! this.hasNo() );
    }
    public void markAsQuestion() {
        this.isQuestion = true;
    }
    public void markAsAnswer() {
        this.isQuestion = false;
    }
    public void addYes(QuestionNode yes) {
        this.yes = yes;
    }
    public void addNo(QuestionNode no) {
        this.no = no;
    }
    public void addAnswer(QuestionNode answer) {
        if( this.hasYes() ) {
            this.addNo(answer);
        } else {
            this.addYes(answer);
        }
    }
    public QuestionNode getYes() {
        return this.yes;
    }
    public QuestionNode getNo() {
        return this.no;
    }
    public String toString(boolean wrap) {
        return this.wrap(this.data);
    }
    public String toString() {
        return this.data;
    }
    public boolean isAnswer() {
        return this.isQuestion == false;
    }
    public boolean hasYes() {
        return this.yes != null;
    }
    public boolean hasNo() {
        return this.no != null;
    }
    private String unwrap(String input) {
        if( input.contains(":") ) {
            return input.split(":")[1];
        }
        return input;
    }
    private String wrap(String input) {
        String prefix = "";
        if( this.isAnswer() ) {
            prefix = "A";
        } else {
            prefix = "Q";
        }
        return prefix + ":" + input;
    }
}

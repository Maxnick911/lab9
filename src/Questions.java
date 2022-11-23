public class Questions {

    private String question;
    private String[] answers;

    public Questions(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
    }

    public String correctAnswer() {
        return this.answers[answers.length - 1];
    }

    public String correctAnswerCheckBox1() { return this.answers[answers.length - 1]; }
    public String correctAnswerCheckBox2() { return this.answers[answers.length - 2]; }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

}

public class Card implements Comparable<Card> {
    private String question; // Question on the flashcard
    private String answer;   // Answer on the flashcard

    // Constructor to initialize question and answer
    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getter for the question
    public String getQuestion() {
        return question;
    }

    // Setter for the question (optional)
    public void setQuestion(String question) {
        this.question = question;
    }

    // Getter for the answer
    public String getAnswer() {
        return answer;
    }

    // Setter for the answer (optional)
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // Override compareTo method for sorting Cards
    @Override
    public int compareTo(Card other) {
        return this.question.compareTo(other.question);
    }

    // Override toString() for a readable representation
    @Override
    public String toString() {
        return "Question: " + question + ", Answer: " + answer;
    }

    // Override equals() for comparing flashcards
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return question.equals(card.question) && answer.equals(card.answer);
    }

    // Override hashCode() to match equals (if needed for collections like HashSet)
    @Override
    public int hashCode() {
        return question.hashCode() + answer.hashCode();
    }
}

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {
    private JLabel displayLabel;
    private JTextField userInput;
    private JButton submitQuestion, submitAnswer, flipButton, readyToStudy, nextButton;
    private SortedList<Card> flashcards;
    private boolean isQuestionSide = true; // Tracks whether the question or answer is displayed
    private int currentIndex = 0; // Tracks the current flashcard
    private String tempQuestion = ""; // Temporarily holds the question during input mode

    public MyFrame() {
        // Initialize flashcards
        flashcards = new SortedList<>();


        // Display label (for showing questions or answers)
        displayLabel = new JLabel("Enter a question:");
        displayLabel.setBounds(50, 50, 300, 30);
        displayLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
        displayLabel.setForeground(Color.WHITE);

        // Input field
        userInput = new JTextField();
        userInput.setBounds(50, 100, 300, 30);
        userInput.setFont(new Font("SansSerif", Font.PLAIN, 16));
        userInput.setForeground(Color.BLACK);
        userInput.setBackground(Color.WHITE);
        userInput.setBorder(BorderFactory.createLineBorder(new Color(0, 200, 90)));

        // Buttons
        submitQuestion = new JButton("Submit Question");
        submitQuestion.setBounds(50, 150, 150, 40);
        submitQuestion.addActionListener(this);

        submitAnswer = new JButton("Submit Answer");
        submitAnswer.setBounds(210, 150, 150, 40);
        submitAnswer.addActionListener(this);
        submitAnswer.setEnabled(false); // Initially disabled

        flipButton = new JButton("Flip");
        flipButton.setBounds(50, 200, 150, 40);
        flipButton.addActionListener(this);
        flipButton.setEnabled(false); // Initially disabled

        readyToStudy = new JButton("Ready to Study");
        readyToStudy.setBounds(210, 200, 150, 40);
        readyToStudy.addActionListener(this);
        readyToStudy.setEnabled(false); // Initially disabled

        nextButton = new JButton("Next");
        nextButton.setBounds(130, 250, 150, 40);
        nextButton.addActionListener(this);
        nextButton.setEnabled(false); // Initially disabled

        // Frame setup
        this.setResizable(false);
        this.setTitle("Flashcard App");
        this.setSize(400, 350);
        this.setLayout(null);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(0, 0, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add components to the frame
        this.add(displayLabel);
        this.add(userInput);
        this.add(submitQuestion);
        this.add(submitAnswer);
        this.add(flipButton);
        this.add(readyToStudy);
        this.add(nextButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitQuestion) {
            tempQuestion = userInput.getText().trim();
            userInput.setText("");
            displayLabel.setText("Enter the answer:");
            submitQuestion.setEnabled(false);
            submitAnswer.setEnabled(true);
        } else if (e.getSource() == submitAnswer) {
            String tempAnswer = userInput.getText().trim();
            userInput.setText("");
            if (!tempQuestion.isEmpty() && !tempAnswer.isEmpty()) {
                flashcards.insert(new Card(tempQuestion, tempAnswer));
                displayLabel.setText("Enter a question:");
                submitQuestion.setEnabled(true);
                submitAnswer.setEnabled(false);
                readyToStudy.setEnabled(true);
            }
        } else if (e.getSource() == readyToStudy) {
            if (flashcards.size() > 0) { // Ensure there are flashcards
                currentIndex = 0;
                isQuestionSide = true;
                displayLabel.setText(flashcards.retrieve(currentIndex).getQuestion());
                userInput.setEnabled(false);
                submitQuestion.setEnabled(false);
                submitAnswer.setEnabled(false);
                flipButton.setEnabled(true);
                nextButton.setEnabled(true);
            }
        } else if (e.getSource() == flipButton) {
            if (isQuestionSide) {
                displayLabel.setText(flashcards.retrieve(currentIndex).getAnswer());
            } else {
                displayLabel.setText(flashcards.retrieve(currentIndex).getQuestion());
            }
            isQuestionSide = !isQuestionSide;
        } else if (e.getSource() == nextButton) {
            currentIndex = (currentIndex + 1) % flashcards.size(); // Cycle through flashcards
            isQuestionSide = true;
            displayLabel.setText(flashcards.retrieve(currentIndex).getQuestion());
        }
    }

    public static void main(String[] args) {
        new MyFrame(); // Launch the application
    }
}

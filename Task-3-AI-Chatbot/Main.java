import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    JTextArea chatArea;
    JTextField inputField;
    JButton sendButton;

    public Main() {

        setTitle("AI Chatbot");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);

        inputField = new JTextField();
        sendButton = new JButton("Send");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        chatArea.append("Bot: Hello! How can I help you?\n");

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
    }

    void sendMessage() {

        String input = inputField.getText().trim();

        if (input.isEmpty())
            return;

        chatArea.append("You: " + input + "\n");

        String response = getResponse(input);

        chatArea.append("Bot: " + response + "\n\n");

        inputField.setText("");
    }

    String getResponse(String input) {

        String text = input.toLowerCase();

        if (text.contains("hello") || text.contains("hi")) {
            return "Hello! Nice to meet you.";
        }

        if (text.contains("name")) {
            return "I am an AI chatbot.";
        }

        if (text.contains("java")) {
            return "Java is a popular programming language.";
        }

        if (text.contains("nlp")) {
            return "NLP helps computers understand human language.";
        }

        if (text.contains("help")) {
            return "I can answer basic questions about Java, NLP and AI.";
        }

        if (text.contains("thank")) {
            return "You're welcome!";
        }

        if (text.contains("bye")) {
            return "Goodbye! Have a nice day.";
        }

        return "Sorry, I don't understand. Please try another question.";
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Main bot = new Main();
            bot.setVisible(true);
        });
    }
}
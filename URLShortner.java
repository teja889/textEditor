import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class URLShortenerApp extends JFrame {
    private Map<String, String> urlToShortUrlMap;
    private Map<String, String> shortUrlToUrlMap;
    private JTextField longURLField;
    private JTextField shortURLField;

    public URLShortenerApp() {
        urlToShortUrlMap = new HashMap<>();
        shortUrlToUrlMap = new HashMap<>();
        
        setTitle("URL Shortener");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel longURLLabel = new JLabel("Enter Long URL:");
        longURLField = new JTextField(25);

        JButton shortenButton = new JButton("Shorten URL");
        shortenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shortenURL();
            }
        });

        JLabel shortURLLabel = new JLabel("Shortened URL:");
        shortURLField = new JTextField(25);
        shortURLField.setEditable(false);

        add(longURLLabel);
        add(longURLField);
        add(shortenButton);
        add(shortURLLabel);
        add(shortURLField);
    }

    private void shortenURL() {
        String longURL = longURLField.getText();
        String shortURL = generateShortURL();

        urlToShortUrlMap.put(longURL, shortURL);
        shortUrlToUrlMap.put(shortURL, longURL);

        shortURLField.setText(shortURL);
    }

    private String generateShortURL() {
        // Implement your short URL generation logic here
        // This can be similar to the previous example

        // For simplicity, we'll use a placeholder.
        return "https://short.url/" + System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            URLShortenerApp app = new URLShortenerApp();
            app.setVisible(true);
        }
        );
    }
}

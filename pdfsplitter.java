import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.multipdf.Splitter;

public class PdfSplitterUI extends JFrame {
    private JTextField inputFilePathField;
    private JTextField outputDirectoryField;
    private JButton splitButton;

    public PdfSplitterUI() {
        // Set up the UI components
        setTitle("PDF Splitter");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputFilePathField = new JTextField("Input PDF File");
        outputDirectoryField = new JTextField("Output Directory");
        splitButton = new JButton("Split PDF");

        // Set layout manager
        setLayout(new GridLayout(3, 1));

        // Add components to the frame
        add(inputFilePathField);
        add(outputDirectoryField);
        add(splitButton);

        // Add action listener for the split button
        splitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                splitPdf(inputFilePathField.getText(), outputDirectoryField.getText());
            }
        });
    }

    private void splitPdf(String inputFilePath, String outputDirectory) {
        try {
            // Load the PDF document
            PDDocument document = PDDocument.load(new File(inputFilePath));

            // Create a PDF splitter
            Splitter splitter = new Splitter();

            // Set the source document for the splitter
            splitter.setDocument(document);

            // Get the list of split documents
            java.util.List<PDDocument> splitDocuments = splitter.split();

            // Save each split document to the output directory
            for (int i = 0; i < splitDocuments.size(); i++) {
                String outputFilePath = outputDirectory + File.separator + "split_part_" + (i + 1) + ".pdf";
                splitDocuments.get(i).save(outputFilePath);
            }

            // Close the documents
            document.close();
            for (PDDocument splitDocument : splitDocuments) {
                splitDocument.close();
            }

            // Display a success message
            JOptionPane.showMessageDialog(this, "PDF Split Successfully!");
        } catch (Exception ex) {
            // Display an error message if an exception occurs
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

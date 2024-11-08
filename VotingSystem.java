import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VotingSystem extends JFrame implements ActionListener {

    // Variables to store vote counts
    private int g1 = 0; // Generation Z
    private int g2 = 0; // Millennials
    private int g3 = 0; // Generation Alpha

    // Form components
    private JTextField nameField, phoneField, idField;
    private JRadioButton genZButton, millennialsButton, genAlphaButton;
    private JButton submitButton, checkResultsButton;
    private ButtonGroup partyGroup;

    public VotingSystem() {
        // Frame title
        super("Online Voting System");

        // Create form labels and text fields
        JLabel nameLabel = new JLabel("Name:");
        JLabel phoneLabel = new JLabel("Phone Number:");
        JLabel idLabel = new JLabel("ID Number:");

        nameField = new JTextField(20);
        phoneField = new JTextField(20);
        idField = new JTextField(20);

        // Create radio buttons for the parties
        genZButton = new JRadioButton("Generation Z");
        millennialsButton = new JRadioButton("Millennials");
        genAlphaButton = new JRadioButton("Generation Alpha");

        // Group radio buttons so only one can be selected at a time
        partyGroup = new ButtonGroup();
        partyGroup.add(genZButton);
        partyGroup.add(millennialsButton);
        partyGroup.add(genAlphaButton);

        // Create buttons
        submitButton = new JButton("Submit Your Vote");
        checkResultsButton = new JButton("Check Results");

        // Set action listeners for the buttons
        submitButton.addActionListener(this);
        checkResultsButton.addActionListener(this);

        // Layout the components on the frame
        setLayout(new GridLayout(8, 2));

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(phoneLabel);
        add(phoneField);
        add(idLabel);
        add(idField);

        add(genZButton);
        add(millennialsButton);
        add(genAlphaButton);

        add(submitButton);
        add(checkResultsButton);

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Check if any party has been selected
            if (!genZButton.isSelected() && !millennialsButton.isSelected() && !genAlphaButton.isSelected()) {
                JOptionPane.showMessageDialog(this, "Select a Party", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cast the vote based on the selected party
            if (genZButton.isSelected()) {
                g1++;
            } else if (millennialsButton.isSelected()) {
                g2++;
            } else if (genAlphaButton.isSelected()) {
                g3++;
            }

            // Reset fields and radio buttons after vote submission
            nameField.setText("");
            phoneField.setText("");
            idField.setText("");
            partyGroup.clearSelection();

            JOptionPane.showMessageDialog(this, "Vote Submitted Successfully!");
        }

        if (e.getSource() == checkResultsButton) {
            // Display the results in a JOptionPane
            String results = "Results:\n" +
                    "Generation Z: " + g1 + " votes\n" +
                    "Millennials: " + g2 + " votes\n" +
                    "Generation Alpha: " + g3 + " votes\n";

            // Find the party with the most votes
            int maxVotes = Math.max(g1, Math.max(g2, g3));
            String winningParty = "";
            if (g1 == maxVotes) {
                winningParty = "Generation Z";
            } else if (g2 == maxVotes) {
                winningParty = "Millennials";
            } else {
                winningParty = "Generation Alpha";
            }

            results += "\nThe party with the maximum votes is: " + winningParty;

            JOptionPane.showMessageDialog(this, results, "Voting Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Start the application
        new VotingSystem();
    }
}

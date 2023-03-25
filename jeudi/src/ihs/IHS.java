package ihs;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IHS {

    private static final String[] OPTIONS = {"Résultats huitièmes de finale de la CAN 2022",
            "Résultats quarts de finale de la CAN 2022",
            "Résultats demi-finales de la CAN 2022",
            "Résultat petite finale de la CAN 2022",
            "Résultat finale de la CAN 2022"};

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static JTextArea textArea;


    	public static void main(String[] args) {
    	    // Création de la fenêtre principale
    	    JFrame frame = new JFrame("Résultats de la Coupe d'Afrique des Nations 2022");
    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    frame.setSize(WIDTH, HEIGHT);
    	    frame.setLocationRelativeTo(null);

    	    // Création des composants de l'interface
    	    JPanel leftPanel = new JPanel(new GridLayout(OPTIONS.length, 1));
    	    for (int i = 0; i < OPTIONS.length; i++) {
    	        JButton button = new JButton(OPTIONS[i]);
    	        final int optionIndex = i;
    	        button.setForeground(Color.BLUE);
    	        button.setBackground(Color.gray);

    	        button.addActionListener(new ActionListener() {
    	            public void actionPerformed(ActionEvent e) {
    	                DefaultTableModel model = createTableModel();
    	                JTable table = new JTable(model);
    	                table.getColumnModel().getColumn(0).setPreferredWidth(100);
    	                table.getColumnModel().getColumn(1).setPreferredWidth(50);
    	                table.getColumnModel().getColumn(2).setPreferredWidth(50);
    	                table.getColumnModel().getColumn(3).setPreferredWidth(100);

    	                JScrollPane scrollPane = new JScrollPane(table);

    	                JButton editButton = new JButton("Modifier");
    	                editButton.addActionListener(new ActionListener() {
    	                    public void actionPerformed(ActionEvent e) {
    	                        int selectedRow = table.getSelectedRow();
    	                        if (selectedRow != -1) {
    	                            JTextField equipe1Field = new JTextField((String) model.getValueAt(selectedRow, 0));
    	                            JTextField score1Field = new JTextField(model.getValueAt(selectedRow, 1).toString());
    	                            JTextField score2Field = new JTextField(model.getValueAt(selectedRow, 2).toString());
    	                            JTextField equipe2Field = new JTextField((String) model.getValueAt(selectedRow, 3));

    	                            JPanel panel = new JPanel(new GridLayout(0, 1));
    	                            panel.add(new JLabel("Équipe 1 :"));
    	                            panel.add(equipe1Field);
    	                            panel.add(new JLabel("Score 1 :"));
    	                            panel.add(score1Field);
    	                            panel.add(new JLabel("Score 2 :"));
    	                            panel.add(score2Field);
    	                            panel.add(new JLabel("Équipe 2 :"));
    	                            panel.add(equipe2Field);

    	                            int result = JOptionPane.showConfirmDialog(null, panel, "Modifier le résultat", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    	                            if (result == JOptionPane.OK_OPTION) {
    	                                model.setValueAt(equipe1Field.getText(), selectedRow, 0);
    	                                model.setValueAt(Integer.parseInt(score1Field.getText()), selectedRow, 1);
    	                                model.setValueAt(Integer.parseInt(score2Field.getText()), selectedRow, 2);
    	                                model.setValueAt(equipe2Field.getText(), selectedRow, 3);
    	                            }
    	                        } else {
    	                            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
    	                        }
    	                    }
    	                });
    	                JButton deleteButton = new JButton("Supprimer");
    	                deleteButton.addActionListener(new ActionListener() {
    	                    public void actionPerformed(ActionEvent e) {
    	                        int selectedRow = table.getSelectedRow();
    	                        if (selectedRow != -1) {
    	                            model.removeRow(selectedRow);
    	                        } else {
    	                            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
    	                        }
    	                    }
    	                });

    	                JPanel buttonsPanel = new JPanel(new FlowLayout());
    	                buttonsPanel.add(editButton);
    	                buttonsPanel.add(deleteButton);

    	                JPanel mainPanel = new JPanel(new BorderLayout());
    	                mainPanel.add(scrollPane, BorderLayout.CENTER);
    	                mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

    	                JOptionPane.showMessageDialog(null, mainPanel, OPTIONS[optionIndex], JOptionPane.PLAIN_MESSAGE);
    	            }
    	            private DefaultTableModel createTableModel() {
    	                String[] columnNames = {"Équipe 1", "Score 1", "Score 2", "Équipe 2"};
    	                Object[][] data = new Object[][]{
    	                        {"Équipe 1A", 1, 2, "Équipe 2A"},
    	                        {"Équipe 1B", 3, 1, "Équipe 2B"},
    	                        {"Équipe 1C", 0, 0, "Équipe 2C"}
    	                };
    	                return new DefaultTableModel(data, columnNames);
    	            }
    	        });
    	        leftPanel.add(button);
    	    }

    	    // Ajout des composants à la fenêtre principale
    	    frame.getContentPane().setLayout(new BorderLayout());
    	    frame.getContentPane().add(leftPanel, BorderLayout.WEST);

    	    // Affichage de la fenêtre principale
    	    frame.setVisible(true);
    	}}
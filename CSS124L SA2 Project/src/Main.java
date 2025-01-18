/*
Villar, Alexandra Elyze
SA2: Project - CSS124L
FOPI01
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    private int shape; // Variable Shape
    private String saladChoice;
    private String drinkChoice;
    private String specialRequest;
    private boolean waterSelected = false;

    private JTextField specialRequestField;
    private JComboBox<String> menuSelectionComboBox;
    private ButtonGroup drinkGroup;

    public Main() {
        JFrame frame = new JFrame("Combined Program");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel shapePanel = new JPanel(new BorderLayout());
        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                int shapeWidth = 150; // Width of the shape
                int shapeHeight = 150; // Height of the shape
                int shapeX = (panelWidth - shapeWidth) / 2; // X position to center the shape
                int shapeY = (panelHeight - shapeHeight) / 2; // Y position to center the shape
                switch (shape) {
                    case 1: // Line
                        g.drawLine(shapeX, shapeY, shapeX + shapeWidth, shapeY + shapeHeight);
                        break;
                    case 2: // Circle
                        g.drawOval(shapeX, shapeY, shapeWidth, shapeHeight);
                        break;
                    case 3: // Rectangle
                        g.drawRect(shapeX, shapeY, shapeWidth, shapeHeight);
                        break;
                }
            }
        };

        JButton lineButton = new JButton("Line");
        JButton circleButton = new JButton("Circle");
        JButton rectangleButton = new JButton("Rectangle");

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int panelWidth = drawingPanel.getWidth();
                int panelHeight = drawingPanel.getHeight();
                int shapeWidth = 150;
                int shapeHeight = 150;
                int shapeX = (panelWidth - shapeWidth) / 2;
                int shapeY = (panelHeight - shapeHeight) / 2;

                if (mouseX >= shapeX && mouseX <= shapeX + shapeWidth &&
                        mouseY >= shapeY && mouseY <= shapeY + shapeHeight) {
                    if (mouseY - shapeY <= shapeHeight / 2) {
                        shape = 1; // Line
                    } else {
                        if (mouseX - shapeX <= shapeWidth / 2) {
                            shape = 2; // Circle
                        } else {
                            shape = 3; // Rectangle
                        }
                    }
                }

                drawingPanel.repaint();
            }
        });

        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = 1; // Line
                drawingPanel.repaint();
            }
        });

        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = 2; // Circle
                drawingPanel.repaint();
            }
        });

        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = 3; // Rectangle
                drawingPanel.repaint();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(lineButton);
        buttonPanel.add(circleButton);
        buttonPanel.add(rectangleButton);

        shapePanel.add(buttonPanel, BorderLayout.NORTH);
        shapePanel.add(drawingPanel, BorderLayout.CENTER);

        tabbedPane.addTab("Shapes", shapePanel);

        JPanel dinerPanel = new JPanel(new BorderLayout());

        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel saladSelection = new JPanel();
        JLabel saladTitle = new JLabel("Menu Selection");
        saladSelection.add(saladTitle);
        menuSelectionComboBox = new JComboBox<>();
        menuSelectionComboBox.addItem("Chicken Salad");
        menuSelectionComboBox.addItem("Tuna Salad");
        menuSelectionComboBox.addItem("Vegetable Salad");
        saladSelection.add(menuSelectionComboBox);
        mainContent.add(saladSelection);

        JPanel drinkSelection = new JPanel();
        JLabel drinkLabel = new JLabel("Drink Selection:");
        drinkSelection.add(drinkLabel);
        drinkGroup = new ButtonGroup();
        JRadioButton sodaRadio = new JRadioButton("Soda");
        JRadioButton juiceRadio = new JRadioButton("Juice");
        JRadioButton milkTeaRadio = new JRadioButton("Milk Tea");
        drinkGroup.add(sodaRadio);
        drinkGroup.add(juiceRadio);
        drinkGroup.add(milkTeaRadio);
        drinkSelection.add(sodaRadio);
        drinkSelection.add(juiceRadio);
        drinkSelection.add(milkTeaRadio);
        mainContent.add(drinkSelection);

        JPanel selectionBox = new JPanel();
        selectionBox.setLayout(new BoxLayout(selectionBox, BoxLayout.X_AXIS));
        selectionBox.add(saladSelection);
        selectionBox.add(drinkSelection);
        mainContent.add(selectionBox);

        JPanel specialRequestBox = new JPanel();
        JLabel specialRequestTitle = new JLabel("Special Request");
        specialRequestField = new JTextField(20);
        specialRequestField.setToolTipText("Special Request");
        specialRequestBox.add(specialRequestTitle);
        specialRequestBox.add(specialRequestField);
        mainContent.add(specialRequestBox);

        JCheckBox waterCheckBox = new JCheckBox("Water");
        waterCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                waterSelected = waterCheckBox.isSelected();
            }
        });
        mainContent.add(waterCheckBox);

        dinerPanel.add(mainContent, BorderLayout.CENTER);

        tabbedPane.addTab("Diner", dinerPanel);

        JPanel timerPanel = new JPanel();
        JButton startButton = new JButton("Start Timer");
        JButton stopButton = new JButton("Stop Timer");
        JLabel timeLabel = new JLabel("Time: 0");

        timerPanel.add(startButton);
        timerPanel.add(stopButton);
        timerPanel.add(timeLabel);

        Timer timer = new Timer(1000, new ActionListener() {
            int timeElapsed = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                timeElapsed++;
                timeLabel.setText("Time: " + timeElapsed);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });

        tabbedPane.addTab("Timer", timerPanel);

        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}



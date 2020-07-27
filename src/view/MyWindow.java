package view;

import controller.KeyEventListener;
import controller.Main;
import controller.MouseEventListener;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {

    public MyCanvas canvas; // public member for fast access
    public JButton startButton;

    public void init() {
        setSize(700, 500);
        setLocation(300, 200);
        setTitle("Game Engine");

        canvas = new MyCanvas();

        MouseEventListener listener = new MouseEventListener();
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

        KeyEventListener keyEventListener = new KeyEventListener();
        canvas.addKeyListener(keyEventListener);
        canvas.setFocusable(true); // this graphical component will receive keyboard input
        // anything else in window besides canvas need setFocusable(false)

        var cp = getContentPane();
        cp.add(BorderLayout.CENTER, canvas);

        startButton = new JButton("Raft");
        startButton.setFocusable(false); // -> -> -> not including this line is why base wouldn't move
        JPanel buttonPanel = new JPanel(); // wrap it up(?) can add more than 1 later
        buttonPanel.add(startButton);
        cp.add(BorderLayout.SOUTH, buttonPanel);

        // **anonymous class, lambda**


        startButton.addActionListener(e -> {
                if (!Main.running) { //start button
                    startButton.setText("Quit");
                    Main.running = true;
                } else { //quit button
                    System.exit(0);
                }
        });
        // this is lambda function: function without name of the function
        // if have more than one parameter (e here) you would need to put in parentheses and use {} for body, & ;
    }
}

package com.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table extends JFrame{
    private JPanel PhilosophersTable;

    // Labels for Philosophers' Text
    static JLabel[] philosopherStatus = new JLabel[5];

    // Labels for Philosophers' Images
    static JLabel[] philosopherImages = new JLabel[5];

    // Labels for Forks' Images
    static JLabel[] forksImages = new JLabel[5];

    // Images
    static ImageIcon waiting = new ImageIcon(new ImageIcon("waiting.png").getImage().getScaledInstance(100, 100 , Image.SCALE_DEFAULT));
    static ImageIcon eating = new ImageIcon(new ImageIcon("eating.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
    static ImageIcon thinking = new ImageIcon(new ImageIcon("thinking.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
    static ImageIcon table = new ImageIcon(new ImageIcon("table.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
    static ImageIcon fork = new ImageIcon(new ImageIcon("fork.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

    // Labels for explanations' Text
    private JLabel waitingT = new JLabel();
    private JLabel eatingT = new JLabel();
    private JLabel thinkingT = new JLabel();

    // Labels for explanations' Images
    private JLabel waitingI = new JLabel();
    private JLabel eatingI = new JLabel();
    private JLabel thinkingI = new JLabel();

    // Buttons
    private JButton start = new JButton();
    private JButton stop = new JButton();
    private JButton deadlock = new JButton();

    private JLabel tableI = new JLabel();


    public Table(){
        createAll();
    }


    public void createAll(){
        // This uses the form designer form
        add(PhilosophersTable);

        setLayout(null);
        setTitle("PHILOSOPHERS TABLE");
        setSize(800, 850);

        tableI.setSize(200,200);
        tableI.setLocation(325, 150);
        tableI.setIcon(table);
        add(tableI);

        // Initialize Philosophers' Text Labels in the Screen
        initializePhilosophers();

        // Set the Philosophers Images and add them to the window
        setPhilosophersImages();

        // Set the Philosophers Text Labels and add them to the window
        setPhilosophersText();

        // Set Explanation Texts and add them to the window
        setExplanationTexts();

        // Set Explanation Images and add them to the window
        setExplanationImages();

        // Set Start Button
        setStartButton();

        // Set Stop Button
        stop.setEnabled(false);
        setStopButton();

        deadlock.setEnabled(false);
        setDeadlockButton();

        setForksImages();
    }

    private void setStartButton() {
        // Start Button
        start.setText("Start Simulation");
        start.setSize(300,50);
        start.setLocation(300,540);
        add(start);

        // Event Listener
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                start.setEnabled(false);
                Simulation.run();
                stop.setEnabled(true);
                deadlock.setEnabled(true);
            }
        });
    }


    private void setStopButton(){
        // Stop Button
        stop.setText("Stop Simulation");
        stop.setSize(300, 50);
        stop.setLocation(300, 650);
        add(stop);

        // Event Listener
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                stop.setEnabled(false);
                deadlock.setEnabled(false);
                Simulation.stop();
                Simulation.kill();
                start.setEnabled(true);
            }
        });
    }


    private void setDeadlockButton() {
        // Deadlock Button
        deadlock.setText("Force Deadlock");
        deadlock.setSize(300,50);
        deadlock.setLocation(300,760);
        add(deadlock);

        // Event Listener
        deadlock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                deadlock.setEnabled(false);
                stop.setEnabled(false);
                Simulation.deadlock();
                start.setEnabled(true);
            }
        });
    }

    // Set the Forks Labels and add them to the window
    private void setForksImages(){

        // Set the Dimensions and Image of each Philosophers' Image
        for(int j=0; j<5; j++){
            forksImages[j].setSize(50,50);
        }

        forksImages[0].setLocation(450,45);
        forksImages[1].setLocation(200,205);
        forksImages[2].setLocation(300,370);
        forksImages[3].setLocation(600,370);
        forksImages[4].setLocation(700,205);

        // Add the Elements to the Table
        for(int k=0; k<5; k++){
            add(forksImages[k]);
        }
    }


    public static void visualizeFork(int index, boolean status){
        if (status){
            forksImages[index].setIcon(fork);
        }
        else {
            forksImages[index].setIcon(null);
        }

    }


    // Set the Philosophers Images and add them to the window
    private void setPhilosophersImages(){

        // Set the Dimensions and Image of each Philosophers' Image
        for(int j=0; j<5; j++){
            philosopherImages[j].setSize(100,100);
            philosopherImages[j].setIcon(waiting);
        }

        philosopherImages[0].setLocation(350,25);
        philosopherImages[1].setLocation(100,185);
        philosopherImages[2].setLocation(200,350);
        philosopherImages[3].setLocation(500,350);
        philosopherImages[4].setLocation(600,185);

        // Add the Elements to the Table
        for(int k=0; k<5; k++){
            add(philosopherImages[k]);
        }
    }

    // Set the Philosophers Text Labels and add them to the window
    private void setPhilosophersText(){

        // Set the Text and Dimensions of each Philosophers' Text
        for(int i=0; i<5; i++){
            philosopherStatus[i].setText("PHILOSOPHER " + i);
            philosopherStatus[i].setSize(100,15);
        }

        philosopherStatus[0].setLocation(355,5);
        philosopherStatus[1].setLocation(105,165);
        philosopherStatus[2].setLocation(205,330);
        philosopherStatus[3].setLocation(505,330);
        philosopherStatus[4].setLocation(605,165);

        // Add the Elements to the Table
        for(int k=0; k<5; k++){
            add(philosopherStatus[k]);
        }
    }


    // Initialize Philosophers Text Labels
    private void initializePhilosophers(){
        for(int i=0; i<5; i++) {
            philosopherStatus[i] = new JLabel();
            philosopherImages[i] = new JLabel();
            forksImages[i] = new JLabel();
        }
    }

    // Set Explanation Texts and add them to the window
    private void setExplanationTexts(){

        // Thinking Explanation
        thinkingT.setText("THINKING");
        thinkingT.setSize(100,25);
        thinkingT.setLocation(120, 775);
        add(thinkingT);

        // Waiting Explanation
        waitingT.setText("WAITING");
        waitingT.setSize(100, 25);
        waitingT.setLocation(120, 555);
        add(waitingT);

        // Eating Explanation
        eatingT.setText("EATING");
        eatingT.setSize(100, 25);
        eatingT.setLocation(120, 665);
        add(eatingT);
    }


    // Set Explanation Images and add them to the window
    private void setExplanationImages(){

        // Thinking Explanation
        thinkingI.setSize(100,100);
        thinkingI.setLocation(10, 730);
        thinkingI.setIcon(thinking);
        add(thinkingI);

        // Waiting Explanation
        waitingI.setSize(100, 100);
        waitingI.setLocation(10, 510);
        waitingI.setIcon(waiting);
        add(waitingI);

        // Eating Explanation
        eatingI.setSize(100, 100);
        eatingI.setLocation(10, 620);
        eatingI.setIcon(eating);
        add(eatingI);
    }


    // Change the State of the Philosophers
    public static void changePhilosopherState(int philosopherNumber, String newState){
        switch (newState){
            case "Waiting":
                philosopherStatus[philosopherNumber].setText("WAITING");
                philosopherImages[philosopherNumber].setIcon(waiting);
                break;
            case "Eating":
                philosopherStatus[philosopherNumber].setText("EATING");
                philosopherImages[philosopherNumber].setIcon(eating);
                break;
            case "Thinking":
                philosopherStatus[philosopherNumber].setText("THINKING");
                philosopherImages[philosopherNumber].setIcon(thinking);
                break;
            case "Stop":
                philosopherStatus[philosopherNumber].setText("STOP");
                philosopherImages[philosopherNumber].setIcon(eating);
                break;
            default:
                break;
        }

    }

    public static void blockRoom(int philosopherNumber, boolean status){
        if (status){
            philosopherStatus[philosopherNumber].setText("ROOM WAITING");
        }
        else {
            philosopherStatus[philosopherNumber].setText("WAITING");
        }
    }

}

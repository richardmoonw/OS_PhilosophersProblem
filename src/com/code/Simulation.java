package com.code;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Simulation {
    // Create the Philosophers
    static Philosophers[] philosophers = new Philosophers[5];

    // Create semaphores for each Fork
    static Semaphore[] forks = new Semaphore[5];

    // Create the Room counter and its Semaphore
    static Semaphore room = null;


    public static void run(){
        // Initialize the semaphores for each Fork
        for(int i=0; i<5; i++){
            forks[i] = new Semaphore(1, true);
        }

        // Initialize the Room semaphore
        room = new Semaphore(4, true);

        // Initialize and start the Philosophers
        for(int i=0; i<5; i++){
            philosophers[i] = new Philosophers(String.valueOf(i));
            philosophers[i].t.start();
        }
    }

    public static void stop(){
        for (int i=0; i<5; i++){
            philosophers[i].stop();
        }
    }

    public static void kill(){
        for (int i=0; i<5; i++){
            try {
                philosophers[i].t.join();
            } catch(Exception e){
                System.out.println("There was an error: " + e);
            }
        }
    }

    public static void deadlock(){
        philosophers[1].forceDeadlock();
    }
}

class Philosophers implements Runnable {

    private boolean dinning = true;

    // Name of the thread
    String name;

    // Thread itself
    Thread t;

    // Variable used to generate random numbers
    Random rand = new Random();

    // Constructor
    Philosophers (String threadname){
        name = threadname;
        t = new Thread(this, name);
    }

    @Override
    public void run() {
        while(dinning){

            // Change the state to "thinking"
            Table.changePhilosopherState(Integer.parseInt(name), "Thinking");

            try{
                Thread.sleep(rand.nextInt(7000)+3000);
            }
            catch(Exception e){
                System.out.println("Something went wrong");
            }


            // Change the state to "waiting"
            Table.changePhilosopherState(Integer.parseInt(name), "Waiting");
            if(Simulation.room.availablePermits() <= 0){
                Table.blockRoom(Integer.parseInt(name), true);
            }
            semWait(Simulation.room);
            Table.blockRoom(Integer.parseInt(name), false);
            semWait(Simulation.forks[Integer.parseInt(name)]);
            semWait(Simulation.forks[(Integer.parseInt(name)+1)%5]);
            Table.visualizeFork(Integer.parseInt(name), true);
            Table.visualizeFork((Integer.parseInt(name)+1)%5, true);

            // Change the state to "eating"
            Table.changePhilosopherState(Integer.parseInt(name), "Eating");
            try{
                Thread.sleep(rand.nextInt(3000)+2000);
            }
            catch(Exception e) {
                System.out.println("Something went wrong");
            }
            semSignal(Simulation.forks[(Integer.parseInt(name)+1)%5]);
            Table.visualizeFork((Integer.parseInt(name)+1)%5, false);
            semSignal(Simulation.forks[Integer.parseInt(name)]);
            Table.visualizeFork(Integer.parseInt(name), false);
            semSignal(Simulation.room);
        }

        if(!dinning){
            Table.changePhilosopherState(Integer.parseInt(name), "Stop");
        }
    }

    void forceDeadlock(){
        Simulation.forks[Integer.parseInt(name)].acquireUninterruptibly(1);
    }

    void stop(){
        dinning = false;
    }

    static void semWait(Semaphore s){
        s.acquireUninterruptibly();
    }

    static void semSignal(Semaphore s){
        s.release();
    }
}
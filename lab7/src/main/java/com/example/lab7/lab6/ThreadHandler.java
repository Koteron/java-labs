package com.example.lab7.lab6;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadHandler
{
    private static ProgramState state;
    static Thread abstractThread;

    final static Object object = new Object();

    static StringBuffer output = new StringBuffer();

    public static class Supervisor implements Runnable 
    {
        @Override
        public void run() 
        {
            output.append("Supervisor: started\n");
            output.append("Supervisor: " + state + "\n");
            abstractThread.start();
            while (!abstractThread.isInterrupted()) 
            {
                synchronized (object) 
                {
                    try 
                    {
                        object.wait();
                    }
                    catch (InterruptedException e) 
                    {
                        System.out.println(e.getMessage() + "\n");
                        break;
                    }
                    switch (state) 
                    {
                        case FATAL_ERROR -> stopProgram();
                        case STOPPING -> startProgram();
                        default -> output.append("Supervisor: RUNNING\n");
                    }
                }
            }
        }

        private void stopProgram() 
        {
            output.append("Supervisor: FATAL_ERROR\n");
            abstractThread.interrupt();
        }

        private void startProgram() 
        {
            output.append("Supervisor: STOPPING\n");
            state = ProgramState.RUNNING;
        }
    }

    public static class AbstractProgram implements Runnable 
    {
        @Override
        public void run() 
        {
            output.append("AbstractProgram: started\n");
            Thread demon = new Thread(() -> 
            {
                output.append("Demon: started\n");
                while (true) 
                {
                    try 
                    {
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e) 
                    {
                        output.append(e.getMessage() + "\n");
                        break;
                    }
                    synchronized (object) 
                    {
                        state = ProgramState.values()[ThreadLocalRandom.current().nextInt(1, ProgramState.values().length)];
                        output.append("Demon: Change state of abstract program on: " + state.toString() + "\n");
                        object.notify();
                    }

                }
            });
            demon.setDaemon(true);
            demon.start();
        }
    }

    public static String start()
    {
        abstractThread = new Thread(new AbstractProgram());
        output.setLength(0);
        state = ProgramState.UNKNOWN;
        Thread supervisorThread = new Thread(new Supervisor());
        supervisorThread.start();
        try
        {
            supervisorThread.join();
        }
        catch (InterruptedException e)
        {
            output.append(e.getMessage());
        }
        return output.toString();
    }
}

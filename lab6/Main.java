import java.util.concurrent.ThreadLocalRandom;

public class Main 
{
    private static ProgramState state = ProgramState.UNKNOWN;
    static Thread  abstractThread = new Thread(new AbstractProgram());

    final static Object object = new Object();

    public static class Supervisor implements Runnable 
    {
        @Override
        public void run() 
        {
            System.out.println("Supervisor: started");
            System.out.println("Supervisor: " + state);
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
                        System.out.println(e.getMessage());
                        break;
                    }
                    switch (state) 
                    {
                        case FATAL_ERROR -> stopProgram();
                        case STOPPING -> startProgram();
                        default -> System.out.println("Supervisor: RUNNING");
                    }
                }
            }
        }

        private void stopProgram() 
        {
            System.out.println("Supervisor: FATAL_ERROR");
            abstractThread.interrupt();
        }

        private void startProgram() 
        {
            System.out.println("Supervisor: STOPPING");
            state = ProgramState.RUNNING;
        }
    }

    public static class AbstractProgram implements Runnable 
    {
        @Override
        public void run() 
        {
            System.out.println("AbstractProgram: started");
            Thread demon = new Thread(() -> 
            {
                System.out.println("Demon: started");
                while (true) 
                {
                    try 
                    {
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e) 
                    {
                        System.out.println(e.getMessage());
                        break;
                    }
                    synchronized (object) 
                    {
                        state = ProgramState.values()[ThreadLocalRandom.current().nextInt(1, ProgramState.values().length)];
                        System.out.println("Demon: Change state of abstract program on: " + state.toString());
                        object.notify();
                    }

                }
            });
            demon.setDaemon(true);
            demon.start();
        }
    }

    public static void main(String[] args) 
    {
        Thread supervisorThread = new Thread(new Supervisor());
        supervisorThread.start();
    }
}

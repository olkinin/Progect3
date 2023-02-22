package HW3;

public class NewTread {
    private final Object mon = new Object();
    private String word = "ping";

    public static void main(String[] args) {
        NewTread newTread = new NewTread();
        Thread thread1 = new Thread(() -> {
                newTread.printPing();
        });
        Thread thread2 = new Thread(() -> {
            newTread.printPong();
        });
        thread1.start();
        thread2.start();
    }

    public void printPing() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (word != "ping") {
                        mon.wait();
                    }
                    System.out.println("ping");
                    word = "pong";
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void printPong() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (word != "pong") {
                        mon.wait();
                    }
                    System.out.println("pong");
                    word="ping";
                   mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}




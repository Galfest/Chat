package HW4;

public class Main {
    static char c = 'A';
    static Object mon = new Object();

    static class WaitClass implements Runnable {
        private char currentLetter;
        private char nextLetter;

        public WaitClass(char currentLetter, char nextLetter) {
            this.currentLetter = currentLetter;
            this.nextLetter = nextLetter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (mon) {
                    try {
                        while (c != currentLetter)
                            mon.wait();
                        System.out.print(currentLetter);
                        c = nextLetter;
                        mon.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Task1");
        new Thread(new WaitClass('A', 'B')).start();
        new Thread(new WaitClass('B', 'C')).start();
        new Thread(new WaitClass('C', 'A')).start();
    }
}

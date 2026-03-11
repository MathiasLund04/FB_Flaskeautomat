import Model.Bottle;

public class Producer implements Runnable {

    private final Buffer buffer;
    public int counter= 0;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        String[] types = {"Beer", "Water"};

        while (true){
                try {
                String type = types[(int)(Math.random() * 2)];

                Bottle bottle = new Bottle(type,counter);
                    System.out.println("Producer produced bottle: " + bottle);
                counter++;
                buffer.produce(bottle);
                Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        }


    }

}

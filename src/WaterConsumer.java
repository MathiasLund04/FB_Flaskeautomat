import Model.Bottle;

public class WaterConsumer implements Runnable {
    private final Buffer waterBuffer;

    public WaterConsumer(Buffer waterBuffer) {
        this.waterBuffer = waterBuffer;
    }

    @Override
    public void run() {
        while (true) {

            try {
                Bottle bottle = waterBuffer.consume();

                if (bottle.getType().equals("Water")) {
                    System.out.println(bottle + " consumed in water crate");
                }
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

import Model.Bottle;

public class BeerConsumer implements Runnable {
    private final Buffer beerBuffer;

    public BeerConsumer(Buffer beerBuffer) {
        this.beerBuffer = beerBuffer;
    }

    @Override
    public void run() {
        while (true) {

            try {
                Bottle bottle = beerBuffer.consume(2);

                if (bottle.getType().equals("Beer")) {
                    System.out.println(bottle + " consumed in beer crate");
                }

                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

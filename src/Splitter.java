import Model.Bottle;

public class Splitter implements Runnable {

    private final Buffer drinkIn;
    private final Buffer beerOut;
    private final Buffer waterOut;

    public Splitter(Buffer drinkIn, Buffer beerOut, Buffer waterout) {
        this.drinkIn = drinkIn;
        this.beerOut = beerOut;
        this.waterOut = waterout;
    }

    @Override
    public void run() {
        while (true) {
                try {
                    Bottle bottle = drinkIn.consume();

                    if (bottle.getType().equals("Beer")) {
                        System.out.println("Splitter sent " + bottle + " to beer crate");
                        beerOut.produce(bottle);
                    } else if (bottle.getType().equals("Water")) {
                        System.out.println("Splitter sent " + bottle + " to water crate");
                        waterOut.produce(bottle);
                    } else {
                        System.out.println("Unknown Bottle type sent to trash");
                    }
                    Thread.sleep(1000);


                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

        }


    }
}

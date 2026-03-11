
public class Main {
    public static void main(String[] args) {
        Buffer mainBuffer = new Buffer(20);
        Buffer beerBuffer = new Buffer(20);
        Buffer waterBuffer = new Buffer(20);

        Thread producer = new Thread(new Producer(mainBuffer));

       Thread splitter = new Thread (new Splitter(mainBuffer, beerBuffer, waterBuffer));

       Thread beerConsumer = new Thread(new BeerConsumer(beerBuffer));
       Thread waterConsumer = new Thread(new WaterConsumer(waterBuffer));
       producer.start();
       splitter.start();
       beerConsumer.start();
       waterConsumer.start();
    }
}
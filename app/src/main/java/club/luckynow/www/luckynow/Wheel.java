package club.luckynow.www.luckynow;

/**
 * Created by vnk2 on 27/8/2017.
 */

public class Wheel extends Thread {

    interface WheelListener {
        void newImage(int img);
    }

    static String[] nombres = {"banana", "berenjena", "cereza", "limon", "manzana", "fresa"};

    private static int[] imgs = {R.drawable.slot_banana, R.drawable.slot_berenjena, R.drawable.slot_cereza, R.drawable.slot_limon,
            R.drawable.slot_manzana, R.drawable.slot_fresa};
    public int currentIndex;
    private WheelListener wheelListener;
    private long frameDuration;
    private long startIn;
    private boolean isStarted;

    public Wheel(WheelListener wheelListener, long frameDuration, long startIn) {
        this.wheelListener = wheelListener;
        this.frameDuration = frameDuration;
        this.startIn = startIn;
        currentIndex = 0;
        isStarted = true;
    }

    public void nextImg() {
        currentIndex++;

        if (currentIndex == imgs.length) {
            currentIndex = 0;
        }
    }
    public void siguiente() {
        currentIndex+=1;
        if (currentIndex == imgs.length) {
            currentIndex = 0;
        }

    }

    @Override
    public void run() {
        try {
            Thread.sleep(startIn);
        } catch (InterruptedException e) {
        }

        while(isStarted) {
            try {
                Thread.sleep(frameDuration);
            } catch (InterruptedException e) {
            }
            nextImg();
            if (wheelListener != null) {
                wheelListener.newImage(imgs[currentIndex]);
            }
        }
    }

    public void stopWheel() {
        isStarted = false;
    }


}
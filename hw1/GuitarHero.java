import synthesizer.GuitarString;

import java.util.HashMap;
import java.util.Map;

public class GuitarHero {

    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */

        synthesizer.GuitarString[] guitarStrings = new synthesizer.GuitarString[keyboard.length()];
        for(double i = 0; i < keyboard.length(); i++){
            guitarStrings[(int)i] = new synthesizer.GuitarString(440.0 * Math.pow(2, (i - 24.0) / 12.0));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if(index != -1){
                    guitarStrings[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for(GuitarString gs : guitarStrings){
                sample += gs.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(GuitarString gs : guitarStrings){
                gs.tic();
            }
        }
    }
}

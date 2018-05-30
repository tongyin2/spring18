public class NBody{
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(radius, -radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");

        for(Planet p : planets){
          p.draw();
        }
        StdAudio.play("audio/2001.mid");
        StdDraw.enableDoubleBuffering();
        for(double time = 0; time <= T; time = time+dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i = 0; i < planets.length; i++){
              double Fx = planets[i].calcNetForceExertedByX(planets);
              double Fy = planets[i].calcNetForceExertedByY(planets);
              xForces[i] = Fx;
              yForces[i] = Fy;
            }

            for(int i = 0; i < planets.length; i++){
              planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Planet p : planets){
              p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

    }

    public static double readRadius(String filepath){
        In in = new In(filepath);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filepath){
      In in = new In(filepath);
      int N = in.readInt();
      Planet[] planets = new Planet[N];
      double radius = in.readDouble();

      for(int i = 0; i < N; i++){
          double xp = in.readDouble();
          double yp = in.readDouble();
          double xv = in.readDouble();
          double yv = in.readDouble();
          double m = in.readDouble();
          String imgFileName = in.readString();
          Planet planet = new Planet(xp, yp, xv, yv, m, imgFileName);
          planets[i] = planet;
      }
      return planets;
    }
}

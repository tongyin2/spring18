public class Planet{
  public double xxPos;// current x position
  public double yyPos;// current y position
  public double xxVel;// current velocity in x direction
  public double yyVel;// current velocity in y direction
  public double mass;// its mass
  public String imgFileName;// the name of the file to depicts Planet
  public static final double G = 6.67e-11;

  public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName){
      this.xxPos = xxPos;
      this.yyPos = yyPos;
      this.xxVel = xxVel;
      this.yyVel = yyVel;
      this.mass = mass;
      this.imgFileName = imgFileName;
  }

  public Planet(Planet a){
      xxPos = a.xxPos;
      yyPos = a.yyPos;
      xxVel = a.xxVel;
      yyVel = a.yyVel;
      mass = a.mass;
      imgFileName = a.imgFileName;
  }

  public double calcDistance(Planet b){
    double yDif = b.yyPos - yyPos;
    double xDif = b.xxPos - xxPos;
    return Math.sqrt(yDif*yDif+xDif*xDif);
  }

  public double calcForceExertedBy(Planet b){
    double d = this.calcDistance(b);
    return G*this.mass*b.mass/(d*d);
  }

  public double calcForceExertedByX(Planet b){
    double F = calcForceExertedBy(b);
    double d = calcDistance(b);
    return F*(b.xxPos - xxPos)/d;
  }

  public double calcForceExertedByY(Planet b){
    double F = calcForceExertedBy(b);
    double d = calcDistance(b);
    return F*(b.yyPos - yyPos)/d;
  }

  public double calcNetForceExertedByX(Planet[] planets){
    double re = 0;
    for(Planet p : planets){
      if(!this.equals(p)){
          re += calcForceExertedByX(p);
      }
    }
    return re;
  }

  public double calcNetForceExertedByY(Planet[] planets){
    double re = 0;
    for(Planet p : planets){
      if(!this.equals(p)){
        re += calcForceExertedByY(p);
      }
    }
    return re;
  }

  public void update(double time, double Fx, double Fy){
    double ax = Fx / mass;
    double ay = Fy / mass;
    xxVel = xxVel + time * ax;
    yyVel = yyVel + time * ay;
    xxPos = xxPos + time * xxVel;
    yyPos = yyPos + time * yyVel;
  }

  public void draw(){
    StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
  }
}

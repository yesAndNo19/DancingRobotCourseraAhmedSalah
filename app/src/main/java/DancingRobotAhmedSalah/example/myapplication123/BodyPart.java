package DancingRobotAhmedSalah.example.myapplication123;

public class BodyPart {

    // this class makes it easy to make a cuboid by just knowing the centre ,width , length and depth//
    double x;
    double y;
    double z1;
    double z2;
    double centreX;
    double centreY;
    int numSides;
    Coordinate[] coordinates;
    public BodyPart(double x, double y, double z1, double z2, int numSides) {
        this.x = x;
        this.y = y;
        this.z1 = z1;
        this.z2 = z2;
        this.numSides = numSides;
    }

    public BodyPart(double x, double y, double z1, double z2, double centreX, double centreY, int numSides) {
        this.x = x;
        this.y = y;
        this.z1 = z1;
        this.z2 = z2;
        this.centreX = centreX;
        this.centreY = centreY;
        this.numSides=numSides;
    }

    public void setCoordinates(Coordinate[] coordinates) {
        this.coordinates = coordinates;
    }

    public void setCentreX(double centreX) {
        this.centreX = centreX;
    }

    public void setCentreY(double centreY) {
        this.centreY = centreY;
    }


    public   Coordinate[] getTheCubeCoordinates(){
        /*
        front face of the cuboid

      index 3                           index 1
        *********************************
        *                               *
        *                               *
        *                               *
        *                               *
        *                               *
        *                               *
        *********************************
       index 2                           index 0

       the back face of the cuboid

       index 7                           index 5
        *********************************
        *                               *
        *                               *
        *                               *
        *                               *
        *                               *
        *                               *
        *********************************
       index 6                           index 4

         */

        double xDiv2=x/2;
        double yDiv2=y/2;
        coordinates=  new Coordinate[numSides*2];
        coordinates[0]=new Coordinate(centreX+xDiv2,centreY+yDiv2,z1,1);
        coordinates[1]=new Coordinate(centreX+xDiv2,centreY-yDiv2,z1,1);
        coordinates[2]=new Coordinate(centreX-xDiv2,centreY+yDiv2,z1,1);
        coordinates[3]=new Coordinate(centreX-xDiv2,centreY-yDiv2,z1,1);
        coordinates[4]=new Coordinate(centreX+xDiv2,centreY+yDiv2,z2,1);
        coordinates[5]=new Coordinate(centreX+xDiv2,centreY-yDiv2,z2,1);
        coordinates[6]=new Coordinate(centreX-xDiv2,centreY+yDiv2,z2,1);
        coordinates[7]=new Coordinate(centreX-xDiv2,centreY-yDiv2,z2,1);
        return coordinates;
    }
}

package DancingRobotAhmedSalah.example.myapplication123;

import android.graphics.Canvas;
import android.graphics.Paint;

public final class staticMethods {
    // methods  not made by me are :  GetIdentityMatrix - Transformation- drawLinePairs -drawCube//
    /*
    transformations here depend on changing the matrix according to the transformation equation
     */

    public static double []GetIdentityMatrix()
    {
        double []matrix=new double[16];
        matrix[0]=1;matrix[1]=0;matrix[2]=0;matrix[3]=0;
        matrix[4]=0;matrix[5]=1;matrix[6]=0;matrix[7]=0;
        matrix[8]=0;matrix[9]=0;matrix[10]=1;matrix[11]=0;
        matrix[12]=0;matrix[13]=0;matrix[14]=0;matrix[15]=1;
        return matrix;
    }
    public static Coordinate Transformation(Coordinate vertex,double []matrix)
    {


        Coordinate result=new Coordinate();
        result.x=matrix[0]*vertex.x+matrix[1]*vertex.y+matrix[2]*vertex.z+matrix[3];
        result.y=matrix[4]*vertex.x+matrix[5]*vertex.y+matrix[6]*vertex.z+matrix[7];
        result.z=matrix[8]*vertex.x+matrix[9]*vertex.y+matrix[10]*vertex.z+matrix[11];
        result.w=matrix[12]*vertex.x+matrix[13]*vertex.y+matrix[14]*vertex.z+matrix[15];
        return result;
    }
    public static Coordinate[]Transformation(Coordinate []vertices,double []matrix,int n)
    {
        Coordinate []result=new Coordinate[vertices.length];
        for (int i=0;i<vertices.length;i++)
        {
            result[i]=Transformation(vertices[i],matrix);
            result[i].Normalise();
        }
        return result;
    }
    //***********************************************************
    public static Coordinate []translate(Coordinate []vertices,double tx,double ty,double tz)
    {
        double []matrix=GetIdentityMatrix();
        matrix[3]=tx;
        matrix[7]=ty;
        matrix[11]=tz;
        return Transformation(vertices,matrix,0);
    }

    public static Coordinate[] scale (Coordinate []vertices,double sx,double sy,double sz)
    {
        double []matrix=GetIdentityMatrix();
        matrix[0]=sx;
        matrix[5]=sy;
        matrix[10]=sz;
        return Transformation(vertices,matrix,0);
    }
    protected static  Coordinate[] rotate(Coordinate[] vertices,double xUnitV,double yUnitV, double zUnitV,int angleOfRotation){
        double angleInRad=Math.toRadians(angleOfRotation);
        double w=Math.cos(angleInRad/2);
        double sin=Math.sin(angleInRad/2);
        double newX=sin*xUnitV;
        double newY=sin*yUnitV;
        double newZ=sin*zUnitV;
        double w2=w*w;
        double x2=newX*newX;
        double y2=newY*newY;
        double z2=newZ*newZ;

        double matrix[]=GetIdentityMatrix();
        matrix[0]=1-2*y2-2*z2;matrix[1]=2*newX*newY-2*newZ*w;matrix[2]=2*newX*newZ+2*newY*w;
        matrix[4]=2*newX*newY+2*newZ*w;  matrix[5]=1-2*x2-2*z2;matrix[6]=2*newZ*newY-2*newX*w;
        matrix[8]=2*newZ*newX-2*newY*w;matrix[9]=2*newZ*newY+2*newX*w;matrix[10]=1-2*x2-2*y2;

        return  Transformation(vertices,matrix,0);
    }

    protected static void DrawLinePairs(Canvas canvas, Coordinate[] vertices, int start, int end, Paint paint)
    {
        //start - index of the starting point//
        //end - index of the ending point//

        canvas.drawLine((int)vertices[start].x,(int)vertices[start].y,(int)vertices[end].x,(int)vertices[end].y,paint);
    }
    protected   static  void DrawCube(Coordinate[] draw_cube_vertices, Canvas canvas, Paint redPaint)
    {
        DrawLinePairs(canvas, draw_cube_vertices, 4, 5, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 5, 7, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 7, 6, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 6, 4, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 0, 4, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 1, 5, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 2, 6, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 3, 7, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 0, 1, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 1, 3, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 3, 2, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 2, 0, redPaint);

    } // unused method to centralize object//
    protected static Coordinate[] centralize(double cX,double cY,double cZ,double numSides,Coordinate[] operatedCoordinated){
        double centreOldX=0;
        double centreOldY=0;
        double centreOldZ=0;

        for(int i=0;i<numSides;i++){

            centreOldX  += operatedCoordinated[i].x;
            centreOldY  += operatedCoordinated[i].y;
            centreOldZ  += operatedCoordinated[i].z;

        }

        centreOldX=centreOldX/numSides;
        centreOldY=centreOldY/numSides;
        centreOldZ=centreOldZ/numSides;

        if(!(cX-centreOldX==0) || !(cY-centreOldY==0)){

       operatedCoordinated=translate(operatedCoordinated,cX-centreOldX,cY-centreOldY,cZ-centreOldZ);}

        return operatedCoordinated;
    }
    // align a body to be at one of the one these four direction  according to the centre . thats why I used this method to align the neck and the face//
    protected static void alignNewBody(BodyPart oldBody,BodyPart newBody,int Top,int Bottom,int Right,int Left){

        double newCentreX=oldBody.centreX;
        double newCentreY=oldBody.centreY;
        if(Top==1){
            newCentreY=oldBody.centreY-oldBody.y/2-newBody.y/2;
            newBody.setCentreY(newCentreY);

        }
        if(Bottom==1){
            newCentreY=oldBody.centreY+oldBody.y/2+newBody.y/2;
            newBody.setCentreY(newCentreY);
        }
        if(Right==1){

            newCentreX=oldBody.centreX+oldBody.x/2+newBody.x/2;
            newBody.setCentreX(newCentreX);
        }
        if(Left==1){

            newCentreX=oldBody.centreX-oldBody.x/2-newBody.x/2;
            newBody.setCentreX(newCentreX);
        }


    }

     // fix a line of the object to another line of another object//
      public static Coordinate[] fixLineFirstToSecond(Coordinate[] firstCordinates,Coordinate[] secondCordinates,int firstPointOne,int firstPointSec,int secondPointFirst,int secondPointSec) {
          double XFirst = (firstCordinates[firstPointOne].x + firstCordinates[firstPointSec].x) / 2;
          double YFirst = (firstCordinates[firstPointOne].y + firstCordinates[firstPointSec].y) / 2;
          double ZFirst = (firstCordinates[firstPointOne].z + firstCordinates[firstPointSec].z) / 2;

          double XSecond = (secondCordinates[secondPointFirst].x + secondCordinates[secondPointSec].x) / 2;
          double YSecond = (secondCordinates[secondPointFirst].y + secondCordinates[secondPointSec].y) / 2;
          double ZSecond = (secondCordinates[secondPointFirst].z + secondCordinates[secondPointSec].z) / 2;

          return translate(firstCordinates, XSecond - XFirst, YSecond - YFirst, ZSecond - ZFirst);
      }/*used fixLine method to make an easier  way to connect cuboids
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

      public  static Coordinate[] fixCubeFirstToSecond(Coordinate[] firstBody, Coordinate[] secondBody, int TopOrBottom1,int RightOrLeft1,int TopOrBottom2, int RightOrLeft2){

        return  fixLineFirstToSecond(firstBody,secondBody,TopOrBottom1+RightOrLeft1,TopOrBottom1+RightOrLeft1+4,TopOrBottom2+RightOrLeft2,TopOrBottom2+RightOrLeft2+4);
      }// used to fix a cuboid on the top of the other more easily//
    public  static Coordinate[] fixCubeFirstToSecond(Coordinate[] firstBody, Coordinate[] secondBody, int TopOrBottom1,int TopOrBottom2){

        return  fixLineFirstToSecond(firstBody,secondBody,TopOrBottom1+4,TopOrBottom1+6,TopOrBottom2+4,TopOrBottom2+6);
    }



}

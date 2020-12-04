package DancingRobotAhmedSalah.example.myapplication123;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;

public class MyView extends View {

    private  TimerTask timerTask;
    private  Timer timer;
    private  TimerTask timerTask2;

    private static final int  TOP=1;
    private static final int BOTTOM=0;
    private static final int RIGHT=0;
    private static final int LEFT=2;

    private int orderOfArmMovement=0;

    int timerFirstAngle=5;
    int timerFirstTotal=0;

    private BodyPart bigBody,neck,face,upperArm,lowerArm,lowerLeftArm,upperLeftArm,rightHand,leftHand,leftUpperLeg,leftLowerLeg,rightUpperLeg,rightLowerLeg,leftFoot,rightFeet;
    private Paint redPaint;
    private Coordinate[]draw_cube_vertices,neckVertices,faceVertices,upperArmVertices,lowerArmVertices,lowerLeftArmVertices,upperLeftArmVertices,rightHandVertices,leftHandVertices,testBodyVertices,leftUpperLegVertices,leftLowerLegVertices,rightUpperLegVertices,rightLowerLegVertices,leftFootVertices,rightFootVertices;

    public MyView(Context context) {
        super(context, null);

        final MyView thisview = this;

        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        redPaint.setColor(Color.RED);
/*
     The beginning of the first block.

     This block is about creating cuboid objects for the body, arm parts and so,
     also it`s responsible for aligning leg parts to be at the bottom of the body itself and same applies to arms being at the sides,
     which is important for rotation .

*/

        bigBody= new BodyPart(400,400,-50,50,0,0,4);
        draw_cube_vertices=bigBody.getTheCubeCoordinates();

        neck= new BodyPart(75,50,-50,50,4);
        staticMethods.alignNewBody(bigBody,neck,1,0,0,0);

        face= new BodyPart(175,175,-50,50,4);
        staticMethods.alignNewBody(neck,face,1,0,0,0);

        neckVertices=neck.getTheCubeCoordinates();

        faceVertices=face.getTheCubeCoordinates();

        upperArm= new BodyPart(130,200,-50,50,4);


        upperArmVertices=upperArm.getTheCubeCoordinates();
        upperArmVertices=staticMethods.fixCubeFirstToSecond(upperArmVertices,draw_cube_vertices,TOP,LEFT,TOP,RIGHT);

        lowerArm= new BodyPart(130,200,-50,50,4);

        lowerArmVertices=lowerArm.getTheCubeCoordinates();
        lowerArmVertices=staticMethods.fixCubeFirstToSecond(lowerArmVertices,upperArmVertices,TOP,BOTTOM);

        upperLeftArm= new BodyPart(130,200,-50,50,4);

        upperLeftArmVertices=upperLeftArm.getTheCubeCoordinates();
        upperLeftArmVertices = staticMethods.fixCubeFirstToSecond(upperLeftArmVertices,draw_cube_vertices,TOP,RIGHT,TOP,LEFT);

        lowerLeftArm= new BodyPart(130,200,-50,50,0,200,4);

        lowerLeftArmVertices=lowerLeftArm.getTheCubeCoordinates();
        lowerLeftArmVertices=staticMethods.fixCubeFirstToSecond(lowerLeftArmVertices,upperLeftArmVertices,TOP,BOTTOM);

        leftUpperLeg = new BodyPart(130,200,-50,50,0,0,4);

        leftUpperLegVertices = leftUpperLeg.getTheCubeCoordinates();
        leftUpperLegVertices= staticMethods.fixCubeFirstToSecond(leftUpperLegVertices,draw_cube_vertices,TOP,LEFT,BOTTOM,LEFT);

        rightUpperLeg = new BodyPart(130,200,-50,50,0,500,4);

        rightUpperLegVertices = rightUpperLeg.getTheCubeCoordinates();
        rightUpperLegVertices= staticMethods.fixCubeFirstToSecond(rightUpperLegVertices,draw_cube_vertices,TOP,RIGHT,BOTTOM,RIGHT);

        leftLowerLeg = new BodyPart(130,200,-50,50,0,700,4);

        leftLowerLegVertices = leftLowerLeg.getTheCubeCoordinates();
        leftLowerLegVertices = staticMethods.fixCubeFirstToSecond(leftLowerLegVertices,leftUpperLegVertices,TOP,BOTTOM);

        rightLowerLeg = new BodyPart(130,200,-50,50,0,0,4);

        rightLowerLegVertices = rightLowerLeg.getTheCubeCoordinates();
        rightLowerLegVertices = staticMethods.fixCubeFirstToSecond(rightLowerLegVertices,rightUpperLegVertices,TOP,BOTTOM);

        rightHand=new BodyPart(130,50,-50,130,200,225,4);

        rightHandVertices=rightHand.getTheCubeCoordinates();
        rightHandVertices=staticMethods.fixCubeFirstToSecond(rightHandVertices,lowerArmVertices,TOP,BOTTOM);

        leftHand=new BodyPart(130,50,-50,130,0,0,4);

        leftHandVertices=leftHand.getTheCubeCoordinates();
        leftHandVertices=staticMethods.fixCubeFirstToSecond(leftHandVertices,lowerLeftArmVertices,TOP,BOTTOM);

        rightFeet=new BodyPart(130,50,-50,130,0,0,4);

        rightFootVertices=rightHand.getTheCubeCoordinates();
        rightFootVertices=staticMethods.fixCubeFirstToSecond(rightFootVertices,rightLowerLegVertices,TOP,BOTTOM);

        leftFoot=new BodyPart(130,50,-50,130,0,0,4);

        leftFootVertices=leftHand.getTheCubeCoordinates();
        leftFootVertices=staticMethods.fixCubeFirstToSecond(leftFootVertices,leftLowerLegVertices,TOP,BOTTOM);


        // The end of the  first block //

        /* The beginning of the second block.

         It`s responsible for the animation of the dancing the robot.

         */
        timer= new Timer();

 timerTask = new TimerTask() {

    @Override
    public void run() {
        // These few lines rotate the whole robot around y axis //
        lowerArmVertices=staticMethods.rotate(lowerArmVertices,0,1,0,timerFirstAngle);
        upperArmVertices=staticMethods.rotate(upperArmVertices,0,1,0,timerFirstAngle);
        lowerLeftArmVertices=staticMethods.rotate(lowerLeftArmVertices,0,1,0,timerFirstAngle);
        upperLeftArmVertices=staticMethods.rotate(upperLeftArmVertices,0,1,0,timerFirstAngle);

        rightHandVertices=staticMethods.rotate(rightHandVertices,0,1,0,timerFirstAngle);
        leftHandVertices=staticMethods.rotate(leftHandVertices,0,1,0,timerFirstAngle);
        rightFootVertices = staticMethods.rotate(rightFootVertices,0,1,0,timerFirstAngle);
        leftFootVertices = staticMethods.rotate(leftFootVertices,0,1,0,timerFirstAngle);

        draw_cube_vertices=staticMethods.rotate(draw_cube_vertices,0,1,0,timerFirstAngle);
        neckVertices=staticMethods.rotate(neckVertices,0,1,0,timerFirstAngle);
        faceVertices=staticMethods.rotate(faceVertices,0,1,0,timerFirstAngle);

        leftLowerLegVertices = staticMethods.rotate(leftLowerLegVertices,0,1,0,timerFirstAngle);
        leftUpperLegVertices = staticMethods.rotate(leftUpperLegVertices,0,1,0,timerFirstAngle);
        rightLowerLegVertices = staticMethods.rotate(rightLowerLegVertices,0,1,0,timerFirstAngle);
        rightUpperLegVertices = staticMethods.rotate(rightUpperLegVertices,0,1,0,timerFirstAngle);

        // incrementing the angle //
       timerFirstTotal+=timerFirstAngle;

        if(timerFirstTotal==50){

            timerFirstAngle=-5;
            timerFirstTotal=-1;

        }
        if(timerFirstTotal==-51){

            timerFirstAngle=5;
            timerFirstTotal=0;
// determine the type of movement for the arm out of 3 different ones//
           if(orderOfArmMovement==3){
               orderOfArmMovement=-1;
           }
            orderOfArmMovement+=1;
        }

    }


};

 timerTask2=new TimerTask() {
     @Override
     public void run() {
// this vector represent the x axis rotated according to the robot movement//
         double x=draw_cube_vertices[6].x-draw_cube_vertices[4].x;
         double y=draw_cube_vertices[6].y-draw_cube_vertices[4].y;
         double z=draw_cube_vertices[6].z-draw_cube_vertices[4].z;
         double inverse =1/Math.sqrt(x*x+y*y+z*z);
         x=x*inverse;
         //y=y*inverse; unused but still.. //
         z=z*inverse;
        // z axis rotated//
         double xZ=draw_cube_vertices[1].x-draw_cube_vertices[5].x;
         double yZ=draw_cube_vertices[1].y-draw_cube_vertices[5].y;
         double zZ=draw_cube_vertices[1].z-draw_cube_vertices[5].z;
         double inverseZ =1/Math.sqrt(xZ*xZ+yZ*yZ+zZ*zZ);
         xZ=xZ*inverseZ;
         yZ=yZ*inverse;
         zZ=zZ*inverse;

         leftLowerLegVertices = staticMethods.rotate(leftLowerLegVertices,x,0,z,timerFirstAngle);
         leftUpperLegVertices = staticMethods.rotate(leftUpperLegVertices,x,0,z,timerFirstAngle);

         rightLowerLegVertices = staticMethods.rotate(rightLowerLegVertices,x,0,z,timerFirstAngle);
         rightUpperLegVertices = staticMethods.rotate(rightUpperLegVertices,x,0,z,timerFirstAngle);

         rightFootVertices = staticMethods.rotate(rightFootVertices,x,0,z,timerFirstAngle);
         leftFootVertices = staticMethods.rotate(leftFootVertices,x,0,z,timerFirstAngle);

         if(orderOfArmMovement==0 ){

         lowerArmVertices = staticMethods.rotate(lowerArmVertices, x, 0, z,timerFirstAngle);
         upperArmVertices = staticMethods.rotate(upperArmVertices, x, 0, z, timerFirstAngle);
         lowerLeftArmVertices = staticMethods.rotate(lowerLeftArmVertices,x ,0,z,timerFirstAngle);
         upperLeftArmVertices = staticMethods.rotate(upperLeftArmVertices,x ,0,z, timerFirstAngle);

         rightHandVertices = staticMethods.rotate(rightHandVertices, x, 0, z, timerFirstAngle);
         leftHandVertices= staticMethods.rotate(leftHandVertices, x, 0, z, timerFirstAngle);

}
         else if(orderOfArmMovement==1){

            lowerArmVertices = staticMethods.rotate(lowerArmVertices, xZ, 0, zZ,3*timerFirstAngle);
            upperArmVertices = staticMethods.rotate(upperArmVertices, xZ, 0, zZ, 3*timerFirstAngle);
            lowerLeftArmVertices = staticMethods.rotate(lowerLeftArmVertices,xZ ,0,zZ,-3*timerFirstAngle);
            upperLeftArmVertices = staticMethods.rotate(upperLeftArmVertices,xZ ,0,zZ, -3*timerFirstAngle);

            rightHandVertices = staticMethods.rotate(rightHandVertices, xZ, 0, zZ, 3*timerFirstAngle);
            leftHandVertices= staticMethods.rotate(leftHandVertices, xZ, 0, zZ, -3*timerFirstAngle);


         }
        else if(orderOfArmMovement ==2){

        lowerArmVertices = staticMethods.rotate(lowerArmVertices, xZ, yZ, 0,4*timerFirstAngle);
        upperArmVertices = staticMethods.rotate(upperArmVertices, xZ, yZ, 0, 4*timerFirstAngle);
        lowerLeftArmVertices = staticMethods.rotate(lowerLeftArmVertices,xZ ,-yZ,0,4*timerFirstAngle);
        upperLeftArmVertices = staticMethods.rotate(upperLeftArmVertices,xZ ,-yZ,0, 4*timerFirstAngle);

        rightHandVertices = staticMethods.rotate(rightHandVertices, xZ, yZ, 0,4* timerFirstAngle);
        leftHandVertices= staticMethods.rotate(leftHandVertices, xZ, -yZ, 0,4* timerFirstAngle);

}
        /*  make sure after rotation body parts aren`t translated
         Note : i could have used fix to cube to give the same results with easier  */

         upperArmVertices=staticMethods.fixCubeFirstToSecond(upperArmVertices,draw_cube_vertices,TOP,LEFT,TOP,RIGHT);
         lowerArmVertices=staticMethods.fixCubeFirstToSecond(lowerArmVertices,upperArmVertices,TOP,BOTTOM);
         upperLeftArmVertices = staticMethods.fixCubeFirstToSecond(upperLeftArmVertices,draw_cube_vertices,TOP,RIGHT,TOP,LEFT);
         lowerLeftArmVertices=staticMethods.fixCubeFirstToSecond(lowerLeftArmVertices,upperLeftArmVertices,TOP,BOTTOM);

         leftUpperLegVertices= staticMethods.fixCubeFirstToSecond(leftUpperLegVertices,draw_cube_vertices,TOP,LEFT,BOTTOM,LEFT);
         rightUpperLegVertices= staticMethods.fixCubeFirstToSecond(rightUpperLegVertices,draw_cube_vertices,TOP,RIGHT,BOTTOM,RIGHT);
         leftLowerLegVertices = staticMethods.fixCubeFirstToSecond(leftLowerLegVertices,leftUpperLegVertices,TOP,BOTTOM);
         rightLowerLegVertices = staticMethods.fixCubeFirstToSecond(rightLowerLegVertices,rightUpperLegVertices,TOP,BOTTOM);

         rightHandVertices=staticMethods.fixCubeFirstToSecond(rightHandVertices,lowerArmVertices,TOP,BOTTOM);
         leftHandVertices=staticMethods.fixCubeFirstToSecond(leftHandVertices,lowerLeftArmVertices,TOP,BOTTOM);
         rightFootVertices=staticMethods.fixCubeFirstToSecond(rightFootVertices,rightLowerLegVertices,TOP,BOTTOM);
         leftFootVertices=staticMethods.fixCubeFirstToSecond(leftFootVertices,leftLowerLegVertices,TOP,BOTTOM);

         // refresh the draw call//

         thisview.postInvalidate();

     }

 };

       timer.scheduleAtFixedRate(timerTask,3000,90);
       timer.scheduleAtFixedRate(timerTask2,3000,90);

    }

    @Override
    protected void onDraw( final Canvas canvas) {

        super.onDraw(canvas);
        //draw objects on the screen//
        // translate the whole canvas//
        canvas.translate(canvas.getWidth()/2,canvas.getHeight()/2.5f);

        staticMethods.DrawCube(leftLowerLegVertices,canvas,redPaint);
        staticMethods.DrawCube(rightLowerLegVertices,canvas,redPaint);
        staticMethods.DrawCube(leftUpperLegVertices,canvas,redPaint);
        staticMethods.DrawCube(rightUpperLegVertices,canvas,redPaint);

        staticMethods.DrawCube(lowerArmVertices,canvas,redPaint);
        staticMethods.DrawCube(upperLeftArmVertices,canvas,redPaint);
        staticMethods.DrawCube(lowerLeftArmVertices,canvas,redPaint);
        staticMethods.DrawCube(upperArmVertices,canvas,redPaint);

        staticMethods.DrawCube(rightHandVertices,canvas,redPaint);
        staticMethods.DrawCube(leftHandVertices,canvas,redPaint);
        staticMethods.DrawCube(rightFootVertices,canvas,redPaint);
        staticMethods.DrawCube(leftFootVertices,canvas,redPaint);

        staticMethods.DrawCube(draw_cube_vertices,canvas,redPaint);
       staticMethods.DrawCube(faceVertices,canvas,redPaint);
        staticMethods.DrawCube(neckVertices,canvas,redPaint);

    }

}

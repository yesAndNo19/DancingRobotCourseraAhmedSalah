package DancingRobotAhmedSalah.example.myapplication123;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.TimerTask;

public class TimerCustom extends TimerTask {
   Canvas canvas;
   Paint paint;
   Coordinate[] coordinate;
   int n=0;

    public TimerCustom(Canvas canvas, Coordinate[] draw_cube_vertices, Paint redPaint) {
        this.canvas=canvas;
        this.paint=redPaint;
        this.coordinate=draw_cube_vertices;
    }


    @Override
    public void run() {




if(n==3){
    cancel();

}

    }
}

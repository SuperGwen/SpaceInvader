package fr.dubois.space.invader;



import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SpaceInvaderView extends View {
	
	// Dimensions souhaitées
	private static final int TARGET_HEIGHT = 800;
	private static final int TARGET_WIDTH = 600;

	private Paint paint; // Style pour le texte	
	private String text; // texte Ã  afficher


	public SpaceInvaderView(Context context) {
		super(context);
		init();
	}
	
	public SpaceInvaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public SpaceInvaderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

/* Sélim Boughriet */
    public Bitmap loadImage(int identifiant, Drawable drawable) {
    	int hauteur = drawable.getIntrinsicHeight();
    	int largeur = drawable.getIntrinsicWidth();
        Bitmap bitmap = Bitmap.createBitmap(hauteur, largeur, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, hauteur, largeur);
        return bitmap;
    }


	void init(){
		paint = new Paint();
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.YELLOW);
		paint.setTypeface(Typeface.SANS_SERIF);
		paint.setTextSize(36);
		paint.setTextAlign(Paint.Align.CENTER);
		text = "Texte";
		/* Sélim Boughriet */
		Bitmap image_alien = loadImage(R.drawable.alien1, null);
		Bitmap image_launcher = loadImage(R.drawable.ic_launcher, null);
		Bitmap image_missile = loadImage(R.drawable.missile, null);
		Bitmap image_missile2 = loadImage(R.drawable.missile2, null);
		Bitmap image_ship = loadImage(R.drawable.ship, null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRGB(0, 0, 0);
		canvas.drawRect(0, 0, TARGET_WIDTH-1, TARGET_HEIGHT-1, paint);
		if (text != null){
			canvas.drawText(text, canvas.getWidth()/2,canvas.getHeight()/2, paint);
		}
	}


	private int computeSize(int spec,int def){
		int mode = View.MeasureSpec.getMode(spec);
		if (mode == View.MeasureSpec.UNSPECIFIED) return def;
		int size = View.MeasureSpec.getSize(spec);
		if (mode == View.MeasureSpec.EXACTLY) {
			return size;
		}
		//		MeasureSpec.AT_MOST
		if (size < def ) return size;
		return def;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int x = computeSize(widthMeasureSpec,TARGET_WIDTH);
		int y = computeSize(heightMeasureSpec,TARGET_HEIGHT);
		this.setMeasuredDimension(x,y);
	}

}

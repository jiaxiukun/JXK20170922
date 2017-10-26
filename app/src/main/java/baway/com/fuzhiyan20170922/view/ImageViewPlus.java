package baway.com.fuzhiyan20170922.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import baway.com.fuzhiyan20170922.R;

public class ImageViewPlus extends ImageView {
    private static final int DEFAULT_BORDER_COLOR = 0;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_RECT_ROUND_RADIUS = 0;
    private static final int DEFAULT_TYPE = 0;
    public static final int TYPE_CIRCLE = 1;
    public static final int TYPE_NONE = 0;
    public static final int TYPE_ROUNDED_RECT = 2;
    private int mBorderColor;
    private int mBorderWidth;
    private Matrix mMatrix = new Matrix();
    private Paint mPaintBitmap = new Paint(1);
    private Paint mPaintBorder = new Paint(1);
    private Bitmap mRawBitmap;
    private RectF mRectBitmap = new RectF();
    private RectF mRectBorder = new RectF();
    private int mRectRoundRadius;
    private BitmapShader mShader;
    private int mType;

    public ImageViewPlus(Context context) {
        super(context);
    }

    public ImageViewPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ImageViewPlus);
        this.mType = ta.getInt(3, 0);
        this.mBorderColor = ta.getColor(0, 0);
        this.mBorderWidth = ta.getDimensionPixelSize(1, dip2px(0));
        this.mRectRoundRadius = ta.getDimensionPixelSize(2, dip2px(0));
        ta.recycle();
    }

    protected void onDraw(Canvas canvas) {
        Bitmap rawBitmap = getBitmap(getDrawable());
        if (rawBitmap == null || this.mType == 0) {
            super.onDraw(canvas);
            return;
        }
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int viewMinSize = Math.min(viewWidth, viewHeight);
        float dstWidth = this.mType == 1 ? (float) viewMinSize : (float) viewWidth;
        float dstHeight = this.mType == 1 ? (float) viewMinSize : (float) viewHeight;
        float halfBorderWidth = ((float) this.mBorderWidth) / 2.0f;
        float doubleBorderWidth = (float) (this.mBorderWidth * 2);
        if (this.mShader == null || !rawBitmap.equals(this.mRawBitmap)) {
            this.mRawBitmap = rawBitmap;
            this.mShader = new BitmapShader(this.mRawBitmap, TileMode.CLAMP, TileMode.CLAMP);
        }
        if (this.mShader != null) {
            this.mMatrix.setScale((dstWidth - doubleBorderWidth) / ((float) rawBitmap.getWidth()), (dstHeight - doubleBorderWidth) / ((float) rawBitmap.getHeight()));
            this.mShader.setLocalMatrix(this.mMatrix);
        }
        this.mPaintBitmap.setShader(this.mShader);
        this.mPaintBorder.setStyle(Style.STROKE);
        this.mPaintBorder.setStrokeWidth((float) this.mBorderWidth);
        this.mPaintBorder.setColor(this.mBorderWidth > 0 ? this.mBorderColor : 0);
        if (this.mType == 1) {
            float radius = ((float) viewMinSize) / 2.0f;
            canvas.drawCircle(radius, radius, radius - halfBorderWidth, this.mPaintBorder);
            canvas.translate((float) this.mBorderWidth, (float) this.mBorderWidth);
            canvas.drawCircle(radius - ((float) this.mBorderWidth), radius - ((float) this.mBorderWidth), radius - ((float) this.mBorderWidth), this.mPaintBitmap);
        } else if (this.mType == 2) {
            this.mRectBorder.set(halfBorderWidth, halfBorderWidth, dstWidth - halfBorderWidth, dstHeight - halfBorderWidth);
            this.mRectBitmap.set(0.0f, 0.0f, dstWidth - doubleBorderWidth, dstHeight - doubleBorderWidth);
            float borderRadius = ((float) this.mRectRoundRadius) - halfBorderWidth > 0.0f ? ((float) this.mRectRoundRadius) - halfBorderWidth : 0.0f;
            float bitmapRadius = ((float) (this.mRectRoundRadius - this.mBorderWidth)) > 0.0f ? (float) (this.mRectRoundRadius - this.mBorderWidth) : 0.0f;
            canvas.drawRoundRect(this.mRectBorder, borderRadius, borderRadius, this.mPaintBorder);
            canvas.translate((float) this.mBorderWidth, (float) this.mBorderWidth);
            canvas.drawRoundRect(this.mRectBitmap, bitmapRadius, bitmapRadius, this.mPaintBitmap);
        }
    }

    private int dip2px(int dipVal) {
        return (int) ((((float) dipVal) * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private Bitmap getBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (!(drawable instanceof ColorDrawable)) {
            return null;
        }
        Rect rect = drawable.getBounds();
        int width = rect.right - rect.left;
        int height = rect.bottom - rect.top;
        int color = ((ColorDrawable) drawable).getColor();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        new Canvas(bitmap).drawARGB(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
        return bitmap;
    }
}

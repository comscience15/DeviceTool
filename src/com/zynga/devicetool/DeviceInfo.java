package com.zynga.devicetool;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.Debug;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class DeviceInfo extends Activity{
	private TextView tvBrand_edt, tvModel_edt, tvAndroidOS_edt, tvBoard_edt, tvBootLoader_edt, tvCPUabi_edt, tvCPUabi2_edt, tvDisplay_edt, 
					 tvSerial_edt, tvGL_Version_edt, tvGL_Vendor_edt, vmHeapSize, tvGL_Renderer_edt, vmAllocatedMem, vmHeapLimit, nativeAllocatedMem;
	private GLSurfaceView glSurfaceView;
	private String glVendor1, glRenderer1, glVersion1;
	private RelativeLayout rlLayout;
	PackageManager packageManager;
	ListView apkList;
	
	private GLSurfaceView.Renderer glVersion = new Renderer() {
	    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
	    	Log.d("GPU info", "gl renderer: "+gl.glGetString(GL10.GL_RENDERER));
	    	Log.d("GPU info", "gl vendor: "+gl.glGetString(GL10.GL_VENDOR));
	        Log.d("GPU info", "gl version: "+gl.glGetString(GL10.GL_VERSION));
	        Log.d("GPU info", "gl extensions: "+gl.glGetString(GL10.GL_EXTENSIONS));

	        glRenderer1 = gl.glGetString(GL10.GL_RENDERER);
	        glVendor1 = gl.glGetString(GL10.GL_VENDOR);
	        glVersion1 = gl.glGetString(GL10.GL_VERSION);
	        runOnUiThread(new Runnable() {

	            @Override
	            public void run() {
	            	tvGL_Version_edt.setText(glVersion1);
	            	tvGL_Vendor_edt.setText(glVendor1);
	            	tvGL_Renderer_edt.setText(glRenderer1);
	            	rlLayout.removeView(glSurfaceView);

	            }
	        });
	     }

	    @Override
	    public void onSurfaceChanged(GL10 gl, int width, int height) {
	        // TODO Auto-generated method stub

	    }

	    @Override
	    public void onDrawFrame(GL10 gl) {
	        // TODO Auto-generated method stub

	    }
    };

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deviceinfo);

		rlLayout = (RelativeLayout)findViewById(R.id.rlLayout);

		tvBrand_edt = (TextView) findViewById(R.id.deviceBrand);
		tvBrand_edt.setText((android.os.Build.MANUFACTURER).toUpperCase());

		tvModel_edt = (TextView) findViewById(R.id.deviceModel);
		tvModel_edt.setText(android.os.Build.MODEL);

		tvAndroidOS_edt = (TextView) findViewById(R.id.deviceOS);
		tvAndroidOS_edt.setText(android.os.Build.VERSION.RELEASE);

		tvBoard_edt = (TextView) findViewById(R.id.deviceBoard);
		tvBoard_edt.setText(android.os.Build.BOARD);

		tvBootLoader_edt = (TextView) findViewById(R.id.deviceBootLoader);
		tvBootLoader_edt.setText(android.os.Build.BOOTLOADER);

		tvCPUabi_edt = (TextView) findViewById(R.id.deviceCPUabi);
		tvCPUabi_edt.setText(android.os.Build.CPU_ABI);

		tvCPUabi2_edt = (TextView) findViewById(R.id.deviceCPUabi2);
		tvCPUabi2_edt.setText(android.os.Build.CPU_ABI2);

		tvDisplay_edt = (TextView) findViewById(R.id.deviceDisplay);
		tvDisplay_edt.setText(android.os.Build.DISPLAY);

		tvGL_Renderer_edt = (TextView) findViewById(R.id.deviceGLRenderer);

		glSurfaceView = new GLSurfaceView(this);
		glSurfaceView.setRenderer(glVersion);

		tvGL_Vendor_edt = (TextView) findViewById(R.id.deviceGLVendor);

		tvGL_Version_edt = (TextView) findViewById(R.id.deviceGLVersion);

		tvSerial_edt = (TextView) findViewById(R.id.deviceSerial);
		tvSerial_edt.setText(android.os.Build.SERIAL);

		rlLayout.addView(glSurfaceView);
		
		vmHeapSize = (TextView) findViewById(R.id.vmheap);
		vmAllocatedMem = (TextView) findViewById(R.id.vmAllocated);
		vmHeapLimit = (TextView) findViewById(R.id.vmHeapLimit);
		nativeAllocatedMem = (TextView) findViewById(R.id.nativeAllocatedMem);
		
		//VM Heap Size, VM Allocated, VM Allocated limit, Native Allocated Memory
		long vmAlloc =  Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long nativeAlloc = Debug.getNativeHeapAllocatedSize();
      
		vmHeapSize.setText(formatMemoeryText(Runtime.getRuntime().totalMemory()));
		vmAllocatedMem.setText(formatMemoeryText(vmAlloc));
		vmHeapLimit.setText(formatMemoeryText(nativeAlloc+vmAlloc));
		nativeAllocatedMem.setText(formatMemoeryText(nativeAlloc));
	}
	
	@SuppressLint("DefaultLocale")
	private String formatMemoeryText(long memory) {
		// TODO Auto-generated method stub
    	float memoryInMB = memory * 1f / 1024 / 1024;
		return String.format("%.1f MB", memoryInMB);
	}

	
}

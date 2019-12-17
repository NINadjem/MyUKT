package projects.uikt.com.myukt;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class mySinglton {
    private static mySinglton mInstance;
    private RequestQueue requestQueue;
    private static Context context;

    public mySinglton(Context context) {
        this.context=context;
        this.requestQueue = getRequestQueue();

    }

    public RequestQueue getRequestQueue() {
        if(requestQueue==null) requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        return requestQueue;
    }
    public static synchronized mySinglton getmInstance(Context context){
        if( mInstance==null){
            mInstance=new mySinglton(context);
        }
        return mInstance;
    }
    public <T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);

    }

}

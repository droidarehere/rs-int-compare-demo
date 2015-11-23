package com.droidarehere.rsintcompare;

import android.app.Activity;
import android.os.Bundle;
import android.renderscript.*;
import android.util.Log;

public class rsintcompareactivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        int a[] = new int[1000];
        int b[] = new int[1000];
        int diffs[] = new int[1];

        RenderScript rs = RenderScript.create(this);
        Allocation alloc_a = Allocation.createSized(rs, Element.I32(rs), 1000);
        Allocation alloc_b = Allocation.createSized(rs, Element.I32(rs), 1000);
        Allocation alloc_diffs = Allocation.createSized(rs, Element.I32(rs), 1);
        alloc_a.copyFrom(a);
        alloc_b.copyFrom(b);
        ScriptC_compare script = new ScriptC_compare(rs);
        script.set_num_diffs(0);
        script.set_alloc_b(alloc_b);
        script.set_alloc_diffs(alloc_diffs);
        script.forEach_compare_ints(alloc_a);
        script.invoke_get_num_diffs();
        alloc_diffs.copyTo(diffs);

        Log.e("TAG", String.format("Number of diffs: %d", diffs[0]));
    }
}

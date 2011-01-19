package com.todoroo.astrid.notes;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.timsu.astrid.R;
import com.todoroo.astrid.data.Task;

public class NoteViewingActivity extends Activity {

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.empty_linear_layout);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.dialog_title_bar);

        LinearLayout body = (LinearLayout) findViewById(R.id.body);

        task = getIntent().getParcelableExtra(NotesActionExposer.EXTRA_TASK);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(task.getValue(Task.TITLE));

        ScrollView scrollView = new ScrollView(this);

        TextView linkifiedTextView = new TextView(this);
        linkifiedTextView.setText(task.getValue(Task.NOTES) + "\n\n"); //$NON-NLS-1$
        Linkify.addLinks(linkifiedTextView, Linkify.ALL);

        scrollView.addView(linkifiedTextView);
        body.addView(scrollView);

        Button ok = new Button(this);
        ok.setText(android.R.string.ok);
        ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
        body.addView(ok);
    }
}
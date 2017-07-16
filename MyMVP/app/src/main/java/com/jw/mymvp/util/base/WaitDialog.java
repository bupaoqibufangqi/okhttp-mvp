package com.jw.mymvp.util.base;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jw.mymvp.R;

public class WaitDialog extends Dialog {

	private WebView runWebView;
	public WaitDialog(Context context) {
		super(context);
	}

	public WaitDialog(Context context, int theme) {
		super(context, theme);
	}

	public WebView getRunWebView() {
		return runWebView;
	}

	public void setRunWebView(WebView runWebView) {
		this.runWebView = runWebView;
	}

	public static class Builder {
		private Context context;
		private String message;
		private TextView msgTextView;
		private View contentView;
		private Boolean Isloading = true;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setLoading(Boolean bloading) {
			this.Isloading = bloading;
			return this;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		public WaitDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final WaitDialog dialog = new WaitDialog(context, R.style.Dialog);
			View layout = inflater.inflate(R.layout.dialog_wait, null);
			WebView runWebView=(WebView) layout.findViewById(R.id.runWebView);
			runWebView.loadDataWithBaseURL(null,"<HTML><body><div align=center><img style='width:30px;height:30px;' src='file:///android_res/drawable/loading.gif''></img></div></body></html>", "text/html", "UTF-8",null);
			runWebView.setBackgroundColor(0);
			dialog.setRunWebView(runWebView);
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			msgTextView = (TextView) layout.findViewById(R.id.message);
			if (message != null && !message.equals("")) {
				msgTextView.setText(message);
			} else if (contentView != null) {
				((LinearLayout) layout.findViewById(R.id.content))
						.removeAllViews();
				((LinearLayout) layout.findViewById(R.id.content)).addView(
						contentView, new LayoutParams(
								LayoutParams.MATCH_PARENT,
								LayoutParams.MATCH_PARENT));
			}
			// loading是否显示
			if (Isloading == false) {
				layout.findViewById(R.id.loading).setVisibility(View.GONE);
			} else {
				layout.findViewById(R.id.loading).setVisibility(View.VISIBLE);
			}
			dialog.setContentView(layout);
			return dialog;
		}
	}
}

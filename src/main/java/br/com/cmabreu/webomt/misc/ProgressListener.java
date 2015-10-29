package br.com.cmabreu.webomt.misc;

import java.util.UUID;

import br.com.cmabreu.webomt.misc.ProgressAwareInputStream.OnProgressListener;

public class ProgressListener implements OnProgressListener {
	private int percentage = 0;
	private String fileName;
	private String serial;
	
	public ProgressListener( String fileName ) {
		this.serial = UUID.randomUUID().toString().substring(0,5).replace("-", "");
		this.fileName = fileName;
	}
	
	@Override
	public void onProgress(int percentage, Object tag) {
		this.percentage = percentage;
	}

	public int getPercentage() {
		return percentage;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String getSerial() {
		return serial;
	}
}

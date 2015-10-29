package br.com.cmabreu.webomt.misc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateLibrary {
	private Calendar calendar;
	private SimpleDateFormat ft;
	private SimpleDateFormat ftm;
	private SimpleDateFormat fm;
	private SimpleDateFormat sq;
	private static DateLibrary dl;
	
	public static DateLibrary getInstance() {
		if ( dl == null ) {
			dl = new DateLibrary();
		}
		return dl;
	}
	
	private DateLibrary() {
		Date dNow = new Date( );
		ft = new SimpleDateFormat ("dd/MM/yyyy");
		ftm = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		fm = new SimpleDateFormat ("HH:mm:ss");
		sq = new SimpleDateFormat ("yyyy-MM-dd");
		calendar = Calendar.getInstance();
		calendar.setTime(dNow);
	}

	public Date getDateFromTime( String time ) throws ParseException {
		Date date = fm.parse( time );
		setTo(date);
		return date;
	}
	
	public void setToStartOfMonth() {
		calendar.set(Calendar.DAY_OF_MONTH, 1);		
	}

	public void setToStartOfYear() {
		calendar.set(Calendar.DAY_OF_YEAR, 1);		
	}
	
	
	public void increaseDay(int days){
		calendar.add(Calendar.DATE, days);
	}

	public void increaseMillis(long millis) {
		Integer i = (int) (long) millis;
		calendar.add(Calendar.MILLISECOND, i );
	}
	
	public long getDiffDaysTo(Calendar date) {
		long daysDiff = getDiffMillisTo(date) / (1000 * 60 * 60 * 24);    	
		return daysDiff;    	
	}

	
	public long getDiffSecondsTo(Calendar date) {
		long secDiff = getDiffMillisTo(date) / 1000;    	
		return secDiff;    	
	}
	
	
	public String getTimeRepresentation( long millis ) {
		String retorno = String.format("%02d:%02d:%02d", 
				TimeUnit.MILLISECONDS.toHours(millis),
				TimeUnit.MILLISECONDS.toMinutes(millis) -  
				TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), 
				TimeUnit.MILLISECONDS.toSeconds(millis) - 
				TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))); 	
		return retorno;
	}

	
	public long getDiffMillisTo(Calendar date) {
		long difference = 0;
		if (  isAfter(date)  ) {
			difference = calendar.getTimeInMillis() - date.getTimeInMillis();
		} else {
			difference = date.getTimeInMillis() - calendar.getTimeInMillis();
		}
		calendar.setTimeInMillis( difference );
		return difference;    	
	}
	
	public Date getDiffDateTo(Calendar date) {
		return new Date( getDiffMillisTo(date) );    	
	}
	
	public boolean isAfter( Calendar data ) {
		return ( calendar.after(data) );
	}

	public boolean isInBetween(Calendar dtInicial, Calendar dtFinal ) {
    	if (   (calendar.after(dtInicial)) && (calendar.before(dtFinal))  ){
    		return true;
    	}
		return false;		
	}

	public Calendar asCalendar() {
		return calendar;
	}
	
	public boolean isBefore( Calendar data ) {
		return ( calendar.before(data) );
	}

	
	public void setTo( Date date ) {
		if ( date != null ) {
			calendar.setTime(date);
		}
	}

	public void setTo( Calendar date ) {
		calendar = date;
	}

	
	public Date asDate() {
		return calendar.getTime();
	}
	
	public String getDateTextSQL() {
		return ft.format( calendar.getTime() );
	}
	
	public String getDateTextHuman() {
		return ft.format( calendar.getTime() );
	}

	public String getHourTextHuman() {
		return fm.format( calendar.getTime() );
	}
	
	public String getDateHourTextHuman( Date input ) {
		setTo( input );
		return ftm.format( calendar.getTime() );
	}
	
	public String getDateHourTextHuman() {
		return ftm.format( calendar.getTime() );
	}

	public void setDateTextHuman(String data) {
		try {
		    setTo( ft.parse(data) );
		} catch (ParseException ex) {
		    setTo( new Date( ) );
		}
	}

	public void setTimeTextHuman(String hora) {
		try {
		    setTo( fm.parse(hora) );
		} catch (ParseException ex) {
		    setTo( new Date( ) );
		}
	}
	
	
	public void setDateTextSQL(String data) {
		try {
		    setTo( sq.parse(data) );
		} catch (ParseException ex) {
		    setTo( new Date( ) );
		}
	}
	
	
}


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JOptionPane;



/**
 * @author josh
 *
 */
public class DateConverter {
	private ArrayList<String> dates, time, dateTime;

	public static void main(String[] args) throws Exception{
		new DateConverter();
	}

	DateConverter() throws Exception{
		dates = new ArrayList<String>();
		time = new ArrayList<String>();
		dateTime = new ArrayList<String>();
		String date = JOptionPane.showInputDialog("Input Date:");		
		JOptionPane.showMessageDialog(null, "ISO 8601: " + parse(date));
	}
	
	/**
	 * @param date
	 * @return
	 * @throws Exception
	 */
	private String parse(String date) throws Exception{	
		ArrayList<DateFormat> formats = new ArrayList<DateFormat>();
				
		//Format 0
		DateFormat format1 = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
		format1.setTimeZone(TimeZone.getTimeZone("UTC"));
		format1.setLenient(true);
		formats.add(format1);
		
		//Format 1
		formats.add(new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a", Locale.US));
		formats.get(formats.size() - 1).setTimeZone(TimeZone.getTimeZone("UTC"));
		
		//Format 2
		formats.add(new SimpleDateFormat("MM/dd/yy", Locale.US));
		formats.get(formats.size() - 1).setTimeZone(TimeZone.getTimeZone("UTC"));
		formats.get(formats.size() - 1).setLenient(false);
		
		//Format 3
		DateFormat format2 = new SimpleDateFormat("h:mm:ss a", Locale.US);
		format2.setTimeZone(TimeZone.getTimeZone("UTC"));
		formats.add(format2);
		
		//Format 4
		DateFormat format3 = new SimpleDateFormat("EEEE, MMMM d, yyyy h:mm:ss a", Locale.US);
		format3.setTimeZone(TimeZone.getTimeZone("UTC"));
		formats.add(format3);
		
		//Format 5
		DateFormat format4 = new SimpleDateFormat("EEEE, MMMM d, yyyy hh:mm a", Locale.US);
		format4.setTimeZone(TimeZone.getTimeZone("UTC"));
		formats.add(format4);
				
		//Format 6
		DateFormat format5 = new SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.US);
		format5.setTimeZone(TimeZone.getTimeZone("UTC"));
		formats.add(format5);
		
		//Format 7
		DateFormat format6 = new SimpleDateFormat("EEEE d'th' 'of' MMMM yyyy h:mm:ss a", Locale.US);
		format6.setTimeZone(TimeZone.getTimeZone("UTC"));
		formats.add(format6);
		
		//Format 8
		DateFormat format7 = new SimpleDateFormat("EEEE, MMM d, yyyy", Locale.US);
		format7.setTimeZone(TimeZone.getTimeZone("UTC"));
		formats.add(format7);
		
		//Format 9
		formats.add(new SimpleDateFormat("MMMM d, yyyy", Locale.US));
		formats.get(formats.size() - 1).setTimeZone(TimeZone.getTimeZone("UTC"));
		
		//Format 10
		formats.add(new SimpleDateFormat("MMMM d", Locale.US));
		formats.get(formats.size() - 1).setTimeZone(TimeZone.getTimeZone("UTC"));
		
		//Format 11
		formats.add(new SimpleDateFormat("MMMM, yyyy", Locale.US));
		formats.get(formats.size() - 1).setTimeZone(TimeZone.getTimeZone("UTC"));
		
		//Format 12
		formats.add(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US));
		formats.get(formats.size() - 1).setTimeZone(TimeZone.getTimeZone("UTC"));
		
		//Format 13
		formats.add(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US));
		formats.get(formats.size() - 1).setTimeZone(TimeZone.getTimeZone("UTC"));
		
		//Format 14
		formats.add(new SimpleDateFormat("yyyyMMd HH:mm:ss", Locale.US));
		formats.get(formats.size() - 1).setTimeZone(TimeZone.getTimeZone("UTC"));
		
		//Format 15
		formats.add(new SimpleDateFormat("yyyyMMd", Locale.US)); 
		formats.get(formats.size() - 1).setTimeZone(TimeZone.getTimeZone("UTC"));
		
		//Format 16
		formats.add(new SimpleDateFormat("yyyy.MM.d", Locale.US));
		formats.get(formats.size() - 1).setTimeZone(TimeZone.getTimeZone("UTC"));
		
		//Format 17
		formats.add(new SimpleDateFormat("dd/MM/yyyy", Locale.US));
		formats.get(formats.size() - 1).setTimeZone(TimeZone.getTimeZone("UTC"));
		
		//Format 18
		formats.add(new SimpleDateFormat("d MMMM yyyy", Locale.US));
		formats.get(formats.size() - 1).setTimeZone(TimeZone.getTimeZone("UTC"));
		
		//Format 19
		formats.add(new SimpleDateFormat("yyyy-dd-MM'T'HH:mm:ss-z", Locale.US));
		formats.get(formats.size() - 1).setTimeZone(TimeZone.getTimeZone("UTC"));
				
		
		
		//ISO 8601
		DateFormat iso8601 = new SimpleDateFormat("yyyy'-'MM'-'dd'T'kk:mm:ss.Sz", Locale.US);		
		iso8601.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		boolean flag = true;
		int count = 0;
		Date tempDate = null;
		while(flag){
			try{
				tempDate = formats.get(count).parse(date);
				System.out.println("Using: format " + count);
				flag = false;
			}
			catch(Exception e){
				if(count > formats.size()){
					JOptionPane.showMessageDialog(null, "FATAL ERROR");
					flag = false;
				}
				count++;
			}
		}
		if(count == 3){
			System.out.println("Running...");
			Calendar c = Calendar.getInstance();	
			c.setTime(tempDate);
			c.set(c.getInstance().get(c.YEAR), c.getInstance().get(c.MONTH), c.getInstance().get(c.DATE));			
			tempDate = c.getTime();
		}
		
		if(count == 10){
			Calendar c = formats.get(count).getCalendar();			
			c.set(c.YEAR, Calendar.getInstance().get(c.YEAR));
			tempDate = c.getTime();
		}
						
		return iso8601.format(tempDate);				
	}
	
}

package zappos2;
import java.util.Timer;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import zappos2.ZapposChallenge2;

public class Alarm {
	
	Timer _timer;

    public Alarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);
        System.out.println("Saved items price will be checked everyday at 8:00 am");
        System.out.println();
        System.out.println("Notification to your email will be sent when saved items price drop by 20 percent of original price.");
        System.out.println();
        System.out.println("Thank you..!!");

        Date alarmTime = calendar.getTime();

        _timer = new Timer();
        _timer.schedule(new AlarmTask(), alarmTime);
    }

    class AlarmTask extends TimerTask {
        /**
         * Called on a background thread by Timer
         */
        public void run(){
            // Do your work here; it's 10:30 AM!
        	
        	ZapposChallenge2 obj = new ZapposChallenge2();
        	try {
				obj.checkPrice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            // If you don't want the alarm to go off again
            // tomorrow (etc), cancel the timer
            //timer.cancel();
        	
        }
    }

}



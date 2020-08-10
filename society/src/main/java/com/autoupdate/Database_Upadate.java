package com.autoupdate;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Database_Upadate {
	public static void schedule()
	{
		try {
			
			JobDetail job1=JobBuilder.newJob(Database_day1.class)
					.withIdentity("job1", "group1").build();
			Trigger trigger1=TriggerBuilder.newTrigger()
					.withIdentity("cronTrigger1", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("* * * 1 * ? *"))
					.build();
			Scheduler scheduler1=new StdSchedulerFactory().getScheduler();
			scheduler1.start();
			scheduler1.scheduleJob(job1, trigger1); // MONTHTOYEAR and COPY
			
			JobDetail job2=JobBuilder.newJob(Temp.class)
					.withIdentity("job2", "group2").build();
			Trigger trigger2=TriggerBuilder.newTrigger()
					.withIdentity("cronTrigger2", "group2")
					.withSchedule(CronScheduleBuilder.cronSchedule("* * * 6,7,8,9,10 * ? *"))
					.build();
			Scheduler scheduler2=new StdSchedulerFactory().getScheduler();
			scheduler2.start();
			scheduler2.scheduleJob(job2, trigger2); // CHANGE STATUS , SEND MAILS and UPDATE MAIL
			
			JobDetail job3=JobBuilder.newJob(Database_Upadate_Day11.class)
					.withIdentity("job3", "group3").build();
			Trigger trigger3=TriggerBuilder.newTrigger()
					.withIdentity("cronTrigger3", "group3")
					.withSchedule(CronScheduleBuilder.cronSchedule("* * * 11 * ? *"))
					.build();
			Scheduler scheduler3=new StdSchedulerFactory().getScheduler();
			scheduler3.start();
			scheduler3.scheduleJob(job3, trigger3); // UPDATE LATE FEE
			
			//Thread.sleep(10000);
			scheduler1.shutdown();
			scheduler2.shutdown();
			scheduler3.shutdown();

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws SchedulerException {
		//schedule();
	}

}

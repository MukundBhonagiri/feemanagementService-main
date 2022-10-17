package com.wrightapps.smartedu.feemanagementservice.config;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wrightapps.smartedu.feemanagementservice.entity.FeesTemplate;
import com.wrightapps.smartedu.feemanagementservice.entity.StudentFeeTemplate;
import com.wrightapps.smartedu.feemanagementservice.repository.FeesManagementRepository;
import com.wrightapps.smartedu.feemanagementservice.repository.StudentFeeRepository;
import com.wrightapps.smartedu.feemanagementservice.service.impl.FeesManagementServiceImpl;

@Component
public class SchedulerConfig {
	
	private static final Logger log = LogManager.getLogger(FeesManagementServiceImpl.class);

	FeesManagementRepository fmRepo;
	StudentFeeRepository studentFmRepo;

	@Autowired
	public SchedulerConfig(FeesManagementRepository fmRepo, StudentFeeRepository studentFmRepo) {
		this.fmRepo = fmRepo;
		this.studentFmRepo = studentFmRepo;
	}

	@Scheduled(cron = "0 * 10 * * ?")
	public void scheduler() {
		List<Integer> feesIds = new ArrayList<>();

		List<FeesTemplate> fees = (List<FeesTemplate>) fmRepo.findAll();
		fees.forEach(feeDetails -> {
			String dueDate = feeDetails.getDueDate() + " " + LocalDate.now().getYear() + "";
			LocalDate loclDueDate = this.convertDDMMMFormatToLocalDate(dueDate);
			long daysBetween = Duration.between(loclDueDate, LocalDate.now()).toDays();
			if (daysBetween <= 15) {
				feesIds.add(feeDetails.getFeesId());
			}
		});

		feesIds.forEach(feesId -> {
			List<StudentFeeTemplate> studentFees = studentFmRepo
					.findByFeesIdAndPaidAndReceiptNumberAndNotificationSent(feesId, false, null, false);
			
			log.info("student fees list:: " + studentFees.size());
		});
	}

	public LocalDate convertDDMMMFormatToLocalDate(String date) {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("YYYY-MM-dd").toFormatter(Locale.US);
		System.out.println(formatter.parse(date));
		return (LocalDate) formatter.parse(date);
	}
}

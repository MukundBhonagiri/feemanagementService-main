package com.wrightapps.smartedu.feemanagementservice.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrightapps.smartedu.feemanagementservice.entity.FeesTemplate;
import com.wrightapps.smartedu.feemanagementservice.entity.StudentFeeTemplate;
import com.wrightapps.smartedu.feemanagementservice.kafka.KafkaSender;
import com.wrightapps.smartedu.feemanagementservice.model.FeePayDetails;
import com.wrightapps.smartedu.feemanagementservice.model.FeeTemplateVo;
import com.wrightapps.smartedu.feemanagementservice.repository.FeesManagementRepository;
import com.wrightapps.smartedu.feemanagementservice.repository.StudentFeeRepository;
import com.wrightapps.smartedu.feemanagementservice.service.FeesManagementService;

@Service
public class FeesManagementServiceImpl implements FeesManagementService {

	private static final Logger log = LogManager.getLogger(FeesManagementServiceImpl.class);

	FeesManagementRepository fmRepo;
	StudentFeeRepository studentFmRepo;

	@Autowired
	private KafkaSender kafkaSender;

	@Value("${kafka.feeManagement.topic}")
	private String fees_template_v1;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	public FeesManagementServiceImpl(FeesManagementRepository fmRepo, StudentFeeRepository studentFmRepo) {
		this.fmRepo = fmRepo;
		this.studentFmRepo = studentFmRepo;
	}

	@Override
	public List<FeesTemplate> saveFeeManagementDetails(FeeTemplateVo templateVo) {
		List<FeesTemplate> feeTemplateList = new ArrayList<>();
		templateVo.getPayTerm().forEach(fee -> {
			FeesTemplate feesTemplate = new FeesTemplate();

			feesTemplate = convertTemplateVotoTemplate(templateVo, fee, feesTemplate);
			fmRepo.save(feesTemplate);
			try {
				kafkaSender.send(fees_template_v1, feesTemplate.getFeesId().toString(),
						mapper.writeValueAsString(feesTemplate));

			} catch (Exception e) {
				log.error("Exception occurred while sending message to kafak topic: " + e.getMessage());
			}

			feeTemplateList.add(feesTemplate);
		});
		return feeTemplateList;
	}

	@Override
	public List<StudentFeeTemplate> getFeeManagementDetails(Integer studentId) {
		return (List<StudentFeeTemplate>) studentFmRepo.findByStudentId(studentId);

	}

	@Override
	public void updateFeeManagementDetails(FeeTemplateVo templateVo) {
		String updateStatus = "";

		FeesTemplate feesTemplate = fmRepo.findByFeesId(templateVo.getFeesId());
		for (FeePayDetails template : templateVo.getPayTerm()) {
			log.info("template.getToPay():: " + template.getToPay() + ":: fee.getToPay():: " + feesTemplate.getToPay());
			if (template.getToPay() != feesTemplate.getToPay()) {
				updateStatus = "PayChange";
			}
			if (!(template.getPayFrequency().equalsIgnoreCase(feesTemplate.getPayFrequency())
					&& template.getTerm().equalsIgnoreCase(feesTemplate.getTerm())
					// && template.getDueDate() == fee.getDueDate()
					&& templateVo.getFeesCategory().equalsIgnoreCase(feesTemplate.getFeesCategory())
					&& templateVo.getFeesDescription().equalsIgnoreCase(feesTemplate.getFeesDescription())
					&& templateVo.getGradeIds().equalsIgnoreCase(feesTemplate.getGradeIds()))) {
				log.info("template.getPayFrequency():: " + template.getPayFrequency() + ":: fee.getPayFrequency():: "
						+ feesTemplate.getPayFrequency());
				log.info("template.getTerm():: " + template.getTerm() + ":: fee.getTerm():: " + feesTemplate.getTerm());
				log.info("template.getDueDate():: " + template.getDueDate() + ":: fee.getDueDate():: "
						+ feesTemplate.getDueDate());
				log.info("template.getFeesCategory():: " + templateVo.getFeesCategory() + ":: fee.getFeesCategory():: "
						+ feesTemplate.getFeesCategory());
				log.info("template.getGradeIds():: " + templateVo.getGradeIds() + ":: fee.getGradeIds():: "
						+ feesTemplate.getGradeIds());
				log.info("template.getToPay():: " + templateVo.getNotifyBefore() + ":: fee.getToPay():: "
						+ feesTemplate.getNotifyBefore());
				updateStatus = "OtherChange";
			}
			if ("PayChange".equalsIgnoreCase(updateStatus)) {
				// update the fee template and student fee template
				feesTemplate.setToPay(template.getToPay());
				fmRepo.save(feesTemplate);
				List<StudentFeeTemplate> studentFeeTemplates = studentFmRepo.findByFeesId(templateVo.getFeesId());
				studentFeeTemplates.forEach(student -> {
					student.setToPay(template.getToPay());
					studentFmRepo.save(student);
				});
				log.info("entered to if block");
			} else if ("OtherChange".equalsIgnoreCase(updateStatus)) {
				log.info("entered to else block");
				StudentFeeTemplate studentFeeTemplate = new StudentFeeTemplate();
				// update the template and delete all records in student template and recreate
				FeesTemplate feesTemplate2 = this.convertTemplateVotoTemplate(templateVo, template, feesTemplate);
				fmRepo.save(feesTemplate2);
				studentFmRepo.deleteByFeesId(templateVo.getFeesId());
				studentFeeTemplate = this.convertTemplateVotoStudentTemplate(templateVo, template, studentFeeTemplate);
				studentFmRepo.save(studentFeeTemplate);
			}
		}
	}

	@Override
	public void deleteFeeManagementDetails(Integer feesId) {
		FeesTemplate fee = fmRepo.findByFeesId(feesId);
		fee.setActive(false);
		fmRepo.save(fee);

		List<StudentFeeTemplate> studentFees = studentFmRepo.findByFeesId(feesId);
		studentFees.forEach(student -> {
			if (!student.isPaid()) {
				studentFmRepo.deleteById(student.getId());
			}
		});

	}

	private FeesTemplate convertTemplateVotoTemplate(FeeTemplateVo templateVo, FeePayDetails fee,
			FeesTemplate feesTemplate) {
		String date = convertLocalDateToDDMMMFormat(fee.getDueDate());
		UUID tenantId= UUID.randomUUID();
		UUID schoolId= UUID.randomUUID();
		UUID createdBy= UUID.randomUUID();
		
		feesTemplate.setTenantId(tenantId);
		feesTemplate.setSchoolId(schoolId);
		feesTemplate.setFeesCategory(templateVo.getFeesCategory());
		feesTemplate.setFeesDescription(templateVo.getFeesDescription());
		feesTemplate.setApplicableTo(templateVo.getApplicableTo());
		feesTemplate.setGradeIds(templateVo.getGradeIds());
		feesTemplate.setCreatedBy(createdBy);
		feesTemplate.setAllowPartialPay(templateVo.isAllowPartialPay());
		feesTemplate.setValidity(convertLocalDateToYYYYFormat(templateVo.getValidity()));
		feesTemplate.setCurrency(templateVo.getCurrency());
		feesTemplate.setNotifyBefore(templateVo.getNotifyBefore());

		feesTemplate.setTerm(fee.getTerm());
		// feesTemplate.setTermId(termId);
		feesTemplate.setDueDate(date);
		feesTemplate.setToPay(fee.getToPay());
		feesTemplate.setPayFrequency(fee.getPayFrequency());
		return feesTemplate;
	}

	private StudentFeeTemplate convertTemplateVotoStudentTemplate(FeeTemplateVo templateVo, FeePayDetails fee,
			StudentFeeTemplate studentFeeTemplate) {

		String date = convertLocalDateToDDMMMFormat(fee.getDueDate());
		studentFeeTemplate.setAcademicYear(templateVo.getTenantId());
		studentFeeTemplate.setFeesCategory(templateVo.getFeesCategory());
		studentFeeTemplate.setStudentId(null);
		studentFeeTemplate.setPaid(false);
		studentFeeTemplate.setPaidDate(null);
		studentFeeTemplate.setPaymentMode(null);
		studentFeeTemplate.setReceiptNumber(null);
		studentFeeTemplate.setReferenceNo(null);
		studentFeeTemplate.setNotificationSent(false);
		studentFeeTemplate.setPayTerm(fee.getTerm());
		studentFeeTemplate.setDueDate(date);
		studentFeeTemplate.setToPay(fee.getToPay());

		return studentFeeTemplate;
	}

	public String convertLocalDateToDDMMMFormat(LocalDate date) {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("dd MMM").toFormatter(Locale.US);
		System.out.println(formatter.format(date));
		return formatter.format(date);
	}

	public String convertLocalDateToYYYYFormat(LocalDate date) {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("YYYY").toFormatter(Locale.US);
		System.out.println(formatter.format(date));
		return formatter.format(date);
	}

}

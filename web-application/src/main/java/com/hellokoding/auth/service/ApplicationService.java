package com.hellokoding.auth.service;

import com.hellokoding.auth.common.Defs;
import com.hellokoding.auth.common.ErrorCodes;
import com.hellokoding.auth.common.ServiceError;
import com.hellokoding.auth.common.Utils;
import com.hellokoding.auth.model.dto.ApplicationInfo;
import com.hellokoding.auth.model.dto.OnlineApplicationInfo;
import com.hellokoding.auth.model.dto.EnrolledApplication;
import com.hellokoding.auth.model.dto.EnrolledApplicationSearchCriteria;
import com.hellokoding.auth.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicationService implements IApplicationService {

    @Autowired
    private BaseRepository repository;

    @Override
    public OnlineApplicationInfo findByUuid(String uuid) {
        if(Utils.isEmpty(uuid)) {
            return null;
        }
        String sql = "SELECT o FROM OnlineApplicationInfo o WHERE uuid = :uuid ";
        Map<String,Object> param = new HashMap<>();
        param.put("uuid",uuid);
        com.hellokoding.auth.model.db.OnlineApplicationInfo applicationInfoEO =  repository.findSingleResult(sql, com.hellokoding.auth.model.db.OnlineApplicationInfo.class,param);
        return new OnlineApplicationInfo(applicationInfoEO);
    }

    @Override
    public boolean isValidUUID(String uuid) {
        if(Utils.isEmpty(uuid)) {
            return false;
        }

        String sql = "SELECT count(o.id) FROM application_info_enroll o WHERE o.uuid =:uuid";
        Map<String,Object> param = new HashMap<>();
        param.put("uuid",uuid);
        Integer count = repository.findCountByNativeQuery(sql,param);
        if(count >0) {
            return true;
        }
        return false;
    }

    @Override
    public Object save(OnlineApplicationInfo applicationInfo) {
        if(applicationInfo == null) {
            return new ServiceError(ErrorCodes.ERROR_CUSTOM,"Application info should not be empty!");
        }
        if(Utils.isNull(applicationInfo.getId())) {
            if(applicationInfo.getPhoto().length/1024 > 100) {
                return new ServiceError(ErrorCodes.ERROR_CUSTOM,"Photo size should be maximum 100KB!");
            }
            if(applicationInfo.getSignature().length/1024 >100) {
                return new ServiceError(ErrorCodes.ERROR_CUSTOM,"Signature size should be maximum 100KB!");
            }
        }
        if(Utils.isEmpty(applicationInfo.getNameEn())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"applicant name english");
        }
        if(Utils.isEmpty(applicationInfo.getFatherEn())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"father name english");
        }
        if(Utils.isEmpty(applicationInfo.getGender())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"gender");
        }
        if(Utils.isEmpty(applicationInfo.getDateOfBirth())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"date of birth");
        }
        if(Utils.isEmpty(applicationInfo.getApplicationType())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"application type");
        }
        if(applicationInfo.getInstallmentPaid() == null) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"installment paid");
        }
        if(Utils.isEmpty(applicationInfo.getBankTransactionNumber())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"Bank transaction number");
        }
        if(Utils.isEmpty(applicationInfo.getAuthorityCode())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"authority code");
        }
        if(Utils.isEmpty(applicationInfo.getCircleCode())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"circle code");
        }
        if(Utils.isEmpty(applicationInfo.getContactNumber())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"contact number");
        }
        if(Utils.isEmpty(applicationInfo.getEmergencyName())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"emergency name");
        }
        if(Utils.isEmpty(applicationInfo.getEmergencyNumber())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"emergency contact name");
        }
        if(Utils.isEmpty(applicationInfo.getPresentAddressLine())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"present address line");
        }
        if(Utils.isEmpty(applicationInfo.getPermanentAddressLine())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"permanent address line");
        }
        if(Utils.isEmpty(applicationInfo.getNid())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"NID/Birth Certificate");
        }
        if(Utils.isNull(applicationInfo.getId())) {
            if (applicationInfo.getPhoto() == null || applicationInfo.getPhoto().length < 4) {
                return new ServiceError(ErrorCodes.ERROR_MISSING, "photo");
            }
            if (applicationInfo.getSignature() == null || applicationInfo.getSignature().length < 4) {
                return new ServiceError(ErrorCodes.ERROR_MISSING, "signature");
            }
        }
        if(!Utils.isInList(applicationInfo.getInstallmentPaid(),0,1,2,3,4)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"installment paid");
        }
        if(Utils.isEmpty(Defs.APPLICATION_TYPE_MAP.get(applicationInfo.getApplicationType()))) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"application type");
        } else {
            applicationInfo.setApplicationType(Defs.APPLICATION_TYPE_MAP.get(applicationInfo.getApplicationType()));
        }

        if(Utils.isEmpty(Defs.GENDER_MAP.get(applicationInfo.getGender()))) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"gender");
        }

        if(!Utils.isValidLength(applicationInfo.getAuthorityCode(),50)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"office code");
        }

        if(!Utils.isValidLength(applicationInfo.getCircleCode(),2)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"circle code");
        }

        if(!Utils.isValidLength(applicationInfo.getNameEn(),100)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"applicant name english");
        }

        if(!Utils.isValidLength(applicationInfo.getNameBn(),150)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"applicant name bangla");
        }

        if(!Utils.isValidLength(applicationInfo.getFatherEn(),100)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"father name english");
        }

        if(!Utils.isValidLength(applicationInfo.getFatherBn(),100)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"father name bangla");
        }

        if(!Utils.isValidLength(applicationInfo.getSpouseEn(),100)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"spouse name english");
        }
        if(!Utils.isValidLength(applicationInfo.getSpouseBn(),100)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"spouse name bangla");
        }

        if(!Utils.isValidLength(applicationInfo.getNid(),20)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"nid number");
        }

        if(!Utils.isValidLength(applicationInfo.getContactNumber(),11)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"contact number");
        }

        if(!Utils.isValidLength(applicationInfo.getEmergencyNumber(),11)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"emergency contact number");
        }

        if(!Utils.isValidLength(applicationInfo.getBankTransactionNumber(),30)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"bank transaction number");
        }

        com.hellokoding.auth.model.db.Authority authorityEO = repository.findSingleByField(com.hellokoding.auth.model.db.Authority.class,"code",applicationInfo.getAuthorityCode());
        if(authorityEO == null || Utils.isEmpty(authorityEO.getCode())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"authority code");
        }

        if(Utils.getInteger(applicationInfo.getCircleCode()) <1 || Utils.getInteger(applicationInfo.getCircleCode()) >10) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"circle code");
        }

        if(Utils.getDateFromString(applicationInfo.getDateOfBirth()) == null) {
            return new ServiceError(ErrorCodes.ERROR_CUSTOM,"Invalid format for date of birth. Required format is:"+Defs.DATE_FORMAT);
        }

        if(!Utils.isEmpty(applicationInfo.getPresentDivision()) && !Utils.isValidDivision(applicationInfo.getPresentDivision())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"present division");
        }
        if(!Utils.isEmpty(applicationInfo.getPresentDistrict()) && !Utils.isValidDistrict(applicationInfo.getPresentDistrict())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"present district");
        }

        if(!Utils.isEmpty(applicationInfo.getPresentUpozila()) && !Utils.isValidThana(applicationInfo.getPresentUpozila())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"present upozila");
        }

        if(!Utils.isEmpty(applicationInfo.getPermanentDivision()) && !Utils.isValidDivision(applicationInfo.getPermanentDivision())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"permanent division");
        }

        if(!Utils.isEmpty(applicationInfo.getPermanentDistrict()) && !Utils.isValidDistrict(applicationInfo.getPermanentDistrict())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"permanent district");
        }

        if(!Utils.isEmpty(applicationInfo.getPermanentUpozila()) && !Utils.isValidThana(applicationInfo.getPermanentUpozila())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"permanent upozila");
        }

        applicationInfo.setUuid(Utils.getUUID(applicationInfo.getDateOfBirth()));

        com.hellokoding.auth.model.db.OnlineApplicationInfo applicationInfoEO = null;


        if(!Utils.isNull(applicationInfo.getId())) {
            applicationInfoEO = repository.findById(com.hellokoding.auth.model.db.OnlineApplicationInfo.class,applicationInfo.getId());
            if(applicationInfoEO == null || Utils.isNull(applicationInfoEO.getId())) {
                return new ServiceError(ErrorCodes.ERROR_INVALID,"application id");
            }
        } else {
            applicationInfoEO = new com.hellokoding.auth.model.db.OnlineApplicationInfo();
        }
        if((applicationInfo.getPhoto() == null || applicationInfo.getPhoto().length <=4) && applicationInfoEO != null) {
            applicationInfo.setPhoto(applicationInfoEO.getPhoto());
        }
        if((applicationInfo.getSignature() == null || applicationInfo.getSignature().length <=4) && applicationInfoEO != null) {
            applicationInfo.setSignature(applicationInfoEO.getSignature());
        }
        prepareData(applicationInfo,applicationInfoEO);
        if(applicationInfoEO.getId() == null) {
            return new OnlineApplicationInfo(repository.persist(applicationInfoEO));
        } else {
            return new OnlineApplicationInfo(repository.update(applicationInfoEO));
        }
    }

    @Override
    public OnlineApplicationInfo findByContactAndDateOfBirth(String contactNumber, String dateOfBirth) {
        String sql = "SELECT o.* FROM application_info_enroll o WHERE o.contact_number = '"+contactNumber.trim()+"' AND date_trunc('day', o.date_of_birth) = '"+Utils.getStringDate(dateOfBirth,"yyyy-MM-dd")+"' ";
        com.hellokoding.auth.model.db.OnlineApplicationInfo applicationInfoEO = repository.findSingleResultByNativeQuery(sql, com.hellokoding.auth.model.db.OnlineApplicationInfo.class,null);
        return new OnlineApplicationInfo(applicationInfoEO);
    }


    private void prepareData(OnlineApplicationInfo applicationInfo, com.hellokoding.auth.model.db.OnlineApplicationInfo applicationInfoEO) {
        if(applicationInfo != null && applicationInfoEO != null) {
            applicationInfoEO.setId(applicationInfo.getId());
            applicationInfoEO.setUuid(applicationInfo.getUuid());
            applicationInfoEO.setNameEn(applicationInfo.getNameEn());
            applicationInfoEO.setNameBn(applicationInfo.getNameBn());
            applicationInfoEO.setFatherEn(applicationInfo.getFatherEn());
            applicationInfoEO.setFatherBn(applicationInfo.getFatherBn());
            applicationInfoEO.setGender(applicationInfo.getGender());
            applicationInfoEO.setDateOfBirth(Utils.getDateFromString(applicationInfo.getDateOfBirth()));
            applicationInfoEO.setMaritalStatus(applicationInfo.getMaritalStatus());
            applicationInfoEO.setBloodGroup(applicationInfo.getBloodGroup());
            applicationInfoEO.setSpouseEn(applicationInfo.getSpouseEn());
            applicationInfoEO.setSpouseBn(applicationInfo.getSpouseBn());
            applicationInfoEO.setApplicationType(applicationInfo.getApplicationType());
            applicationInfoEO.setNumberOfEmi(4);
            applicationInfoEO.setNumberOfEmiPaid(applicationInfo.getInstallmentPaid());
            applicationInfoEO.setBankTransactionNumber(applicationInfo.getBankTransactionNumber());
            applicationInfoEO.setOfficeCode(applicationInfo.getAuthorityCode());
            applicationInfoEO.setCircleCode(applicationInfo.getCircleCode());
            applicationInfoEO.setContactNumber(applicationInfo.getContactNumber());
            applicationInfoEO.setEmergencyContact(applicationInfo.getContactNumber());
            applicationInfoEO.setEmergencyName(applicationInfo.getEmergencyName());
            applicationInfoEO.setEmergencyRelationship(applicationInfo.getEmergencyRelation());
            applicationInfoEO.setPresentDivision(applicationInfo.getPresentDivision());
            applicationInfoEO.setPresentDistrict(applicationInfo.getPresentDistrict());
            applicationInfoEO.setPresentThana(applicationInfo.getPresentUpozila());
            applicationInfoEO.setPresentAddressLine(applicationInfo.getPresentAddressLine());
            applicationInfoEO.setPermanentDivision(applicationInfo.getPermanentDivision());
            applicationInfoEO.setPermanentDistrict(applicationInfo.getPermanentDistrict());
            applicationInfoEO.setPermanentThana(applicationInfo.getPermanentUpozila());
            applicationInfoEO.setPermanentAddressLine(applicationInfo.getPermanentAddressLine());
            applicationInfoEO.setPhoto(applicationInfo.getPhoto());
            applicationInfoEO.setSignature(applicationInfo.getSignature());
            applicationInfoEO.setStatus(1);
            applicationInfoEO.setNid(applicationInfo.getNid());
            applicationInfoEO.setCreatedDate(Utils.getCurrentTimeStamp());
            applicationInfoEO.setUpdatedDate(Utils.getCurrentTimeStamp());
            applicationInfoEO.setUploadedDate(Utils.getCurrentTimeStamp());
        }
    }

    @Override
    public OnlineApplicationInfo findById(Integer id) {
        com.hellokoding.auth.model.db.OnlineApplicationInfo applicationInfoEO = repository.findById(com.hellokoding.auth.model.db.OnlineApplicationInfo.class,id);
        return new OnlineApplicationInfo(applicationInfoEO);
    }

    @Override
    public ApplicationInfo findByApplicationInfoId(Integer id) {
        com.hellokoding.auth.model.db.ApplicationInfo applicationInfoEO = repository.findById(com.hellokoding.auth.model.db.ApplicationInfo.class,id);
        return new ApplicationInfo(applicationInfoEO);
    }

    @Override
    public List<EnrolledApplication> searchEnrolledApplication(EnrolledApplicationSearchCriteria criteria) {
        List<EnrolledApplication> toBeReturn = new ArrayList<>();
        String sql = "SELECT id,name_en,uuid,contact_number,number_of_emi_paid,emergency_contact,office_code,application_type FROM application_info_enroll WHERE 1=1 ";
        Map<String,Object> param = new HashMap<>();
        if(criteria != null) {
            if(!Utils.isEmpty(criteria.getName())) {
                sql += " AND UPPER(name_en) LIKE :nameEn ";
                param.put("nameEn","%"+criteria.getName().trim().toUpperCase()+"%");
            }
            if(!Utils.isEmpty(criteria.getContactNumber())) {
                sql += " AND contact_number =:contactNumber ";
                param.put("contactNumber",criteria.getContactNumber().trim());
            }
            if(!Utils.isEmpty(criteria.getFatherName())) {
                sql += " AND UPPER(father_en) LIKE :fatherEn ";
                param.put("fatherEn","%"+criteria.getFatherName().trim().toUpperCase()+"%");
            }

            if(!Utils.isEmpty(criteria.getAuthorityCode())) {
                sql += " AND UPPER(OFFICE_CODE) =:authorityCode ";
                param.put("authorityCode",criteria.getAuthorityCode().trim().toUpperCase());
            }

            if(criteria.getInstallmentPaid() != null) {
                sql += "AND number_of_emi_paid = :installmentPaid ";
                param.put("installmentPaid",criteria.getInstallmentPaid());
            }
        }

        List<Object[]> results = repository.findByNativeQuery(sql,param);
        if(results != null && results.size() >0) {
            results.stream().filter(r-> r != null).forEach(r->{
                toBeReturn.add(new EnrolledApplication(r));
            });
        }

        return toBeReturn;
    }

    @Override
    public List<EnrolledApplication> searchUplodedApplication(EnrolledApplicationSearchCriteria criteria) {
        String sql = "SELECT id,name_en,CAST ( '' AS varchar(1) ),contact_number,number_of_emi_paid,emergency_contact,office_code,application_type FROM application_info WHERE 1=1 ";
        List<EnrolledApplication> toBeReturn = new ArrayList<>();
        Map<String,Object> param = new HashMap<>();
        if(criteria != null) {
            if(!Utils.isEmpty(criteria.getName())) {
                sql += " AND UPPER(name_en) LIKE :nameEn ";
                param.put("nameEn","%"+criteria.getName().trim().toUpperCase()+"%");
            }
            if(!Utils.isEmpty(criteria.getContactNumber())) {
                sql += " AND contact_number =:contactNumber ";
                param.put("contactNumber",criteria.getContactNumber().trim());
            }
            if(!Utils.isEmpty(criteria.getFatherName())) {
                sql += " AND UPPER(father_en) LIKE :fatherEn ";
                param.put("fatherEn","%"+criteria.getFatherName().trim().toUpperCase()+"%");
            }

            if(!Utils.isEmpty(criteria.getAuthorityCode())) {
                sql += " AND UPPER(OFFICE_CODE) =:authorityCode ";
                param.put("authorityCode",criteria.getAuthorityCode().trim().toUpperCase());
            }

            if(criteria.getInstallmentPaid() != null) {
                sql += "AND number_of_emi_paid = :installmentPaid ";
                param.put("installmentPaid",criteria.getInstallmentPaid());
            }
        }

        List<Object[]> results = repository.findByNativeQuery(sql,param);
        if(results != null && results.size() >0) {
            results.stream().filter(r-> r != null).forEach(r->{
                toBeReturn.add(new EnrolledApplication(r));
            });
        }

        return toBeReturn;
    }

    @Override
    public Object updateEnrolledApplication(ApplicationInfo applicationInfo) {
        if(applicationInfo == null) {
            return new ServiceError(ErrorCodes.ERROR_CUSTOM,"Application info should not be empty!");
        }
        if(Utils.isNull(applicationInfo.getId())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"application id");
        }
        if(Utils.isNull(applicationInfo.getId())) {
            if(applicationInfo.getPhoto().length/1024 > 100) {
                return new ServiceError(ErrorCodes.ERROR_CUSTOM,"Photo size should be maximum 100KB!");
            }
            if(applicationInfo.getSignature().length/1024 >100) {
                return new ServiceError(ErrorCodes.ERROR_CUSTOM,"Signature size should be maximum 100KB!");
            }
        }

        if(Utils.isEmpty(applicationInfo.getReferenceNumber())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"reference number");
        }

        if(Utils.isEmpty(applicationInfo.getNameEn())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"applicant name english");
        }
        if(Utils.isEmpty(applicationInfo.getFatherEn())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"father name english");
        }
        if(Utils.isEmpty(applicationInfo.getGender())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"gender");
        }
        if(Utils.isEmpty(applicationInfo.getDateOfBirth())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"date of birth");
        }
        if(Utils.isEmpty(applicationInfo.getApplicationType())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"application type");
        }
        if(applicationInfo.getNumberOfEmiPaid() == null) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"installment paid");
        }
        if(Utils.isEmpty(applicationInfo.getBankTransactionNumber())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"Bank transaction number");
        }
        if(Utils.isEmpty(applicationInfo.getOfficeCode())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"authority code");
        }
        if(Utils.isEmpty(applicationInfo.getCircleCode())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"circle code");
        }
        if(Utils.isEmpty(applicationInfo.getContactNumber())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"contact number");
        }
        if(Utils.isEmpty(applicationInfo.getEmergencyName())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"emergency name");
        }
        if(Utils.isEmpty(applicationInfo.getEmergencyContact())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"emergency contact number");
        }
        if(Utils.isEmpty(applicationInfo.getPresentAddressLine())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"present address line");
        }
        if(Utils.isEmpty(applicationInfo.getPermanentAddressLine())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"permanent address line");
        }
        if(Utils.isEmpty(applicationInfo.getNid())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"NID/Birth Certificate");
        }
        if(Utils.isNull(applicationInfo.getId())) {
            if (applicationInfo.getPhoto() == null || applicationInfo.getPhoto().length < 4) {
                return new ServiceError(ErrorCodes.ERROR_MISSING, "photo");
            }
            if (applicationInfo.getSignature() == null || applicationInfo.getSignature().length < 4) {
                return new ServiceError(ErrorCodes.ERROR_MISSING, "signature");
            }
        }
        if(!Utils.isInList(applicationInfo.getNumberOfEmiPaid(),0,1,2,3,4)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"installment paid");
        }
        if(Utils.isEmpty(Defs.APPLICATION_TYPE_MAP.get(applicationInfo.getApplicationType()))) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"application type");
        } else {
            applicationInfo.setApplicationType(Defs.APPLICATION_TYPE_MAP.get(applicationInfo.getApplicationType()));
        }

        if(Utils.isEmpty(Defs.GENDER_MAP.get(applicationInfo.getGender()))) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"gender");
        }

        if(!Utils.isValidLength(applicationInfo.getOfficeCode(),50)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"office code");
        }

        if(!Utils.isValidLength(applicationInfo.getCircleCode(),2)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"circle code");
        }

        if(!Utils.isValidLength(applicationInfo.getNameEn(),100)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"applicant name english");
        }

        if(!Utils.isValidLength(applicationInfo.getNameBn(),150)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"applicant name bangla");
        }

        if(!Utils.isValidLength(applicationInfo.getFatherEn(),100)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"father name english");
        }

        if(!Utils.isValidLength(applicationInfo.getFatherBn(),100)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"father name bangla");
        }

        if(!Utils.isValidLength(applicationInfo.getSpouseEn(),100)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"spouse name english");
        }
        if(!Utils.isValidLength(applicationInfo.getSpouseBn(),100)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"spouse name bangla");
        }

        if(!Utils.isValidLength(applicationInfo.getNid(),20)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"nid number");
        }

        if(!Utils.isValidLength(applicationInfo.getContactNumber(),11)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"contact number");
        }

        if(!Utils.isValidLength(applicationInfo.getEmergencyContact(),11)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"emergency contact number");
        }

        if(!Utils.isValidLength(applicationInfo.getBankTransactionNumber(),30)) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"bank transaction number");
        }

        com.hellokoding.auth.model.db.Authority authorityEO = repository.findSingleByField(com.hellokoding.auth.model.db.Authority.class,"code",applicationInfo.getOfficeCode());
        if(authorityEO == null || Utils.isEmpty(authorityEO.getCode())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"authority code");
        }

        if(Utils.getInteger(applicationInfo.getCircleCode()) <1 || Utils.getInteger(applicationInfo.getCircleCode()) >10) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"circle code");
        }

        if(Utils.getDateFromString(applicationInfo.getDateOfBirth()) == null) {
            return new ServiceError(ErrorCodes.ERROR_CUSTOM,"Invalid format for date of birth. Required format is:"+Defs.DATE_FORMAT);
        }

        if(!Utils.isEmpty(applicationInfo.getPresentDivision()) && !Utils.isValidDivision(applicationInfo.getPresentDivision())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"present division");
        }
        if(!Utils.isEmpty(applicationInfo.getPresentDistrict()) && !Utils.isValidDistrict(applicationInfo.getPresentDistrict())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"present district");
        }

        if(!Utils.isEmpty(applicationInfo.getPresentThana()) && !Utils.isValidThana(applicationInfo.getPresentThana())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"present upozila");
        }

        if(!Utils.isEmpty(applicationInfo.getPermanentDivision()) && !Utils.isValidDivision(applicationInfo.getPermanentDivision())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"permanent division");
        }

        if(!Utils.isEmpty(applicationInfo.getPermanentDistrict()) && !Utils.isValidDistrict(applicationInfo.getPermanentDistrict())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"permanent district");
        }

        if(!Utils.isEmpty(applicationInfo.getPermanentThana()) && !Utils.isValidThana(applicationInfo.getPermanentThana())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"permanent upozila");
        }

        com.hellokoding.auth.model.db.ApplicationInfo applicationInfoEO = repository.findById(com.hellokoding.auth.model.db.ApplicationInfo.class,applicationInfo.getId());
        if(applicationInfoEO == null || Utils.isNull(applicationInfoEO.getId())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"application id");
        }

        if(!Utils.isEmpty(applicationInfo.getOfficeCode())) {
            applicationInfoEO.setOfficeCode(applicationInfo.getOfficeCode());
        }

        if(!Utils.isEmpty(applicationInfo.getNameEn())) {
            applicationInfoEO.setNameEn(applicationInfo.getNameEn());
        }
        if(!Utils.isEmpty(applicationInfo.getNameBn())) {
            applicationInfoEO.setNameBn(applicationInfo.getNameBn());
        }

        if(!Utils.isEmpty(applicationInfo.getFatherEn())) {
            applicationInfoEO.setFatherEn(applicationInfo.getFatherEn());
        }
        if(!Utils.isEmpty(applicationInfo.getFatherBn())) {
            applicationInfoEO.setFatherBn(applicationInfo.getFatherBn());
        }
        if(!Utils.isEmpty(applicationInfo.getSpouseEn())) {
            applicationInfoEO.setSpouseEn(applicationInfo.getSpouseEn());
        }
        if(!Utils.isEmpty(applicationInfo.getSpouseBn())) {
            applicationInfoEO.setSpouseBn(applicationInfo.getSpouseBn());
        }
        if(!Utils.isEmpty(applicationInfo.getDateOfBirth())) {
            applicationInfoEO.setDateOfBirth(Utils.getDateFromString(applicationInfo.getDateOfBirth()));
        }
        if(!Utils.isEmpty(applicationInfo.getNid())) {
            applicationInfoEO.setNid(applicationInfo.getNid());
        }
        if(!Utils.isEmpty(applicationInfo.getContactNumber())) {
            applicationInfoEO.setContactNumber(applicationInfo.getContactNumber());
        }
        if(!Utils.isEmpty(applicationInfo.getEmergencyContact())) {
            applicationInfoEO.setEmergencyContact(applicationInfo.getEmergencyContact());
        }
        if(!Utils.isEmpty(applicationInfo.getPresentDivision())) {
            applicationInfoEO.setPresentDivision(applicationInfo.getPresentDivision());
        }
        if(!Utils.isEmpty(applicationInfo.getPresentDistrict())) {
            applicationInfoEO.setPresentDistrict(applicationInfo.getPresentDistrict());
        }
        if(!Utils.isEmpty(applicationInfo.getPresentThana())) {
            applicationInfoEO.setPresentThana(applicationInfo.getPresentThana());
        }
        if(!Utils.isEmpty(applicationInfo.getPresentAddressLine())) {
            applicationInfoEO.setPresentAddressLine(applicationInfo.getPresentAddressLine());
        }
        if(!Utils.isEmpty(applicationInfo.getPermanentDivision())) {
            applicationInfoEO.setPermanentDivision(applicationInfo.getPermanentDivision());
        }
        if(!Utils.isEmpty(applicationInfo.getPermanentDistrict())) {
            applicationInfoEO.setPermanentDistrict(applicationInfo.getPermanentDistrict());
        }
        if(!Utils.isEmpty(applicationInfo.getPermanentThana())) {
            applicationInfoEO.setPermanentThana(applicationInfo.getPermanentThana());
        }
        if(!Utils.isEmpty(applicationInfo.getPermanentAddressLine())) {
            applicationInfoEO.setPermanentAddressLine(applicationInfo.getPermanentAddressLine());
        }
        applicationInfoEO.setNumberOfEmiPaid(applicationInfo.getNumberOfEmiPaid());
        applicationInfoEO.setApplicationType(applicationInfo.getApplicationType());
        if(applicationInfo.getPhoto() != null && applicationInfo.getPhoto().length >4) {
            applicationInfoEO.setPhoto(applicationInfo.getPhoto());
        }
        if(applicationInfo.getSignature() != null && applicationInfo.getSignature().length >4) {
            applicationInfoEO.setSignature(applicationInfo.getSignature());
        }
        if(!Utils.isEmpty(applicationInfo.getGender())) {
            applicationInfoEO.setGender(applicationInfo.getGender());
        }
        if(!Utils.isEmpty(applicationInfo.getReferenceNumber())) {
            applicationInfoEO.setReferenceNumber(applicationInfo.getReferenceNumber());
        }
        if(!Utils.isEmpty(applicationInfo.getBloodGroup())) {
            applicationInfoEO.setBloodGroup(applicationInfo.getBloodGroup());
        }
        if(!Utils.isEmpty(applicationInfo.getMaritalStatus())) {
            applicationInfoEO.setMaritalStatus(applicationInfo.getMaritalStatus());
        }
        if(!Utils.isEmpty(applicationInfo.getEmergencyName())) {
            applicationInfoEO.setEmergencyName(applicationInfo.getEmergencyName());
        }
        if(!Utils.isEmpty(applicationInfo.getEmergencyRelation())) {
            applicationInfoEO.setEmergencyRelationship(applicationInfo.getEmergencyRelation());
        }
        if(!Utils.isEmpty(applicationInfo.getBankTransactionNumber())) {
            applicationInfoEO.setBankTransactionNumber(applicationInfo.getBankTransactionNumber());
        }
        repository.update(applicationInfoEO);
        return Defs.OPERATION_SUCCESS;
    }
}

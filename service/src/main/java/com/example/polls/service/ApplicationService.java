package com.example.polls.service;

import com.example.polls.payload.ApplicationInfo;
import com.example.polls.payload.OnlineApplicationInfo;
import com.example.polls.payload.OnlineApplicationSearchCriteria;
import com.example.polls.repository.ApplicationInfoRepository;
import com.example.polls.repository.BaseRepository;
import com.example.polls.security.UserPrincipal;
import com.example.polls.util.ErrorCodes;
import com.example.polls.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicationService implements IApplicationService {

    @Autowired
    private ApplicationInfoRepository repository;

    @Autowired
    private BaseRepository baseRepository;

    @Override
    public Object enrollApplication(ApplicationInfo applicationInfo, UserPrincipal currentUser) {
        if (applicationInfo == null) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"application");
        }
        if(Utils.isEmpty(applicationInfo.getReferenceNumber())) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"reference number");
        }
        if(Utils.isEmpty(applicationInfo.getNameEn())) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"applicant name");
        }
        if(Utils.isEmpty(applicationInfo.getFatherEn())) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"father name");
        }
        if(Utils.isEmpty(applicationInfo.getApplicationType())) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"application type");
        }
        if(Utils.isEmpty(applicationInfo.getContactNumber())) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"contact number");
        }
        if(Utils.isEmpty(applicationInfo.getDateOfBirth())) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"date of birth");
        }
        if(Utils.isEmpty(applicationInfo.getPermanentAddressLine())) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"permanent address line");
        }
        if(Utils.isEmpty(applicationInfo.getPresentAddressLine())) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"present address line");
        }
        if(Utils.isEmpty(applicationInfo.getUpdatedBy())) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"uploaded by");
        }
        if(Utils.isNull(applicationInfo.getNumberOfEmi())) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"number of emi");
        }
        if(applicationInfo.getNumberOfEmiPaid() == null) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"number of emi paid");
        }
        if(applicationInfo.getPhoto() == null || applicationInfo.getPhoto().length <4) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"photo");
        }
        if(applicationInfo.getSignature() == null || applicationInfo.getSignature().length <4) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"Signature");
        }

        Optional<com.example.polls.model.ApplicationInfo> application = repository.findFirstByReferenceNumberOrderByIdDesc(applicationInfo.getReferenceNumber());
        if(application.isPresent()) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_CUSTOM,"Application with reference:"+applicationInfo.getReferenceNumber()+" already exists!");
        }

        com.example.polls.model.ApplicationInfo applicationInfoEO = new com.example.polls.model.ApplicationInfo();
        applicationInfoEO.setReferenceNumber(applicationInfo.getReferenceNumber());
        applicationInfoEO.setNameEn(applicationInfo.getNameEn());
        applicationInfoEO.setFatherEn(applicationInfo.getFatherEn());
        applicationInfoEO.setSpouseEn(applicationInfo.getSpouseEn());
        applicationInfoEO.setGender(applicationInfo.getGender());
        applicationInfoEO.setNid(applicationInfo.getNid());
        applicationInfoEO.setBloodGroup(applicationInfo.getBloodGroup());
        applicationInfoEO.setEmergencyName(applicationInfo.getEmergencyName());
        applicationInfoEO.setEmergencyRelationship(applicationInfo.getEmergencyRelation());
        applicationInfoEO.setDateOfBirth(Utils.getDateFromString(applicationInfo.getDateOfBirth()));
        applicationInfoEO.setContactNumber(applicationInfo.getContactNumber());
        applicationInfoEO.setEmergencyContact(applicationInfo.getEmergencyContact());
        applicationInfoEO.setPresentDivision(applicationInfo.getPresentDivision());
        applicationInfoEO.setPresentDistrict(applicationInfo.getPresentDistrict());
        applicationInfoEO.setPresentThana(applicationInfo.getPresentThana());
        applicationInfoEO.setPresentAddressLine(applicationInfo.getPresentAddressLine());

        applicationInfoEO.setPermanentDivision(applicationInfo.getPermanentDivision());
        applicationInfoEO.setPermanentDistrict(applicationInfo.getPermanentDistrict());
        applicationInfoEO.setPermanentThana(applicationInfo.getPermanentThana());
        applicationInfoEO.setPermanentAddressLine(applicationInfo.getPermanentAddressLine());

        applicationInfoEO.setApplicationType(applicationInfo.getApplicationType());
        applicationInfoEO.setNumberOfEmi(applicationInfo.getNumberOfEmi());
        applicationInfoEO.setNumberOfEmiPaid(applicationInfo.getNumberOfEmiPaid());
        applicationInfoEO.setCreatedBy(applicationInfo.getCreatedBy());
        applicationInfoEO.setUpdatedBy(applicationInfo.getUpdatedBy());
        applicationInfoEO.setCreatedDate(Utils.getCurrentTimeStamp());
        applicationInfoEO.setUpdatedDate(Utils.getCurrentTimeStamp());
        applicationInfoEO.setStatus(1);
        applicationInfoEO.setPhoto(applicationInfo.getPhoto());
        applicationInfoEO.setSignature(applicationInfo.getSignature());
        applicationInfoEO.setBankTransactionNumber(applicationInfo.getBankTransactionNumber());

        if(applicationInfo.getLanguage() != null && applicationInfo.getLanguage().equalsIgnoreCase("BANGLA")) {
            applicationInfoEO.setLanguage(2);
        } else {
            applicationInfoEO.setLanguage(1);
        }
        applicationInfoEO.setUploadedBy(currentUser.getUsername());
        applicationInfoEO.setUploadedDate(Utils.getCurrentTimeStamp());

        try {
            applicationInfoEO = repository.save(applicationInfoEO);
        } catch (Throwable t) {
            t.printStackTrace();
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_CUSTOM,"Internal server error! Please contact with admin!");
        }

        return Arrays.asList(applicationInfoEO.getId(),applicationInfoEO.getReferenceNumber());
    }

    @Override
    public Object getApplicationByReferenceNumber(String referenceNumber,String mobileNumber) {
        if(Utils.isEmpty(referenceNumber)) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"reference number");
        }

        Optional<com.example.polls.model.ApplicationInfo> applicationInfo = repository.findFirstByReferenceNumberOrderByIdDesc(referenceNumber);

        if(!applicationInfo.isPresent()) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_NOT_FOUND,"reference number:"+referenceNumber);
        }

        return new ApplicationInfo(applicationInfo.get());
    }

    @Override
    public Object getOnlineApplication(String uuid) {
        if(Utils.isEmpty(uuid)) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"request uuid");
        }

        com.example.polls.model.OnlineApplicationInfo applicationInfoEO = baseRepository.findSingleByField(com.example.polls.model.OnlineApplicationInfo.class,"uuid",uuid);
        if(applicationInfoEO == null || Utils.isNull(applicationInfoEO.getId())) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_NOT_FOUND,"uuid:"+uuid);
        }

        return new OnlineApplicationInfo(applicationInfoEO);
    }

    @Override
    public Object searchOnlineApplication(OnlineApplicationSearchCriteria criteria) {
        if(criteria == null) {
            return ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"search criteria");
        }

        List<OnlineApplicationInfo> list = new ArrayList<>();

        String sql = "select o.id,o.name_en,o.uuid,o.contact_number,o.nid,o.application_type,o.office_code,o.circle_code from application_info_enroll o where 1=1 ";
        Map<String,Object> param = new HashMap<>();
        if(!Utils.isEmpty(criteria.getAuthorityCode())) {
            sql += " and o.office_code =:officeCode ";
            param.put("officeCode",criteria.getAuthorityCode().trim());
        }

        if(!Utils.isEmpty(criteria.getCircleCode())) {
            sql += " and o.circle_code = :circleCode ";
            param.put("circleCode",criteria.getCircleCode().trim());
        }

        if(!Utils.isEmpty(criteria.getMobileNumber())) {
            sql += " and o.contact_number =:contactNumber ";
            param.put("contactNumber",criteria.getMobileNumber());
        }

        if(!Utils.isEmpty(criteria.getUuid())) {
            sql += " and o.uuid =:uuid ";
            param.put("uuid",criteria.getUuid().trim());
        }

        if(!Utils.isNull(criteria.getId())) {
            sql += " and o.id =:id ";
            param.put("id",criteria.getId());
        }

        if(!Utils.isEmpty(criteria.getFromDate()) && Utils.getDateFromString(criteria.getFromDate()) != null) {
            sql += " and o.created_date >= :fromDate ";
            param.put("fromDate",Utils.getDateParam(Utils.getDateFromString(criteria.getFromDate()),false));

        }

        if(!Utils.isEmpty(criteria.getToDate()) && Utils.getDateFromString(criteria.getToDate()) != null) {
            sql += " and o.created_date <= :toDate";
            param.put("toDate",Utils.getDateParam(Utils.getDateFromString(criteria.getToDate()),true));
        }

        List<Object[]> results = baseRepository.findByNativeuery(sql,param);
        if(results != null && results.size() >0) {
            results.stream().filter(r->r != null && r.length >0).forEach(r->{
                list.add(new OnlineApplicationInfo(r));
            });
        }

        return list;
    }
}

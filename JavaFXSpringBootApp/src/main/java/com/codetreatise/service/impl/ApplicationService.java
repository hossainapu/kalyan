package com.codetreatise.service.impl;

import com.codetreatise.common.Defs;
import com.codetreatise.common.ErrorCodes;
import com.codetreatise.common.Utils;
import com.codetreatise.dto.ApplicationInfo;
import com.codetreatise.dto.ApplicationSearchCriteria;
import com.codetreatise.repository.BaseDao;
import com.codetreatise.service.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public class ApplicationService implements IApplicationService , Serializable {

    @Autowired
    private BaseDao service;

    public Object saveApplication(ApplicationInfo applicationInfo) {
        if(applicationInfo == null) {
            return ErrorCodes.getServiceError(ErrorCodes.GENERAL_MISSING,"application info");
        }

        com.codetreatise.entity.ApplicationInfo applicationInfoEO = service.findSingleByField(com.codetreatise.entity.ApplicationInfo.class,"referenceNumber",applicationInfo.getReferenceNumber().toUpperCase());
        if(applicationInfoEO != null && !Utils.isNull(applicationInfoEO.getId())) {
            return ErrorCodes.getServiceError(ErrorCodes.GENERAL_CUSTOM,"Application with reference number:"+applicationInfo.getReferenceNumber()+" Already exists!");
        }
        applicationInfoEO = new com.codetreatise.entity.ApplicationInfo();
        applicationInfoEO.setReferenceNumber(applicationInfo.getReferenceNumber());
        applicationInfoEO.setNameEn(applicationInfo.getNameEn());
        applicationInfoEO.setFatherEn(applicationInfo.getFatherEn());
        applicationInfoEO.setSpouseEn(applicationInfo.getSpouseEn());
        applicationInfoEO.setGender(applicationInfo.getGender());
        applicationInfoEO.setNid(applicationInfo.getNid());
        applicationInfoEO.setMaritalStatus(applicationInfo.getMaritalStatus());
        applicationInfoEO.setBloodGroup(applicationInfo.getBloodGroup());
        applicationInfoEO.setEmergencyName(applicationInfo.getEmergencyName());
        applicationInfoEO.setEmergencyRelationship(applicationInfo.getEmergencyRelation());
        applicationInfoEO.setDateOfBirth(applicationInfo.getDateOfBirth());
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

        applicationInfoEO.setBankTransactionNumber(applicationInfo.getBankTransactionNumber());
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
        if(applicationInfo.getLanguage() != null && applicationInfo.getLanguage().equalsIgnoreCase("BANGLA")) {
            applicationInfoEO.setLanguage(2);
        } else {
            applicationInfoEO.setLanguage(1);
        }

        try {
            service.persist(applicationInfoEO);
            return Defs.OPERATION_SUCCESS;
        } catch (Throwable t) {
            t.printStackTrace();
            return ErrorCodes.getServiceError(ErrorCodes.GENERAL_CUSTOM,"Internal server error! Please contact with admin!");
        }
    }

    @Override
    public Object updateApplication(ApplicationInfo applicationInfo) {
        if(applicationInfo == null) {
            return ErrorCodes.getServiceError(ErrorCodes.GENERAL_MISSING,"application info");
        }

        com.codetreatise.entity.ApplicationInfo applicationInfoEO = service.findById(com.codetreatise.entity.ApplicationInfo.class,applicationInfo.getId());
        if(applicationInfoEO == null) {
            return ErrorCodes.getServiceError(ErrorCodes.GENERAL_INVALID,"application id:"+applicationInfo.getId());
        }

        com.codetreatise.entity.ApplicationInfo applicationInfoDUP = service.findSingleByField(com.codetreatise.entity.ApplicationInfo.class,"referenceNumber",applicationInfo.getReferenceNumber());
        if(applicationInfoDUP != null && applicationInfoEO.getId().intValue() != applicationInfoDUP.getId().intValue()) {
            return ErrorCodes.getServiceError(ErrorCodes.GENERAL_CUSTOM,"Reference number:"+applicationInfo.getReferenceNumber()+" Already exists!");
        }

        applicationInfoEO.setReferenceNumber(applicationInfo.getReferenceNumber());
        applicationInfoEO.setNameEn(applicationInfo.getNameEn());
        applicationInfoEO.setFatherEn(applicationInfo.getFatherEn());
        applicationInfoEO.setSpouseEn(applicationInfo.getSpouseEn());
        applicationInfoEO.setGender(applicationInfo.getGender());
        applicationInfoEO.setMaritalStatus(applicationInfo.getMaritalStatus());
        applicationInfoEO.setNid(applicationInfo.getNid());
        applicationInfoEO.setBloodGroup(applicationInfo.getBloodGroup());
        applicationInfoEO.setEmergencyName(applicationInfo.getEmergencyName());
        applicationInfoEO.setEmergencyRelationship(applicationInfo.getEmergencyRelation());
        applicationInfoEO.setDateOfBirth(applicationInfo.getDateOfBirth());
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
        applicationInfoEO.setCreatedDate(Utils.getCurrentTimeStamp());
        applicationInfoEO.setUpdatedDate(Utils.getCurrentTimeStamp());
        applicationInfoEO.setStatus(1);
        applicationInfoEO.setBankTransactionNumber(applicationInfo.getBankTransactionNumber());
        applicationInfoEO.setPhoto(applicationInfo.getPhoto());
        applicationInfoEO.setSignature(applicationInfo.getSignature());
        if(applicationInfo.getLanguage() != null && applicationInfo.getLanguage().equalsIgnoreCase("BANGLA")) {
            applicationInfoEO.setLanguage(2);
        } else {
            applicationInfoEO.setLanguage(1);
        }

        try {
            service.update(applicationInfoEO);
            return Defs.OPERATION_SUCCESS;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return ErrorCodes.getServiceError(ErrorCodes.GENERAL_CUSTOM,"Internal server error! Please contact with admin!");
    }


    @Override
    public List<ApplicationInfo> searchApplication(ApplicationSearchCriteria criteria) {
        List<ApplicationInfo> list = new ArrayList<>();
        Map<String,Object> param = new HashMap<>();
        String sql = "SELECT A.* FROM APPLICATION_INFO A WHERE 1=1 ";
        List<Object> generate = buildSQL(sql,param,criteria);

        List<com.codetreatise.entity.ApplicationInfo> applicationInfos = service.findByNativeQuery((String)generate.get(0),com.codetreatise.entity.ApplicationInfo.class,(Map<String, Object>) generate.get(1),criteria.getStartIndex(),criteria.getLimit());

        if(applicationInfos != null && !applicationInfos.isEmpty()) {
            applicationInfos.stream().forEach(applicationInfo -> {
                list.add(new ApplicationInfo(applicationInfo));
            });
        }

        return list;
    }

    @Override
    public ApplicationInfo findById(Integer id) {
        com.codetreatise.entity.ApplicationInfo applicationInfo = service.findById(com.codetreatise.entity.ApplicationInfo.class,id);
        return new ApplicationInfo(applicationInfo);
    }

    @Override
    public Integer countApplication(ApplicationSearchCriteria criteria) {
        if(criteria == null) {
          return 0;
        }

        String sql = "SELECT COUNT(A.ID) FROM APPLICATION_INFO A WHERE 1=1 ";
        Map<String,Object> map = new HashMap<>();
        List<Object> generate = buildSQL(sql,map,criteria);

        return service.findCountByNativeQuery((String)generate.get(0),(Map<String, Object>)generate.get(1));
    }

    public List<Object> buildSQL(String sql,Map<String,Object> param,ApplicationSearchCriteria criteria) {
        if(!Utils.isEmpty(criteria.getReferenceNumber())) {
            sql += " AND reference_number LIKE :referenceNumber ";
            param.put("referenceNumber","%"+criteria.getReferenceNumber().trim().toUpperCase()+"%");
        }
        if(!Utils.isEmpty(criteria.getContactNumber())) {
            sql +=" AND CONTACT_NUMBER  =:contactNumber ";
            param.put("contactNumber",criteria.getContactNumber().trim());
        }
        if(!Utils.isEmpty(criteria.getGender())) {
            sql += " AND GENDER = :gender ";
            param.put("gender",criteria.getGender().trim());
        }
        if(!Utils.isEmpty(criteria.getApplicationType())) {
            sql += " AND APPLICATION_TYPE = :appType ";
            param.put("appType",criteria.getApplicationType().trim());
        }
        if(!Utils.isEmpty(criteria.getName())) {
            sql += " AND NAME_EN LIKE :nameEn ";
            param.put("nameEn","%"+criteria.getName().trim()+"%");
        }

        if(!Utils.isEmpty(criteria.getInstallment()) && !Utils.isNull(Utils.getIntegerFromString(criteria.getInstallment()))) {
            sql += " AND NUMBER_OF_EMI_PAID =:paid ";
            param.put("paid",Utils.getIntegerFromString(criteria.getInstallment()));
        }

        if(!Utils.isEmpty(criteria.getNid())) {
            sql += " AND NID = :nid";
            param.put("nid",criteria.getNid().trim());
        }

        if(!Utils.isEmpty(criteria.getFatherName())) {
            sql += " AND FATHER_EN LIKE :father";
            param.put("father","%"+criteria.getFatherName().trim().toUpperCase()+"%");
        }

        if(!Utils.isNull(criteria.getStatus())) {
            sql += " AND STATUS = :status ";
            param.put("status",criteria.getStatus());
        }

        if(criteria.getFromDate() != null) {
            sql += " AND CREATED_DATE >= :startDate ";
            param.put("startDate",Utils.getDateParam(criteria.getFromDate(),false));
        }

        if(criteria.getToDate() != null) {
            sql += " AND CREATED_DATE<= :endDate ";
            param.put("endDate",Utils.getDateParam(criteria.getToDate(),true));
        }

        return Arrays.asList(sql,param);
    }

    @Override
    public int updateByNativeQuery(String sql, Map<String,Object> param) {
        return service.updateByNativeSQL(sql,param);
    }


}

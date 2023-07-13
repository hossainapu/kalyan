package com.hellokoding.auth.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import com.hellokoding.auth.common.Defs;
import com.hellokoding.auth.common.Utils;
import com.hellokoding.auth.model.db.SMSLog;
import com.hellokoding.auth.model.dto.District;
import com.hellokoding.auth.model.dto.Division;
import com.hellokoding.auth.model.dto.Thana;
import com.hellokoding.auth.model.dto.User;
import com.hellokoding.auth.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LookupService implements ILookupService {

    @Autowired
    private BaseRepository repository;

    public List<Division> getAllDivision() {
        List<Division> divisionList = new ArrayList<>();
        List<com.hellokoding.auth.model.db.Division> divisions = repository.findAll(com.hellokoding.auth.model.db.Division.class);
        if(divisions != null) {
            divisions.stream().filter(div->div != null && !Utils.isNull(div.getId())).forEach(div->{
                divisionList.add(new Division(div));
            });
        }

        return divisionList;
    }

    public List<District> getAllDistrict() {
        List<District> districtList = new ArrayList<>();

        List<com.hellokoding.auth.model.db.District> districts = repository.findAll(com.hellokoding.auth.model.db.District.class);
        if(districts != null && districts.size() >0) {
            districts.stream().filter(dis->dis != null && !Utils.isNull(dis.getId())).forEach(dis->{
                districtList.add(new District(dis));
            });
        }
        return districtList;
    }

    public List<Thana> getAllThana() {
        List<Thana> thanaList = new ArrayList<>();

        List<com.hellokoding.auth.model.db.Thana> thanas = repository.findAll(com.hellokoding.auth.model.db.Thana.class);
        if(thanas != null && thanas.size() >0) {
            thanas.stream().filter(thana->thana != null && !Utils.isNull(thana.getId())).forEach(thana->{
                thanaList.add(new Thana(thana));
            });
        }
        return thanaList;
    }

    @Override
    public User getLoggedInUser() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(object instanceof User) {
            return (User)object;
        }
        return null;
    }

    @Override
    public void sendSMS(String text,String mobileNumbers) {
        if(Utils.isEmpty(text) || Utils.isEmpty(mobileNumbers)) {
            return;
        }
        if(text.length() >160) {
            text = text.substring(0,160);
        }
        List<String> mobiles = new ArrayList<>();
        if(mobileNumbers.contains(",")) {
            String[] mob = mobileNumbers.split(",");
            if(mob.length >0) {
                for (String mobile : mob) {
                    if(!Utils.isEmpty(mobile)) {
                        mobiles.add(mobile);
                    }
                }
            }
        } else {
            mobiles.add(mobileNumbers);
        }

        Map<String,String> uuids = new HashMap<>();

        if(mobiles.size() >0) {
            for (String mobile : mobiles) {
                String uuid = Utils.getUUID();
                uuids.put(mobile,uuid);
                SMSLog logEO = new SMSLog();
                logEO.setMobileNumber(mobile);
                logEO.setSendStatus("SEND");
                logEO.setSmsText(text);
                logEO.setUuid(Utils.getUUID());
                logEO.setCreationDate(Utils.getCurrentTimeStamp());
                logEO.setSendBy("ADMIN");
                repository.persist(logEO);
            }
            sendSMS(mobiles,text,uuids);
        }

    }

    public static String sendSMS(List<String> mobiles,String smsBody,Map<String,String> uuids) {
        try {
            StringBuilder params = new StringBuilder();
            params.append("user="+Defs.SMS_USER);
            params.append("&pass="+Defs.SMS_PASSWORD);
            for (int i = 0; i < mobiles.size(); i++) {
                params.append("&sms[" + i + "][0]="+mobiles.get(i));
                params.append("&sms[" + i + "][1]=" +Utils.convertBanglatoUnicode(smsBody));
                params.append("&sms[" + i + "][2]=" + uuids.get(mobiles.get(i)));
            }
            params.append("&sid="+Defs.SID_BN);
            // creating net components
            URL url = new URL(Defs.SMS_URL);
            URLConnection urlConnection = url.openConnection();
            // Posting the content
            urlConnection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
            System.out.println("PARAM CALLED:" + params.toString());
            wr.writeBytes(params.toString());
            wr.flush();
            // make an input stream from the remote url
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            // read until all the html codes are fetched
            String response = reader.readLine();
            // close the reader
            reader.close();
            wr.close();
            //return response;
            System.out.println("REPLY:"+response);
            return Defs.OPERATION_SUCCESS;
        } catch (Throwable t) {
            t.printStackTrace();
            return Defs.OPERATION_FAILED;
        }
    }
}

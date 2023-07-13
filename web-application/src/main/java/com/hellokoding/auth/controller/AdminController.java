package com.hellokoding.auth.controller;

import com.hellokoding.auth.common.SelectItem;
import com.hellokoding.auth.common.ServiceError;
import com.hellokoding.auth.common.Utils;
import com.hellokoding.auth.model.dto.ApplicationInfo;
import com.hellokoding.auth.model.dto.OnlineApplicationInfo;
import com.hellokoding.auth.model.dto.EnrolledApplication;
import com.hellokoding.auth.model.dto.EnrolledApplicationSearchCriteria;
import com.hellokoding.auth.service.IApplicationService;
import com.hellokoding.auth.service.ILookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private ILookupService lookupService;

    @ModelAttribute("criteria")
    public EnrolledApplicationSearchCriteria initiateCriteria() {
        return new EnrolledApplicationSearchCriteria();
    }

    @GetMapping("/application-search")
    public String loadSearchApplication(@ModelAttribute("criteria") EnrolledApplicationSearchCriteria criteria) {
        return "enrolledApplicationSummary";
    }

    @PostMapping("/application-search")
    public String searchApplication(@ModelAttribute("criteria") EnrolledApplicationSearchCriteria criteria, Model model) {
        List<EnrolledApplication> applications = applicationService.searchEnrolledApplication(criteria);
        model.addAttribute("applications",applications);
        return "enrolledApplicationSummary";

    }

    @GetMapping("/viewEnrolledApplication/{id}")
    public String loadDetails(@PathVariable(value = "id") Integer applicationId,Model model) {
        OnlineApplicationInfo applicationInfo = applicationService.findById(applicationId);

        model.addAttribute("application",applicationInfo);
        return "applicationDetails";
    }

    @GetMapping("/search-uploaded-application")
    public String loadSearchUploadedApplication(@ModelAttribute("criteria") EnrolledApplicationSearchCriteria criteria) {
        return "uploadedApplicationSummery";
    }

    @PostMapping("/search-uploaded-application")
    public String searchUploadedApplication(@ModelAttribute("criteria") EnrolledApplicationSearchCriteria criteria,Model model) {
        List<EnrolledApplication> uploadedApplications = applicationService.searchUplodedApplication(criteria);
        model.addAttribute("applications",uploadedApplications);
        return "uploadedApplicationSummery";
    }

    @GetMapping("/viewUploadedApplication/{id}")
    public String loadUploadedApplicationDetails(@PathVariable("id") Integer applicationId,Model model) {
        ApplicationInfo applicationInfo = applicationService.findByApplicationInfoId(applicationId);
        model.addAttribute("application",applicationInfo);
        return "uploadedApplicationDetails";
    }

    @GetMapping("/update-application/{id}")
    public String loadUpdateApplication(@PathVariable("id") Integer applicationId,Model model) {
        ApplicationInfo applicationInfo = applicationService.findByApplicationInfoId(applicationId);
        model.addAttribute("application",applicationInfo);
        model.addAttribute("divisions", Utils.getDivisions());
        if(!Utils.isEmpty(applicationInfo.getPresentDivision())) {
            List<SelectItem> items = Utils.getDistricts(applicationInfo.getPresentDivision());
            if(items != null && items.size() >0) {
                model.addAttribute("presentDistricts",items);
            }
        }
        if(!Utils.isEmpty(applicationInfo.getPresentDistrict())) {
            List<SelectItem> items = Utils.getThanas(applicationInfo.getPresentDistrict());
            if(items != null && items.size() >0) {
                model.addAttribute("presentUpozilas",items);
            }
        }

        if(!Utils.isEmpty(applicationInfo.getPermanentDivision())) {
            List<SelectItem> items = Utils.getDistricts(applicationInfo.getPermanentDivision());
            if(items != null && items.size() >0) {
                model.addAttribute("permanentDistricts",items);
            }
        }
        if(!Utils.isEmpty(applicationInfo.getPermanentDistrict())) {
            List<SelectItem> items = Utils.getThanas(applicationInfo.getPermanentDistrict());
            if(items != null && items.size() >0) {
                model.addAttribute("permanentUpozilas",items);
            }
        }


        return "updateApplication";
    }


    @PostMapping("/update-application")
    public String updateApplication(@RequestParam(name = "photoFile",required = false) MultipartFile photo,@RequestParam(value = "signatureFile",required = false) MultipartFile signature, @ModelAttribute("application") ApplicationInfo applicationInfo, RedirectAttributes redirectAttributes) {
        if(applicationInfo == null || Utils.isNull(applicationInfo.getId())) {
            redirectAttributes.addFlashAttribute("message",Utils.buildMessage("Invalid update request!",2));
            return "redirect:/admin/search-uploaded-application";
        }

        try {
            if (!Utils.isEmpty(photo.getOriginalFilename())) {
                applicationInfo.setPhoto(photo.getBytes());
            }

            if (!Utils.isEmpty(signature.getOriginalFilename())) {
                applicationInfo.setSignature(signature.getBytes());
            }

        } catch (IOException ioe) {
            redirectAttributes.addFlashAttribute("message", Utils.buildMessage("Internal server error. Please contact with admin!", 2));
            return "redirect:/admin/search-uploaded-application";
        }

        Object result = applicationService.updateEnrolledApplication(applicationInfo);
        if(result instanceof ServiceError) {
            redirectAttributes.addFlashAttribute("message",Utils.buildMessage(((ServiceError)result).getMessage(),2));
            return "redirect:/admin/update-application/"+applicationInfo.getId();
        }
        redirectAttributes.addFlashAttribute("message",Utils.buildMessage("Application successfully updated!",1));
        return "redirect:/admin/update-application/"+applicationInfo.getId();
    }

    @GetMapping("/sendSMS")
    public String loadSendSMS() {
        return  "sendSMS";
    }

    @PostMapping("/sendSMS")
    public String sendSMS(Model model,@RequestParam("smsText") String smsText,@RequestParam("contactNumber") String contactNumbers) {
        lookupService.sendSMS(smsText,contactNumbers);
        model.addAttribute("message",Utils.buildMessage("SMS Send Successfully!",1));
        model.addAttribute("smsText",smsText);
        model.addAttribute("contactNumber",contactNumbers);
        return "sendSMS";
    }

}

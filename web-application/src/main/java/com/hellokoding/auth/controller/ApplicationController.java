package com.hellokoding.auth.controller;

import com.hellokoding.auth.common.Defs;
import com.hellokoding.auth.common.SelectItem;
import com.hellokoding.auth.common.ServiceError;
import com.hellokoding.auth.common.Utils;
import com.hellokoding.auth.model.dto.OnlineApplicationInfo;
import com.hellokoding.auth.service.IApplicationService;
import com.hellokoding.auth.service.ILookupService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    private ILookupService lookupService;

    @Autowired
    private IApplicationService applicationService;

    @PostConstruct
    public void init() {
        Defs.divisionsLookup = lookupService.getAllDivision();
        Defs.districtsLookup = lookupService.getAllDistrict();
        Defs.thanasLookup = lookupService.getAllThana();

    }

    @ModelAttribute("application")
    public OnlineApplicationInfo initApplicationInfo() {
        return new OnlineApplicationInfo();
    }

    @ModelAttribute("divisions")
    public List<SelectItem> initDivision() {
        return Utils.getDivisions();
    }

    @GetMapping("/registration")
    public String registration() {
        return "enrollApplication";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("application") OnlineApplicationInfo applicationInfo, RedirectAttributes attributes, @RequestParam(value = "photoFile")MultipartFile photo, @RequestParam(value = "signatureFile") MultipartFile signature) {

        if(Utils.isNull(applicationInfo.getId())) {
            if (Utils.isEmpty(photo.getOriginalFilename())) {
                attributes.addFlashAttribute("error", Utils.buildMessage("Photo is required!", 2));
                return "redirect:/registration";
            }

            if (Utils.isEmpty(signature.getOriginalFilename())) {
                attributes.addFlashAttribute("error", Utils.buildMessage("Required signature is missing!", 2));
            }
        }
        try {
            if (!Utils.isEmpty(photo.getOriginalFilename())) {
                applicationInfo.setPhoto(photo.getBytes());
            }

            if (!Utils.isEmpty(signature.getOriginalFilename())) {
                applicationInfo.setSignature(signature.getBytes());
            }

        } catch (IOException ioe) {
            attributes.addFlashAttribute("error", Utils.buildMessage("Internal server error. Please contact with admin!", 2));
            return "redirect:/registration";
        }

        Object result = applicationService.save(applicationInfo);
        if(result instanceof ServiceError) {
            attributes.addFlashAttribute("application",applicationInfo);
            attributes.addFlashAttribute("error",Utils.buildMessage(((ServiceError)result).getMessage(),2));
        } else {
            attributes.addFlashAttribute("application",result);
            attributes.addFlashAttribute("error",Utils.buildMessage("Application saved successfully!",1));
        }

        if(!Utils.isEmpty(applicationInfo.getPermanentDivision())) {
            attributes.addFlashAttribute("permanentDistricts",Utils.getDistricts(applicationInfo.getPermanentDivision()));
        }
        if(!Utils.isEmpty(applicationInfo.getPermanentDistrict())) {
            attributes.addFlashAttribute("permanentUpozilas",Utils.getThanas(applicationInfo.getPermanentDistrict()));
        }

        if(!Utils.isEmpty(applicationInfo.getPresentDivision())) {
            attributes.addFlashAttribute("presentDistricts",Utils.getDistricts(applicationInfo.getPresentDivision()));
        }
        if(!Utils.isEmpty(applicationInfo.getPresentDistrict())) {
            attributes.addFlashAttribute("presentUpozilas",Utils.getThanas(applicationInfo.getPresentDistrict()));
        }

        return "redirect:/registration";
    }


    @GetMapping("/download/{uuid}")
    public String download(@PathVariable(value = "uuid") String uuid,RedirectAttributes attributes) {

        if(applicationService.isValidUUID(uuid)) {
            return "redirect:/application/"+uuid;
        } else {
            attributes.addFlashAttribute("error",Utils.buildMessage("Trying to download an invalid submission!",2));
            return "redirect:/registration";
        }
    }

    @GetMapping("/application/{uuid}")
    public void downloadBarcode(@PathVariable(value = "uuid") String uuid,HttpServletResponse response) {

        try {
            Document document = new Document();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(uuid+".pdf"));
            document.open();
            PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
            Barcode128 barcode128 = new Barcode128();
            barcode128.setCode(uuid);
            barcode128.setCodeType(Barcode128.CODE128);
            Image code128Image = barcode128.createImageWithBarcode(pdfContentByte, null, null);
            code128Image.setAlignment(Element.ALIGN_CENTER);
            code128Image.scalePercent(100);
            document.add(code128Image);
            document.close();

            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+uuid+".pdf");
            Files.copy(Paths.get(uuid+".pdf"), response.getOutputStream());
            response.getOutputStream().flush();


        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/district/{divisionName}", method = RequestMethod.GET)
    public @ResponseBody  List<SelectItem> getDistricts(@PathVariable("divisionName") String divisionName) {
        return Utils.getDistricts(divisionName);
    }

    @RequestMapping(value = "/thana/{districtName}", method = RequestMethod.GET)
    public @ResponseBody  List<SelectItem> getThanas(@PathVariable("districtName") String districtName) {
        return Utils.getThanas(districtName);
    }

    @GetMapping("/downloadReceipt")
    public String loadDownload(){
        return "downloadReceipt";
    }

    @PostMapping("/downloadReceipt")
    public String downloadReceipt(@RequestParam("dateOfBirth") String dateOfBirth,@RequestParam("contactNumber") String contactNumber,RedirectAttributes redirectAttributes) {
        if(Utils.isEmpty(dateOfBirth) || Utils.getDateFromString(dateOfBirth) == null) {
            redirectAttributes.addFlashAttribute("error",Utils.buildMessage("Date of Birth is required in a valid format!",2));
            return "redirect:/downloadReceipt";
        }
        if(Utils.isEmpty(contactNumber)) {
            redirectAttributes.addFlashAttribute("error",Utils.buildMessage("Contact Number is required!",2));
            return "redirect:/downloadReceipt";
        }

        OnlineApplicationInfo applicationInfo = applicationService.findByContactAndDateOfBirth(contactNumber,dateOfBirth);

        if(applicationInfo == null || Utils.isNull(applicationInfo.getId())) {
            redirectAttributes.addFlashAttribute("error",Utils.buildMessage("No Application found with given info!",2));
            return "redirect:/downloadReceipt";
        }

        return "redirect:/application/"+applicationInfo.getUuid();

    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
}

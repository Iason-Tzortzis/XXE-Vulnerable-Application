package xxe_vulnerable_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class UploadController {

    @Autowired
    XmlProcessorSax xmlProcessor;

    @GetMapping("/upload")
    public String index(Model model) {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:upload";
        }

        try {
            if (file.getContentType().equals("text/xml")) {
                // Get the file and save it somewhere
                InputStream inputStream = file.getInputStream();
                List<String> messages = xmlProcessor.parseXML(inputStream);
                redirectAttributes.addFlashAttribute("messages", messages);
            }else{
                redirectAttributes.addFlashAttribute("message", "Please select a valid XML file to upload");
                return "redirect:upload";
            }
        } catch (IOException e) {
            throw new RuntimeException("Upload error occurred", e);
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}

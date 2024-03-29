package vn.hdweb.team9.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.NoSuchFileException;

@ControllerAdvice
public class FileUploadExceptionAdvice {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public RedirectView handleMaxSizeException(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("error", "File size is bigger than 5MB. Or not a valid file!");
        String referer = request.getHeader("Referer");
        return new RedirectView(referer);
    }
    
    @ExceptionHandler(NoSuchFileException.class)
    public RedirectView handleFindNotFound(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("error", "File not found!");
        String referer = request.getHeader("Referer");
        return new RedirectView(referer);
    }
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public RedirectView handleRequestMethodNotSupportedException(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("error", "Error upload!");
        String referer = request.getHeader("Referer");
        return new RedirectView(referer);
    }
    
    @ExceptionHandler(NoResourceFoundException.class)
    public RedirectView handleRequestNoResourceFoundException(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("error", "Error upload!");
        String referer = request.getHeader("Referer");
        return new RedirectView(referer);
    }
    
    
}


package controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import model.User;
import services.UserService;

@Controller
public class AuthController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }
    
    @RequestMapping(value="/auth", method=RequestMethod.POST)
    public String login(@RequestParam String username, 
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("loggedUser", user);
            return "redirect:/index";
        }
        return "redirect:/login?error=true";
    }
    
    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String showRegisterPage() {
        return "register";
    }
    
    @RequestMapping(value="/doRegister", method=RequestMethod.POST)
    public String register(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String confirmPassword,
                          Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }
        
        if (password.length() < 3) {
            model.addAttribute("error", "Password must be at least 3 characters");
            return "register";
        }
        
        User newUser = new User(username, password, "USER");
        boolean success = userService.register(newUser);
        
        if (!success) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        
        return "redirect:/login?registered=true";
    }
    
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

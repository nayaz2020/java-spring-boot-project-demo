package edu.greenriver.sdiv.myspringproject.controllers;
import edu.greenriver.sdiv.myspringproject.models.ResumeData;
import edu.greenriver.sdiv.myspringproject.models.User;
import edu.greenriver.sdiv.myspringproject.service.ResumeService;
import edu.greenriver.sdiv.myspringproject.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author nematu
 * @version 1.0
 */
@Controller
public class IndexController
{

    private ResumeService service;
    private UserService userService;
    private BCryptPasswordEncoder encoder;

    /**
     * @param service service layer for the class
     * @param userService service for the User Class
     */
    public IndexController(ResumeService service, UserService userService)
    {
        this.service = service;
        this.userService = userService;
    }


    /**
     * @return home page "localhost:8080"
     */
    @RequestMapping(path = {"","/","index","index.html","resume"})
    public String home()
    {
        return "home/index";
    }

    /**
     * @param model resume data class model
     * @return summary page
     */
    @RequestMapping("resume/summary")
    public String summary(Model model)
    {
        model.addAttribute("data",service.allData());
        return "home/summary";
    }

    /**
     * @return returns individual-elements page
     */
    @RequestMapping("resume/summary/individual")
    public String individualElm()
    {
        return "home/individual-element";
    }

    /**
     * @return empty form page
     */
    @RequestMapping("resume/form")
    public String formLoad()
    {
        return "home/form";
    }

    /**
     * @param model Resume Data Model
     * @param id id of the record
     * @return corresponding record
     */
    @RequestMapping("resume/summary/individual/{id}")
    public String byId(Model model, @PathVariable int id)
    {
        model.addAttribute("activity", service.byId(id));
        return "home/individual-element";
    }


    /**
     * @param model Resume Data Class
     * @return form page
     */
    @GetMapping("resume/form")
    public String loadForm(Model model)
    {
        model.addAttribute("newData", new ResumeData());
        return "home/form";
    }

    /**
     * @param newData copy of the Resume Data object
     * @return summary page with added record
     */
    @PostMapping("resume/form")
    public String submitForm( @ModelAttribute ResumeData newData)
    {
        service.saveData(newData);
        return "redirect:/resume/summary";
    }

    /**
     * @param model class for Resume Data
     * @param id of the record to search for
     * @return form with filled data to be updated
     */

   @GetMapping("resume/edit/{id}")
    public String editForm(Model model, @PathVariable int id)
    {
        ResumeData newData = service.byId(id);
        model.addAttribute("newData", newData);
        return "home/form";
    }


    /**
     * @param model model class of resume data
     * @param newData new Resume Data object
     * @return summary page with updated records
     */
    @PostMapping("resume/edit")
    public String saveEditedForm(Model model, @ModelAttribute ResumeData newData)
    {
        model.addAttribute("newData", newData);
        service.saveData(newData);
        return "redirect:/resume/summary";
    }


    /**
     * @param id of the record to be deleted
     * @return summary page with updated record
     */
    @RequestMapping(path = "/resume/{id}")
    public String deletebyId( @PathVariable int id)
    {
        service.deleteRecord(id);
        return "redirect:/resume/summary";
    }


    /**
     * @return consumer page for view
     */
    @RequestMapping(path = {"consumer", "consumer.html"})
    public String showConsumerPage()
    {
        return"/home/consumer";
    }


    /**
     * @return admin page for view
     */
    @RequestMapping(path = {"admin", "admin.html"})
    public String adminPage()
    {
        return"/home/admin";
    }


    /**
     * @return userform
     */
    @RequestMapping("resume/userform")
    public String userForm()
    {
        return "home/userform";
    }

    /**
     * @param newUser copy of the User object
     * @return summary page with added record
     */
    @PostMapping("resume/userform")
    public String register( @ModelAttribute User newUser)
    {
        userService.saveUser(newUser);
        return "redirect:/resume";
    }

    // login log out

    /**
     * @return login page
     */
    @RequestMapping("/login")
    public String login()
    {
        return "home/login";
    }

    /**
     * @return logout page
     */
    @RequestMapping("/logout")
    public String gologout()
    {
        return "home/logout";
    }

    /**
     * @return redirect to login page
     */
    @PostMapping("/logout")
    public String logout( )
    {
        return "redirect:/login";
    }


    @Override
    public String toString() {
        return "IndexController{" +
                "service=" + service +
                '}';
    }
}

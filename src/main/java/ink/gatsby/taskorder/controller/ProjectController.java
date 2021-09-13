package ink.gatsby.taskorder.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("project")
public class ProjectController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping()
    public ModelAndView project() {
        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.addObject("title", "项目管理");
        modelAndView.setViewName("project/list");
        return modelAndView;
    }
}

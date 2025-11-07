package net.ads.springboot.controller;

import net.ads.springboot.model.Employee;
import net.ads.springboot.service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/")
	public String home() {
		return "home";
	}
    
    @GetMapping("/index")
    public String viewHomePage(Model model) {
    	model.addAttribute("employee", employeeService.getAllEmployees());
		model.addAttribute("employee", new Employee());
        return findPaginated(1, "id", "asc", model);
    }
    
    // Show form to add a new employee
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    // Save or update employee
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/index";
    }

    // Show form to update an employee
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable("id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    // Delete an employee
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") long id) {
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/index";
    }
    

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
        @RequestParam("sortField") String sortField,
        @RequestParam("sortDir") String sortDir,
        Model model) {
        int pageSize = 5;

        Page < Employee > page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < Employee > listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        
        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }
}

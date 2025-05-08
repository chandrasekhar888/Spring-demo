package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Dto.StudentDto;
import com.demo.service.StudentService;

import javax.validation.*; //java x
@Controller
public class StudentControllerview {

    @Autowired
    private StudentService service;

    @RequestMapping("/view")
    public String viewRegistrationpage() {
        return "create_registration"; // JSP name
    }

    @RequestMapping("/createReg")
    public String createStudent(
    //@ModelAttribute StudentDto dto , 1
    		//other way of reading form
    		@RequestParam String name, //2   //for 3rd approach comment these param and uncomment the model 
                    @RequestParam String email,
                    @RequestParam String course,
                    //one by one it should go do dto 
    		ModelMap model //model or modelmap bothworks
    		) { 
    			//saves the msg in model
    	StudentDto dto= new StudentDto(); //this line added after @requestparam line
    	dto.setName(name); //3 dto.setName(student.getName())
    	dto.setEmail(email);
    	dto.setCourse(course);
    	  StudentDto saved =service.createStudent(dto);
        model.addAttribute("msg","Record saved");
        return "create_registration"; // or redirect to confirmation view
    }


    @RequestMapping("/deleteReg")
    public String deleteStudent(@RequestParam Long id, ModelMap model) {
        service.deletestudent(id);
        // Optional: add success message to show after deletion
        model.addAttribute("msg", "Student deleted successfully.");
        return "redirect:/listReg";  // Redirect to list page after deletion
    }
    //this is for after update redirect
    @RequestMapping("/listReg")
    public String listStudents(Model model) {
        List<StudentDto> students = service.findstudent(0, 5, "id", "asc");  // Adjust pagination if needed
        model.addAttribute("students", students);
        return "list_registration";  // Your JSP page to display the list
    }


//	  @PutMapping("/updateReg")
//	    public ResponseEntity<StudentDto> updateStudent(@RequestParam long id, @RequestBody StudentDto dto) {
//	        StudentDto updatedStudent = service.updateStudent(id, dto);
//	        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
//	    }
//	  
	  //------------modifying update for http -------working on update
    @RequestMapping("/updateReg")
    public String updateStudent(@ModelAttribute StudentDto dto) {
        service.updateStudent(dto.getId(), dto);
        return "redirect:/listReg";
    }


	  @GetMapping("/findallstudentsReg")
	    public String findstudent(
	    		@RequestParam(name="pageNo",defaultValue="0",required=false)int pageNo,
	    		@RequestParam(name="pageSize",defaultValue="5",required=false)int pageSize,
	    		@RequestParam(name="sortBy",defaultValue="id",required=false)String sortBy,
	    		@RequestParam(name="sortDir",defaultValue="asc",required=false)String sortDir,
	    		Model model 
	    		) {
		  List<StudentDto> listall = service.findstudent(pageNo,pageSize,sortBy, sortDir);
           model.addAttribute("students", listall);
	        return "list_registration";

	  }
//		@GetMapping("/FindByIdReg")
//	  public ResponseEntity<?> getbyid(@RequestParam long id) {
//		  StudentDto fetchid =service.getbyid(id);
//		  if (fetchid == null) {
//		        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
//	        }
//	        return new ResponseEntity<>(fetchid, HttpStatus.OK);
//	  }
	  
//changing for update
	  @GetMapping("/FindByIdReg")
	  public String getbyid(@RequestParam long id, 
			  Model model) { //Which ever id comes here we are fetching it and using model
		  StudentDto fetchid =service.getbyid(id);
		  model.addAttribute("fetchid",fetchid);
	        return "update_registration";
	  }

		
		
		@GetMapping("/FindByCourseReg")
		public ResponseEntity<List<StudentDto>> getbycourse( @RequestParam String course ){
			List<StudentDto> fetchcourse =	service.getbycourse(course);
	        return new ResponseEntity<>(fetchcourse, HttpStatus.OK);
			
		}
		@GetMapping("/findByemailandcourseReg")
		public ResponseEntity<StudentDto> findByemailandcourse(@RequestParam String email, @RequestParam String course) {
		    StudentDto fetchemailandcourse = service.findByemailandcourse(email, course);
		    if (fetchemailandcourse == null) {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		    return new ResponseEntity<>(fetchemailandcourse, HttpStatus.OK);
		}
}

package com.example.consultationWebBacked.controller;

import com.example.consultationWebBacked.DTO.*;
import com.example.consultationWebBacked.DTO.requestDTO.RegistrationOrLoginAdminsDTO;
import com.example.consultationWebBacked.entity.Appointment;
import com.example.consultationWebBacked.service.*;
import com.example.consultationWebBacked.util.VarList;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/v1")
public class UsersControler {
    @Autowired
    private ReportService reportService;

    @Autowired
    private ResponseDTO responseDTO;
    private final UserService userService;


    private final MailService mailService;
    private final AppointmentService appointmentService;
    private final ScheduleService scheduleService;
    public UsersControler(ReportService reportService, ResponseDTO responseDTO, UserService userService, MailService mailService, AppointmentService appointmentService, ScheduleService scheduleService) {
        this.reportService = reportService;
        this.responseDTO = responseDTO;
        this.userService = userService;

        this.mailService = mailService;
        this.appointmentService = appointmentService;
        this.scheduleService = scheduleService;
    }

    @PostMapping(path = "/create")
    public TokenDTO createUsers(@RequestBody RegistrationOrLoginAdminsDTO registrationOrLoginAdminsDTO) {
        return userService.createAdmin(registrationOrLoginAdminsDTO);
    }



    @PostMapping(path = "/login")
    public TokenDTO loginUsers(@RequestBody RegistrationOrLoginAdminsDTO registrationOrLoginAdminsDTO) {
        System.out.println(registrationOrLoginAdminsDTO.email());
        return userService.loginAdmin(registrationOrLoginAdminsDTO);
    }


    @GetMapping(path = "/all")
    public List<UsersDTO> getAllUsers() {
        return userService.getAllAdmins();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        // Perform logout logic here, e.g., invalidate the user's session or clear authentication tokens
        // You can also revoke the user's JWT token if applicable

        // Clear any authentication cookies or tokens
        // For example, if using JWT, you might want to blacklist the token or simply clear it from the client-side

        // Invalidate the user's session
        request.getSession().invalidate();

        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/saveSchedule")
    public ResponseEntity saveSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        try {
            String res = scheduleService.saveSchedule(scheduleDTO);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(scheduleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Employee Registered");
                responseDTO.setContent(scheduleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/AllSchedulet")
    public ResponseEntity getAllSchedulet() {
        try {
            List<ScheduleDTO> scheduleDTOList = scheduleService.getAllSchedule();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(scheduleDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/searchSchedulet/{id}")
    public ResponseEntity searchSchedule(@PathVariable int id) {
        try {
            ScheduleDTO scheduleDTO = scheduleService.searchSchedule(id);
            if (scheduleDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(scheduleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/searchschedulet/{name}")
    public ResponseEntity searchSchedule2(@PathVariable String name) {
        try {
            ScheduleDTO scheduleDTO = scheduleService.searchSchedule2(name);
            if (scheduleDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(scheduleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/searchcategoryschedulet/{category}")
    public ResponseEntity searchSchedule3(@PathVariable String category) {
        try {
            ScheduleDTO scheduleDTO = scheduleService.searchSchedule3(category);
            if (scheduleDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(scheduleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/updateSchedulet")
    public ResponseEntity updateSchedulet(@RequestBody ScheduleDTO scheduleDTO) {
        try {
            String res = scheduleService.updateSchedule(scheduleDTO);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(scheduleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("01")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Not A Registered Employee");
                responseDTO.setContent(scheduleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @DeleteMapping("/deleteSchedulet/{id}")
    public ResponseEntity deleteSchedulet(@PathVariable int id) {
        try {
            String res = scheduleService.deleteSchedule(id);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/CreateAppointment")
    public ResponseEntity saveAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            String res = appointmentService.saveAppointment(appointmentDTO);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(appointmentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Employee Registered");
                responseDTO.setContent(appointmentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @GetMapping("/getAllAppointment")
    public ResponseEntity getAllAppointment() {
        try {
            List<AppointmentDTO> employeeDTOList = appointmentService.getAllAppointment();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(employeeDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/searchAppointment/{id}")
    public ResponseEntity searchAppointment(@PathVariable int id) {
        try {
            AppointmentDTO employeeDTO = appointmentService.searchAppointment(id);
            if (employeeDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAppointment/{id}")
    public ResponseEntity deleteAppointment(@PathVariable int id) {
        try {
            String res = appointmentService.deleteAppointment(id);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/emailSend")
    public String sendEmail(@RequestBody MailDTO mailDTO) {
        try {
            mailService.sendEmail(mailDTO);
            return "Email sent and saved successfully";
        } catch (Exception e) {
            return "Failed to send and save email: " + e.getMessage();
        }
    }
    @GetMapping("/AllEmail")
    public ResponseEntity getAllEmail() {
        try {
            List<MailDTO> MailDTOList = mailService.getAllEmail();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(MailDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/downloadPdf")
    public ResponseEntity<ByteArrayResource> downloadPdfReport() throws IOException, DocumentException {
        List<Appointment> appointment = appointmentService. getallAppointmen();
        String fileName = "ishara.pdf";
        String filePath = reportService.generatePdfReport(appointment, fileName);

        File reportFile = new File(filePath);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        ByteArrayResource resource = new ByteArrayResource(FileUtils.readFileToByteArray(reportFile));

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(reportFile.length())
                .body(resource);
    }
}

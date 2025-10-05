package WebService_Uppgifter_Lektion_7.Controller;


import WebService_Uppgifter_Lektion_7.model.Student;
import WebService_Uppgifter_Lektion_7.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // GET alla studenter
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    // GET student via ID - Med Optional (Uppgift #5)
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findStudentById(id);

        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student med ID " + id + " hittades inte");
        }
    }

    // POST - Skapa ny student
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        if (student.getFirstName() == null || student.getFirstName().isBlank()) {
            return ResponseEntity.badRequest().body("Förnamn saknas");
        }

        if (student.getLastName() == null || student.getLastName().isBlank()) {
            return ResponseEntity.badRequest().body("Efternamn saknas");
        }

        if (student.getEmail() == null || student.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("Email saknas");
        }

        if (studentRepository.existsByEmail(student.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email är redan registrerad");
        }

        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    // PUT - Uppdatera student
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {

        Optional<Student> student = studentRepository.findStudentById(id);

        if (!student.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student med ID " + id + " existerar inte");
        }

        if (updatedStudent.getFirstName() == null || updatedStudent.getFirstName().isBlank()) {
            return ResponseEntity.badRequest().body("Förnamn får inte vara tomt");
        }

        if (updatedStudent.getLastName() == null || updatedStudent.getLastName().isBlank()) {
            return ResponseEntity.badRequest().body("Efternamn får inte vara tomt");
        }

        if (updatedStudent.getEmail() == null || updatedStudent.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("Email får inte vara tomt");
        }

        Student existingStudent = student.get();
        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setEmail(updatedStudent.getEmail());

        Student savedStudent = studentRepository.save(existingStudent);
        return ResponseEntity.ok(savedStudent);
    }

    // DELETE - Ta bort student
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findStudentById(id);

        if (!student.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student med ID " + id + " hittades inte");
        }

        studentRepository.deleteById(id);
        return ResponseEntity.ok("Student med ID " + id + " har tagits bort");
    }
}
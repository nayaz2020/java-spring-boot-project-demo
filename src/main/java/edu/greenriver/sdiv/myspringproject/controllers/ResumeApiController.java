package edu.greenriver.sdiv.myspringproject.controllers;


import edu.greenriver.sdiv.myspringproject.models.ResumeData;
import edu.greenriver.sdiv.myspringproject.service.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nemat
 * @version 1.2
 */
@RestController
@RequestMapping("myresume") // http://localhost:8080/myresume
@CrossOrigin(origins = "*")
public class ResumeApiController
{
    private ResumeService service;

    /**
     * @param service service layer of resume data class
     */
    public ResumeApiController(ResumeService service)
    {
        this.service = service;
    }

    /**
     * @return all records
     */
    //READ
    @GetMapping
    public ResponseEntity<List<ResumeData>> allData()
    {
        return new ResponseEntity<>(service.allData(), HttpStatus.OK);
    }

    /**
     * @param id of the record to be read
     * @return requested record based on its id
     */
    @GetMapping("{id}")
    public ResponseEntity<ResumeData> dataById(@PathVariable int id)
    {
        if(!service.dataExist(id))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.byId(id), HttpStatus.OK);
    }

    /**
     * @param data to be added
     * @return added record
     */
    // Create
    @PostMapping
    public ResponseEntity<ResumeData> addData(@RequestBody ResumeData data)
    {
        return new ResponseEntity<>(service.saveData(data), HttpStatus.CREATED);
    }

    /**
     * @param data to be updated
     * @return updated record
     */
    //Update
    @PutMapping
    public ResponseEntity<ResumeData> editData(@RequestBody ResumeData data)
    {
        if(!service.dataExist(data.getId()))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.editData(data), HttpStatus.NOT_FOUND);
    }

    /**
     * @param id of the record to be deleted
     * @return HTTP code status
     */
    //Delete
    @DeleteMapping("{id}")
    public ResponseEntity<ResumeData> deleteData(@PathVariable int id)
    {
        service.deleteRecord(id);
        return new ResponseEntity<ResumeData>(HttpStatus.NOT_FOUND);
    }

    @Override
    public String toString() {
        return "ResumeApiController{" +
                "service=" + service +
                '}';
    }
}
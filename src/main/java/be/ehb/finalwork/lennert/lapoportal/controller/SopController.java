package be.ehb.finalwork.lennert.lapoportal.controller;


import be.ehb.finalwork.lennert.lapoportal.dao.SopDAO;
import be.ehb.finalwork.lennert.lapoportal.dao.UserDAO;
import be.ehb.finalwork.lennert.lapoportal.dto.SopDTO;
import be.ehb.finalwork.lennert.lapoportal.entities.SOP;
import be.ehb.finalwork.lennert.lapoportal.entities.User;
import be.ehb.finalwork.lennert.lapoportal.exceptions.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/api/sops")
@CrossOrigin("http://localhost:3000")
public class SopController {

    private final SopDAO dao;

    @Autowired
    public SopController(SopDAO dao){
        this.dao = dao;
    }

    //TODO use DTO
    @GetMapping( value = "")
    @ResponseBody
    public Iterable<SOP> findAllSops(){
        return dao.findAll();
    }


    //TODO use DTO
    @GetMapping(value = "/{id}",
            produces = "application/json")
    public Optional<SOP> findSopById(@PathVariable(name = "id") Long id ) throws EntityNotFound {
       return Optional.ofNullable(dao.findById(id))
                .orElseThrow(EntityNotFound::new);

    }

    //POST request
    @PostMapping(value = "c", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addNewSop(HttpServletResponse resp,@RequestBody SOP newSop) throws IOException, EntityNotFound {
        Optional.ofNullable(dao.save(newSop))
                .orElseThrow(EntityNotFound::new);
        resp.setStatus(201);

    }

    @DeleteMapping(value = "/{id}")
    public void deleteSop(HttpServletResponse resp ,@PathVariable(name = "id") Long id){
        dao.deleteById(id);
    }
}

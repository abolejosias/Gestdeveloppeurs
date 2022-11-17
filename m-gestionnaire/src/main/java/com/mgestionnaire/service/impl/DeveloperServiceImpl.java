/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.service.impl;

import com.mgestionnaire.models.Developer;
import com.mgestionnaire.models.DeveloperSkills;
import com.mgestionnaire.models.Skills;
import com.mgestionnaire.models.SkillsInOutDeveloper;
import com.mgestionnaire.repository.DeveloperRepository;
import com.mgestionnaire.repository.DeveloperskillsRepository;
import com.mgestionnaire.repository.SkillsRepository;
import com.mgestionnaire.service.DeveloperService;
import com.mgestionnaire.toolkits.commonparameter;
import com.mgestionnaire.toolkits.utils.logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp i3
 */
@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    DeveloperRepository developerRepository;

    @Autowired
    DeveloperskillsRepository developerskillsRepository;

    @Autowired
    SkillsRepository skillsRepository;

    private String str_message;
    private String str_Detailmessage;

    @Override
    public String getMessage() {
        return str_message;
    }

    @Override
    public void setMessage(String message) {
        this.str_message = message;
    }

    @Override
    public String getDetailmessage() {
        return str_Detailmessage;
    }

    @Override
    public void setDetailmessage(String Detailmessage) {
        this.str_Detailmessage = Detailmessage;
    }

    @Override
    public void buildTraceMessage(String Message, String DetailMessage) {
        this.setDetailmessage(DetailMessage);
        this.setMessage(Message);
    }

    @Override
    public void buildErrorTraceMessage(String DetailMessage) {
        this.setDetailmessage(DetailMessage);
        this.setMessage(commonparameter.PROCESS_FAILED);
        new logger().OCategory.error(DetailMessage);
    }

    @Override
    public void buildErrorTraceMessage(String DetailMessage, String ErrorSystem) {
        this.setDetailmessage(DetailMessage + ".ERREUR  SYS:[ " + ErrorSystem + "]");
        setMessage(commonparameter.PROCESS_FAILED);
        new logger().OCategory.error(DetailMessage);
    }

    @Override
    public void buildSuccesTraceMessage(String DetailMessage) {
        this.setDetailmessage(DetailMessage);
        setMessage(commonparameter.PROCESS_SUCCESS);
        new logger().OCategory.info(DetailMessage);
    }

    @Override
    public void prinntTraceInfo(Object message) {
        new logger().OCategory.info(message);
    }

    @Override
    public void prinntTraceDebug(Object message) {
        new logger().OCategory.debug(message);
    }

    @Override
    public void prinntTraceError(Object message) {
        new logger().OCategory.error(message);

    }

    @Override
    public Boolean CreateDeveloper(String first_name, String last_name, String created_by,Integer iduser) {
        Boolean resultat = false;
        try {
            if (this.CheckifDeveloperExist(first_name, last_name)) {
                Developer odeveloper = new Developer();
                odeveloper.setFirstName(first_name);
                odeveloper.setLastName(last_name);
                odeveloper.setCreatedBy(created_by);
                odeveloper.setCreatedAt(new Date());
                odeveloper.setStrStatut(commonparameter.statut_enable);
                odeveloper.setIduser(iduser);
                this.developerRepository.save(odeveloper);
                this.buildSuccesTraceMessage(commonparameter.SUCCES_CREATION);
                resultat = true;
            } else {
                this.buildErrorTraceMessage(commonparameter.EXIST_DATA);
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    public Boolean UpdateDeveloper(Integer idDeveloper, String first_name, String last_name, String updated_by) {
        Boolean resultat = false;
        try {
            Optional<Developer> optionDeveloper = developerRepository.findById(idDeveloper);
            if (optionDeveloper.get() != null) {
                Developer odeveloper = optionDeveloper.get();
                odeveloper.setFirstName(first_name);
                odeveloper.setLastName(last_name);
                odeveloper.setUpdatedBy(updated_by);
                odeveloper.setUpdatedAt(new Date());
                this.developerRepository.save(odeveloper);
                this.buildSuccesTraceMessage(commonparameter.SUCCES_UPDATE);
                resultat = true;
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    public List<Developer> getListDevelopers(String str_statut) {
        List<Developer> lstDevelopers = new ArrayList<>();
        try {
            lstDevelopers = developerRepository.getallDeveloper(str_statut);
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return lstDevelopers;
    }

    @Override
    public Boolean DeleteDeveloper(Integer idDeveloper) {
        Boolean resultat = false;
        try {
            Optional<Developer> optionDeveloper = developerRepository.findById(idDeveloper);
            if (optionDeveloper.get() != null) {
                Developer developer = optionDeveloper.get();
                developer.setStrStatut(commonparameter.statut_delete);
                developerRepository.save(developer);
                resultat = true;
            }
            this.buildSuccesTraceMessage(commonparameter.SUCCES_DELETE);
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    public Boolean CreateDeveloperSkills(Integer idDeveloper, Integer idskills, String created_by) {
        Boolean resultat = false;
        try {
            DeveloperSkills odeveloperskills = new DeveloperSkills();
            odeveloperskills.setIddeveloper(idDeveloper);
            odeveloperskills.setIdskills(idskills);
            odeveloperskills.setCreatedBy(created_by);
            odeveloperskills.setCreatedAt(new Date());
            odeveloperskills.setStrStatut(commonparameter.statut_enable);
            this.developerskillsRepository.save(odeveloperskills);
            this.buildSuccesTraceMessage(commonparameter.SUCCES_CREATION);
            resultat = true;
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    public Boolean DeleteDeveloperSkills(Integer idDeveloperskills) {
        Boolean resultat = false;
        try {
            this.developerskillsRepository.deleteById(idDeveloperskills);
            this.buildSuccesTraceMessage(commonparameter.SUCCES_DELETE);
            resultat = true;
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    public Developer findByIdDeveloper(Integer idDeveloper) {
        Developer developer = null;
        try {
            Optional<Developer> optionDeveloper = developerRepository.findById(idDeveloper);
            if (optionDeveloper.get() != null) {
                developer = optionDeveloper.get();
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return developer;
    }

    @Override
    public Boolean CheckifDeveloperExist(String first_name, String last_name) {
        Boolean resultat = false;
        try {
            List<Developer> LstDeveloper = this.developerRepository.findDeveloper(first_name, last_name);
            if (LstDeveloper.isEmpty()) {
                resultat = true;
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    public List<Skills> getListSkillsByIdDeveloper(Integer idDeveloper) {
        List<Skills> LstSkills = new ArrayList<>();
        try {
            List<DeveloperSkills> LstDeveloperskills = this.developerskillsRepository.getallDevelopperskillsByidDeveloper(idDeveloper, commonparameter.statut_enable);
            if (!LstDeveloperskills.isEmpty()) {
                LstDeveloperskills.forEach((developperills) -> {
                    Optional<Skills> optionskills = skillsRepository.findById(developperills.getIdskills());
                    if (optionskills.get() != null) {
                        Skills skills = optionskills.get();
                        LstSkills.add(skills);
                    }
                });
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return LstSkills;
    }

    @Override
    public List<Skills> getListSkills() {
        List<Skills> lstSkills = new ArrayList<>();
        try {
            lstSkills = skillsRepository.findAll();
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return lstSkills;
    }

    @Override
    public List<SkillsInOutDeveloper> getAllSkillsAutho_UnAutho_To_Developer(String idDeveloper) {
        List<Skills> LstSkills = new ArrayList<>();
        List<SkillsInOutDeveloper> LstSkillsInOutDeveloper = new ArrayList<>();
        try {
            LstSkills = this.getListSkills();
            for (Skills Oskills : LstSkills) {
                SkillsInOutDeveloper oskillsInOutDeveloper = new SkillsInOutDeveloper();
                oskillsInOutDeveloper.setId(Oskills.getId());
                oskillsInOutDeveloper.setName(Oskills.getName());
                oskillsInOutDeveloper.setDescription(Oskills.getDescription());
                if (isExistwithDeveloperSkills(Oskills.getId(), Integer.valueOf(idDeveloper))) {
                    oskillsInOutDeveloper.setStrStatut(commonparameter.statut_Authorize);
                } else {
                    oskillsInOutDeveloper.setStrStatut(commonparameter.statut_UnAuthorize);
                }
                System.out.println("Statut oskillsInOutDeveloper --------->" + oskillsInOutDeveloper.getName() + "  ----- " + oskillsInOutDeveloper.getStrStatut());
                LstSkillsInOutDeveloper.add(oskillsInOutDeveloper);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LstSkillsInOutDeveloper;
    }

    @Override
    public Boolean isExistwithDeveloperSkills(Integer idskills, Integer idDeveloper) {
        Boolean result = false;
        try {
            List<DeveloperSkills> LstDeveloperSkills = developerskillsRepository.findByidskillsandIdrole(idskills, idDeveloper);
            if (!LstDeveloperSkills.isEmpty()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Boolean DeleteDeveloperSkills(Integer idDeveloper, Integer idskills) {
        Boolean result = false;
        try {
            List<DeveloperSkills> LstDeveloperSkills = developerskillsRepository.findByidskillsandIdrole(idskills, idDeveloper);
            if (!LstDeveloperSkills.isEmpty()) {
                DeveloperSkills ODeveloperSkills = LstDeveloperSkills.get(0);
                developerskillsRepository.delete(ODeveloperSkills);
                System.out.println("Developer Skills supprime avec succes!!");
                this.setMessage(commonparameter.PROCESS_SUCCESS);
                this.setDetailmessage(commonparameter.SUCCES_DELETE);
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Developer findByIdDeveloperByIduser(Integer iduser) {
         Developer developer = null;
        try {
            List<Developer> Lstdeveloper = developerRepository.findDeveloperByIduser(iduser);
            if (!Lstdeveloper.isEmpty()) {
                developer = Lstdeveloper.get(0);
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return developer;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.service.impl;

import com.mgestionnaire.models.Developer;
import com.mgestionnaire.models.DeveloperInOutProjects;
import com.mgestionnaire.models.DeveloperSkills;
import com.mgestionnaire.models.Projectmember;
import com.mgestionnaire.models.Projects;
import com.mgestionnaire.models.Requesttoparticipate;
import com.mgestionnaire.models.Skills;
import com.mgestionnaire.repository.DeveloperRepository;
import com.mgestionnaire.repository.DeveloperskillsRepository;
import com.mgestionnaire.repository.ProjectmemberRepository;
import com.mgestionnaire.repository.ProjectsRepository;
import com.mgestionnaire.repository.RequesttoparticipateRepository;
import com.mgestionnaire.repository.SkillsRepository;
import com.mgestionnaire.service.ProjectService;
import com.mgestionnaire.toolkits.commonparameter;
import com.mgestionnaire.toolkits.utils.logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hp i3
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    private String str_message;
    private String str_Detailmessage;

    @Autowired
    ProjectsRepository projectsRepository;

    @Autowired
    ProjectmemberRepository projectmemberRepository;

    @Autowired
    DeveloperRepository developerRepository;

    @Autowired
    DeveloperskillsRepository developerskillsRepository;

    @Autowired
    SkillsRepository skillsRepository;

    @Autowired
    RequesttoparticipateRepository requesttoparticipateRepository;

    @Override
    public Boolean CreateProjects(String name, String description, String created_by) {
        Boolean resultat = false;
        try {
            if (this.CheckifProjectExist(name)) {
                Projects oProjects = new Projects();
                oProjects.setName(name);
                oProjects.setDescription(description);
                oProjects.setCreatedBy(created_by);
                oProjects.setCreatedAt(new Date());
                oProjects.setStrStatut(commonparameter.statut_enable);
                oProjects.setDevnumber(0);
                this.projectsRepository.save(oProjects);
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
    public Boolean UpdateProjects(Integer IdProjects, String description, String updated_by) {
        Boolean resultat = false;
        try {
            Optional<Projects> optionProjects = projectsRepository.findById(IdProjects);
            if (optionProjects.get() != null) {
                Projects oProjects = optionProjects.get();
                oProjects.setDescription(description);
                oProjects.setUpdatedBy(updated_by);
                oProjects.setUpdatedAt(new Date());
                this.projectsRepository.save(oProjects);
                this.buildSuccesTraceMessage(commonparameter.SUCCES_UPDATE);
                resultat = true;
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    public Boolean DeleteProjects(Integer IdProjects) {
        Boolean resultat = false;
        try {
            Optional<Projects> optionProjects = projectsRepository.findById(IdProjects);
            if (optionProjects.get() != null) {
                Projects projects = optionProjects.get();
                projects.setStrStatut(commonparameter.statut_delete);
                projectsRepository.save(projects);
                resultat = true;
            }
            this.buildSuccesTraceMessage(commonparameter.SUCCES_UPDATE);
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    public List<Projects> getListProjects(String str_statut) {
        List<Projects> lstProjects = new ArrayList<>();
        try {
            lstProjects = projectsRepository.getallProjects(str_statut);
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return lstProjects;
    }

    @Override
    public Boolean CheckifProjectExist(String name) {
        Boolean resultat = false;
        try {
            List<Projects> LstProjects = this.projectsRepository.findProjects(name);
            if (LstProjects.isEmpty()) {
                resultat = true;
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    @Transactional
    public Boolean CreateProjectmember(Integer idproject, Integer iddeveloper, String created_by) {
        Boolean resultat = false;
        try {
            Projectmember oProjectmember = new Projectmember();
            oProjectmember.setIddeveloper(iddeveloper);
            oProjectmember.setIdproject(idproject);
            oProjectmember.setCreatedBy(created_by);
            oProjectmember.setCreatedAt(new Date());
            oProjectmember.setStrStatut(commonparameter.statut_enable);
            this.projectmemberRepository.save(oProjectmember);
            this.UpdateDeveloperNumber(idproject, true);
            this.buildSuccesTraceMessage(commonparameter.SUCCES_CREATION);
            resultat = true;
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    @Transactional
    public Boolean DeleteProjectmember(Integer idproject, Integer iddeveloper) {
        Boolean result = false;
        try {
            List<Projectmember> LstProjectmember = projectmemberRepository.findbyIdprojectandIdDeveloper(idproject, iddeveloper);
            if (!LstProjectmember.isEmpty()) {
                Projectmember OProjectmember = LstProjectmember.get(0);
                projectmemberRepository.delete(OProjectmember);
                this.UpdateDeveloperNumber(idproject, false);
                System.out.println("OProjectmember supprime avec succes!!");
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
    public List<Projectmember> getListProjectmember(Integer idproject) {
        List<Projectmember> Lstprojectmember = null;
        try {
            Lstprojectmember = this.projectmemberRepository.getallDevelopperByidProjects(idproject, commonparameter.statut_enable);
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return Lstprojectmember;
    }

    @Override
    public List<Developer> getListDeveloperByIdProject(Integer idproject) {
        List<Developer> LstDeveloper = new ArrayList<>();
        try {
            List<Projectmember> LstProjectmember = this.getListProjectmember(idproject);
            if (!LstProjectmember.isEmpty()) {
                LstProjectmember.forEach((projectmember) -> {
                    Optional<Developer> optiondeveloper = developerRepository.findById(projectmember.getIddeveloper());
                    if (optiondeveloper.get() != null) {
                        Developer developer = optiondeveloper.get();
                        LstDeveloper.add(developer);
                    }
                });
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return LstDeveloper;
    }

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
    public List<DeveloperInOutProjects> getAllDeveloperAutho_UnAutho_To_Projects(String idProject) {
        List<Developer> LstDeveloper = new ArrayList<>();
        List<DeveloperInOutProjects> LstDeveloperInOutProjects = new ArrayList<>();
        try {
            LstDeveloper = developerRepository.getallDeveloper(commonparameter.statut_enable);
            for (Developer ODeveloper : LstDeveloper) {
                DeveloperInOutProjects odeveloperInOutProjects = new DeveloperInOutProjects();
                odeveloperInOutProjects.setId(ODeveloper.getId());
                odeveloperInOutProjects.setFirst_name(ODeveloper.getFirstName());
                odeveloperInOutProjects.setLast_name(ODeveloper.getLastName());
                odeveloperInOutProjects.setSkills(this.getSkillsToString(ODeveloper.getId()));
                if (isExistwithProjectsDeveloper(ODeveloper.getId(), Integer.valueOf(idProject))) {
                    odeveloperInOutProjects.setStrStatut(commonparameter.statut_Authorize);
                } else {
                    odeveloperInOutProjects.setStrStatut(commonparameter.statut_UnAuthorize);
                }
                System.out.println("Statut odeveloperInOutProjects --------->" + odeveloperInOutProjects.getFirst_name() + "  ----- " + odeveloperInOutProjects.getStrStatut());
                LstDeveloperInOutProjects.add(odeveloperInOutProjects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LstDeveloperInOutProjects;
    }

    @Override
    public Boolean isExistwithProjectsDeveloper(Integer idDevelopper, Integer idProject) {
        Boolean result = false;
        try {
            List<Projectmember> LstProjectmember = this.projectmemberRepository.findbyIdprojectandIdDeveloper(idProject, idDevelopper);
            if (!LstProjectmember.isEmpty()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void UpdateDeveloperNumber(Integer IdProjects, Boolean str_etat) {
        try {
            Integer intNbreDev = 0;
            Projects projects = this.projectsRepository.findById(IdProjects).get();
            if (str_etat) {
                intNbreDev = projects.getDevnumber() + 1;
            } else {
                intNbreDev = projects.getDevnumber() - 1;
            }
            projects.setDevnumber(intNbreDev);
            this.projectsRepository.save(projects);
            this.buildSuccesTraceMessage(commonparameter.SUCCES_DELETE);
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
    }

    @Override
    public Projects findByIdProjects(Integer idProject) {
        Projects projects = null;
        try {
            Optional<Projects> optionprojects = projectsRepository.findById(idProject);
            if (optionprojects.get() != null) {
                projects = optionprojects.get();
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return projects;
    }

    @Override
    public String getSkillsToString(Integer idDevelopper) {
        String SkillstoString = "";
        try {
            List<DeveloperSkills> LstDeveloperSkillses = this.developerskillsRepository.getallDevelopperskillsByidDeveloper(idDevelopper, commonparameter.statut_enable);
            if (!LstDeveloperSkillses.isEmpty()) {
                for (int i = 0; i < LstDeveloperSkillses.size(); i++) {
                    Skills skills = this.skillsRepository.findById(LstDeveloperSkillses.get(i).getIdskills()).get();
                    SkillstoString = skills.getName() + " | " + SkillstoString;
                }
            }
            if (SkillstoString.equals("")) {
                SkillstoString = "Aucune Compétences definies";
            } else {
                SkillstoString = SkillstoString.substring(0, SkillstoString.length() - 2);
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return SkillstoString;
    }

    @Override
    public List<Projects> getListMyProjects(Integer idDevelopper, String str_statut) {
        List<Projects> lstProjects = new ArrayList<>();
        List<Projects> lstProjectsfinal = new ArrayList<>();
        try {
            lstProjects = projectsRepository.getallProjects(str_statut);
            if (!lstProjects.isEmpty()) {
                for (int i = 0; i < lstProjects.size(); i++) {
                    if (this.isExistwithProjectsDeveloper(idDevelopper, lstProjects.get(i).getId())) {
                        lstProjectsfinal.add(lstProjects.get(i));
                    }
                }
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return lstProjectsfinal;
    }

    @Override
    public List<Projects> getListOtherProjects(Integer idDevelopper, String str_statut) {
        List<Projects> lstProjects = new ArrayList<>();
        List<Projects> lstProjectsfinal = new ArrayList<>();
        try {
            lstProjects = projectsRepository.getallProjects(str_statut);
            if (!lstProjects.isEmpty()) {
                System.out.println("lstProjects******************" + lstProjects.size());
                for (int i = 0; i < lstProjects.size(); i++) {
                    if (!this.isExistwithProjectsDeveloper(idDevelopper, lstProjects.get(i).getId())) {
                        System.out.println("dans isExistwithProjectsDeveloper false******************");
                        List<Requesttoparticipate> LstRequesttoparticipates = requesttoparticipateRepository.findbyIdprojectandIdDeveloper(lstProjects.get(i).getId(), idDevelopper);
                        if (LstRequesttoparticipates.isEmpty()) {
                            lstProjects.get(i).setEtatdemande(false);
                        } else {
                            lstProjects.get(i).setEtatdemande(true);
                        }
                        lstProjectsfinal.add(lstProjects.get(i));
                    }
                }
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return lstProjectsfinal;
    }

    @Override
    public Boolean Createrequesttoparticipate(Integer idproject, Integer iddeveloper, String created_by) {
        Boolean resultat = false;
        try {
            Projects projects = projectsRepository.findById(idproject).get();
            Developer developer = developerRepository.findById(iddeveloper).get();
            String message = developer.getFirstName() + " " + developer.getLastName() + " souhaiterait participer au developpement du projet : " + projects.getName();
            Requesttoparticipate oRequesttoparticipate = new Requesttoparticipate();
            oRequesttoparticipate.setIddeveloper(iddeveloper);
            oRequesttoparticipate.setIdproject(idproject);
            oRequesttoparticipate.setCreatedAt(new Date());
            oRequesttoparticipate.setStrStatut(commonparameter.statut_enattente);
            oRequesttoparticipate.setStrMessage(message);
            this.requesttoparticipateRepository.save(oRequesttoparticipate);
            this.UpdateDeveloperNumber(idproject, true);
            this.buildSuccesTraceMessage(commonparameter.SUCCES_CREATION);
            resultat = true;
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    @Transactional
    public Boolean Validedrequesttoparticipate(Integer idrequesttoparticipate, String created_by) {
        Boolean resultat = false;
        try {
                Requesttoparticipate requesttoparticipate = this.requesttoparticipateRepository.findById(idrequesttoparticipate).get();
                requesttoparticipate.setStrStatut(commonparameter.statut_valided);
                requesttoparticipate.setUpdatedAt(new Date());
                this.requesttoparticipateRepository.save(requesttoparticipate);
                this.CreateProjectmember(requesttoparticipate.getIdproject(), requesttoparticipate.getIddeveloper(), created_by);
                this.buildSuccesTraceMessage(commonparameter.SUCCES_VALIDATION);
                resultat = true;
            
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    public Boolean Rejectrequesttoparticipate(Integer idrequesttoparticipate) {
        Boolean resultat = false;
        try {
            
                Requesttoparticipate requesttoparticipate = this.requesttoparticipateRepository.findById(idrequesttoparticipate).get();
                requesttoparticipate.setStrStatut(commonparameter.statut_rejeted);
                requesttoparticipate.setUpdatedAt(new Date());
                this.requesttoparticipateRepository.save(requesttoparticipate);
                this.buildSuccesTraceMessage(commonparameter.SUCCES_REJET);
                resultat = true;
            
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    public List<Requesttoparticipate> getRequesttoparticipate(String str_statut) {
       List<Requesttoparticipate> lstRequesttoparticipate = new ArrayList<>();
        try {
            lstRequesttoparticipate = requesttoparticipateRepository.getallRequesttoparticipate(str_statut);
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return lstRequesttoparticipate;
    }

}
